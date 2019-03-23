package br.com.pupposoft.racetime.api.domains;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

	
	public Race(final LocalDateTime start) {
		this.start = start;
		this.status = RaceStatus.STARTED;
		this.pilots = new ArrayList<>();
		this.laps = new ArrayList<>();
	}
	
	public void addPilot(final Pilot pilot) {
		this.pilots.add(pilot);
	}
	
	public void addLap(final Lap lap) {
		this.laps.add(lap);
	}
	
	public void end(final LocalDateTime end) {
		this.status = RaceStatus.FINISHED;
		this.finish = end;
	}
	
	public boolean isCurrent() {
		return RaceStatus.STARTED.equals(this.status);
	}
}
