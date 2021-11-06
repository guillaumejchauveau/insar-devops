import { NatureTile } from '../shared/tile.model';

export class MapModel {
  public readonly  name: string;
  public readonly  width: number;
  public readonly  height: number;
  public readonly tiles: Array<NatureTile>;

  public constructor(name: string, width: number, height: number, tiles: Array<NatureTile>) {
    this.name = name;
    this.width = width;
    this.height = height;
    this.tiles = tiles;
  }
}
