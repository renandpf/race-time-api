package br.com.pupposoft.racetime.api.usecase;

import static org.mockito.Mockito.verify;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;

//import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import br.com.pupposoft.racetime.api.domains.Lap;
import br.com.pupposoft.racetime.api.domains.Pilot;
import br.com.pupposoft.racetime.api.domains.Race;

public class CreateLapUnitTest {

	@InjectMocks
	private CreateLap createLap;
	
	@Mock
	private GetCurrentRaceOrCreateRaceNew getCurrentRaceOrCreateNew;
	
	@Mock
	private UpdateRace updateRace;
	
    @Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
    @Test
	public void createWithSuccess() {
		final Lap newLap = new Lap(1L, LocalTime.now(), 10D, new Pilot(1L, "AnyName"));
		this.createLap.create(newLap);
    	
		verify(this.getCurrentRaceOrCreateNew, VerificationModeFactory.times(1)).getOrCreate();
		verify(this.updateRace, VerificationModeFactory.times(1)).update(any(Race.class), eq(newLap));
	}
}
