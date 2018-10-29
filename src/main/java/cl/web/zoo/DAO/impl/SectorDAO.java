package cl.web.zoo.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import cl.web.zoo.DAO.ISector;

@Repository
@Transactional
public class SectorDAO {
  
  @Autowired
  private ISector crud;

  public ISector getCrud() {
    return crud;
  }
  
}
