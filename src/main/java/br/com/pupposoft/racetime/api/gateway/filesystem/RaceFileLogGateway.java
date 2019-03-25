package br.com.pupposoft.racetime.api.gateway.filesystem;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.pupposoft.racetime.api.gateway.filesystem.dto.LapDto;

public interface RaceFileLogGateway {
	public List<LapDto> getLapsFromFile(final String fileName);
	public String storeFile(final MultipartFile file);
}
