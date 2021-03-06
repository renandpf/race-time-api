package br.com.pupposoft.racetime.api.domains;

import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import br.com.pupposoft.racetime.api.domains.enums.RaceStatus;
import lombok.Getter;

@Getter
public class Race {
	private Long id;
	private LocalTime open;
	private LocalTime finish;
	private LocalTime close;
	private RaceStatus status;
	private Set<Pilot> pilots;
	private List<Pilot> ranking;
	private Lap bestLap;
	private Lap worstLap;
	
	public Race(final LocalTime start) {
		this.open = start;
		this.status = RaceStatus.OPEN;
		this.pilots = new LinkedHashSet<>();
		this.bestLap = null;
		this.worstLap = null;
	}
	
	public void addPilot(final Pilot pilot) {
		this.pilots.add(pilot);
	}
	
	public void end(final LocalTime end) {
		this.status = RaceStatus.FINISHED;
		this.finish = end;
	}
	
	public void close(final LocalTime close) {
		this.status = RaceStatus.CLOSE;
		this.close = close;
	}
	
	public boolean isCurrent() {
		return !RaceStatus.CLOSE.equals(this.status);
	}
	
	public void setBestLap(final Lap lap) {
		this.bestLap = lap;
	}
	
	public void setWorstLap(final Lap lap) {
		this.worstLap = lap;
	}
	
	public void setRanking(final List<Pilot> ranking) {
		this.ranking = ranking;
	}
}
