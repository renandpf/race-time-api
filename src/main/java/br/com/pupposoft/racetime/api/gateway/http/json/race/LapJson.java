package br.com.pupposoft.racetime.api.gateway.http.json.race;

import java.time.Duration;
import java.time.LocalTime;

import br.com.pupposoft.racetime.api.domains.Lap;
import lombok.Getter;

@Getter
public class LapJson {
	private Long id;
	private Long number;
	private LocalTime time;
	private Duration duration;
	private Double averageSpeed;

	
	public LapJson(final Lap lap) {
		this.id = lap.getId();
		this.number = lap.getNumber();
		this.time = lap.getTime();
		this.duration = lap.getDuration();
		this.averageSpeed = lap.getAverageSpeed();
	}

}
