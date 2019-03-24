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
	
	public void update(Race raceToUpdate, final Lap lap) {
		this.addLap(raceToUpdate, lap);
		raceToUpdate = this.checkAndEndRace(raceToUpdate, lap);
		this.dataBaseGateway.updateRace(raceToUpdate);
	}

	private Race checkAndEndRace(final Race raceToUpdate, final Lap lap) {
		if(this.isRaceCompleted(raceToUpdate)) {
			raceToUpdate.end(lap.getTime());
		}
		return raceToUpdate;
	}

	private boolean isRaceCompleted(Race raceToUpdate) {
		return raceToUpdate.getPilots().stream().anyMatch(p -> p.getLaps().size() >= AMOUNT_LAPS_TO_FINISH_RACE);
	}

	private void addLap(final Race raceToUpdate, final Lap lap) {
		raceToUpdate.addPilot(lap.getPilot());
		raceToUpdate.getPilots().stream().filter(p -> p.getId().equals(lap.getPilot().getId())).findFirst().get().addLap(lap);
	}
}
