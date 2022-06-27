package br.com.treinamentospring.aula;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.treinamentospring.aula.entities.Paciente;
import br.com.treinamentospring.aula.repositories.IPacienteRepository;
import br.com.treinamentospring.aula.service.ReportService;
import net.sf.jasperreports.engine.JRException;



@RestController
@SpringBootApplication
public class AulaApplication {
	@Autowired
	private IPacienteRepository repository;
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/getPacientes")
	public List<Paciente> getPaciente(){
		return repository.findAll();
	}
	
	@GetMapping("/report/{format}")
	public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException{
		return service.exportReport(format);
	}

	public static void main(String[] args) {
		SpringApplication.run(AulaApplication.class, args);
	}

}
