package br.com.treinamentospring.aula.service;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import br.com.treinamentospring.aula.entities.Paciente;
import br.com.treinamentospring.aula.repositories.IPacienteRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportService {

	@Autowired
	private IPacienteRepository repository;

	public String exportReport(String reportFormat) throws FileNotFoundException, JRException {

		String path = "C:\\testjasper";

		// recebe empregados do banco
		List<Paciente> pacientes = repository.findAll();

		File file = ResourceUtils.getFile("classpath:pacientes.jrxml");

		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(pacientes);

		Map<String, Object> parameters = new HashMap<>();
		parameters.put("Criado por", "Treinamento CastGroup");

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);

		if (reportFormat.equalsIgnoreCase("html")) {

			JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\pacientes.html");

		}

		if (reportFormat.equalsIgnoreCase("pdf")) {

			JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\pacientes.pdf");

		}

		return "report gererado no caminho : " + path;

	}

}
