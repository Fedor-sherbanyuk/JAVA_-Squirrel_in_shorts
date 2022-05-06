package com.game.facad;

import com.game.controller.PlayerOrder;
import com.game.converter.PlayerConverter;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.model.PlayerDto;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerFacadeImpl implements PlayerFacade {
    @Autowired
    private PlayerService playerService;
    @Autowired
    PlayerConverter playerConverter;

    @Override
    public List<PlayerDto> findAll(String name, String title, Race race, Profession profession,
                                   Long after, Long before, Boolean banned, Integer minExperience,
                                   Integer maxExperience, Integer minLevel, Integer maxLevel, PlayerOrder order,
                                   Integer pageNumber, Integer pageSize) {

        return playerConverter.convert(playerService.getAllPlayers(name, title, race, profession,
                after, before, banned, minExperience,
                maxExperience, minLevel, maxLevel, order,
                pageNumber, pageSize));
    }

    @Override
    public Integer getPlayersCount(String name, String title, Race race, Profession profession,
                                   Long after, Long before, Boolean banned, Integer minExperience,
                                   Integer maxExperience, Integer minLevel, Integer maxLevel) {
        return playerService.getPlayersCount(name, title, race, profession,
                after, before, banned, minExperience,
                maxExperience, minLevel, maxLevel);
    }

    @Override
    public PlayerDto createPlayer(PlayerDto player) {
       Player newPlayer= playerConverter.convert(player);
       return playerConverter.convert(playerService.createPlayer(newPlayer));
    }

    @Override
    public PlayerDto getPlayerById(Long id) {

        return playerConverter.convert(playerService.getPlayerById(id));
    }




    @Override
    public PlayerDto updatePlayer(Long id, PlayerDto playerDto) {
        Player newPlayer= playerConverter.convertUpdate(playerDto);
        return playerConverter.convert(playerService.updatePlayer(id,newPlayer));
    }

    @Override
    public void deletePlayer(Long id) {
       playerService.deletePlayer(id);
    }
}
