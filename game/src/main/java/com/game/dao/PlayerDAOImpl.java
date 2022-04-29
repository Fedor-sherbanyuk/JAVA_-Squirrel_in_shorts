package com.game.dao;


import com.game.entity.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public class PlayerDAOImpl implements PlayerDAO {
//    @Override
//    @Transactional
//    public void deletePlayer(int id) {
//        Session session=sessionFactory.getCurrentSession();
//        Query<Player> query=session.createQuery("delete from Player "+"where  id=:PlayerId");
//        query.setParameter("PlayerId",id);
//        query.executeUpdate();
//    }
//
//    @Override
//    @Transactional
//    public Player getPlayer(int id) {
//        Session session=sessionFactory.getCurrentSession();
//        Player Player=session.get(Player.class,id);
//        return Player;
//    }

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    @Transactional
    public List<Player> getAllPlayers() {

        Session session=sessionFactory.getCurrentSession();
        Query<Player> query=session.createQuery("from Player",Player.class);
        List<Player> allPlayers=query.getResultList();
        return allPlayers;
    }
//    @Override
//    @Transactional
//    public void savePlayer(Player Player) {
//        Session session=sessionFactory.getCurrentSession();
//
//        session.saveOrUpdate(Player);
//    }
}
