package br.com.pupposoft.racetime.api.gateway.database.inmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.pupposoft.racetime.api.domains.Race;
import br.com.pupposoft.racetime.api.gateway.database.DataBaseGateway;

@Component
public class MemoryDataBase implements DataBaseGateway {
	
	private List<Race> rides = new ArrayList<>(); 
	
	@Override
	public Optional<Race> getCurrentRace() {
		return this.rides.stream()
				.filter(Race::isCurrent)
				.findFirst();
	}

	@Override
	public void createNewRace(Race newRace) {
		//TODO - implementar multiplas corridas
		this.rides = new ArrayList<>();
		this.rides.add(newRace);
	}

	@Override
	public void updateRace(Race race) {
		// TODO Auto-generated method stub
	}

	@Override
	public void clean() {
		this.rides = new ArrayList<>();
	}

}
