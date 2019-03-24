package br.com.pupposoft.racetime.api.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;

import java.time.Duration;
import java.time.LocalTime;
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
    	final Race raceNotFinished = new Race(LocalTime.now());
    	final Lap lap = new Lap(1L, LocalTime.now(), Duration.ofMinutes(1), 10D, new Pilot(1L, "Fulano de Tal"));
    	this.updateRace.update(raceNotFinished, lap);
    	
    	assertEquals(1, raceNotFinished.getPilots().size());
    	assertEquals(RaceStatus.OPEN, raceNotFinished.getStatus());
    	assertNotNull(raceNotFinished.getOpen());
    	assertNull(raceNotFinished.getFinish());
    	assertNull(raceNotFinished.getClose());
    	assertEquals(1L, raceNotFinished.getPilots().iterator().next().getId(), 0);
    	assertNotNull(raceNotFinished.getPilots().iterator().next().getLaps());
    	assertEquals(1L, raceNotFinished.getPilots().iterator().next().getLaps().size(), 0);
    	
    	verify(this.dataBaseGateway, VerificationModeFactory.times(1)).updateRace(raceNotFinished);
    }
    
    @Test
    public void updateRaceNotFinishedSecondLap() {
    	final Race raceNotFinished = new Race(LocalTime.now());
    	final Lap firstLap = new Lap(1L, LocalTime.now(), Duration.ofMinutes(1), 10D, new Pilot(1L, "Fulano de Tal"));
    	raceNotFinished.addPilot(new Pilot(1L, "Fulano de Tal"));
    	raceNotFinished.getPilots().iterator().next().addLap(firstLap);
    	
    	final Lap lap = new Lap(2L, LocalTime.now(), Duration.ofMinutes(1), 10D, new Pilot(1L, "Fulano de Tal"));
    	this.updateRace.update(raceNotFinished, lap);
    	
    	assertEquals(1, raceNotFinished.getPilots().size());
    	assertEquals(RaceStatus.OPEN, raceNotFinished.getStatus());
    	assertNotNull(raceNotFinished.getOpen());
    	assertNull(raceNotFinished.getFinish());
    	assertNull(raceNotFinished.getClose());
    	assertNotNull(raceNotFinished.getPilots().iterator().next().getLaps());
    	assertEquals(2L, raceNotFinished.getPilots().iterator().next().getLaps().size(), 0);
    	
    	verify(this.dataBaseGateway, VerificationModeFactory.times(1)).updateRace(raceNotFinished);
    }
    
    @Test
    public void updateRaceNotFinishedSecondLapTwoPilot() {
    	final Race raceNotFinished = new Race(LocalTime.now());
    	
    	final Pilot pilot_1 = new Pilot(1L, "Fulano de Tal");
    	final Lap firstLap_pilot_1 = new Lap(1L, LocalTime.now(), Duration.ofMinutes(1), 10D, pilot_1);
    	raceNotFinished.addPilot(pilot_1);
    	raceNotFinished.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(firstLap_pilot_1);
    	final Lap secondLap_pilot_1 = new Lap(2L, LocalTime.now(), Duration.ofMinutes(1), 10D, pilot_1);
    	raceNotFinished.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(secondLap_pilot_1);

    	final Pilot pilot_2 = new Pilot(2L, "Ciclano de Tal");
    	final Lap firstLap_pilot_2 = new Lap(1L, LocalTime.now(), Duration.ofMinutes(1), 10D, pilot_2);
    	raceNotFinished.addPilot(pilot_2);
    	raceNotFinished.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().addLap(firstLap_pilot_2);
    	final Lap secondLap_pilot_2 = new Lap(2L, LocalTime.now(), Duration.ofMinutes(1), 10D, pilot_2);
    	raceNotFinished.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().addLap(secondLap_pilot_2);

    	final Lap lap = new Lap(3L, LocalTime.now(), Duration.ofMinutes(1), 10D, pilot_1);
    	this.updateRace.update(raceNotFinished, lap);
    	
    	assertEquals(2, raceNotFinished.getPilots().size());
    	assertEquals(RaceStatus.OPEN, raceNotFinished.getStatus());
    	assertNotNull(raceNotFinished.getOpen());
    	assertNull(raceNotFinished.getFinish());
    	assertNull(raceNotFinished.getClose());
    	assertNotNull(raceNotFinished.getPilots().iterator().next().getLaps());
    	assertEquals(3L, raceNotFinished.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().getLaps().size(), 0);
    	assertEquals(2L, raceNotFinished.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().getLaps().size(), 0);
    	
    	verify(this.dataBaseGateway, VerificationModeFactory.times(1)).updateRace(raceNotFinished);
    }
    
    @Test
    public void updateRaceFinishingRace() {
    	final Race raceToFinish = new Race(LocalTime.now());
    	final Pilot pilot_1 = new Pilot(1L, "Fulano de Tal");
    	raceToFinish.addPilot(pilot_1);
    	final Lap firstLap_pilot_1 = new Lap(1L, LocalTime.now(), Duration.ofMinutes(1), 10D, pilot_1);
    	raceToFinish.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(firstLap_pilot_1);
    	final Lap secondLap_pilot_1 = new Lap(2L, LocalTime.now(), Duration.ofMinutes(1), 10D, pilot_1);
    	raceToFinish.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(secondLap_pilot_1);
    	final Lap thirtLap_pilot_1 = new Lap(3L, LocalTime.now(), Duration.ofMinutes(1), 10D, pilot_1);
    	raceToFinish.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(thirtLap_pilot_1);

    	final Lap lap = new Lap(4L, LocalTime.now(), Duration.ofMinutes(1), 10D, pilot_1);
    	this.updateRace.update(raceToFinish, lap);

    	assertEquals(1, raceToFinish.getPilots().size());
    	assertEquals(RaceStatus.FINISHED, raceToFinish.getStatus());
    	assertNotNull(raceToFinish.getOpen());
    	assertNotNull(raceToFinish.getFinish());
    	assertNull(raceToFinish.getClose());
    	assertEquals(1L, raceToFinish.getPilots().iterator().next().getId(), 0);
    	assertNotNull(raceToFinish.getPilots().iterator().next().getLaps());
    	assertEquals(4L, raceToFinish.getPilots().iterator().next().getLaps().size(), 0);
    	
    	verify(this.dataBaseGateway, VerificationModeFactory.times(1)).updateRace(raceToFinish);
    }
    
    @Test
    public void updateRaceRealTest() {
    	final Race raceNotClose = new Race(LocalTime.now());
    	
    	final Lap line_01 = new Lap(1L, LocalTime.of(23, 49, 8, 277), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:02.852")), 44.275D, new Pilot(38L, "F.MASSA")); 
    	final Lap line_02 = new Lap(1L, LocalTime.of(23, 49, 10, 858), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:04.352")), 43.243D, new Pilot(33L, "R.BARRICHELLO")); 
    	final Lap line_03 = new Lap(1L, LocalTime.of(23, 49, 11, 075), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:04.108")), 43.408D, new Pilot(2L, "K.RAIKKONEN"));
    	final Lap line_04 = new Lap(1L, LocalTime.of(23, 49, 12, 667), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:04.414")), 43.202D, new Pilot(23L, "M.WEBBER"));
    	final Lap line_05 = new Lap(1L, LocalTime.of(23, 49, 30, 976), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:18.456")), 35.470D, new Pilot(15L, "F.ALONSO"));
    	
    	final Lap line_06 = new Lap(2L, LocalTime.of(23, 50, 11, 447), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:03.170")), 44.053D, new Pilot(38L, "F.MASSA"));
    	final Lap line_07 = new Lap(2L, LocalTime.of(23, 50, 14, 860), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:04.002")), 43.480D, new Pilot(33L, "R.BARRICHELLO"));
    	final Lap line_08 = new Lap(2L, LocalTime.of(23, 50, 15, 057), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:03.982")), 43.493D, new Pilot(2L, "K.RAIKKONEN"));
    	final Lap line_09 = new Lap(2L, LocalTime.of(23, 50, 17, 472), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:04.805")), 42.941D, new Pilot(23L, "M.WEBBER"));
    	final Lap line_10 = new Lap(2L, LocalTime.of(23, 50, 37, 987), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:07.011")), 41.528D, new Pilot(15L, "F.ALONSO"));
    	
    	final Lap line_11 = new Lap(3L, LocalTime.of(23, 51, 14, 216), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:02.769")), 44.334D, new Pilot(38L, "F.MASSA"));
    	final Lap line_12 = new Lap(3L, LocalTime.of(23, 51, 18, 576), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:03.716")), 43.675D, new Pilot(33L, "R.BARRICHELLO"));
    	final Lap line_13 = new Lap(3L, LocalTime.of(23, 51, 19, 044), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:03.987")), 43.490D, new Pilot(2L, "K.RAIKKONEN"));
    	final Lap line_14 = new Lap(3L, LocalTime.of(23, 51, 21, 759), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:04.287")), 43.287D, new Pilot(23L, "M.WEBBER"));
    	final Lap line_15 = new Lap(3L, LocalTime.of(23, 51, 46, 691), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:08.704")), 40.504D, new Pilot(15L, "F.ALONSO"));
    	
    	final Lap line_16 = new Lap(1L, LocalTime.of(23, 52, 1, 796), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "3:31.315")), 13.169D, new Pilot(11L, "S.VETTEL"));
    	final Lap line_17 = new Lap(4L, LocalTime.of(23, 52, 17,003), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:02.787")), 44.321D, new Pilot(38L, "F.MASS"));
    	final Lap line_18 = new Lap(4L, LocalTime.of(23, 52, 22,586), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:04.010")), 43.474D, new Pilot(33L, "R.BARRICHELLO"));
    	final Lap line_19 = new Lap(4L, LocalTime.of(23, 52, 22,120), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:03.076")), 44.118D, new Pilot(2L, "K.RAIKKONEN"));
    	final Lap line_20 = new Lap(4L, LocalTime.of(23, 52, 25,975), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:04.216")), 43.335D, new Pilot(23L, "M.WEBBER"));
    	
    	final Lap line_21 = new Lap(4L, LocalTime.of(23, 53, 6, 741), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:20.050")), 34.763D, new Pilot(15L, "F.ALONSO"));
    	final Lap line_22 = new Lap(2L, LocalTime.of(23, 53, 39,660), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:37.864")), 28.435D, new Pilot(11L, "S.VETTEL"));
    	final Lap line_23 = new Lap(3L, LocalTime.of(23, 54, 57,757), Duration.between(LocalTime.MIN, LocalTime.parse("00:0" + "1:18.097")), 35.633D, new Pilot(11L, "S.VETTEL"));
    	
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
    	assertNotNull(raceNotClose.getOpen());
    	assertNotNull(raceNotClose.getFinish());
    	assertNull(raceNotClose.getClose());
    	assertNotNull(raceNotClose.getPilots().iterator().next().getLaps());
    	
    	//38 - F.MASSA
    	Pilot pilot = raceNotClose.getPilots().stream().filter(p -> p.getId().equals(38L)).findFirst().get();
    	assertEquals(38, pilot.getId(), 0);
    	assertEquals("F.MASSA", pilot.getNome());
    	assertEquals(4, pilot.getLaps().size());
    	List<Lap> laps = pilot.getLaps();
    	assertEquals(1L, laps.get(0).getNumber(), 0);
    	assertEquals(38L, laps.get(0).getPilot().getId(), 0);
    	assertEquals(44.275, laps.get(0).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:02.852")), laps.get(0).getDuration());
    	assertEquals(2L, laps.get(1).getNumber(), 0);
    	assertEquals(38L, laps.get(1).getPilot().getId(), 0);
    	assertEquals(44.053, laps.get(1).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:03.170")), laps.get(1).getDuration());
    	assertEquals(3L, laps.get(2).getNumber(), 0);
    	assertEquals(38L, laps.get(2).getPilot().getId(), 0);
    	assertEquals(44.334, laps.get(2).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:02.769")), laps.get(2).getDuration());
    	assertEquals(4L, laps.get(3).getNumber(), 0);
    	assertEquals(38L, laps.get(3).getPilot().getId(), 0);
    	assertEquals(44.321, laps.get(3).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:02.787")), laps.get(3).getDuration());

    	//33 - R.BARRICHELLO
    	pilot = raceNotClose.getPilots().stream().filter(p -> p.getId().equals(33L)).findFirst().get();
    	assertEquals(33, pilot.getId(), 0);
    	assertEquals("R.BARRICHELLO", pilot.getNome());
    	assertEquals(4, pilot.getLaps().size());
    	laps = pilot.getLaps();
    	assertEquals(1L, laps.get(0).getNumber(), 0);
    	assertEquals(33L, laps.get(0).getPilot().getId(), 0);
    	assertEquals(43.243, laps.get(0).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.352")), laps.get(0).getDuration());
    	assertEquals(2L, laps.get(1).getNumber(), 0);
    	assertEquals(33L, laps.get(1).getPilot().getId(), 0);
    	assertEquals(43.480, laps.get(1).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.002")), laps.get(1).getDuration());
    	assertEquals(3L, laps.get(2).getNumber(), 0);
    	assertEquals(33L, laps.get(2).getPilot().getId(), 0);
    	assertEquals(43.675, laps.get(2).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:03.716")), laps.get(2).getDuration());
    	assertEquals(4L, laps.get(3).getNumber(), 0);
    	assertEquals(33L, laps.get(3).getPilot().getId(), 0);
    	assertEquals(43.474, laps.get(3).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.010")), laps.get(3).getDuration());
    	
    	//2 - K.RAIKKONEN
    	pilot = raceNotClose.getPilots().stream().filter(p -> p.getId().equals(2L)).findFirst().get();
    	assertEquals(2L, pilot.getId(), 0);
    	assertEquals("K.RAIKKONEN", pilot.getNome());
    	assertEquals(4, pilot.getLaps().size());
    	laps = pilot.getLaps();
    	assertEquals(1L, laps.get(0).getNumber(), 0);
    	assertEquals(2L, laps.get(0).getPilot().getId(), 0);
    	assertEquals(43.408, laps.get(0).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.108")), laps.get(0).getDuration());
    	assertEquals(2L, laps.get(1).getNumber(), 0);
    	assertEquals(2L, laps.get(1).getPilot().getId(), 0);
    	assertEquals(43.493, laps.get(1).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:03.982")), laps.get(1).getDuration());
    	assertEquals(3L, laps.get(2).getNumber(), 0);
    	assertEquals(2L, laps.get(2).getPilot().getId(), 0);
    	assertEquals(43.490, laps.get(2).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:03.987")), laps.get(2).getDuration());
    	assertEquals(4L, laps.get(3).getNumber(), 0);
    	assertEquals(2L, laps.get(3).getPilot().getId(), 0);
    	assertEquals(44.118D, laps.get(3).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:03.076")), laps.get(3).getDuration());

    	
    	//23 - M.WEBBER
    	pilot = raceNotClose.getPilots().stream().filter(p -> p.getId().equals(23L)).findFirst().get();
    	assertEquals(23L, pilot.getId(), 0);
    	assertEquals("M.WEBBER", pilot.getNome());
    	assertEquals(4, pilot.getLaps().size());
    	laps = pilot.getLaps();
    	assertEquals(1L, laps.get(0).getNumber(), 0);
    	assertEquals(23L, laps.get(0).getPilot().getId(), 0);
    	assertEquals(43.202, laps.get(0).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.414")), laps.get(0).getDuration());
    	assertEquals(2L, laps.get(1).getNumber(), 0);
    	assertEquals(23L, laps.get(1).getPilot().getId(), 0);
    	assertEquals(42.941, laps.get(1).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.805")), laps.get(1).getDuration());
    	assertEquals(3L, laps.get(2).getNumber(), 0);
    	assertEquals(23L, laps.get(2).getPilot().getId(), 0);
    	assertEquals(43.287, laps.get(2).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.287")), laps.get(2).getDuration());
    	assertEquals(4L, laps.get(3).getNumber(), 0);
    	assertEquals(23L, laps.get(3).getPilot().getId(), 0);
    	assertEquals(43.335D, laps.get(3).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.216")), laps.get(3).getDuration());
    	
    	//15 - F.ALONSO
    	pilot = raceNotClose.getPilots().stream().filter(p -> p.getId().equals(15L)).findFirst().get();
    	assertEquals(15L, pilot.getId(), 0);
    	assertEquals("F.ALONSO", pilot.getNome());
    	assertEquals(4, pilot.getLaps().size());
    	laps = pilot.getLaps();
    	assertEquals(1L, laps.get(0).getNumber(), 0);
    	assertEquals(15L, laps.get(0).getPilot().getId(), 0);
    	assertEquals(35.470D, laps.get(0).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:18.456")), laps.get(0).getDuration());
    	assertEquals(2L, laps.get(1).getNumber(), 0);
    	assertEquals(15L, laps.get(1).getPilot().getId(), 0);
    	assertEquals(41.528, laps.get(1).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:07.011")), laps.get(1).getDuration());
    	assertEquals(3L, laps.get(2).getNumber(), 0);
    	assertEquals(15L, laps.get(2).getPilot().getId(), 0);
    	assertEquals(40.504D, laps.get(2).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:08.704")), laps.get(2).getDuration());
    	assertEquals(4L, laps.get(3).getNumber(), 0);
    	assertEquals(15L, laps.get(3).getPilot().getId(), 0);
    	assertEquals(34.763D, laps.get(3).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:20.050")), laps.get(3).getDuration());
    	
    	//11 - S.VETTEL
    	pilot = raceNotClose.getPilots().stream().filter(p -> p.getId().equals(11L)).findFirst().get();
    	assertEquals(11L, pilot.getId(), 0);
    	assertEquals("S.VETTEL", pilot.getNome());
    	assertEquals(3, pilot.getLaps().size());
    	laps = pilot.getLaps();
    	assertEquals(1L, laps.get(0).getNumber(), 0);
    	assertEquals(11L, laps.get(0).getPilot().getId(), 0);
    	assertEquals(13.169D, laps.get(0).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:03:31.315")), laps.get(0).getDuration());
    	assertEquals(2L, laps.get(1).getNumber(), 0);
    	assertEquals(11L, laps.get(1).getPilot().getId(), 0);
    	assertEquals(28.435, laps.get(1).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:37.864")), laps.get(1).getDuration());
    	assertEquals(3L, laps.get(2).getNumber(), 0);
    	assertEquals(11L, laps.get(2).getPilot().getId(), 0);
    	assertEquals(35.633D, laps.get(2).getAverageSpeed(), 0);
    	assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:18.097")), laps.get(2).getDuration());

    	verify(this.dataBaseGateway, VerificationModeFactory.times(23)).updateRace(raceNotClose);
    }
}
