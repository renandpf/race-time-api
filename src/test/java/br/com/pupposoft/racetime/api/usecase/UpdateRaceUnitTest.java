package br.com.pupposoft.racetime.api.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

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
    	assertEquals(1L, raceToFinish.getPilots().iterator().next().getId(), 0);
    	assertNotNull(raceToFinish.getPilots().iterator().next().getLaps());
    	assertEquals(4L, raceToFinish.getPilots().iterator().next().getLaps().size(), 0);
    	
    	verify(this.dataBaseGateway, VerificationModeFactory.times(1)).updateRace(raceToFinish);
    }
}
