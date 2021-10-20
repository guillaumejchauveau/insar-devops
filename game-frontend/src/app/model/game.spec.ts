import { Game } from './game';
import { GameMap } from './game-map';

describe('Game', () => {
  it('should create an instance', () => {
    expect(new Game(new GameMap('test', 10, 10, []), 'player')).toBeTruthy();
  });
});
