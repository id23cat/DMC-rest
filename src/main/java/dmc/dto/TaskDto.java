package dmc.dto;

import dmc.enums.ArgumentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private long id;
    private String name;
    private ArgumentType type;
    private Object defaultType;
    private List<BlockDto> blockDtos;
}
