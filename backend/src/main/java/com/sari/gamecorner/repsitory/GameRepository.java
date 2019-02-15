package com.sari.gamecorner.repsitory;

import com.sari.gamecorner.model.Game;

import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;

@Transactional
public interface GameRepository extends JpaRepository<Game, Long> {
}
