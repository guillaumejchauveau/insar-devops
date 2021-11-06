import { MapModel } from './map.model';

describe('!gameMap', () => {
  it('should create an instance', () => {
    expect(new MapModel('test' , 0, 0, [])).toBeTruthy();
  });
});
