import { Player } from './player';
import { GameReplay } from './game-replay';
import { GameMap } from './game-map';

describe('Replay', () => {
  it('should create an instance', () => {
    expect(new GameReplay(new Player('coucou'), new GameMap('test', 0, 0, []))).toBeTruthy();
  });
});
