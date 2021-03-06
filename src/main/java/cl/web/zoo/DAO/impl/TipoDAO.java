package cl.web.zoo.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import cl.web.zoo.DAO.ITipo;

@Repository
@Transactional
public class TipoDAO {

  @Autowired
  private ITipo crud;

  public ITipo getCrud() {
    return crud;
  }
}
