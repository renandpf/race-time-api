package br.com.pupposoft.racetime.api.gateway.database.inmemory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import org.junit.Test;

import br.com.pupposoft.racetime.api.domains.Race;
import br.com.pupposoft.racetime.api.domains.enums.RaceStatus;

public class MemoryDataBaseUnitTest {
	private MemoryDataBase memoryDataBase = new MemoryDataBase();
	
	
	@Test
	public void getCurrentRaceWithSucess() {
		final LocalDateTime startRaceOld_1 = LocalDateTime.of(2019, Month.MARCH, 1, 10, 0); //2019-03-01T10:00
		final LocalDateTime endRaceOld_1 = LocalDateTime.of(2019, Month.MARCH, 1, 11, 0);	//2019-03-01T11:00
		final Race oldRace_1 = new Race(startRaceOld_1);
		oldRace_1.end(endRaceOld_1);
		
		final LocalDateTime startRaceOld_2 = LocalDateTime.of(2019, Month.MARCH, 2, 10, 0);	//2019-03-02T10:00
		final LocalDateTime endRaceOld_2 = LocalDateTime.of(2019, Month.MARCH, 2, 11, 0);	//2019-03-02T11:00 
		final Race oldRace_2 = new Race(startRaceOld_2);
		oldRace_2.end(endRaceOld_2);
		
		final LocalDateTime startCurrent = LocalDateTime.of(2019, Month.MARCH, 3, 10, 0); //2019-03-03T10:00
		final Race currentRace = new Race(startCurrent);
		
		this.memoryDataBase.createNewRace(oldRace_1);
		this.memoryDataBase.createNewRace(oldRace_2);
		this.memoryDataBase.createNewRace(currentRace);// Current race
		
		final Optional<Race> currentRaceOptional = this.memoryDataBase.getCurrentRace();
		assertTrue(currentRaceOptional.isPresent());
		assertEquals(startCurrent, currentRaceOptional.get().getStart());
		assertNull(currentRaceOptional.get().getFinish());
		assertEquals(RaceStatus.STARTED, currentRaceOptional.get().getStatus());
	}
	
	@Test
	public void getCurrentRaceWithNoCurrent() {
		final LocalDateTime startRaceOld_1 = LocalDateTime.of(2019, Month.MARCH, 1, 10, 0); //2019-03-01T10:00
		final LocalDateTime endRaceOld_1 = LocalDateTime.of(2019, Month.MARCH, 1, 11, 0);	//2019-03-01T11:00
		final Race oldRace_1 = new Race(startRaceOld_1);
		oldRace_1.end(endRaceOld_1);
		
		final LocalDateTime startRaceOld_2 = LocalDateTime.of(2019, Month.MARCH, 2, 10, 0);	//2019-03-02T10:00
		final LocalDateTime endRaceOld_2 = LocalDateTime.of(2019, Month.MARCH, 2, 11, 0);	//2019-03-02T11:00 
		final Race oldRace_2 = new Race(startRaceOld_2);
		oldRace_2.end(endRaceOld_2);
		
		this.memoryDataBase.createNewRace(oldRace_1);
		this.memoryDataBase.createNewRace(oldRace_2);
		
		final Optional<Race> currentRaceOptional = this.memoryDataBase.getCurrentRace();
		assertFalse(currentRaceOptional.isPresent());
	}
}
