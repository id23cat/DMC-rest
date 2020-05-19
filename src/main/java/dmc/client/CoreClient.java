package dmc.client;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("core")
public interface CoreClient {

    @RequestMapping(value = "/test",
            method = GET)
    String getString();
}
