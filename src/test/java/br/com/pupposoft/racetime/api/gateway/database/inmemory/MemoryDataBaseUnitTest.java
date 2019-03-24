package br.com.pupposoft.racetime.api.gateway.database.inmemory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalTime;
import java.util.Optional;

import org.junit.Test;

import br.com.pupposoft.racetime.api.domains.Race;
import br.com.pupposoft.racetime.api.domains.enums.RaceStatus;

public class MemoryDataBaseUnitTest {
	private MemoryDataBase memoryDataBase = new MemoryDataBase();
	
	
	@Test
	public void getCurrentRaceWithSucess() {
		final LocalTime startRaceOld_1 = LocalTime.of(1, 10, 0); //2019-03-01T10:00
		final LocalTime endRaceOld_1 = LocalTime.of(1, 11, 0);	//2019-03-01T11:00
		final Race oldRace_1 = new Race(startRaceOld_1);
		oldRace_1.end(endRaceOld_1);
		
		final LocalTime startRaceOld_2 = LocalTime.of(2, 10, 0);	//2019-03-02T10:00
		final LocalTime endRaceOld_2 = LocalTime.of(2, 11, 0);	//2019-03-02T11:00 
		final Race oldRace_2 = new Race(startRaceOld_2);
		oldRace_2.end(endRaceOld_2);
		
		final LocalTime startCurrent = LocalTime.of(3, 10, 0); //2019-03-03T10:00
		final Race currentRace = new Race(startCurrent);
		
		this.memoryDataBase.createNewRace(oldRace_1);
		this.memoryDataBase.createNewRace(oldRace_2);
		this.memoryDataBase.createNewRace(currentRace);// Current race
		
		final Optional<Race> currentRaceOptional = this.memoryDataBase.getCurrentRace();
		assertTrue(currentRaceOptional.isPresent());
		assertEquals(startCurrent, currentRaceOptional.get().getOpen());
		assertNull(currentRaceOptional.get().getFinish());
		assertEquals(RaceStatus.OPEN, currentRaceOptional.get().getStatus());
	}
	
	@Test
	public void getCurrentRaceWithNoCurrent() {
		final LocalTime startRaceOld_1 = LocalTime.of(1, 10, 0); //2019-03-01T10:00
		final LocalTime endRaceOld_1 = LocalTime.of(1, 11, 0);	//2019-03-01T11:00
		final Race oldRace_1 = new Race(startRaceOld_1);
		oldRace_1.end(endRaceOld_1);
		
		final LocalTime startRaceOld_2 = LocalTime.of(2, 10, 0);	//2019-03-02T10:00
		final LocalTime endRaceOld_2 = LocalTime.of(2, 11, 0);	//2019-03-02T11:00 
		final Race oldRace_2 = new Race(startRaceOld_2);
		oldRace_2.end(endRaceOld_2);
		
		this.memoryDataBase.createNewRace(oldRace_1);
		this.memoryDataBase.createNewRace(oldRace_2);
		
		final Optional<Race> currentRaceOptional = this.memoryDataBase.getCurrentRace();
		assertFalse(currentRaceOptional.isPresent());
	}
}
