package cl.web.zoo.controllers;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cl.web.zoo.DAO.IAnimal;
import cl.web.zoo.DAO.ISector;
import cl.web.zoo.DAO.ITipo;

@Controller
@RequestMapping("/animal")
public class AnimalController {

  @Autowired
  private IAnimal animalDao;
  
  @Autowired
  private ITipo tipoDao;
  
  @Autowired
  private ISector sectorDao;
  
  @GetMapping("/listar")
  public String listAnimal(Model model, Principal principal) {
    model.addAttribute("animales", animalDao.findAll());
    model.addAttribute("usuario", principal);
    return "listar_animal.html";
  }
  
  @GetMapping("/agregar")
  public String agregarAnimal(Model model, Principal principal) {
    model.addAttribute("tipos", tipoDao.findAll());
    model.addAttribute("sectores", sectorDao.findAll());
    model.addAttribute("usuario", principal);
    
    return "form_animal.html";
  }
  
  @PostMapping("/modificar")
  public String modificarAnimal(Model model, Principal principal, @RequestParam("id") int codigo) {
    model.addAttribute("animal", animalDao.findById(codigo));
    return "form_animal.html";
  }
}
