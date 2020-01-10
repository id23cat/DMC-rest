package dmc.service;

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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userManager.findByUsername(username);
        if (userManager.findByUsername(username) != null) {
            return userManager.findByUsername(username);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public void delete(UserDto dto) {
        userManager.delete(new UserModel(dto));
    }

    public boolean isUserExist(UserDto dto) {
        return userManager.findByUsername(dto.getUsername()) != null;
    }

    public UserModel saveOrUpdate(UserDto dto) {
        dto.setPassword(bcryptEncoder.encode(dto.getPassword()));
        return userManager.save(new UserModel(dto));
    }

}