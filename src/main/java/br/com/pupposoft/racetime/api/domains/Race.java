package br.com.pupposoft.racetime.api.domains;

import java.time.LocalDateTime;
import java.util.List;

import br.com.pupposoft.racetime.api.domains.enums.RaceStatus;
import lombok.Getter;

@Getter
public class Race {
	private Long id;
	private LocalDateTime start;
	private LocalDateTime finish;
	private RaceStatus status;
	
	private List<Pilot> pilots;
	private List<Lap> laps;
}
