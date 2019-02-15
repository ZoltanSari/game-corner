package com.sari.gamecorner.repsitory;

import com.sari.gamecorner.model.Game;

import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findTop5ByOrderByRatingDesc();

    List<Game> findByNameContainingIgnoreCase(String substring);
}
