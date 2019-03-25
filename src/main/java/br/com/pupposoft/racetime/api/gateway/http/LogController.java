package br.com.pupposoft.racetime.api.gateway.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.pupposoft.racetime.api.gateway.filesystem.RaceFileLogGateway;

@Scope("request")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value="log")
public class LogController {
	
	@Autowired
	private RaceFileLogGateway raceFileLogGateway;
	
    @PostMapping()
    public String handleFileUpload(@RequestParam("logFile") MultipartFile file,
            RedirectAttributes redirectAttributes) {

    	this.raceFileLogGateway.storeFile(file);
        return "OK!";
    }
	
}
