package net.royal.spring.alertas.boot;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.alertas.dao.impl.ReglaNegocioDaoImpl;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoBootRest;

@RestController
@RequestMapping("/spring/publico/ma")
@CrossOrigin(origins = "*")
public class SpringAlertasAplicacionRest extends GenericoBootRest{
	
	private static Logger logger = LogManager.getLogger(SpringAlertasAplicacionRest.class);
	
	@Autowired
	private ReglaNegocioDaoImpl whItemmastDao;
	
	@GetMapping(value = "/estavivo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> estavivo() {
		System.out.println("alertas : esta vivo");
		logger.debug("alertas : esta vivo");
		Date now = new Date();
		return new ResponseEntity<String>("MA - " + now.toString(), HttpStatus.OK);
	}
	
	@Transactional
	@GetMapping(value = "/estavivobd", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> estavivobd() {
		String now="";
		try {
			List lst = whItemmastDao.listarPorQuery(DtoTabla.class, "reglanegocio.estavivobd");
			if (lst.size()==1) {
				DtoTabla d=(DtoTabla)lst.get(0);
				now=d.getNombre();
			}				
		} catch (Exception e) {
			now = e.getMessage();
		}
		return new ResponseEntity<String>("MA(BD) - " + now, HttpStatus.OK);
	}
}
