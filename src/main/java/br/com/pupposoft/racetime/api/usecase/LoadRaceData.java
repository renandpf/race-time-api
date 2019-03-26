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
	public static final String logFile = "race.log";
	
	@Autowired
	private RaceFileLogGateway raceFileLogGateway;
	
	@Autowired
	private CreateLap createLap; 
	
	@Autowired
	private IdentifyBestAndWorstLap identifyBestAndWorstLap;
	
	@Autowired
	private SortByWinner sortByWinner;

	@Autowired
	private CalculateDurationLapsByPilot calculateDurationLapsByPilot;

	@Autowired
	private CalculateAverageByPilot calculateAverageByPilot;
	
	@Autowired
	private DataBaseGateway dataBaseGateway; 

	public Race loadData() {
		this.loadLapsFromFile();
		final Race currentRace = this.dataBaseGateway.getCurrentRace().orElseThrow();//TODO - Tratar fallbacks
		this.identifyBestAndWorstLap.identify(currentRace);
		this.calculateDurationLapsByPilot.calculate(currentRace);
		this.sortByWinner.sort(currentRace);
		this.calculateAverageByPilot.calculate(currentRace);
		
		return currentRace;
	}

	private void loadLapsFromFile() {
		final String logFile = "race.log";
		final List<Lap> laps =  this.raceFileLogGateway.getLapsFromFile(logFile);
		laps.forEach(l -> this.createLap.create(l));
	}
}
