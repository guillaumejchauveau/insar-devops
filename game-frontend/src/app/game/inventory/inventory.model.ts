import { CityTile } from '../shared/tile.model';
import { GameService } from '../shared/game.service';

export class InventoryModel {
  private tiles: Map<CityTile, number> = new Map();
  private selectedTile: CityTile | undefined;

  public constructor() {
    this.tiles.set(CityTile.HOUSE, 1);
    this.tiles.set(CityTile.WINDMILL, 0);
    this.tiles.set(CityTile.CIRCUS, 0);
    this.tiles.set(CityTile.FOUNTAIN, 0);
    this.selectedTile = CityTile.HOUSE;
  }

  public getTiles(): Map<CityTile, number> {
    return this.tiles;
  }

  public removeTile(tile: CityTile, nb: number = 1): void {
    const tileNb = this.tiles.get(tile) as number;
    this.tiles.set(tile, tileNb - nb);
    if (this.tiles.get(tile) === 0) {
      this.selectedTile = undefined;
    }
  }

  public isEmpty(): boolean {
    let bool = true;
    this.tiles.forEach((value) => {
      if (value !== 0) {
        bool = false;
      }
    });
    return bool;
  }

  public containsTile(tile: CityTile): boolean {
    return this.tiles.get(tile as CityTile) !== 0;
  }

  public addTile(tile: CityTile, nb: number = 1): void {
    const tileNb = this.tiles.get(tile) as number;
    this.tiles.set(tile, tileNb + nb);
  }

  public setSelectedTile(tile: CityTile | undefined): void {
    if (tile instanceof CityTile && this.tiles.get(tile) !== 0) {
      this.selectedTile = tile;
    }
  }

  public getSelectedTile(): CityTile | undefined {
    return this.selectedTile;
  }
}
