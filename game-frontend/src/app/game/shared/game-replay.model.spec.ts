import { GameReplayModel } from './game-replay.model';
import { MapModel } from '../map/map.model';

describe('Replay', () => {
  it('should create an instance', () => {
    expect(new GameReplayModel('coucou', new MapModel('test', 0, 0, []), 0, [])).toBeTruthy();
  });
});
