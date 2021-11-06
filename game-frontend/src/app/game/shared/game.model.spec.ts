import { GameModel } from './game.model';
import { MapModel } from '../map/map.model';

describe('Game', () => {
  it('should create an instance', () => {
    expect(new GameModel(new MapModel('test', 10, 10, []), 'player')).toBeTruthy();
  });
});
