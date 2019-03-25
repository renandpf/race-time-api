package br.com.pupposoft.racetime.api.usecase;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.pupposoft.racetime.api.domains.Race;
import br.com.pupposoft.racetime.api.gateway.filesystem.RaceFileLogGateway;

public class LoadRaceData {
	
	@Autowired
	private RaceFileLogGateway raceFileLogGateway;
	
	@Autowired
	private CreateLap createLap; 

	public Race loadData() {
		//this.raceFileLogGateway.getLapsFromFile(fileName)
		
		
		return null;
	}
	
}
