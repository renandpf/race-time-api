package br.com.pupposoft.racetime.api.gateway.database.inmemory;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.pupposoft.racetime.api.domains.Race;
import br.com.pupposoft.racetime.api.gateway.database.DataBaseGateway;

@Component
public class MemoryDataBase implements DataBaseGateway {

	@Override
	public Optional<Race> getCurrentRace() {
		// TODO Implmenetar
		return null;
	}

	@Override
	public Race createNewRace(Race newRace) {
		// TODO Implementar
		return null;
	}

}
