package com.game.controller;


import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.facad.PlayerFacade;
import com.game.model.PlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyContr {
@Autowired
    PlayerFacade playerFacade;
    @GetMapping("/rest/players")
    public List<PlayerDto> showAll(@RequestParam(required = false) String name, @RequestParam(required = false)String title,
                                   @RequestParam(required = false) Race race,
                                   @RequestParam(required = false)Profession profession,
                                   @RequestParam(required = false)Long after,
                                   @RequestParam(required = false)Long before,
                                   @RequestParam(required = false)Boolean banned,
                                   @RequestParam(required = false)Integer minExperience,
                                   @RequestParam(required = false)Integer maxExperience,
                                   @RequestParam(required = false)Integer minLevel,
                                   @RequestParam(required = false)Integer maxLevel,
                                   @RequestParam(required = false, defaultValue ="ID")PlayerOrder order,
                                   @RequestParam(required = false,defaultValue = "0")Integer pageNumber,
                                   @RequestParam(required = false,defaultValue = "3")Integer pageSize)
    {

      return playerFacade.findAll(name, title, race, profession,
               after, before, banned,  minExperience,
               maxExperience, minLevel, maxLevel, order,
               pageNumber, pageSize);

    }
    @GetMapping("/rest/players/count")
    public Integer getPlayersCount(@RequestParam(required = false) String name,
                                   @RequestParam(required = false)String title,
                                   @RequestParam(required = false) Race race,
                                   @RequestParam(required = false)Profession profession,
                                   @RequestParam(required = false)Long after,
                                   @RequestParam(required = false)Long before,
                                   @RequestParam(required = false)Boolean banned,
                                   @RequestParam(required = false)Integer minExperience,
                                   @RequestParam(required = false)Integer maxExperience,
                                   @RequestParam(required = false)Integer minLevel,
                                   @RequestParam(required = false)Integer maxLevel)
    {
        return playerFacade.getPlayersCount(name, title, race, profession,
                after, before, banned,  minExperience,
                maxExperience, minLevel, maxLevel);
    }
    @PostMapping("/rest/players")
  public PlayerDto createPlayer(
            @RequestBody PlayerDto playerDto) {
      return playerFacade.createPlayer(playerDto);



    }
        @GetMapping("/rest/players/{id}")
                public PlayerDto getPlayerById(@PathVariable Long id)
        {
            return playerFacade.getPlayerById(id);
        }
        @PostMapping("/rest/players/{id}")
    public PlayerDto updatePlayer(@PathVariable Long id,@RequestBody(required = false) PlayerDto playerDto)
        {
            return playerFacade.updatePlayer(id,playerDto);
        }

        @DeleteMapping("/rest/players/{id}")
    public void deletePlayer(@PathVariable(required = false) Long id){
        playerFacade.deletePlayer(id);
        }

}
