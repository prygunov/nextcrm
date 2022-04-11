package net.artux.nextcrm.configuration;

import lombok.RequiredArgsConstructor;
import net.artux.nextcrm.model.role.RoleEntity;
import net.artux.nextcrm.model.user.UserEntity;
import net.artux.nextcrm.repository.settings.management.UsersRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

  private final UsersRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = usersRepository.findByLogin(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    if (userEntity.getApproved() == null || !userEntity.getApproved())
      throw new UsernameNotFoundException("Not approved");
    if (userEntity.getRole() == null)
      throw new UsernameNotFoundException("User does not have role.");

    RoleEntity role = userEntity.getRole();
    List<SimpleGrantedAuthority> authorities = new LinkedList<>();
    if (role.isClients())
      authorities.add(new SimpleGrantedAuthority("clients"));
    if (role.isOrders())
      authorities.add(new SimpleGrantedAuthority("orders"));
    if (role.isTasks())
      authorities.add(new SimpleGrantedAuthority("tasks"));
    return new User(userEntity.getLogin(), userEntity.getPassword(), authorities);
  }
}
