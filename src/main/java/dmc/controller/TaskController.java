package dmc.controller;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import DMCmodels.dto.TaskDto;
import dmc.jwt.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @RequestMapping(value = "/loadTask", method = POST)
    public ResponseEntity<?> loadTask(@RequestBody final TaskDto taskDto) throws Exception {

        return ok(null);
    }

}
