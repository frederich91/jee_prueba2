package cl.web.zoo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

  @GetMapping("/")
  public String home() {
      return "home.html";
  }
  
  @GetMapping("/403")
  public String forbidden() {
      return "403.html";
  }
}
