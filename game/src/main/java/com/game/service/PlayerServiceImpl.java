package com.game.service;


import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exceptions.BadRequest;
import com.game.exceptions.NotFoundException;
import com.game.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Transactional
    @Override
    public List<Player> getAllPlayers(String name, String title, Race race,
                                      Profession profession, Long after, Long before, Boolean banned, Integer minExperience,
                                      Integer maxExperience, Integer minLevel, Integer maxLevel,
                                      PlayerOrder order, Integer pageNumber, Integer pageSize) {
        Specification<Player> specification = (root, query, criteriaBuilder) -> {
            Predicate predicate = null;
            if (name != null) {
                predicate = criteriaBuilder.like(root.get("name"), criteriaBuilder.literal("%" + name + "%"));
            }
            if (title != null) {
                predicate = criteriaBuilder.like(root.get("title"), criteriaBuilder.literal("%" + title + "%"));
            }
            if (race != null) {
                predicate = criteriaBuilder.equal(root.get("race"), criteriaBuilder.literal(race));
            }
            if (profession != null) {
                predicate = criteriaBuilder.equal(root.get("profession"), criteriaBuilder.literal(profession));
            }
            if (after != null) {
                predicate =criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), new Date(after)));
            }
            if (before != null) {
                predicate = criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), new Date(before)));
            }


            if (minExperience != null) {
                predicate = criteriaBuilder.and(criteriaBuilder.le(root.get("experience"), minExperience));
            }
            if (maxExperience != null) {
                predicate = criteriaBuilder.and(criteriaBuilder.le(root.get("experience"), maxExperience));
            }

            if (minLevel != null) {
                predicate = criteriaBuilder.and(criteriaBuilder.le(root.get("level"), minLevel));
            }
            if (maxLevel != null) {
                predicate = criteriaBuilder.and(criteriaBuilder.le(root.get("level"), maxLevel));
            }

            return predicate;
        };

        return playerRepository.findAll(specification,
                PageRequest.of(pageNumber == null ? 0 : pageNumber,
                        pageSize == null ? 3 : pageSize, Sort.by(order == null ? PlayerOrder.ID.getFieldName() : order.getFieldName()))).getContent();
    }

    @Transactional
    @Override
    public Integer getPlayersCount(String name, String title, Race race, Profession profession,
                                   Long after, Long before, Boolean banned, Integer minExperience,
                                   Integer maxExperience, Integer minLevel, Integer maxLevel) {
        Specification<Player> specification = (root, query, criteriaBuilder) -> {
            Predicate predicate = null;
            if (name != null) {
                predicate = criteriaBuilder.like(root.get("name"), criteriaBuilder.literal("%" + name + "%"));
            }
            if (title != null) {
                predicate = criteriaBuilder.like(root.get("title"), criteriaBuilder.literal("%" + title + "%"));
            }
            if (race != null) {
                predicate = criteriaBuilder.equal(root.get("race"), criteriaBuilder.literal(race));
            }
            if (profession != null) {
                predicate = criteriaBuilder.equal(root.get("profession"), criteriaBuilder.literal(profession));
            }

            if (after != null) {
                predicate = criteriaBuilder.lessThanOrEqualTo(root.get("birthday"), new Date(after));
            }
            if (before != null) {
                predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"), new Date(before));
            }

            if (minExperience != null) {
                predicate = criteriaBuilder.le(root.get("experience"), minExperience);
            }
            if (maxExperience != null) {
                predicate = criteriaBuilder.le(root.get("experience"), maxExperience);
            }

            if (minLevel != null) {
                predicate = criteriaBuilder.le(root.get("level"), minLevel);
            }
            if (maxLevel != null) {
                predicate = criteriaBuilder.le(root.get("level"), maxLevel);
            }

            return predicate;
        };

        return (int) playerRepository.count(specification);

    }

    @Transactional
    @Override
    public Player createPlayer(Player player) {

        Date date = player.getBirthday();
        player.setBirthday(date);

        if (player.getName() == null || player.getName().equals("") || player.getName().length() > 12) {
            throw new BadRequest();
        }
        if (player.getTitle() == null || player.getTitle().equals("") || player.getTitle().length() > 30) {
            throw new BadRequest();
        }

        if (player.getRace() == null) {
            throw new BadRequest();
        }
        if (player.getProfession() == null) {
            throw new BadRequest();
        }
        if (player.getBirthday() == null) {
            throw new BadRequest();
        }
        if (player.getExperience() < 0) {
            throw new BadRequest();
        }
        if (player.getExperience() > 10000000) {
            throw new BadRequest();
        }
        if (player.getExperience() == null) {
            throw new BadRequest();
        }
        int time = player.getBirthday().getYear();
        if (time > 1100 || time < 100 ||
                time < 0) {
            throw new BadRequest();
        }

        player.calculateExp();
        Player player1 = playerRepository.save(player);
        return player1;

    }

    @Transactional
    @Override
    public Player getPlayerById(Long id) {

        if (id == null || id % 1 != 0 || id <= 0) {
            throw new BadRequest();
        }
        return playerRepository.findById(id).orElseThrow(() -> new NotFoundException());
    }


    @Transactional
    @Override
    public Player updatePlayer(Long id, Player player) {

        Player playerUpdate = getPlayerById(id);
        if(player==null){
            return playerUpdate;
        }
         if(player.getId()!=null&&!player.getId().equals(playerUpdate.getId())){
             return playerUpdate;
         }



        if (player.getExperience() !=null&&player.getExperience()< 0) {
            throw new BadRequest();
        }
        if (player.getExperience() !=null&&player.getExperience() > 10000000) {
            throw new BadRequest();
        }
        if(player.getBirthday()!=null)
        {
            int time = player.getBirthday().getYear();
            if (time > 1100 || time < 100 ||
                    time < 0) {
                throw new BadRequest();
            }
        }
        if (player.getName() != null)
            playerUpdate.setName(player.getName());
        if (player.getTitle() != null)
            playerUpdate.setTitle(player.getTitle());
        if (player.getRace() != null)
            playerUpdate.setRace(player.getRace());
        if (player.getProfession() != null)
            playerUpdate.setProfession(player.getProfession());
        if (player.getBirthday() != null)
            playerUpdate.setBirthday(player.getBirthday());
        if (player.getExperience() != null)
            playerUpdate.setExperience(player.getExperience());
        if(player.getLevel()!=null){
//            playerUpdate.setLevel(player.getLevel());
        }
        else
        {
            playerUpdate.calculateExp();
        }

        Player player1 = playerRepository.save(playerUpdate);
        return player1;

    }

    @Transactional
    @Override
    public void deletePlayer(Long id) {
        Player player = getPlayerById(id);
        playerRepository.delete(player);

    }
}
