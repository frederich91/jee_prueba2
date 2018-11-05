package cl.web.zoo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cl.web.zoo.DAO.impl.SectorDAO;
import cl.web.zoo.entity.Sector;


@Controller
@RequestMapping("/sector")
public class SectorController {

  @Autowired
  private SectorDAO sectorDao;
  
  @GetMapping("/listar")
  public String listarSector(Model model) {
    model.addAttribute("sectores", sectorDao.getCrud().findAll());
    return "listar_sector.html";
  }
  
  @GetMapping("/agregar")
  public String agregarSector(Model model) {
    
    return "form_sector.html";
  }
  
  @PostMapping("/almacenar")
  public String guardarSector(Model model, RedirectAttributes ra,
      @RequestParam("txtNombre") String nombre,
      @RequestParam("txtDesc") String desc) {
    
    String mensaje = "Error al agregar";
    Sector sector = new Sector();
    sector.setNombre(nombre);
    sector.setDesc(desc);
    
    if (sectorDao.getCrud().save(sector) != null) {
      mensaje = "Agregado correctamente";
    }
    ra.addFlashAttribute("mensaje", mensaje);

    return "redirect:agregar";
  }
  
  @PostMapping("/editar")
  public String editarSector(Model model, RedirectAttributes ra,
      @RequestParam("txtId") int id,
      @RequestParam("txtNombre") String nombre,
      @RequestParam("txtDesc") String desc) {
    
    String mensaje = "Error al modificar";
    Sector sector = new Sector();
    sector.setNombre(nombre);
    sector.setDesc(desc);
    
    if (sectorDao.getCrud().save(sector) != null) {
      mensaje = "Modificado correctamente";
    }
    ra.addFlashAttribute("mensaje", mensaje);

    return "redirect:listar";
  }
  
  @GetMapping("/modificar")
  public String modificarSector(Model model, @RequestParam("id") int sectorId) {
    Sector sector = sectorDao.getCrud().findById(sectorId).get();
    model.addAttribute("sector", sector);
    return "modificar_sector.html";
  }
  
  @GetMapping("/eliminar")
  public String eliminarSector(Model model, @RequestParam("id") int sectorId) {
    String mensaje = "";
    try {
      sectorDao.getCrud().deleteById(sectorId);
      mensaje = "Eliminado correctamente";
    } catch (Exception e) {
      mensaje = "Eliminado correctamente";

    }
    model.addAttribute("mensaje", mensaje);
    return "redirect:listar";
  }
}
