package br.com.pupposoft.racetime.api.domains;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Lap {
	private Long id;
	private Long number;
	private LocalDateTime time;
	private Double averageSpeed;
	
	private Pilot pilot;
	private Race race;
	
	public Lap(Long number, LocalDateTime time, Double averageSpeed, Pilot pilot) {
		super();
		this.number = number;
		this.time = time;
		this.averageSpeed = averageSpeed;
		this.pilot = pilot;
	}
}
