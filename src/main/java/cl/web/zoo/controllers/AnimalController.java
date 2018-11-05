package cl.web.zoo.controllers;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cl.web.zoo.DAO.impl.AnimalDAO;
import cl.web.zoo.DAO.impl.SectorDAO;
import cl.web.zoo.DAO.impl.TipoDAO;
import cl.web.zoo.entity.Animal;
import cl.web.zoo.entity.Sector;
import cl.web.zoo.entity.Tipo;

@Controller
@RequestMapping("/animal")
public class AnimalController {

  @Autowired
  private AnimalDAO animalDao;
  
  @Autowired
  private TipoDAO tipoDao;
  
  @Autowired
  private SectorDAO sectorDao;
  
  @ModelAttribute("tipos")
  public List<Tipo> listarTipos() {
      return (List<Tipo>) tipoDao.getCrud().findAll();
  }
  
  @ModelAttribute("sectores")
  public List<Sector> listarSectores() {    
      return (List<Sector>) sectorDao.getCrud().findAll();
  }
  
  @GetMapping("/listar")
  public String listAnimal(Model model) {
    model.addAttribute("animales", animalDao.getCrud().findAll());
    return "listar_animal.html";
  }
  
  
  @GetMapping("/agregar")
  public String agregarAnimal(Model model) {
    
    return "form_animal.html";
  }
  
  @GetMapping("/modificar")
  public String modificarAnimal(Model model, @RequestParam("id") int codigo) {
    Animal animal = animalDao.getCrud().findById(codigo).get();
    model.addAttribute("animal", animal);
    return "modificar_animal.html";
  }
  
  @PostMapping("/almacenar")
  public String guardarAnimal(Model model, RedirectAttributes ra,
      @RequestParam("txtNombre") String nombre,
      @RequestParam("txtFchIngreso") @DateTimeFormat(pattern="yyyy-MM-dd") Date fchIngreso,
      @RequestParam("txtFchNac") @DateTimeFormat(pattern="yyyy-MM-dd") Date fchNac,
      @RequestParam("txtFchMuerte") @DateTimeFormat(pattern="yyyy-MM-dd") Date fchMuerte,
      @RequestParam("txtPeso") float peso,
      @RequestParam("cboTipo") int tipoId,
      @RequestParam("cboSector") int sectorId,
      @RequestParam("cboGenero") String genero) {
    
    String mensaje = "Error al agregar";
    Tipo tipo = new Tipo();
    tipo.setId(tipoId);
    
    Sector sector = new Sector();
    sector.setId(sectorId);
    
    Animal animal = new Animal();
    animal.setNombre(nombre);

    animal.setFchIngreso(fchIngreso);
    animal.setFchMuerte(fchMuerte!= null? fchMuerte: null );
    animal.setTipo(tipo);
    animal.setGenero(genero);
    animal.setSector(sector); 
    animal.setPeso(peso);
    animal.setFchNac(fchNac != null? fchNac: null);
    
    if (animalDao.getCrud().save(animal) != null) {
      mensaje = "Agregado correctamente";
    }
    ra.addFlashAttribute("mensaje", mensaje);
    
    return "redirect:agregar";
  }
  
  @PostMapping("/editar")
  public String actualizar(Model model, RedirectAttributes ra,
      @RequestParam("txtCodigo") int codigo,
      @RequestParam("txtNombre") String nombre,
      @RequestParam("txtFchIngreso") @DateTimeFormat(pattern="yyyy-MM-dd") Date fchIngreso,
      @RequestParam("txtFchNac") @DateTimeFormat(pattern="yyyy-MM-dd") Date fchNac,
      @RequestParam("txtFchMuerte") @DateTimeFormat(pattern="yyyy-MM-dd") Date fchMuerte,
      @RequestParam("txtPeso") float peso,
      @RequestParam("cboTipo") int tipoId,
      @RequestParam("cboSector") int sectorId,
      @RequestParam("cboGenero") String genero) {
      
      String mensaje = "Error al modificar";
      
      Tipo tipo = new Tipo();
      tipo.setId(tipoId);
      
      Sector sector = new Sector();
      sector.setId(sectorId);
      
      Animal animal = animalDao.getCrud().findById(codigo).get();
      animal.setNombre(nombre);

      animal.setFchIngreso(fchIngreso);
      animal.setFchMuerte(fchMuerte!= null? fchMuerte: null );
      animal.setTipo(tipo);
      animal.setGenero(genero);
      animal.setSector(sector); 
      animal.setPeso(peso);
      animal.setFchNac(fchNac != null? fchNac: null);
      
      if (animalDao.getCrud().save(animal) != null) {
        mensaje = "Modificado correctamente";
      }
      ra.addFlashAttribute("mensaje", mensaje);
      

      return "redirect:listar";       
  }
  
  @GetMapping("/eliminar")
  public String eliminarAnimal(Model model, @RequestParam("id") int codigo) {
    String mensaje = "";
    try {
      animalDao.getCrud().deleteById(codigo);
      mensaje = "Eliminado correctamente";
    } catch (Exception e) {
      mensaje = "Eliminado correctamente";

    }
    model.addAttribute("mensaje", mensaje);
    return "redirect:listar";
  }
}
