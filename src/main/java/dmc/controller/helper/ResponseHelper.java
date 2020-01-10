package dmc.controller.helper;

import static org.springframework.http.ResponseEntity.unprocessableEntity;

import com.google.gson.JsonObject;
import dmc.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ResponseHelper implements Serializable {

    public ResponseEntity userAlreadyExist(UserDto dto) {
        JsonObject response = new JsonObject();
        response.addProperty("error", "User with username: " + dto.getUsername() + " already exist");
        return unprocessableEntity().body(response.toString());
    }
}