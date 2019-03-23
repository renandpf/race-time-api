package br.com.pupposoft.racetime.api.usecase;

import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
//import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import br.com.pupposoft.racetime.api.domains.Lap;
import br.com.pupposoft.racetime.api.domains.Pilot;
import br.com.pupposoft.racetime.api.domains.Race;
import br.com.pupposoft.racetime.api.gateway.database.DataBaseGateway;

public class GetCurrentRaceOrCreateRaceNewUnitTest {

	@InjectMocks
	private GetCurrentRaceOrCreateRaceNew getCurrentRaceOrCreateRaceNew;
	
	@Mock
	private DataBaseGateway dataBaseGateway;
	
    @Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
    @Test
	public void getCurrentSuccess() {
    	final Race currentRace = new Race();
    	currentRace.addLap(new Lap());
    	currentRace.addLap(new Lap());
    	
    	currentRace.addPilot(new Pilot());
    	
    	when(this.dataBaseGateway.getCurrentRace()).thenReturn(Optional.of(currentRace));
    	
    	Race currentRaceReturned =  this.getCurrentRaceOrCreateRaceNew.getOrCreate();
    	
    	assertNotNull(currentRaceReturned);
    	assertNotNull(currentRaceReturned.getStart());
    	assertNull(currentRaceReturned.getFinish());
    	assertNotNull(currentRaceReturned.getLaps());
    	assertEquals(2, currentRaceReturned.getLaps().size());
    	assertNotNull(currentRaceReturned.getPilots());
    	assertEquals(1, currentRaceReturned.getPilots().size());
    	
		verify(this.dataBaseGateway, VerificationModeFactory.times(1)).getCurrentRace();
		verify(this.dataBaseGateway, VerificationModeFactory.times(0)).createNewRace(any(Race.class));
	}
    
    @Test
	public void getNewSuccess() {
    	when(this.dataBaseGateway.getCurrentRace()).thenReturn(Optional.empty());
    	when(this.dataBaseGateway.createNewRace(any(Race.class))).thenReturn(new Race());
    	
    	Race currentRaceReturned =  this.getCurrentRaceOrCreateRaceNew.getOrCreate();
    	
    	assertNotNull(currentRaceReturned);
    	assertNotNull(currentRaceReturned.getStart());
    	assertNull(currentRaceReturned.getFinish());
    	assertNotNull(currentRaceReturned.getLaps());
    	assertEquals(0, currentRaceReturned.getLaps().size());
    	assertNotNull(currentRaceReturned.getPilots());
    	assertEquals(0, currentRaceReturned.getPilots().size());
    	
		verify(this.dataBaseGateway, VerificationModeFactory.times(1)).getCurrentRace();
		verify(this.dataBaseGateway, VerificationModeFactory.times(1)).createNewRace(any(Race.class));
	}
}

