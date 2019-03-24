package br.com.pupposoft.racetime.api.gateway.filesystem.dto;

import java.time.Duration;
import java.time.LocalTime;

import lombok.Getter;

@Getter
public class LapDto {
	private LocalTime time;
	private Long pilotCode;
	private String pilotName;
	private Long number;
	private Duration duration;
	private Double average;
	
	//23:49:08.277      038 – F.MASSA                           1		1:02.852                        44,275
	public LapDto(final String line) {
		super();
		final String timeStr = line.substring(0, 12).trim();
		final String pilotCodeStr = line.substring(18, 21).trim();
		final String pilotNameStr = line.substring(24, 58).trim();
		//final String numberStr = line.substring(58, 66).trim(); //Se não fosse tabulação
		final String numberStr = line.substring(58, 60).trim();
		final String durationStr = line.substring(60, 93).trim();
		final String avaregeStr = line.substring(93, line.length()).replace(",", ".").trim();
		
		this.time = LocalTime.parse(timeStr);
		this.pilotCode = Long.valueOf(pilotCodeStr);
		this.pilotName = pilotNameStr;
		this.number = Long.valueOf(numberStr);
		this.duration = Duration.between(LocalTime.MIN, LocalTime.parse("00:0"+durationStr));
		this.average = Double.valueOf(avaregeStr);
	}
}
