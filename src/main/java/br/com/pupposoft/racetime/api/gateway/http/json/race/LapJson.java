package br.com.pupposoft.racetime.api.gateway.http.json.race;

import java.time.Duration;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.pupposoft.racetime.api.domains.Lap;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class LapJson {
	private Long id;
	private Long number;
	private LocalTime time;
	private Duration duration;
	private Double averageSpeed;
	private PilotJson pilot;
	private Boolean best;
	private Boolean worst;

	
	public LapJson(final Lap lap) {
		this.id = lap.getId();
		this.number = lap.getNumber();
		this.time = lap.getTime();
		this.duration = lap.getDuration();
		this.averageSpeed = lap.getAverageSpeed();
		this.best = lap.isBest();
		this.worst = lap.isWorst();
	}


	public LapJson(Long id, Long number, LocalTime time, Duration duration, Double averageSpeed, PilotJson pilot) {
		super();
		this.id = id;
		this.number = number;
		this.time = time;
		this.duration = duration;
		this.averageSpeed = averageSpeed;
		this.pilot = pilot;
	}
	
	
}
