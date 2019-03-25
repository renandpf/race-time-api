package br.com.pupposoft.racetime.api.gateway.http.json.race;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.pupposoft.racetime.api.domains.Pilot;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class PilotJson {
	private Long id;
	private String nome;
	private Duration duration;
	private List<LapJson> laps;

	
	public PilotJson(final Pilot pilot) {
		this.id = pilot.getId();
		this.nome = pilot.getNome();
		this.duration = pilot.getDuration();
		this.laps = pilot.getLaps().stream().map(LapJson::new).collect(Collectors.toList());
	}

	public PilotJson(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
}
