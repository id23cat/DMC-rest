package dmc.service;

import DMCmodels.dto.TaskDto;
import dmc.modelsTemp.ResultDto;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    @Override
    public ResultDto sendDateToCore(TaskDto taskDto) {
        return null;
    }

    @Override
    public TaskDto getTask(String id) {
        return null;
    }

    @Override
    public List<String> getAllWorkers() {
        return null;
    }

    @Override
    public Boolean isWorkerAvailable(String workerId) {
        return null;
    }

    @Override
    public Integer getProggressByTask(String taskId) {
        return null;
    }

    @Override
    public List<TaskDto> getUsersTask(String userId) {
        return null;
    }
}
