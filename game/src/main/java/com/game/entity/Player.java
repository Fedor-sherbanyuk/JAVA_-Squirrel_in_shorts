package com.game.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

@Entity
@Table(name = "player")
public class Player {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable = false,length = 12)
    public String name;
    @Column(nullable = false,length = 30)
    public String title;
    @Enumerated(EnumType.STRING)
    public Race race;
   @Enumerated(EnumType.STRING)
    public Profession profession;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-mm-dd")
    public Long birthday;

   public Player() {
   }

   @Column
    public Boolean banned;
    @Column(nullable = false,scale = 10000000)
    public Integer experience;
    @Column(nullable = false)
    public Integer level;
    @Column(nullable = false)
    public Integer untilNextLevel;
}
