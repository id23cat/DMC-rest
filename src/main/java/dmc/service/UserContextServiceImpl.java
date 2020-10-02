package dmc.service;

import DMCmodels.dto.UserContext;
import dmc.jwt.JwtTokenUtil;
import DMCmodels.model.UserModel;
import dmc.repo.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserContextServiceImpl implements UserContextService {
    private final UserManager userManager;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public UserContextServiceImpl(final UserManager userManager,
                                  final JwtTokenUtil jwtTokenUtil) {
        this.userManager = userManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public UserContext getUserContextByToken(final String token) {
        final UserModel user = userManager.findByUsername(jwtTokenUtil.getUsernameFromToken(token));
        return new UserContext(user.getId(), user.getUsername(), token);
    }
}
