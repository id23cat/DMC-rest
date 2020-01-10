package dmc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlockDto {
    private long id;
    private List<ConnectionDto> connectionDtos;
    private List<ArgumentDto> argumentDtos;
}
