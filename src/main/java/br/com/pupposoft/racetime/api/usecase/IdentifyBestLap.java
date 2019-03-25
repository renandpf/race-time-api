package br.com.pupposoft.racetime.api.usecase;

import java.util.Comparator;

import org.springframework.stereotype.Service;

import br.com.pupposoft.racetime.api.domains.Lap;
import br.com.pupposoft.racetime.api.domains.Race;

@Service
public class IdentifyBestLap {

	public void identify(final Race race) {
		race.getPilots().forEach(p -> {
			p.getLaps().stream().min(Comparator.comparing(Lap::getDuration)).get().setBest();
			p.getLaps().stream().max(Comparator.comparing(Lap::getDuration)).get().setWorst();
		});
	}
	
}
