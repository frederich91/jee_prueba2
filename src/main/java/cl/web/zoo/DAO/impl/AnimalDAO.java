package cl.web.zoo.DAO.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import cl.web.zoo.DAO.IAnimal;
import cl.web.zoo.entity.Animal;
import cl.web.zoo.entity.Tipo;

@Repository
@Transactional
public class AnimalDAO {

  @PersistenceContext
  EntityManager entityManager;

  @Autowired
  private IAnimal crud;

  public IAnimal getCrud() {
    return crud;
  }

  public List<Animal> filterByTipo(Tipo tipo) {

    String hql = "select a from Animal a where a.tipo= :tipo";
    List<Animal> animales = entityManager.createQuery(hql, Animal.class).setParameter("tipo", tipo).getResultList();
    return animales;
  }
  
  
  public List<Animal> filterBetweenDates(Date startDate, Date endDate ){
    String hql = "select a from Animal a where a.fchIngreso BETWEEN :startDate AND :endDate";
    
    return (List<Animal>)entityManager.createQuery(hql, Animal.class)  
      .setParameter("startDate", startDate, TemporalType.DATE)  
      .setParameter("endDate", endDate, TemporalType.DATE)  
      .getResultList();
  }
}
