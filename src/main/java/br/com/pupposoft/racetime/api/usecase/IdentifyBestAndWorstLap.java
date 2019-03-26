package br.com.pupposoft.racetime.api.usecase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.pupposoft.racetime.api.domains.Lap;
import br.com.pupposoft.racetime.api.domains.Race;

@Service
public class IdentifyBestAndWorstLap {

	public void identify(final Race race) {
		
		final List<Lap> onlyBetterAndWorsts = new ArrayList<>();
		race.getPilots().forEach(p -> {
			p.getLaps().stream().min(Comparator.comparing(Lap::getDuration)).get().setBest();
			p.getLaps().stream().max(Comparator.comparing(Lap::getDuration)).get().setWorst();
			
			onlyBetterAndWorsts.addAll(p.getLaps().stream().filter(l -> l.isBest() || l.isWorst()).collect(Collectors.toList()));
		});
		race.setBestLap(onlyBetterAndWorsts.stream().filter(Lap::isBest).min(Comparator.comparing(Lap::getDuration)).get());
		race.setWorstLap(onlyBetterAndWorsts.stream().filter(Lap::isWorst).max(Comparator.comparing(Lap::getDuration)).get());
	}
}
