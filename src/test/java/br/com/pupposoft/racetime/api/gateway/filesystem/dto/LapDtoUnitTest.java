package br.com.pupposoft.racetime.api.gateway.filesystem.dto;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Test;

public class LapDtoUnitTest {

	@Test
	public void createFromLine() {
		final String line = "23:49:08.277      038 – F.MASSA                           1		1:02.852                        44,275";
		
		LapDto lapDto = new LapDto(line);
		
		assertEquals(LocalTime.parse("23:49:08.277"), lapDto.getTime());
		assertEquals(38, lapDto.getPilotCode(), 0);
		assertEquals("F.MASSA", lapDto.getPilotName());
		assertEquals(1, lapDto.getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:02.852")), lapDto.getDuration());
		assertEquals(44.275D, lapDto.getAverage(), 0);
	}
	
}
