package br.com.pupposoft.racetime.api.gateway.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pupposoft.racetime.api.gateway.http.json.race.RaceRequestJson;
import br.com.pupposoft.racetime.api.usecase.GetCurrentRaceOrCreateRaceNew;

@Scope("request")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="race")
public class RaceController {
	
	@Autowired
	private GetCurrentRaceOrCreateRaceNew createRace;
	
	public void createRace(final RaceRequestJson raceRequestJson) {
		// TODO - Implementar
	}
	
}
