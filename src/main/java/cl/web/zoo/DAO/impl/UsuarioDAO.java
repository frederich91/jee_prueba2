package cl.web.zoo.DAO.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import cl.web.zoo.DAO.IUsuario;
import cl.web.zoo.entity.Usuario;

@Repository
@Transactional
public class UsuarioDAO {
  
  @PersistenceContext
  EntityManager entityManager;
  
  @Autowired
  private IUsuario crud;

  public IUsuario getCrud() {
    return crud;
  }
  
  public cl.web.zoo.entity.Usuario login(String usuario) {
    
    String hql  = "select u from Usuario u where u.username = :usuario";
    
    return (Usuario)entityManager.createQuery(hql, Usuario.class)
            .setParameter("usuario", usuario).getSingleResult();
        
}
}
