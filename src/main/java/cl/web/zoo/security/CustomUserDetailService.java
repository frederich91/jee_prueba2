package cl.web.zoo.security;

import static org.springframework.security.core.userdetails.User.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import cl.web.zoo.DAO.impl.UsuarioDAO;
import cl.web.zoo.entity.Usuario;

@Service
public class CustomUserDetailService implements UserDetailsService {

  @Autowired
  private UsuarioDAO usuarioDAO;
  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
      Usuario usuario = usuarioDAO.login(username);
      
      UserBuilder builder = null;
      if (usuario != null) {
        builder = withUsername(username);
        builder.password(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        builder.roles("USER");
      } else {
        throw new UsernameNotFoundException("User not found.");
      }

      return builder.build();
      
      
  }
}
