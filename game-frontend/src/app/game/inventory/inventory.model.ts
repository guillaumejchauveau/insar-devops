import { CityTile } from '../shared/tile.model';

export class InventoryModel {
  private tiles: Map<CityTile, number> = new Map();
  private selectedTile: CityTile | undefined;

  public constructor() {
    this.tiles.set(CityTile.HOUSE, 1);
    this.tiles.set(CityTile.WINDMILL, 2);
    this.tiles.set(CityTile.CIRCUS, 3);
    this.tiles.set(CityTile.FOUNTAIN, 4);
    this.selectedTile = CityTile.HOUSE;
  }

  public getTiles(): Map<CityTile, number> {
    return this.tiles;
  }

  public useTile(tile: CityTile): void {
    const nb = this.tiles.get(tile) as number;
    this.tiles.set(tile, nb - 1);
    if (this.tiles.get(tile) === 0) {
      this.selectedTile = undefined;
    }
  }

  public containsTile(tile: CityTile): boolean {
    return this.tiles.get(tile as CityTile) !== 0;
  }

  public addTile(tile: CityTile): void {
    const nb = this.tiles.get(tile) as number;
    this.tiles.set(tile, nb + 1);
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
