package net.artux.nextcrm.configuration;

import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.UserEntity;
import net.artux.nextcrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

  @Autowired
  private final UserRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = usersRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    if (userEntity.getApproved() == null || !userEntity.getApproved())
      throw new UsernameNotFoundException("Not approved");
    if (userEntity.getRole() == null)
      throw new UsernameNotFoundException("User does not have role.");
    List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRole().getName()));

    return new User(userEntity.getLogin(), userEntity.getPassword(), authorities);
  }
}
