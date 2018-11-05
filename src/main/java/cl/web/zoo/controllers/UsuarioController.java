package cl.web.zoo.controllers;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cl.web.zoo.DAO.impl.UsuarioDAO;
import cl.web.zoo.entity.Usuario;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

  @Autowired
  private UsuarioDAO usuarioDao;
  
  @GetMapping("/listar")
  public String listarUsuario(Model model) {
    model.addAttribute("usuarios", usuarioDao.getCrud().findAll());
    return "listar_usuario.html";
  }
  
  
  @GetMapping("/agregar")
  public String agregarUsuario(Model model) {
    
    return "form_usuario.html";
  }
  
  @GetMapping("/modificar")
  public String modificarAnimal(Model model, @RequestParam("id") int id) {
    Usuario usuario = usuarioDao.getCrud().findById(id).get();
    model.addAttribute("usuario", usuario);
    return "modificar_usuario.html";
  }
  
  @PostMapping("/almacenar")
  public String guardarUsuario(Model model, RedirectAttributes ra,
      @RequestParam("txtNombre") String nombre,
      @RequestParam("txtUsername") String username,
      @RequestParam("txtPass") String pass) {
    
    String mensaje = "Error al agregar";
    Usuario usuario= new Usuario();
    usuario.setNombre(nombre);
    usuario.setPassword(pass);
    usuario.setUsername(username);
    
    if (usuarioDao.getCrud().save(usuario) != null) {
      mensaje = "Agregado correctamente";
    }
    ra.addFlashAttribute("mensaje", mensaje);
    
    return "redirect:agregar";
  }
  
  @PostMapping("/editar")
  public String editarUsuario(Model model, RedirectAttributes ra,
      @RequestParam("txtId") int id,
      @RequestParam("txtNombre") String nombre,
      @RequestParam("txtUsername") String username,
      @RequestParam("txtPass") String pass) {
    
    String mensaje = "Error al modificar";
    Usuario usuario= usuarioDao.getCrud().findById(id).get();
    usuario.setNombre(nombre);
    usuario.setPassword(pass);
    usuario.setUsername(username);
    
    if (usuarioDao.getCrud().save(usuario) != null) {
      mensaje = "Modificado correctamente";
    }
    ra.addFlashAttribute("mensaje", mensaje);
    
    return "redirect:listar";
  }
  
  @GetMapping("/eliminar")
  public String eliminarUsuario(Model model, @RequestParam("id") int id) {
    String mensaje = "";
    try {
      usuarioDao.getCrud().deleteById(id);
      mensaje = "Eliminado correctamente";
    } catch (Exception e) {
      mensaje = "Eliminado correctamente";

    }
    model.addAttribute("mensaje", mensaje);
    return "redirect:listar";
  }
}
