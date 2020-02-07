package dmc.service;

import static java.util.Objects.isNull;

import dmc.dto.UserDto;
import dmc.model.UserModel;
import dmc.repo.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserManager userManager;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final UserDetails details = userManager.findByUsername(username);
        if (!isNull(details)) {
            return userManager.findByUsername(username);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public void delete(UserDto dto) {
        userManager.deleteById(dto.getId());
    }

    public boolean isUserExist(UserDto dto) {
        return userManager.findByUsername(dto.getUsername()) != null;
    }

    public UserModel saveOrUpdate(UserDto dto) {
        dto.setPassword(bcryptEncoder.encode(dto.getPassword()));
        return userManager.save(new UserModel(dto));
    }

}