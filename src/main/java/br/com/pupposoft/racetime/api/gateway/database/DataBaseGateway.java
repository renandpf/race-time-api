package br.com.pupposoft.racetime.api.gateway.database;

import java.util.Optional;

import br.com.pupposoft.racetime.api.domains.Race;

public interface DataBaseGateway {
	Optional<Race> getCurrentRace();
	void createNewRace(final Race newRace);
}
