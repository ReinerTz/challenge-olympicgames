package com.challenge.olympicgames.model.dto;

import com.challenge.olympicgames.model.enums.StageEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GameDto {

    private String modality;
    private String local;
    private LocalDateTime initialDate;
    private LocalDateTime finalDate;
    private String countryA;
    private String countryB;
    private StageEnum stage;
}
