package com.challenge.olympicgames.service;

import com.challenge.olympicgames.exception.GameException;
import com.challenge.olympicgames.model.GameModel;
import com.challenge.olympicgames.model.enums.StageEnum;
import com.challenge.olympicgames.repository.GameRepository;
import com.mysema.commons.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class GameServiceTest {

    @Autowired
    private GameService service;

    @MockBean
    private GameRepository repository;

    @Test
    public void criarJogoSemErros(){
        GameModel gameModel = getGame();
        when(repository.save(gameModel)).thenReturn(gameModel);
        GameModel gameModel2 = service.createGame(gameModel);
        Assert.notNull(gameModel2, "game model não deve ser nulo");
        assertEquals(gameModel2, gameModel);
    }

    @Test
    public void encontrarJogosSemErros() {
        List<GameModel> games = new ArrayList<>();
        games.add(getGame());
        when(repository.findAll()).thenReturn(games);
        games = service.list();
        assertEquals(1, games.size());
    }

    @Test
    public void tentarCadastrarJogosComDatasSemIntervalo(){
        List<GameModel> games = new ArrayList<>();
        games.add(getGame());
        when(repository.findAll()).thenReturn(games);
        Exception exception = assertThrows(GameException.class, () -> {
            service.createGame(getGame());
        });
        assertTrue(exception.getMessage().contains("Já existe uma competição cadastrada neste horário, local e para esta modalidade."));
    }

    @Test
    public void tentarCadastrarJogosComDataInicialMaiorDoQueDataFinal(){
        List<GameModel> games = new ArrayList<>();
        games.add(getGame());
        when(repository.findAll()).thenReturn(games);
        Exception exception = assertThrows(GameException.class, () -> {
            GameModel gameModel = getGame();
            gameModel.setInitialDate(LocalDateTime.now().plusDays(1));
            service.createGame(gameModel);
        });
        assertTrue(exception.getMessage().contains("A data inicial não pode ser menor do que a data final"));
    }

    @Test
    public void tentarCadastrarJogosComMenosDe30MinutosDeIntervalo(){
        List<GameModel> games = new ArrayList<>();
        games.add(getGame());
        when(repository.findAll()).thenReturn(games);
        Exception exception = assertThrows(GameException.class, () -> {
            GameModel gameModel = getGame();
            gameModel.setFinalDate(LocalDateTime.now());
            service.createGame(gameModel);
        });
        assertTrue(exception.getMessage().contains("A competição deve ter duração de no mínimo 30 minutos."));
    }


    private GameModel getGame() {
        GameModel gameModel = new GameModel();
        gameModel.setCountryA("Brasil");
        gameModel.setCountryB("Argentina");
        gameModel.setFinalDate(LocalDateTime.now().plusMinutes(31));
        gameModel.setInitialDate(LocalDateTime.now());
        gameModel.setLocal("Espanha");
        gameModel.setStage(StageEnum.FINAL);
        gameModel.setModality("Futebol");
        return gameModel;
    }

}
