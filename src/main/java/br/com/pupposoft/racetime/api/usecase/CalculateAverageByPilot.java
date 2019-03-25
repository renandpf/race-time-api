package br.com.pupposoft.racetime.api.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import br.com.pupposoft.racetime.api.domains.Race;

@Service
public class CalculateAverageByPilot {

	public void calculate(final Race race) {
		race.getPilots().forEach(p -> {
			final List<Double> averages = p.getLaps().stream().map(l -> l.getAverageSpeed()).collect(Collectors.toList());
			p.setAverage(averages.stream().mapToDouble(Double::doubleValue).sum() / averages.size());
		});
	}
	
}
