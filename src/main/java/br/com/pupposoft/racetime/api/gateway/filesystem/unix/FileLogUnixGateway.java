package br.com.pupposoft.racetime.api.gateway.filesystem.unix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.pupposoft.racetime.api.gateway.filesystem.dto.LapDto;

public class FileLogUnixGateway {
	
	public List<LapDto> getLapsFromFile(final String fileName){
		final List<String> lines = this.fileStreamUsingFiles(fileName);
		return lines.stream().map(LapDto::new).collect(Collectors.toList());
	}
	
	
	private List<String> fileStreamUsingFiles(final String fileName) {
        try {
            return Files.lines(Paths.get(fileName)).collect(Collectors.toList());
            
            //lines.close(); //TODO analisar
        } catch(IOException io) {
            io.printStackTrace();
            //TODO - Tratar fallback
        }
        return new ArrayList<>();
    }
}
