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
	private List<PilotJson> pilots;
	
	public ResponseRaceJson(final Race race) {
		this.id = race.getId();
		this.open = race.getOpen();
		this.finish = race.getFinish();
		this.close = race.getClose();
		this.status = race.getStatus();
		
		this.pilots = race.getPilots().stream().map(PilotJson::new).collect(Collectors.toList());
	}

}
