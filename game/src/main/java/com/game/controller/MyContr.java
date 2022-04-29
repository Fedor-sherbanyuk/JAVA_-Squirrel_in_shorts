package com.game.controller;

import com.game.entity.Player;
import com.game.service.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyContr {
@Autowired
    PlayerServiceImpl playerService;
    @GetMapping("/rest/players")
    public List<Player> showAll()
    {
  return playerService.getAllPlayers();
    }
}
