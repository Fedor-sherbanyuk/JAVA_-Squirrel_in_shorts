package com.game.converter;

import com.game.entity.Player;
import com.game.model.PlayerDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerConverter {
    public PlayerDto convert(Player player){
        if(player ==null)
        {
            return null;
        }
        else
        {
            PlayerDto playerDto=new PlayerDto();
            playerDto.setId(player.getId());
            playerDto.setName(player.getName());
            playerDto.setTitle(player.getTitle());
            playerDto.setRace(player.getRace());
            playerDto.setProfession(player.getProfession());
            playerDto.setBirthday(player.getBirthday());
            playerDto.setBanned(player.getBanned());
            playerDto.setExperience(player.getExperience());
            playerDto.setLevel(player.getLevel());
            playerDto.setUntilNextLevel(player.getUntilNextLevel());
            return playerDto;
        }

    }
    public List<PlayerDto> convert(List<Player> playerList){
        if(playerList==null || playerList.isEmpty())
        {
            return Collections.emptyList();
        }
        else
        {
            return playerList.stream().map(this::convert).collect(Collectors.toList());
        }

    }
    public Player convert(PlayerDto player){
        if(player ==null)
        {
            return null;
        }
        else
        {
            Player playerDto=new Player();
            playerDto.setId(player.getId());
            playerDto.setName(player.getName());
            playerDto.setTitle(player.getTitle());
            playerDto.setRace(player.getRace());
            playerDto.setProfession(player.getProfession());
            playerDto.setBirthday(player.getBirthday());
            playerDto.setBanned(player.getBanned());
            playerDto.setExperience(player.getExperience());
            playerDto.setLevel(player.getLevel());
            playerDto.setUntilNextLevel(player.getUntilNextLevel());
            return playerDto;
        }

    }
    public Player convertUpdate(PlayerDto player){
        if(player ==null||(player.getName()==null&&player.getTitle()==null&&player.getRace() == null&&player.getProfession()==null
        &&player.getBirthday()==null&&player.getBanned()==null&&player.getExperience()==null))
        {
            return null;
        }
        else
        {
            Player playerDto=new Player();
            playerDto.setId(player.getId());
            playerDto.setName(player.getName());
            playerDto.setTitle(player.getTitle());
            playerDto.setRace(player.getRace());
            playerDto.setProfession(player.getProfession());
            playerDto.setBirthday(player.getBirthday());
            playerDto.setBanned(player.getBanned());
            playerDto.setExperience(player.getExperience());
            playerDto.setLevel(player.getLevel());
            playerDto.setUntilNextLevel(player.getUntilNextLevel());
            return playerDto;
        }

    }

}
