package br.com.pupposoft.racetime.api.usecase;

import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
//import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
    	Optional<Race> currentRaceOptional = Optional.of(new Race());
    	when(this.dataBaseGateway.getCurrentRace()).thenReturn(currentRaceOptional);
    	
    	Race currentRace =  this.getCurrentRaceOrCreateRaceNew.getOrCreate();
    	
    	assertNotNull(currentRace);
    	assertNotNull(currentRace.getStart());
    	assertNull(currentRace.getFinish());
    	assertNotNull(currentRace.getLaps());
    	assertNotNull(currentRace.getPilots());
    	
		verify(this.dataBaseGateway, VerificationModeFactory.times(1)).getCurrentRace();
		verify(this.dataBaseGateway, VerificationModeFactory.times(0)).createNewRace(any(Race.class));
	}
}
