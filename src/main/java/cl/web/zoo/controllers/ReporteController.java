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
import cl.web.zoo.DAO.impl.AnimalDAO;
import cl.web.zoo.DAO.impl.TipoDAO;
import cl.web.zoo.entity.Animal;
import cl.web.zoo.entity.Tipo;

@Controller
@RequestMapping("/reporte")
public class ReporteController {
  
  @Autowired
  private AnimalDAO animalDao;
  
  @Autowired
  private TipoDAO tipoDao;

  
  @ModelAttribute("tipos")
  public List<Tipo> listarTipos() {
      return (List<Tipo>) tipoDao.getCrud().findAll();
  }
  
  @GetMapping("/tipo")
  public String reporteTipo(Model model) {

    return "reporte_tipo.html";
  }
  
  @PostMapping("/tipo")
  public String reportePorTipo(Model model,@RequestParam("cboTipo") int tipoId) {
    Tipo tipo = tipoDao.getCrud().findById(tipoId).get();
    List<Animal> lista = animalDao.filterByTipo(tipo);
    model.addAttribute("animales", lista);
    return "reporte_tipo.html";
  }
  
  @GetMapping("/fechas")
  public String reporteFechas(Model model) {

    return "reporte_fecha.html";
  }
  
  @PostMapping("/fechas")
  public String reportePorFechas(Model model,
      @RequestParam("txtFchInicio") @DateTimeFormat(pattern="yyyy-MM-dd") Date fchInicio,
      @RequestParam("txtFchFin") @DateTimeFormat(pattern="yyyy-MM-dd") Date fchFin) {
    List<Animal> lista = animalDao.filterBetweenDates(fchInicio, fchFin);
    model.addAttribute("animales", lista);
    return "reporte_fecha.html";
  }
  
}
