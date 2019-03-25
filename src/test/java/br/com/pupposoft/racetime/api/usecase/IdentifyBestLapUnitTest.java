package br.com.pupposoft.racetime.api.usecase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

import br.com.pupposoft.racetime.api.domains.Lap;
import br.com.pupposoft.racetime.api.domains.Pilot;
import br.com.pupposoft.racetime.api.domains.Race;

public class IdentifyBestLapUnitTest {

	private IdentifyBestAndWorstLap identifyBestLap = new IdentifyBestAndWorstLap(); 
	
	@Test
	public void checkeBestLapForPilot() {
		final Race anyRace = new Race(LocalTime.now());
		
    	final Pilot pilot_1 = new Pilot(1L, "Fulano de Tal");
    	final Lap firstLap_pilot_1 = new Lap(1L, LocalTime.now(), Duration.between(LocalTime.MIN, LocalTime.parse("00:00:05.000")), 10D, pilot_1);//worst
    	anyRace.addPilot(pilot_1);
    	anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(firstLap_pilot_1);
    	final Lap secondLap_pilot_1 = new Lap(2L, LocalTime.now(), Duration.between(LocalTime.MIN, LocalTime.parse("00:00:03.000")), 10D, pilot_1);
    	anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(secondLap_pilot_1);
    	final Lap thirtLap_pilot_1 = new Lap(3L, LocalTime.now(), Duration.between(LocalTime.MIN, LocalTime.parse("00:00:02.000")), 10D, pilot_1);// best * melhor de todas
    	anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().addLap(thirtLap_pilot_1);

    	final Pilot pilot_2 = new Pilot(2L, "Ciclano de Tal");
    	anyRace.addPilot(pilot_2);
    	final Lap firstLap_pilot_2 = new Lap(1L, LocalTime.now(), Duration.between(LocalTime.MIN, LocalTime.parse("00:01:00.000")), 10D, pilot_2);// best
    	anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().addLap(firstLap_pilot_2);
    	final Lap secondLap_pilot_2 = new Lap(2L, LocalTime.now(), Duration.between(LocalTime.MIN, LocalTime.parse("00:05:00.000")), 10D, pilot_2);
    	anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().addLap(secondLap_pilot_2);
    	final Lap thirtLap_pilot_2 = new Lap(3L, LocalTime.now(), Duration.between(LocalTime.MIN, LocalTime.parse("00:11:00.000")), 10D, pilot_2);//worst * pior de todas
    	anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().addLap(thirtLap_pilot_2);
    	final Lap fourtLap_pilot_2 = new Lap(4L, LocalTime.now(), Duration.between(LocalTime.MIN, LocalTime.parse("00:06:00.000")), 10D, pilot_2);
    	anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().addLap(fourtLap_pilot_2);
    	
    	this.identifyBestLap.identify(anyRace);
    	
    	//Pilot_1#Lap1
    	assertTrue(anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().getLaps().get(0).isWorst());
    	assertFalse(anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().getLaps().get(0).isBest());
    	
    	//Pilot1#Lap2
    	assertFalse(anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().getLaps().get(1).isWorst());
    	assertFalse(anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().getLaps().get(1).isBest());
    	
    	//Pilot1#Lap3
    	assertFalse(anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().getLaps().get(2).isWorst());
    	assertTrue(anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_1.getId())).findFirst().get().getLaps().get(2).isBest());
    	
    	//---
    	
    	//Pilot_2#Lap1
    	assertFalse(anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().getLaps().get(0).isWorst());
    	assertTrue(anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().getLaps().get(0).isBest());
    	
    	//Pilot2#Lap2
    	assertFalse(anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().getLaps().get(1).isWorst());
    	assertFalse(anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().getLaps().get(1).isBest());
    	
    	//Pilot3#Lap3
    	assertTrue(anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().getLaps().get(2).isWorst());
    	assertFalse(anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().getLaps().get(2).isBest());
    	
    	//Pilot2#Lap2
    	assertFalse(anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().getLaps().get(3).isWorst());
    	assertFalse(anyRace.getPilots().stream().filter(p -> p.getId().equals(pilot_2.getId())).findFirst().get().getLaps().get(3).isBest());
    	
    	
    	final Lap bestOfTheBestsLap = anyRace.getBestLap();
    	assertEquals(thirtLap_pilot_1.getDuration(), bestOfTheBestsLap.getDuration());
    	
    	final Lap wosrtOfTheWosrtsLap = anyRace.getWorstLap();
    	assertEquals(thirtLap_pilot_2.getDuration(), wosrtOfTheWosrtsLap.getDuration());
	}
	
}

