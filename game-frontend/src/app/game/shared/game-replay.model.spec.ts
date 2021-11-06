import { PlayerModel } from '../../shared/player.model';
import { GameReplayModel } from './game-replay.model';
import { MapModel } from '../map/map.model';

describe('Replay', () => {
  it('should create an instance', () => {
    expect(new GameReplayModel(new PlayerModel('coucou'), new MapModel('test', 0, 0, []))).toBeTruthy();
  });
});
