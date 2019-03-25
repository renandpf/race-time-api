package br.com.pupposoft.racetime.api.usecase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pupposoft.racetime.api.domains.Lap;
import br.com.pupposoft.racetime.api.domains.Race;
import br.com.pupposoft.racetime.api.gateway.database.DataBaseGateway;
import br.com.pupposoft.racetime.api.gateway.filesystem.RaceFileLogGateway;

@Service
public class LoadRaceData {
	
	@Autowired
	private RaceFileLogGateway raceFileLogGateway;
	
	@Autowired
	private CreateLap createLap; 
	
	@Autowired
	private DataBaseGateway dataBaseGateway; 

	public Race loadData() {
		final String logFile = "race.log";
		final List<Lap> laps =  this.raceFileLogGateway.getLapsFromFile(logFile);
		
		laps.forEach(l -> this.createLap.create(l));
		
		return this.dataBaseGateway.getCurrentRace().orElseThrow();//TODO - Tratar fallbacks
	}
}
