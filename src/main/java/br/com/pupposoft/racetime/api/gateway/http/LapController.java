package br.com.pupposoft.racetime.api.gateway.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pupposoft.racetime.api.gateway.http.json.lap.LapRequestJson;
import br.com.pupposoft.racetime.api.usecase.CreateLap;

@Scope("request")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="lap")
public class LapController {
	
	@Autowired
	private CreateLap createLap;
	
	public void createLap(final LapRequestJson lapRequest) {
		// TODO - Implementar
	}
	
}
