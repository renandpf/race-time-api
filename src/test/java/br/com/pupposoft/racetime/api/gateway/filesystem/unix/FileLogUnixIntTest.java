package br.com.pupposoft.racetime.api.gateway.filesystem.unix;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.pupposoft.racetime.api.domains.Lap;

@Ignore
public class FileLogUnixIntTest {

	private FileLogUnix fileLogUnix = new FileLogUnix();

	@Test
	public void readFileReal() {
		List<Lap> laps = this.fileLogUnix.getLapsFromFile("/home/renandpf/Documentos/desenvolvimento/projetos/code/race-time-api/src/test/resources/race.log");
		assertEquals(23, laps.size());
		assertEquals(LocalTime.parse("23:49:08.277"), laps.get(0).getTime());
		assertEquals(38, laps.get(0).getPilot().getId(), 0);
		assertEquals("F.MASSA", laps.get(0).getPilot().getNome());
		assertEquals(1, laps.get(0).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:02.852")), laps.get(0).getDuration());
		assertEquals(44.275, laps.get(0).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:49:10.858"), laps.get(1).getTime());
		assertEquals(33, laps.get(1).getPilot().getId(), 0);
		assertEquals("R.BARRICHELLO", laps.get(1).getPilot().getNome());
		assertEquals(1, laps.get(1).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.352")), laps.get(1).getDuration());
		assertEquals(43.243, laps.get(1).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:49:11.075"), laps.get(2).getTime());
		assertEquals(2, laps.get(2).getPilot().getId(), 0);
		assertEquals("K.RAIKKONEN", laps.get(2).getPilot().getNome());
		assertEquals(1, laps.get(2).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.108")), laps.get(2).getDuration());
		assertEquals(43.408, laps.get(2).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:49:12.667"), laps.get(3).getTime());
		assertEquals(23, laps.get(3).getPilot().getId(), 0);
		assertEquals("M.WEBBER", laps.get(3).getPilot().getNome());
		assertEquals(1, laps.get(3).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.414")), laps.get(3).getDuration());
		assertEquals(43.202, laps.get(3).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:49:30.976"), laps.get(4).getTime());
		assertEquals(15, laps.get(4).getPilot().getId(), 0);
		assertEquals("F.ALONSO", laps.get(4).getPilot().getNome());
		assertEquals(1, laps.get(4).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:18.456")), laps.get(4).getDuration());
		assertEquals(35.47, laps.get(4).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:50:11.447"), laps.get(5).getTime());
		assertEquals(38, laps.get(5).getPilot().getId(), 0);
		assertEquals("F.MASSA", laps.get(5).getPilot().getNome());
		assertEquals(2, laps.get(5).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:03.170")), laps.get(5).getDuration());
		assertEquals(44.053, laps.get(5).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:50:14.860"), laps.get(6).getTime());
		assertEquals(33, laps.get(6).getPilot().getId(), 0);
		assertEquals("R.BARRICHELLO", laps.get(6).getPilot().getNome());
		assertEquals(2, laps.get(6).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.002")), laps.get(6).getDuration());
		assertEquals(43.48, laps.get(6).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:50:15.057"), laps.get(7).getTime());
		assertEquals(2, laps.get(7).getPilot().getId(), 0);
		assertEquals("K.RAIKKONEN", laps.get(7).getPilot().getNome());
		assertEquals(2, laps.get(7).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:03.982")), laps.get(7).getDuration());
		assertEquals(43.493, laps.get(7).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:50:17.472"), laps.get(8).getTime());
		assertEquals(23, laps.get(8).getPilot().getId(), 0);
		assertEquals("M.WEBBER", laps.get(8).getPilot().getNome());
		assertEquals(2, laps.get(8).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.805")), laps.get(8).getDuration());
		assertEquals(42.941, laps.get(8).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:50:37.987"), laps.get(9).getTime());
		assertEquals(15, laps.get(9).getPilot().getId(), 0);
		assertEquals("F.ALONSO", laps.get(9).getPilot().getNome());
		assertEquals(2, laps.get(9).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:07.011")), laps.get(9).getDuration());
		assertEquals(41.528, laps.get(9).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:51:14.216"), laps.get(10).getTime());
		assertEquals(38, laps.get(10).getPilot().getId(), 0);
		assertEquals("F.MASSA", laps.get(10).getPilot().getNome());
		assertEquals(3, laps.get(10).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:02.769")), laps.get(10).getDuration());
		assertEquals(44.334, laps.get(10).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:51:18.576"), laps.get(11).getTime());
		assertEquals(33, laps.get(11).getPilot().getId(), 0);
		assertEquals("R.BARRICHELLO", laps.get(11).getPilot().getNome());
		assertEquals(3, laps.get(11).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:03.716")), laps.get(11).getDuration());
		assertEquals(43.675, laps.get(11).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:51:19.044"), laps.get(12).getTime());
		assertEquals(2, laps.get(12).getPilot().getId(), 0);
		assertEquals("K.RAIKKONEN", laps.get(12).getPilot().getNome());
		assertEquals(3, laps.get(12).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:03.987")), laps.get(12).getDuration());
		assertEquals(43.49, laps.get(12).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:51:21.759"), laps.get(13).getTime());
		assertEquals(23, laps.get(13).getPilot().getId(), 0);
		assertEquals("M.WEBBER", laps.get(13).getPilot().getNome());
		assertEquals(3, laps.get(13).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.287")), laps.get(13).getDuration());
		assertEquals(43.287, laps.get(13).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:51:46.691"), laps.get(14).getTime());
		assertEquals(15, laps.get(14).getPilot().getId(), 0);
		assertEquals("F.ALONSO", laps.get(14).getPilot().getNome());
		assertEquals(3, laps.get(14).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:08.704")), laps.get(14).getDuration());
		assertEquals(40.504, laps.get(14).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:52:01.796"), laps.get(15).getTime());
		assertEquals(11, laps.get(15).getPilot().getId(), 0);
		assertEquals("S.VETTEL", laps.get(15).getPilot().getNome());
		assertEquals(1, laps.get(15).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:03:31.315")), laps.get(15).getDuration());
		assertEquals(13.169, laps.get(15).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:52:17.003"), laps.get(16).getTime());
		assertEquals(38, laps.get(16).getPilot().getId(), 0);
		assertEquals("F.MASS", laps.get(16).getPilot().getNome());
		assertEquals(4, laps.get(16).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:02.787")), laps.get(16).getDuration());
		assertEquals(44.321, laps.get(16).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:52:22.586"), laps.get(17).getTime());
		assertEquals(33, laps.get(17).getPilot().getId(), 0);
		assertEquals("R.BARRICHELLO", laps.get(17).getPilot().getNome());
		assertEquals(4, laps.get(17).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.010")), laps.get(17).getDuration());
		assertEquals(43.474, laps.get(17).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:52:22.120"), laps.get(18).getTime());
		assertEquals(2, laps.get(18).getPilot().getId(), 0);
		assertEquals("K.RAIKKONEN", laps.get(18).getPilot().getNome());
		assertEquals(4, laps.get(18).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:03.076")), laps.get(18).getDuration());
		assertEquals(44.118, laps.get(18).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:52:25.975"), laps.get(19).getTime());
		assertEquals(23, laps.get(19).getPilot().getId(), 0);
		assertEquals("M.WEBBER", laps.get(19).getPilot().getNome());
		assertEquals(4, laps.get(19).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:04.216")), laps.get(19).getDuration());
		assertEquals(43.335, laps.get(19).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:53:06.741"), laps.get(20).getTime());
		assertEquals(15, laps.get(20).getPilot().getId(), 0);
		assertEquals("F.ALONSO", laps.get(20).getPilot().getNome());
		assertEquals(4, laps.get(20).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:20.050")), laps.get(20).getDuration());
		assertEquals(34.763, laps.get(20).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:53:39.660"), laps.get(21).getTime());
		assertEquals(11, laps.get(21).getPilot().getId(), 0);
		assertEquals("S.VETTEL", laps.get(21).getPilot().getNome());
		assertEquals(2, laps.get(21).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:37.864")), laps.get(21).getDuration());
		assertEquals(28.435, laps.get(21).getAverageSpeed(), 0);

		assertEquals(LocalTime.parse("23:54:57.757"), laps.get(22).getTime());
		assertEquals(11, laps.get(22).getPilot().getId(), 0);
		assertEquals("S.VETTEL", laps.get(22).getPilot().getNome());
		assertEquals(3, laps.get(22).getNumber(), 0);
		assertEquals(Duration.between(LocalTime.MIN, LocalTime.parse("00:01:18.097")), laps.get(22).getDuration());
		assertEquals(35.633, laps.get(22).getAverageSpeed(), 0);
	}

	@Test
	public void readFileChengeLinepositionsReal() {
		List<Lap> laps = this.fileLogUnix.getLapsFromFile("/home/renandpf/Documentos/desenvolvimento/projetos/code/race-time-api/src/test/resources/race2.log");
		assertEquals(23, laps.size());
	}
}
