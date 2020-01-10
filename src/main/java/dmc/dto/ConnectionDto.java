package dmc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionDto {
    private long id;
    private List<FromDto> fromDtos;
    private List<ToDto> toDtos;
}
