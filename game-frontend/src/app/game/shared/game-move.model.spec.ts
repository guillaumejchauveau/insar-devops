import { GameMoveModel } from './game-move.model';
import { GameModel } from './game.model';
import { MapModel } from '../map/map.model';

describe('GameMove', () => {
  it('should create an instance', () => {
    expect(new GameMoveModel(0, 0, new GameModel(
      new MapModel('mapname', 10, 10, []),
      'playername'
    ))).toBeTruthy();
  });
});
