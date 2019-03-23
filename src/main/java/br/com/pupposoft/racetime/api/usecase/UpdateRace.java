package br.com.pupposoft.racetime.api.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pupposoft.racetime.api.domains.Lap;
import br.com.pupposoft.racetime.api.domains.Race;
import br.com.pupposoft.racetime.api.gateway.database.DataBaseGateway;

@Service
public class UpdateRace {
	public static final int AMOUNT_LAPS_TO_FINISH_RACE = 4;
	
	@Autowired
	private DataBaseGateway dataBaseGateway;
	
	@Autowired
	private GetCurrentRaceOrCreateRaceNew getCurrentRaceOrCreateRaceNew;
	
	public void update(final Race raceToUpdate, final Lap lap) {
		raceToUpdate.addPilot(lap.getPilot());
		raceToUpdate.getPilots().stream().filter(p -> p.getId().equals(lap.getPilot().getId())).findFirst().get().addLap(lap);
		this.dataBaseGateway.updateRace(raceToUpdate);
		
//		final boolean raceCompleted = raceToUpdate.getPilots().stream().anyMatch(p -> p.getLaps().size() >= AMOUNT_LAPS_TO_FINISH_RACE);
//		if(raceCompleted) {
//			raceToUpdate.end(LocalDateTime.now());
//			this.dataBaseGateway.updateRace(raceToUpdate);
//			final Race newRace = this.getCurrentRaceOrCreateRaceNew.getOrCreate();
//			newRace.addLap(lap);
//			newRace.addPilot(lap.getPilot());
//		}
	}
}
