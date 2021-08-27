package com.challenge.olympicgames.repository;

import com.challenge.olympicgames.model.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GameRepository extends JpaRepository<GameModel, Long>, QuerydslPredicateExecutor<GameModel> {}
