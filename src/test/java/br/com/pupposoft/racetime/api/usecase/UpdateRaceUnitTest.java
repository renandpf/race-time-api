package br.com.pupposoft.racetime.api.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import br.com.pupposoft.racetime.api.domains.Lap;
import br.com.pupposoft.racetime.api.domains.Pilot;
import br.com.pupposoft.racetime.api.domains.Race;
import br.com.pupposoft.racetime.api.domains.enums.RaceStatus;
import br.com.pupposoft.racetime.api.gateway.database.DataBaseGateway;

public class UpdateRaceUnitTest {
	
	@InjectMocks
	private UpdateRace updateRace;
	
	@Mock
	private DataBaseGateway dataBaseGateway;
	
    @Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
    
    @Test
    public void updateRaceNotFinished() {
    	final Race raceNotFinished = new Race(LocalDateTime.now());
    	final Lap lap = new Lap(1L, LocalDateTime.now(), 10D, new Pilot(1L, "Fulano de Tal"));
    	this.updateRace.update(raceNotFinished, lap);
    	
    	assertEquals(1, raceNotFinished.getPilots().size());
    	assertEquals(RaceStatus.STARTED, raceNotFinished.getStatus());
    	assertNotNull(raceNotFinished.getStart());
    	assertNull(raceNotFinished.getFinish());
    	assertNull(raceNotFinished.getClose());
    	assertEquals(1L, raceNotFinished.getPilots().iterator().next().getId(), 0);
    	assertNotNull(raceNotFinished.getPilots().iterator().next().getLaps());
    	assertEquals(1L, raceNotFinished.getPilots().iterator().next().getLaps().size(), 0);
    	
    	verify(this.dataBaseGateway, VerificationModeFactory.times(1)).updateRace(raceNotFinished);
    }
    
    @Test
    public void updateRaceNotFinishedSecondLap() {
    	final Race raceNotFinished = new Race(LocalDateTime.now());
    	final Lap firstLap = new Lap(1L, LocalDateTime.now(), 10D, new Pilot(1L, "Fulano de Tal"));
    	raceNotFinished.addPilot(new Pilot(1L, "Fulano de Tal"));
    	raceNotFinished.getPilots().iterator().next().addLap(firstLap);
    	
    	final Lap lap = new Lap(2L, LocalDateTime.now(), 10D, new Pilot(1L, "Fulano de Tal"));
    	this.updateRace.update(raceNotFinished, lap);
    	
    	assertEquals(1, raceNotFinished.getPilots().size());
    	assertEquals(RaceStatus.STARTED, raceNotFinished.getStatus());
    	assertNotNull(raceNotFinished.getStart());
    	assertNull(raceNotFinished.getFinish());
    	assertNull(raceNotFinished.getClose());
    	assertNotNull(raceNotFinished.getPilots().iterator().next().getLaps());
    	assertEquals(2L, raceNotFinished.getPilots().iterator().next().getLaps().size(), 0);
    	
    	verify(this.dataBaseGateway, VerificationModeFactory.times(1)).updateRace(raceNotFinished);
    }
    
    @Test
    public void updateRaceNotFinishedSecondLapTwoPilot() {
    	final Race raceNotFinished = new Race(LocalDateTime.now());
    	
    	final Pilot pilot_1 = new Pilot(1L, "Fulano de Tal");
    	final Lap firstLap_pilot_1 = new Lap(1L, LocalDateTime.now(), 10D, pilot_1);
    	raceNotFinished.addPilot(pilot_1);
    	raceNotFinished.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(firstLap_pilot_1);
    	final Lap secondLap_pilot_1 = new Lap(2L, LocalDateTime.now(), 10D, pilot_1);
    	raceNotFinished.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(secondLap_pilot_1);

    	final Pilot pilot_2 = new Pilot(2L, "Ciclano de Tal");
    	final Lap firstLap_pilot_2 = new Lap(1L, LocalDateTime.now(), 10D, pilot_2);
    	raceNotFinished.addPilot(pilot_2);
    	raceNotFinished.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().addLap(firstLap_pilot_2);
    	final Lap secondLap_pilot_2 = new Lap(2L, LocalDateTime.now(), 10D, pilot_2);
    	raceNotFinished.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().addLap(secondLap_pilot_2);

    	final Lap lap = new Lap(3L, LocalDateTime.now(), 10D, pilot_1);
    	this.updateRace.update(raceNotFinished, lap);
    	
    	assertEquals(2, raceNotFinished.getPilots().size());
    	assertEquals(RaceStatus.STARTED, raceNotFinished.getStatus());
    	assertNotNull(raceNotFinished.getStart());
    	assertNull(raceNotFinished.getFinish());
    	assertNull(raceNotFinished.getClose());
    	assertNotNull(raceNotFinished.getPilots().iterator().next().getLaps());
    	assertEquals(3L, raceNotFinished.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().getLaps().size(), 0);
    	assertEquals(2L, raceNotFinished.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().getLaps().size(), 0);
    	
    	verify(this.dataBaseGateway, VerificationModeFactory.times(1)).updateRace(raceNotFinished);
    }
    
    @Test
    public void updateRaceFinishingRace() {
    	final Race raceToFinish = new Race(LocalDateTime.now());
    	final Pilot pilot_1 = new Pilot(1L, "Fulano de Tal");
    	raceToFinish.addPilot(pilot_1);
    	final Lap firstLap_pilot_1 = new Lap(1L, LocalDateTime.now(), 10D, pilot_1);
    	raceToFinish.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(firstLap_pilot_1);
    	final Lap secondLap_pilot_1 = new Lap(2L, LocalDateTime.now(), 10D, pilot_1);
    	raceToFinish.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(secondLap_pilot_1);
    	final Lap thirtLap_pilot_1 = new Lap(3L, LocalDateTime.now(), 10D, pilot_1);
    	raceToFinish.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(thirtLap_pilot_1);

    	final Lap lap = new Lap(4L, LocalDateTime.now(), 10D, pilot_1);
    	this.updateRace.update(raceToFinish, lap);

    	assertEquals(1, raceToFinish.getPilots().size());
    	assertEquals(RaceStatus.FINISHED, raceToFinish.getStatus());
    	assertNotNull(raceToFinish.getStart());
    	assertNotNull(raceToFinish.getFinish());
    	assertNull(raceToFinish.getClose());
    	assertEquals(1L, raceToFinish.getPilots().iterator().next().getId(), 0);
    	assertNotNull(raceToFinish.getPilots().iterator().next().getLaps());
    	assertEquals(4L, raceToFinish.getPilots().iterator().next().getLaps().size(), 0);
    	
    	verify(this.dataBaseGateway, VerificationModeFactory.times(1)).updateRace(raceToFinish);
    }
    
    @Test
    public void updateRaceRealTest() {
    	final Race raceNotClose = new Race(LocalDateTime.now());
    	
    	final Lap line_01 = new Lap(1L, LocalDateTime.now(), 44D, new Pilot(38L, "F.MASSA")); //1:02.852
    	final Lap line_02 = new Lap(1L, LocalDateTime.now(), 43D, new Pilot(33L, "R.BARRICHELLO")); //1:04.352
    	final Lap line_03 = new Lap(1L, LocalDateTime.now(), 43D, new Pilot(2L, "K.RAIKKONEN"));//1:04.108
    	final Lap line_04 = new Lap(1L, LocalDateTime.now(), 43D, new Pilot(23L, "M.WEBBER"));//1:04.414 
    	final Lap line_05 = new Lap(1L, LocalDateTime.now(), 35D, new Pilot(15L, "F.ALONSO"));//1:18.456
    	final Lap line_06 = new Lap(2L, LocalDateTime.now(), 44D, new Pilot(38L, "F.MASSA"));//1:03.170
    	final Lap line_07 = new Lap(2L, LocalDateTime.now(), 43D, new Pilot(33L, "R.BARRICHELLO"));//1:04.002
    	final Lap line_08 = new Lap(2L, LocalDateTime.now(), 43D, new Pilot(2L, "K.RAIKKONEN"));//1:03.982
    	final Lap line_09 = new Lap(2L, LocalDateTime.now(), 42D, new Pilot(23L, "M.WEBBER"));//1:04.805
    	final Lap line_10 = new Lap(2L, LocalDateTime.now(), 41D, new Pilot(15L, "F.ALONSO"));//1:07.011
    	final Lap line_11 = new Lap(3L, LocalDateTime.now(), 44D, new Pilot(38L, "F.MASSA"));//1:02.769
    	final Lap line_12 = new Lap(3L, LocalDateTime.now(), 43D, new Pilot(33L, "R.BARRICHELLO"));//1:03.716
    	final Lap line_13 = new Lap(3L, LocalDateTime.now(), 43D, new Pilot(2L, "K.RAIKKONEN"));//1:03.987
    	final Lap line_14 = new Lap(3L, LocalDateTime.now(), 43D, new Pilot(23L, "M.WEBBER"));//1:04.287
    	final Lap line_15 = new Lap(3L, LocalDateTime.now(), 40D, new Pilot(15L, "F.ALONSO"));//1:08.704
    	final Lap line_16 = new Lap(1L, LocalDateTime.now(), 13D, new Pilot(11L, "S.VETTEL"));//3:31.315
    	final Lap line_17 = new Lap(4L, LocalDateTime.now(), 44D, new Pilot(38L, "F.MASSA"));//1:02.787
    	final Lap line_18 = new Lap(4L, LocalDateTime.now(), 43D, new Pilot(33L, "R.BARRICHELLO"));//1:04.010
    	final Lap line_19 = new Lap(4L, LocalDateTime.now(), 44D, new Pilot(2L, "K.RAIKKONEN"));//1:03.076
    	final Lap line_20 = new Lap(4L, LocalDateTime.now(), 43D, new Pilot(23L, "M.WEBBER"));//1:04.216
    	final Lap line_21 = new Lap(4L, LocalDateTime.now(), 34D, new Pilot(15L, "F.ALONSO"));//1:20.050
    	final Lap line_22 = new Lap(2L, LocalDateTime.now(), 28D, new Pilot(11L, "S.VETTEL"));//1:37.864
    	final Lap line_23 = new Lap(3L, LocalDateTime.now(), 35D, new Pilot(11L, "S.VETTEL"));//1:18.097
    	
    	this.updateRace.update(raceNotClose, line_01);
    	this.updateRace.update(raceNotClose, line_02);
    	this.updateRace.update(raceNotClose, line_03);
    	this.updateRace.update(raceNotClose, line_04);
    	this.updateRace.update(raceNotClose, line_05);
    	this.updateRace.update(raceNotClose, line_06);
    	this.updateRace.update(raceNotClose, line_07);
    	this.updateRace.update(raceNotClose, line_08);
    	this.updateRace.update(raceNotClose, line_09);
    	this.updateRace.update(raceNotClose, line_10);
    	this.updateRace.update(raceNotClose, line_11);
    	this.updateRace.update(raceNotClose, line_12);
    	this.updateRace.update(raceNotClose, line_13);
    	this.updateRace.update(raceNotClose, line_14);
    	this.updateRace.update(raceNotClose, line_15);
    	this.updateRace.update(raceNotClose, line_16);
    	this.updateRace.update(raceNotClose, line_17);
    	this.updateRace.update(raceNotClose, line_18);
    	this.updateRace.update(raceNotClose, line_19);
    	this.updateRace.update(raceNotClose, line_20);
    	this.updateRace.update(raceNotClose, line_21);
    	this.updateRace.update(raceNotClose, line_22);
    	this.updateRace.update(raceNotClose, line_23);
    	
    	assertEquals(6, raceNotClose.getPilots().size());
    	assertEquals(RaceStatus.FINISHED, raceNotClose.getStatus());
    	assertNotNull(raceNotClose.getStart());
    	assertNotNull(raceNotClose.getFinish());
    	assertNull(raceNotClose.getClose());
    	assertNotNull(raceNotClose.getPilots().iterator().next().getLaps());
    	
    	
    	//38 - F.MASSA
    	final Pilot pilot = raceNotClose.getPilots().stream().filter(p -> p.getId().equals(38L)).findFirst().get();
    	assertEquals(38, pilot.getId(), 0);
    	assertEquals("F.MASSA", pilot.getNome());
    	assertEquals(4, pilot.getLaps().size());
    	final List<Lap> laps = pilot.getLaps();
    	//laps.get(0).getNumber();
    	// TODO - Continuar

    	//33 - R.BARRICHELLO
    	final Pilot rBarrichelo = raceNotClose.getPilots().stream().filter(p -> p.getId().equals(33L)).findFirst().get();
    	
    	//2 - K.RAIKKONEN
    	final Pilot kRaikkone = raceNotClose.getPilots().stream().filter(p -> p.getId().equals(2L)).findFirst().get();
    	
    	//23 - M.WEBBER
    	final Pilot mWebber = raceNotClose.getPilots().stream().filter(p -> p.getId().equals(23L)).findFirst().get();
    	
    	//15 - F.ALONSO
    	final Pilot fAlonso = raceNotClose.getPilots().stream().filter(p -> p.getId().equals(15L)).findFirst().get();
    	
    	//11 - S.VETTEL
    	final Pilot sVettel = raceNotClose.getPilots().stream().filter(p -> p.getId().equals(11L)).findFirst().get();
    	
//    	assertEquals(3L, raceNotFinished.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().getLaps().size(), 0);
//    	assertEquals(2L, raceNotFinished.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().getLaps().size(), 0);
    	
    	verify(this.dataBaseGateway, VerificationModeFactory.times(23)).updateRace(raceNotClose);
    }
}
