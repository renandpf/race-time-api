package br.com.pupposoft.racetime.api.usecase;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

import br.com.pupposoft.racetime.api.domains.Lap;
import br.com.pupposoft.racetime.api.domains.Pilot;
import br.com.pupposoft.racetime.api.domains.Race;

public class CalculateDurationLapsByPilotUnitTest {

	private CalculateDurationLapsByPilot calculateDurationLapsByPilot = new CalculateDurationLapsByPilot();
	
	@Test
	public void calculate() {
		final Race anyRace = new Race(LocalTime.now());
		
    	final Pilot pilot_1 = new Pilot(1L, "Fulano de Tal");
    	final Lap firstLap_pilot_1 = new Lap(1L, LocalTime.now(), Duration.between(LocalTime.MIN, LocalTime.parse("00:00:05.000")), 10D, pilot_1);
    	anyRace.addPilot(pilot_1);
    	anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(firstLap_pilot_1);
    	final Lap secondLap_pilot_1 = new Lap(2L, LocalTime.now(), Duration.between(LocalTime.MIN, LocalTime.parse("00:00:03.000")), 10D, pilot_1);
    	anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(secondLap_pilot_1);
    	final Lap thirtLap_pilot_1 = new Lap(3L, LocalTime.now(), Duration.between(LocalTime.MIN, LocalTime.parse("00:00:02.000")), 10D, pilot_1);
    	anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(thirtLap_pilot_1);

    	final Pilot pilot_2 = new Pilot(2L, "Ciclano de Tal");
    	anyRace.addPilot(pilot_2);
    	final Lap firstLap_pilot_2 = new Lap(1L, LocalTime.now(), Duration.between(LocalTime.MIN, LocalTime.parse("00:01:00.000")), 10D, pilot_2);
    	anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().addLap(firstLap_pilot_2);
    	final Lap secondLap_pilot_2 = new Lap(2L, LocalTime.now(), Duration.between(LocalTime.MIN, LocalTime.parse("00:05:00.000")), 10D, pilot_2);
    	anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().addLap(secondLap_pilot_2);
    	final Lap thirtLap_pilot_2 = new Lap(3L, LocalTime.now(), Duration.between(LocalTime.MIN, LocalTime.parse("00:11:00.000")), 10D, pilot_2);
    	anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().addLap(thirtLap_pilot_2);
    	final Lap fourtLap_pilot_2 = new Lap(4L, LocalTime.now(), Duration.between(LocalTime.MIN, LocalTime.parse("00:06:00.000")), 10D, pilot_2);
    	anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().addLap(fourtLap_pilot_2);

		this.calculateDurationLapsByPilot.calculate(anyRace);
		
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:00:10.000")), anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().getDuration());
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:23:00.000")), anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().getDuration());
	}
	
}
