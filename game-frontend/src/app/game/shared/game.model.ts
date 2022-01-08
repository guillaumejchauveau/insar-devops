import { InventoryModel } from '../inventory/inventory.model';
import { MapModel } from '../map/map.model';
import { CityTile, Tile, NatureTile } from './tile.model';

export class GameModel {
  private score: number;
  private turn: number;
  private playername: string;
  private inventory: InventoryModel;
  public map: MapModel;
  private tiles: Array<Array<Tile>>;

  public constructor(map: MapModel, playername: string) {
    this.score = 0;
    this.turn = 1;
    this.playername = playername;
    this.inventory = new InventoryModel();
    this.map = map;
    this.tiles = new Array();
    for (let i = 0; i < map.width; i++) { // copy map.getTiles() in tiles
      this.tiles[i] = new Array();
      for (let j = 0; j < map.height; j++) {
        this.tiles[i][j] = map.tiles[i * map.width + j];
      }
    }
  }

  private static computeTurnScoreThreshold(turn: number): number {
    let score = 0;
    for (let i = 1; i <= turn; i++) {
      score += 10 * i;
    }
    return score;
  }

  public getScoreThreshold(): number {
    return GameModel.computeTurnScoreThreshold(this.turn);
  }

  public getPreviousScoreThreshold(): number {
    return GameModel.computeTurnScoreThreshold(this.turn - 1);
  }

  public addToScore(points: number): void{
    this.score += points;
    // If the score is high enough, the player reaches the next turn
    while (this.score >= this.getScoreThreshold()) {
      this.turn ++;
      // By reaching the next turn, the player earns 1 of each tile
      this.inventory.addTile(CityTile.CIRCUS);
      this.inventory.addTile(CityTile.HOUSE);
      this.inventory.addTile(CityTile.WINDMILL);
      this.inventory.addTile(CityTile.FOUNTAIN);
    }
  }

  public getScore(): number {
    return this.score;
  }

  public getInventory(): InventoryModel {
    return this.inventory;
  }

  public getTurn(): number {
    return this.turn;
  }

  public setTurn(turn: number): void {
    this.turn = turn;
  }

  public getTiles(): Array<Array<Tile>> {
    return this.tiles;
  }

  public getPlayerName(): string {
    return this.playername;
  }

  public setPlayerName(name: string): void {
    this.playername = name;
  }

  public noMoreSpace(): boolean {
    let bool = true;
    this.tiles.forEach((value) => {
      value.forEach(tile => {
        if (tile === NatureTile.GRASS) {
          bool = false;
        }
      });
    });
    return bool;
  }
}
