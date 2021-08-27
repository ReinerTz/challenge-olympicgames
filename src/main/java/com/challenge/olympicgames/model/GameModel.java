package com.challenge.olympicgames.model;

import com.challenge.olympicgames.model.enums.StageEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "game")
public class GameModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "modality", length = 40)
    private String modality;

    @Column(name = "local", length = 40)
    private String local;

    @Column(name = "initial_date")
    private LocalDateTime initialDate;

    @Column(name = "final_date")
    private LocalDateTime finalDate;

    @Column(name = "country_a", length = 40)
    private String countryA;

    @Column(name = "country_b", length = 40)
    private String countryB;

    @Enumerated(EnumType.STRING)
    private StageEnum stage;


}
