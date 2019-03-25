package br.com.pupposoft.racetime.api.gateway.http.json.race;

import java.util.List;
import java.util.stream.Collectors;

import br.com.pupposoft.racetime.api.domains.Pilot;
import lombok.Getter;

@Getter
public class PilotJson {
	private Long id;
	private String nome;
	private List<LapJson> laps;

	
	public PilotJson(final Pilot pilot) {
		this.id = pilot.getId();
		this.nome = pilot.getNome();
		this.laps = pilot.getLaps().stream().map(LapJson::new).collect(Collectors.toList());
	}

}
