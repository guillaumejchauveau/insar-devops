import { GameMoveModel } from './game-move.model';
import { CityTile } from './tile.model';

describe('GameMove', () => {
  it('should create an instance', () => {
    expect(new GameMoveModel(0, 0, CityTile.HOUSE)).toBeTruthy();
  });
});
