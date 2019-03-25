package br.com.pupposoft.racetime.api.usecase;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.pupposoft.racetime.api.domains.Race;

@Service
public class CalculateDurationLapsByPilot {
	public void calculate(final Race race) {
		race.getPilots().forEach(p ->  {
			List<Duration> durations = new ArrayList<>();
			p.getLaps().forEach(l -> durations.add(l.getDuration()));
			p.setDuration(durations.stream().reduce(Duration.ZERO, (x,y) -> x.plus(y)));
		});
	}


}
