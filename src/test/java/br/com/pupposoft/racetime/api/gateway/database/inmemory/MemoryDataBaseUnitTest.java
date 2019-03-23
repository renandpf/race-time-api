package br.com.pupposoft.racetime.api.gateway.database.inmemory;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.Test;

import br.com.pupposoft.racetime.api.domains.Race;

public class MemoryDataBaseUnitTest {
	private MemoryDataBase memoryDataBase = new MemoryDataBase();
	
	
	@Test
	public void getCurrentRaceWithSucess() {
		final Race oldRace_1 = new Race();
		oldRace_1.end(LocalDateTime.now());
		final Race oldRace_2 = new Race();
		oldRace_1.end(LocalDateTime.now());
		
		this.memoryDataBase.createNewRace(oldRace_1);
		this.memoryDataBase.createNewRace(oldRace_2);
		this.memoryDataBase.createNewRace(new Race());// Current race
		
		final Optional<Race> currentRaceOptional = this.memoryDataBase.getCurrentRace();
		assertTrue(currentRaceOptional.isPresent());
	}
}
