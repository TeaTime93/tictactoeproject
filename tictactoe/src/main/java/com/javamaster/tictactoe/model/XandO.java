package com.javamaster.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum XandO {
    X(1), O(2);

    private Integer value;
}
