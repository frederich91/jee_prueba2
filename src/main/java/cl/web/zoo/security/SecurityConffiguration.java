package cl.web.zoo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConffiguration extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private CustomUserDetailService usuario;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  };
  
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(usuario).passwordEncoder(passwordEncoder());
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {

      http.csrf().disable();

      http.authorizeRequests()
//      .antMatchers("/java/**")
//      .authenticated()
      .antMatchers("/error/**")
      .permitAll()
      .antMatchers("/static/**")
      .permitAll()
      .antMatchers("/")
      .permitAll().and()
      .formLogin()
      .defaultSuccessUrl("/")
      .loginPage("/login")
      .permitAll()
      .and()
      .logout()
      .permitAll()
      .and()
      .logout()
      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
      .logoutSuccessUrl("/login");

  }
  
}
