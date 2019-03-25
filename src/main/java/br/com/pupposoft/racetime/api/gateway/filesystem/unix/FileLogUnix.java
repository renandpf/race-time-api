package br.com.pupposoft.racetime.api.gateway.filesystem.unix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.pupposoft.racetime.api.gateway.filesystem.RaceFileLogGateway;
import br.com.pupposoft.racetime.api.gateway.filesystem.dto.LapDto;

@Service
public class FileLogUnix implements RaceFileLogGateway {
	private final Path fileStorageLocation = Paths.get("./racelogs").toAbsolutePath().normalize();
	
	public FileLogUnix() {
		try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
	}
	
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
	
	public String storeFile(final MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
            	throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
        	throw new RuntimeException(ex);
        }
    }
}
