package cl.web.zoo.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import cl.web.zoo.DAO.IAnimal;
import cl.web.zoo.entity.Animal;

@Repository
@Transactional
public class AnimalDAO {

  @Autowired
  private IAnimal crud;

  public IAnimal getCrud() {
    return crud;
  }
  
  public Animal filterByTipo() {
    
    return null;
  }
}
