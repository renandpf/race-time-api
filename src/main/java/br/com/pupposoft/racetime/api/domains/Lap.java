package br.com.pupposoft.racetime.api.domains;

import java.time.Duration;
import java.time.LocalTime;

import lombok.Getter;

/**
 * @author renandpf
 *
 */
@Getter
public class Lap {
	private Long id;
	private Long number;
	private LocalTime time;
	private Duration duration;
	private Double averageSpeed;
	private boolean best;
	private boolean worst;
	
	private Pilot pilot;
	private Race race;
	
	public Lap(	final Long number, 
				final LocalTime time, 
				final Duration duration, 
				final Double averageSpeed, 
				final Pilot pilot) {
		super();
		this.number = number;
		this.time = time;
		this.averageSpeed = averageSpeed;
		this.pilot = pilot;
		this.duration = duration;
		this.best = false;
		this.worst = false;
	}
	
	public void setBest() {
		this.best = true;
	}
	
	public void setWorst() {
		this.worst = true;
	}
}
