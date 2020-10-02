package dmc.client;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import DMCmodels.dto.TaskDto;
import dmc.modelsTemp.ResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("core")
public interface CoreClient {

    @RequestMapping(value = "/execute", method = POST)
    ResultDto sendDateToCore(TaskDto taskDto);

    @RequestMapping(value = "/getTask", method = POST)
    TaskDto getTask(String id);

    @RequestMapping(value = "/getAllWorkers", method = POST)
    List<String> getAllWorkers();

    @RequestMapping(value = "/isWorkerAvailable", method = GET)
    Boolean isWorkerAvailable(String workerId);

    @RequestMapping(value = "/getProggressByTask", method = GET)
    Integer getProggressByTask(String taskId);

    @RequestMapping(value = "/getUsersTask", method = GET)
    List<TaskDto> getUsersTask(String userId);
}
