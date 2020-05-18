package dmc.controller;


import static dmc.jwt.JWTConstants.AUTHORIZATION_HEADER;
import static dmc.jwt.JWTConstants.TOKEN_PREFIX;
import static java.util.Objects.isNull;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import dmc.controller.helper.ResponseHelper;
import dmc.dto.TaskDto;
import dmc.dto.UserContext;
import dmc.dto.UserDto;
import dmc.jwt.JwtRequest;
import dmc.jwt.JwtResponse;
import dmc.jwt.JwtTokenUtil;
import dmc.service.JwtUserDetailsService;
import dmc.service.UserContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final ResponseHelper responseHelper;
    private final UserContextService userContextService;

    @Autowired
    public UserController(final AuthenticationManager authenticationManager,
                          final JwtTokenUtil jwtTokenUtil,
                          final JwtUserDetailsService userDetailsService,
                          final ResponseHelper responseHelper,
                          final UserContextService userContextService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.responseHelper = responseHelper;
        this.userContextService = userContextService;
    }

    @RequestMapping(value = "/authenticate", method = POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody final JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = POST)
    public ResponseEntity<?> saveUser(@RequestBody final UserDto user) throws Exception {
        if (userDetailsService.isUserExist(user)) {
            return responseHelper.userAlreadyExist(user);
        }
        return ok(userDetailsService.saveOrUpdate(user));
    }

    @RequestMapping(value = "/deleteUser", method = POST)
    public void deleteUser(@RequestBody UserDto user) throws Exception {
        userDetailsService.delete(user);
    }

    @RequestMapping(value = "/getUserContext", method = GET)
    public UserContext getUserContext(final HttpServletRequest request) {
        final String token = request.getHeader(AUTHORIZATION_HEADER);
        return isNull(token)
                ? new UserContext()
                : userContextService.getUserContextByToken(token.substring(TOKEN_PREFIX.length()));
    }

    /*not implemented, temp endpoint for debug*/
    @RequestMapping(value = "/testJson", method = POST)
    public void testJson(@RequestBody TaskDto dto) throws Exception {
        System.out.println(dto);
    }

    @RequestMapping(value = "/update", method = POST)
    public ResponseEntity<?> updateUser(@RequestBody UserDto user) throws Exception {
        return ok(userDetailsService.saveOrUpdate(user));
    }

    private void authenticate(final String username, final String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}