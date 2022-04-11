package net.artux.nextcrm.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableConfigurationProperties
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailService userDetailsService;

  private static final String[] ADMIN_LIST = {
          "/settings", "/**"
  };

  private static final String[] WHITE_LIST = {
          "/css/**", "/js/*", "/static/**"
  };

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf()
            .disable()
            .authorizeRequests()
            //Доступ только для не зарегистрированных пользователей
            .antMatchers("/registration").not().fullyAuthenticated()
            //Доступ только для пользователей с ролью Администратор


            .antMatchers(WHITE_LIST).permitAll()//Доступ разрешен всем пользователей
            .antMatchers("/orders").hasRole("orders")
            .antMatchers("/tasks").hasRole("tasks")
            .antMatchers("/clients").hasRole("clients")
            .antMatchers(ADMIN_LIST).hasRole("admin")
            //Все остальные страницы требуют аутентификации
            .anyRequest().authenticated()
            .and()
            //Настройка для входа в систему
            .formLogin()
            .loginPage("/login")
            //Перенарпавление на главную страницу после успешного входа
            .defaultSuccessUrl("/")
            .permitAll()
            .and()
            .logout()
            .permitAll()
            .logoutSuccessUrl("/login");
  }


  @Override
  public void configure(AuthenticationManagerBuilder builder)
          throws Exception {
    builder.userDetailsService(userDetailsService);
    builder.inMemoryAuthentication()
            .withUser("admin")
            .password(new BCryptPasswordEncoder().encode("admin")).roles("admin");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}