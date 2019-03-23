package br.com.pupposoft.racetime.api.domains;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(exclude= {"nome","laps"})
public class Pilot {
	private Long id;
	private String nome;
	private List<Lap> laps;
	public Pilot(final Long id, final String nome) {
		this.id = id;
		this.nome = nome;
		this.laps = new ArrayList<>();
	}

	public void addLap(final Lap lap) {
		this.laps.add(lap);
	}
}
