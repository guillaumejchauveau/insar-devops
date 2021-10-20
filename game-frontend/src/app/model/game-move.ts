import { CityTile } from './tile';

export class GameMove {
  private x: number;
  private y: number;
  private tile: CityTile;

  public constructor(x: number, y: number, tile: CityTile) {
    this.x = x;
    this.y = y;
    this.tile = tile;
  }
}
