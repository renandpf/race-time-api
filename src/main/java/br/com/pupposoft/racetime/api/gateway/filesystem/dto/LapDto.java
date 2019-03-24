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
		String pilotNameStr = line.substring(24, 58).trim();

		String numberStr = "";
		String durationStr = "";
		String avaregeStr = "";
		
		final int lineSize = line.lastIndexOf(",");
		
		if(lineSize == 74) {
			numberStr = line.substring(58, 61).trim();
			durationStr = line.substring(61, 72).trim();
			avaregeStr = line.substring(72, line.length()).replace(",", ".").trim();
			
		}else if(lineSize == 86) {
			pilotNameStr = line.substring(24, 49).trim();
			numberStr = line.substring(49, 52).trim();
			durationStr = line.substring(52, 84).trim();
			avaregeStr = line.substring(84, line.length()).replace(",", ".").trim();
			
		}else if(lineSize == 95) {
			numberStr = line.substring(58, 61).trim();
			durationStr = line.substring(61, 93).trim();
			avaregeStr = line.substring(93, line.length()).replace(",", ".").trim();
			
		}else if(lineSize == 106) {
			numberStr = line.substring(58, 72).trim();
			durationStr = line.substring(72, 104).trim();
			avaregeStr = line.substring(104, line.length()).replace(",", ".").trim();
			
		}
		
		this.time = LocalTime.parse(timeStr);
		this.pilotCode = Long.valueOf(pilotCodeStr);
		this.pilotName = pilotNameStr;
		this.number = Long.valueOf(numberStr);
		this.duration = Duration.between(LocalTime.MIN, LocalTime.parse("00:0"+durationStr));
		this.average = Double.valueOf(avaregeStr);
	}
}
