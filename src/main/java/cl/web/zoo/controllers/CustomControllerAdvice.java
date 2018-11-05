package cl.web.zoo.controllers;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class CustomControllerAdvice {
  
  
  @ModelAttribute("username")
  public String messages(Principal principal) {   
      if(principal!=null) {
          return principal.getName();    
      }
      return "";
  }
  
  
  @ModelAttribute("title")
  public String getTitle() {
    return "Zoo Concepcion";
  }
}
