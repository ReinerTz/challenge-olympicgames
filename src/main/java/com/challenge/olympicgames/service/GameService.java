package com.challenge.olympicgames.service;

import com.challenge.olympicgames.exception.GameException;
import com.challenge.olympicgames.mapper.GameMapper;
import com.challenge.olympicgames.model.GameModel;
import com.challenge.olympicgames.model.QGameModel;
import com.challenge.olympicgames.model.dto.GameDto;
import com.challenge.olympicgames.repository.GameRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GameService {

    @Autowired
    private GameRepository repository;

    public GameModel createGame(GameModel game) {
        if (game.getInitialDate().isAfter(game.getFinalDate())) {
            throw new GameException("A data inicial não pode ser menor do que a data final");
        }

        if ((game.getInitialDate().isAfter(game.getFinalDate().minusMinutes(30).minusSeconds(1)))) {
            throw new GameException("A competição deve ter duração de no mínimo 30 minutos.");
        }


        // Pode ser feito com a seguinte QueryDSL, mas como o intuito é um teste prático, será desenvolvido com uma o filtro da lista mesmo.
//        Iterable<GameModel> iterable = repository.findAll(QGameModel
//                .gameModel.initialDate.between(gameModel.getInitialDate(), gameModel.getFinalDate())
//                .or(QGameModel.
//                        gameModel.finalDate.between(gameModel.getInitialDate(), gameModel.getFinalDate()))
//                .and(QGameModel.gameModel.local.eq(gameModel.getLocal()))
//                .and(QGameModel.gameModel.modality.eq(gameModel.getModality())));
//
        List<GameModel> games = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList()).stream()
                .filter(gameModel ->
                        (gameModel.getInitialDate().isBefore(game.getInitialDate().plusSeconds(1))
                                && gameModel.getFinalDate().isAfter(game.getInitialDate().plusSeconds(1))
                                || ((gameModel.getInitialDate().isBefore(game.getFinalDate().minusSeconds(1))
                                && gameModel.getFinalDate().isAfter(game.getFinalDate().minusSeconds(1)))))
                                && gameModel.getLocal().equals(game.getLocal())
                                && gameModel.getModality().equals(game.getModality())).collect(Collectors.toList());



        if (games.isEmpty()) {
            return repository.save(game);
        } else {
            throw new GameException("Já existe uma competição cadastrada neste horário, local e para esta modalidade.");
        }

    }

    public List<GameModel> list() {
        return repository.findAll();
    }
}
