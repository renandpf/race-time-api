package br.com.pupposoft.racetime.api.usecase;

import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pupposoft.racetime.api.domains.Race;
import br.com.pupposoft.racetime.api.gateway.database.DataBaseGateway;

@Service
public class GetCurrentRaceOrCreateRaceNew {

	@Autowired
	private DataBaseGateway dataBaseGateway;
	
	public Race getOrCreate(final LocalTime start) {
		Optional<Race> optionalRace = this.dataBaseGateway.getCurrentRace();
		if(optionalRace.isPresent()) {
			return optionalRace.get();
		}
		final Race newRace = this.createNew(start);
		this.dataBaseGateway.createNewRace(newRace);
		return newRace;
	}
	
	private Race createNew(final LocalTime start) {
		final Race newRace = new Race(start);
		
		return newRace;
	}
}
