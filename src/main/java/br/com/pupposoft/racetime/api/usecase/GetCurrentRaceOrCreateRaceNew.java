package br.com.pupposoft.racetime.api.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pupposoft.racetime.api.domains.Race;
import br.com.pupposoft.racetime.api.gateway.database.DataBaseGateway;

@Service
public class GetCurrentRaceOrCreateRaceNew {

	@Autowired
	private DataBaseGateway dataBaseGateway;
	
	public Race getOrCreate() {
		Optional<Race> optionalRace = this.dataBaseGateway.getCurrentRace();
		if(optionalRace.isPresent()) {
			return optionalRace.get();
		}
		return this.dataBaseGateway.createNewRace(this.createNew());
	}
	
	private Race createNew() {
		final Race newRace = new Race();
		
		return newRace;
	}
}
