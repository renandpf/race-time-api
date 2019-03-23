package br.com.pupposoft.racetime.api.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
    	
    	assertEquals(1, raceNotFinished.getLaps().size());
    	assertEquals(1, raceNotFinished.getPilots().size());
    	assertEquals(RaceStatus.STARTED, raceNotFinished.getStatus());
    	assertEquals(1L, raceNotFinished.getPilots().iterator().next().getId(), 0);
    	assertNotNull(raceNotFinished.getPilots().iterator().next().getLaps());
    	assertEquals(1L, raceNotFinished.getPilots().iterator().next().getLaps().size(), 0);
    	
    	verify(this.dataBaseGateway, VerificationModeFactory.times(1)).updateRace(raceNotFinished);
    }
    
    @Test
    public void updateRaceNotFinishedSecondLap() {
    	final Race raceNotFinished = new Race(LocalDateTime.now());
    	final Lap firstLap = new Lap(1L, LocalDateTime.now(), 10D, new Pilot(1L, "Fulano de Tal"));
    	raceNotFinished.addLap(firstLap);
    	raceNotFinished.addPilot(new Pilot(1L, "Fulano de Tal"));
    	raceNotFinished.getPilots().iterator().next().addLap(firstLap);
    	
    	final Lap lap = new Lap(1L, LocalDateTime.now(), 10D, new Pilot(1L, "Fulano de Tal"));
    	this.updateRace.update(raceNotFinished, lap);
    	
    	assertEquals(2, raceNotFinished.getLaps().size());
    	assertEquals(1, raceNotFinished.getPilots().size());
    	assertEquals(RaceStatus.STARTED, raceNotFinished.getStatus());
    	assertNotNull(raceNotFinished.getPilots().iterator().next().getLaps());
    	assertEquals(2L, raceNotFinished.getPilots().iterator().next().getLaps().size(), 0);
    	
    	verify(this.dataBaseGateway, VerificationModeFactory.times(1)).updateRace(raceNotFinished);
    }
}
