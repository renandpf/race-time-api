package br.com.pupposoft.racetime.api.gateway.filesystem.dto;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

public class LapDtoUnitTest {

	@Test
	public void createFromLine_74() {
		final String line = "23:49:30.976      015 – F.ALONSO                          1		1:18.456			35,47";
		
		LapDto lapDto = new LapDto(line);
		
		assertEquals(LocalTime.parse("23:49:30.976"), lapDto.getTime());
		assertEquals(15, lapDto.getPilotCode(), 0);
		assertEquals("F.ALONSO", lapDto.getPilotName());
		assertEquals(1, lapDto.getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:18.456")), lapDto.getDuration());
		assertEquals(35.47D, lapDto.getAverage(), 0);
	}

	@Test
	public void createFromLine_86() {
		final String line = "23:51:18.576      033 – R.BARRICHELLO		          3		1:03.716                        43,675";
		
		LapDto lapDto = new LapDto(line);
		
		assertEquals(LocalTime.parse("23:51:18.576"), lapDto.getTime());
		assertEquals(33, lapDto.getPilotCode(), 0);
		assertEquals("R.BARRICHELLO", lapDto.getPilotName());
		assertEquals(3, lapDto.getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:03.716")), lapDto.getDuration());
		assertEquals(43.675D, lapDto.getAverage(), 0);
	}
	
	@Test
	public void createFromLine_95() {
		final String line = "23:49:08.277      038 – F.MASSA                           1		1:02.852                        44,275";
		
		LapDto lapDto = new LapDto(line);
		
		assertEquals(LocalTime.parse("23:49:08.277"), lapDto.getTime());
		assertEquals(38, lapDto.getPilotCode(), 0);
		assertEquals("F.MASSA", lapDto.getPilotName());
		assertEquals(1, lapDto.getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:02.852")), lapDto.getDuration());
		assertEquals(44.275D, lapDto.getAverage(), 0);
	}

	@Test
	public void createFromLine_106() {
		final String line = "23:49:11.075      002 – K.RAIKKONEN                       1             1:04.108                        43,408";
		
		LapDto lapDto = new LapDto(line);
		
		assertEquals(LocalTime.parse("23:49:11.075"), lapDto.getTime());
		assertEquals(2, lapDto.getPilotCode(), 0);
		assertEquals("K.RAIKKONEN", lapDto.getPilotName());
		assertEquals(1, lapDto.getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.108")), lapDto.getDuration());
		assertEquals(43.408D, lapDto.getAverage(), 0);
	}
	
	

}
