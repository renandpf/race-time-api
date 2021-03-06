package br.com.pupposoft.racetime.api.usecase;

import static org.mockito.Mockito.verify;

import java.time.LocalTime;
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
    	final LocalTime startCurrent = LocalTime.of(3, 10, 0); //2019-03-03T10:00
    	final Race currentRace = new Race(startCurrent);
    	currentRace.addPilot(new Pilot(1L, "AnyName"));
    	
    	when(this.dataBaseGateway.getCurrentRace()).thenReturn(Optional.of(currentRace));
    	
    	Race currentRaceReturned =  this.getCurrentRaceOrCreateRaceNew.getOrCreate(startCurrent);
    	
    	assertNotNull(currentRaceReturned);
    	assertNotNull(currentRaceReturned.getOpen());
    	assertNull(currentRaceReturned.getFinish());
    	assertNull(currentRaceReturned.getClose());
    	assertNotNull(currentRaceReturned.getPilots());
    	assertEquals(1, currentRaceReturned.getPilots().size());
    	
		verify(this.dataBaseGateway, VerificationModeFactory.times(1)).getCurrentRace();
		verify(this.dataBaseGateway, VerificationModeFactory.times(0)).createNewRace(any(Race.class));
	}
    
    @Test
	public void getNewSuccess() {
    	when(this.dataBaseGateway.getCurrentRace()).thenReturn(Optional.empty());
    	Race currentRaceReturned =  this.getCurrentRaceOrCreateRaceNew.getOrCreate(LocalTime.now());
    	
    	assertNotNull(currentRaceReturned);
    	assertNotNull(currentRaceReturned.getOpen());
    	assertNull(currentRaceReturned.getFinish());
    	assertNotNull(currentRaceReturned.getPilots());
    	assertEquals(0, currentRaceReturned.getPilots().size());
    	
		verify(this.dataBaseGateway, VerificationModeFactory.times(1)).getCurrentRace();
		verify(this.dataBaseGateway, VerificationModeFactory.times(1)).createNewRace(any(Race.class));
	}
}


