import { GameMove } from './game-move';
import { CityTile } from './tile';

describe('GameMove', () => {
  it('should create an instance', () => {
    expect(new GameMove(0, 0, CityTile.HOUSE)).toBeTruthy();
  });
});
