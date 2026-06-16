package net.royal.spring;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.web.rest.GenericoCheckRest;

@RestController
@RequestMapping("/check")
@CrossOrigin(origins = "*")
public class CheckRest extends GenericoCheckRest{

}
