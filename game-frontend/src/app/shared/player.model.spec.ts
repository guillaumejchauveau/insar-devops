import { PlayerModel } from './player.model';

describe('Player', () => {
  it('should create an instance', () => {
    expect(new PlayerModel('player')).toBeTruthy();
  });
});
