package br.com.pupposoft.racetime.api.gateway.http.json.race;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.pupposoft.racetime.api.domains.Race;
import br.com.pupposoft.racetime.api.domains.enums.RaceStatus;
import lombok.Getter;

@Getter

public class ResponseRaceJson {
	private Long id;
	private LocalTime open;
	private LocalTime finish;
	private LocalTime close;
	private RaceStatus status;
	private LapJson bestLap;
	private LapJson worstLap;
	
	private List<PilotJson> pilots;
	
	public ResponseRaceJson(final Race race) {
		this.id = race.getId();
		this.open = race.getOpen();
		this.finish = race.getFinish();
		this.close = race.getClose();
		this.status = race.getStatus();
		
		this.bestLap = new LapJson(	race.getBestLap().getId(), 
									race.getBestLap().getNumber(), 
									race.getBestLap().getTime(), 
									race.getBestLap().getDuration(), 
									race.getBestLap().getAverageSpeed(), 
									new PilotJson(	race.getBestLap().getPilot().getId(), 
													race.getBestLap().getPilot().getNome()));
		
		this.worstLap = new LapJson(	race.getWorstLap().getId(), 
				race.getWorstLap().getNumber(), 
				race.getWorstLap().getTime(), 
				race.getWorstLap().getDuration(), 
				race.getWorstLap().getAverageSpeed(), 
				new PilotJson(	race.getWorstLap().getPilot().getId(), 
								race.getWorstLap().getPilot().getNome()));
		
		
		this.pilots = race.getRanking().stream().map(PilotJson::new).collect(Collectors.toList());
	}

}
