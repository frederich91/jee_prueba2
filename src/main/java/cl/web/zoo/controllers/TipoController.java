package cl.web.zoo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cl.web.zoo.DAO.impl.TipoDAO;
import cl.web.zoo.entity.Tipo;

@Controller
@RequestMapping("/tipo")
public class TipoController {
  
  @Autowired
  private TipoDAO tipoDao;
  
  @GetMapping("/listar")
  public String listarTipos(Model model) {
    model.addAttribute("tipos", tipoDao.getCrud().findAll());
    return "listar_tipo.html";
  }
  
  @GetMapping("/agregar")
  public String agregarTipos(Model model) {
    
    return "form_tipo.html";
  }
  
  @PostMapping("/almacenar")
  public String guardarTipo(Model model, RedirectAttributes ra,
      @RequestParam("txtNombre") String nombre,
      @RequestParam("txtDesc") String descripcion) {
    
    String mensaje = "Error al agregar";
    Tipo tipo = new Tipo();
    tipo.setNombre(nombre);
    tipo.setDesc(descripcion);
    
    if (tipoDao.getCrud().save(tipo) != null) {
      mensaje = "Agregado correctamente";
    }
    ra.addFlashAttribute("mensaje", mensaje);

    return "redirect:agregar";
  }
  
  @GetMapping("/modificar")
  public String modificarTipos(Model model, @RequestParam("id") int tipoId) {
    Tipo tipo = tipoDao.getCrud().findById(tipoId).get();
    model.addAttribute("tipo", tipo);
    return "modificar_tipo.html";
  }
  
  @PostMapping("/editar")
  public String editarTipo(Model model, RedirectAttributes ra,
      @RequestParam("txtId") int id,
      @RequestParam("txtNombre") String nombre,
      @RequestParam("txtDesc") String desc) {
    
    String mensaje = "Error al modificar";
    Tipo tipo = tipoDao.getCrud().findById(id).get();
    tipo.setNombre(nombre);
    tipo.setDesc(desc);
    
    if (tipoDao.getCrud().save(tipo) != null) {
      mensaje = "Modificado correctamente";
    }
    ra.addFlashAttribute("mensaje", mensaje);

    return "redirect:listar";
  }
  
  @GetMapping("/eliminar")
  public String eliminarTipo(Model model, @RequestParam("id") int tipoId) {
    String mensaje = "";
    try {
      tipoDao.getCrud().deleteById(tipoId);
      mensaje = "Eliminado correctamente";
    } catch (Exception e) {
      mensaje = "Eliminado correctamente";

    }
    model.addAttribute("mensaje", mensaje);
    return "redirect:listar";
  }
}
