package com.javamaster.tictactoe.model;

import lombok.Data;

@Data
public class GamePlay {
    private XandO type;
    private Integer coordinateX;
    private Integer coordinateY;
    private String gameId;
}
