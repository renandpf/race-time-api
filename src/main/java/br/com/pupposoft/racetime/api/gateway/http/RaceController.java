package br.com.pupposoft.racetime.api.gateway.http;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Scope("request")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="race")
public class RaceController {
	//TODO - Implementar
}
