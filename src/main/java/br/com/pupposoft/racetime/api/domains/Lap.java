package br.com.pupposoft.racetime.api.domains;

import java.time.Duration;
import java.time.LocalTime;

import lombok.Getter;

@Getter
public class Lap {
	private Long id;
	private Long number;
	private LocalTime time;
	private Duration duration;
	private Double averageSpeed;
	
	private Pilot pilot;
	private Race race;
	
	public Lap(Long number, LocalTime time, Double averageSpeed, Pilot pilot) {
		super();
		this.number = number;
		this.time = time;
		this.averageSpeed = averageSpeed;
		this.pilot = pilot;
	}
}
