import apiClient from './apiClient';

const startGame = async (player) => {
  const response = await apiClient.post('/game/start', player);
  return response.data;
};

const connect = async (connectRequest) => {
  const response = await apiClient.post('/game/connect', connectRequest);
  return response.data;
};

const connectRandom = async (player) => {
  const response = await apiClient.post('/game/connect/random', player);
  return response.data;
};

const gamePlay = async (gamePlayRequest) => {
  const response = await apiClient.post('/game/gameplay', gamePlayRequest);
  return response.data;
};

export {
  startGame,
  connect,
  connectRandom,
  gamePlay,
};
