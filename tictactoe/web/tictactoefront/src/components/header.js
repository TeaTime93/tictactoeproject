import React from 'react';
import './header.css';
import { startGame, connect } from '../api/gameApi';

const Header = () => {
  const handleStartGame = () => {
    const startNewGame = async (player) => {
      try {
        const game = await startGame(player);
        console.log("New game started:", game);
      } catch (error) {
        console.error("Failed to start a new game:", error.response?.data || error.message);
      }
    };
    startNewGame({ name: "JohnDoe" });
    
    console.log('Start game clicked!');
  };

  return (
    <div className="header">
      <h1>Tic Tac Toe</h1>
      <button className="start-game-button" onClick={handleStartGame}>
        Start Game
      </button>
    </div>
  );
};

export default Header;
