package com.challenge.olympicgames.mapper;

import com.challenge.olympicgames.model.GameModel;
import com.challenge.olympicgames.model.dto.GameDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface GameMapper {
    GameModel toGameModel(GameDto gameDto);
}
