package br.com.pupposoft.racetime.api.gateway.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pupposoft.racetime.api.domains.Race;
import br.com.pupposoft.racetime.api.gateway.http.json.race.ResponseRaceJson;
import br.com.pupposoft.racetime.api.usecase.LoadRaceData;

@Scope("request")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="race")
public class RaceController {
	
	@Autowired
	private LoadRaceData loadRaceData;
	
	@GetMapping
	public ResponseRaceJson get() {
		final Race currentRace = this.loadRaceData.loadData(); 
		return new ResponseRaceJson(currentRace);
	}
}
