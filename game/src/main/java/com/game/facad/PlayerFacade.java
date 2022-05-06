package com.game.facad;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.model.PlayerDto;

import java.util.List;

public interface PlayerFacade {
    List<PlayerDto> findAll(String name, String title, Race race,
                            Profession profession, Long after, Long before,
                            Boolean banned, Integer minExperience, Integer maxExperience,
                            Integer minLevel, Integer maxLevel,
                            PlayerOrder order, Integer pageNumber, Integer pageSize);
    Integer getPlayersCount(String name, String title, Race race, Profession profession,
                            Long after, Long before, Boolean banned, Integer minExperience,
                            Integer maxExperience, Integer minLevel, Integer maxLevel);

    PlayerDto createPlayer(PlayerDto playerDto);

    PlayerDto getPlayerById(Long id);



    PlayerDto updatePlayer(Long id,PlayerDto playerDto);

    void deletePlayer(Long id);
}
