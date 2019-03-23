package br.com.pupposoft.racetime.api.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pupposoft.racetime.api.domains.Lap;
import br.com.pupposoft.racetime.api.domains.Race;

@Service
public class CreateLap {
	@Autowired
	private GetCurrentRaceOrCreateRaceNew getCurrentRaceOrCreateNew;
	
	@Autowired
	private UpdateRace updateRace; 
	
	public void create(final Lap lap) {
		final Race race = this.getCurrentRaceOrCreateNew.getOrCreate();
		this.updateRace.update(race, lap);
	}
}
