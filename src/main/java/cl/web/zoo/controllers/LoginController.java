package cl.web.zoo.controllers;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String getLogin(Model model, Principal principal,
      @RequestParam(value = "error", required = false) String error) {

    
      return "login.html";
  }
  
  @PostMapping("/login")
  public String login(Model model, Principal principal,
      @RequestParam(value = "error", required = false) String error) {

    if (error != null) {
      model.addAttribute("error", "Usuario/contraseña invalidos");
    }
    
      return principal!= null? "redirect:animal/listar":"redirect:home";
  }
}
