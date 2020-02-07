package dmc.controller;


import static org.springframework.http.ResponseEntity.ok;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserDto user) throws Exception {
        if (userDetailsService.isUserExist(user)) {
            return responseHelper.userAlreadyExist(user);
        }
        return ok(userDetailsService.saveOrUpdate(user));
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public void deleteUser(@RequestBody UserDto user) throws Exception {
        userDetailsService.delete(user);
    }

    @RequestMapping(value = "/getUserContext", method = RequestMethod.GET)
    public UserContext getUserContext(@RequestParam(value = "token") final String token) {
        return userContextService.getUserContextByToken(token);
    }

    /*not implemented, temp endpoint for debug*/
    @RequestMapping(value = "/testJson", method = RequestMethod.POST)
    public void testJson(@RequestBody TaskDto dto) throws Exception {
        System.out.println(dto);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody UserDto user) throws Exception {
        return ok(userDetailsService.saveOrUpdate(user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}