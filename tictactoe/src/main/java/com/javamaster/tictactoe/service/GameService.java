package com.javamaster.tictactoe.service;

import com.javamaster.tictactoe.exception.GameNotFoundException;
import com.javamaster.tictactoe.exception.InvalidGameException;
import com.javamaster.tictactoe.exception.InvalidParameterException;
import com.javamaster.tictactoe.model.Game;
import com.javamaster.tictactoe.model.GamePlay;
import com.javamaster.tictactoe.model.Player;
import com.javamaster.tictactoe.model.XandO;
import com.javamaster.tictactoe.storage.GameStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.javamaster.tictactoe.model.GameStatus.*;

@Service
@AllArgsConstructor
public class GameService {
    public Game createGame(Player player) {
        Game game = new Game();
        game.setBoard(new int[3][3]);
        game.setGameId(UUID.randomUUID().toString());
        game.setPlayer1(player);
        game.setStatus(NEW);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game connectToGame(Player player2, String gameId)
            throws InvalidParameterException, InvalidGameException {
        if (!GameStorage.getInstance().getGames().containsKey(gameId)) {
            throw new InvalidParameterException("Game with provided ID does not exist!");
        }
        Game game = GameStorage.getInstance().getGames().get(gameId);
        if (game.getPlayer2() != null) {
            throw new InvalidGameException("Game with provided ID already has a player2!");
        }
        game.setPlayer2(player2);
        game.setStatus(IN_PROGRESS);
        return game;
    }

    public Game connectToRandomGame(Player player2)
            throws GameNotFoundException {
        Game game = GameStorage.getInstance().getGames().values()
                .stream()
                .filter(it -> it.getStatus().equals(NEW))
                .findFirst().orElseThrow(() -> new GameNotFoundException("No games available"));
        game.setPlayer2(player2);
        game.setStatus(IN_PROGRESS);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game gamePlay(GamePlay gamePlay)
            throws GameNotFoundException,
            InvalidGameException {
        if (!GameStorage.getInstance().getGames().containsKey(gamePlay.getGameId())) {
            throw new GameNotFoundException("Game not found!");
        }
        Game game = GameStorage.getInstance().getGames().get(gamePlay.getGameId());
        if (game.getStatus().equals(FINISHED)) {
            throw new InvalidGameException("Game already finished!");
        }
        int[][] board = game.getBoard();
        board[gamePlay.getCoordinateX()][gamePlay.getCoordinateY()] = gamePlay.getType().getValue();


        Boolean xWinner = checkWinner(game.getBoard(), XandO.X);
        Boolean oWinner = checkWinner(game.getBoard(), XandO.O);

        if(xWinner){
            game.setWinner(XandO.X);
        }else if (oWinner) {
            game.setWinner(XandO.O);
        }

        GameStorage.getInstance().setGame(game);
        return game;
    }
    public Boolean checkWinner(int[][] board, XandO type){
        int[] boardArray = new int[9];
        int counterIndex = 0;
        for(int i = 0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                boardArray[counterIndex] = board[i][j];
                counterIndex++;
            }
        }
        int[][] winCombinations = {{0,1,2}, {3,4,5}, {6,7,8},
                {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
        for(int i=0; i<winCombinations.length; i++) {
            int counter = 0;
            for (int j = 0; j < winCombinations[i].length; j++) {
                if (boardArray[winCombinations[i][j]] == type.getValue()){
                    counter++;
                    if(counter==3){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
