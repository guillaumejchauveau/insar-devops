import { GameMap } from './game-map';

describe('!gameMap', () => {
  it('should create an instance', () => {
    expect(new GameMap('test' , 0, 0, [])).toBeTruthy();
  });
});
