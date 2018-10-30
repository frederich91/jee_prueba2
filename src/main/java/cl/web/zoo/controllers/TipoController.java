package cl.web.zoo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import cl.web.zoo.DAO.ITipo;

@Controller
@RequestMapping("/tipo")
public class TipoController {
  
  @Autowired
  private ITipo tipoDao;
  
  
}
