package dmc.service;

import DMCmodels.dto.TaskDto;
import dmc.modelsTemp.ResultDto;

import java.util.List;

public interface TaskService {
    ResultDto sendDateToCore(TaskDto taskDto);
    TaskDto getTask(String id);
    List<String> getAllWorkers();
    Boolean isWorkerAvailable(String workerId);
    Integer getProggressByTask(String taskId);
    List<TaskDto> getUsersTask(String userId);
}
