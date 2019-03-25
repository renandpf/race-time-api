package br.com.pupposoft.racetime.api.usecase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.pupposoft.racetime.api.domains.Pilot;
import br.com.pupposoft.racetime.api.domains.Race;

@Service
public class SortByWinner {
	public void sort(final Race race) {
		final List<Pilot> allPilotsWithAllValidLaps = this.getClassifedPilots(race);
		final List<Pilot> allPilotsWithAnyInValidLaps = this.getUnClassifedPilots(race);
		
		final List<Pilot> ranking = allPilotsWithAllValidLaps.stream().sorted(Comparator.comparing(Pilot::getDuration)).collect(Collectors.toList());
		ranking.addAll(allPilotsWithAnyInValidLaps);// TODO - Classificar este também
		
		race.setRanking(ranking);
	}

	private List<Pilot> getClassifedPilots(final Race race) {
		final List<Pilot> allPilotsWithAllValidLaps = new ArrayList<>();
		allPilotsWithAllValidLaps.addAll(race.getPilots().stream().filter(p -> p.getLaps().size() >= UpdateRace.AMOUNT_LAPS_TO_FINISH_RACE).collect(Collectors.toList()));
		return allPilotsWithAllValidLaps;
	}
	
	private List<Pilot> getUnClassifedPilots(final Race race) {
		final List<Pilot> allPilotsWithAnyInValidLaps = new ArrayList<>();
		allPilotsWithAnyInValidLaps.addAll(race.getPilots().stream().filter(p -> p.getLaps().size() < UpdateRace.AMOUNT_LAPS_TO_FINISH_RACE).collect(Collectors.toList()));
		return allPilotsWithAnyInValidLaps;
	}
}
