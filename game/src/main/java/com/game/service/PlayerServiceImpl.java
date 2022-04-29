package com.game.service;


import com.game.dao.PlayerDAO;
import com.game.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class PlayerServiceImpl implements PlayerService {
//    @Override
//    @Transactional
//    public void deletePlayer(int id) {
//        PlayerDAO.deletePlayer(id);
//    }
//
//    @Override
//    @Transactional
//    public Player getPlayer(int id) {
//        return PlayerDAO.getPlayer(id);
//    }

    @Autowired
    private PlayerDAO playerDAO;
    @Override
    @Transactional
    public List<Player> getAllPlayers() {
        return playerDAO.getAllPlayers();
    }
//    @Override
//    @Transactional
//    public void savePlayer(Player player) {
//PlayerDAO.savePlayer(player);
//    }

}
