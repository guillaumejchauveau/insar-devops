import { GameMoveModel } from './game-move.model';
import { InventoryModel } from '../inventory/inventory.model';
import { MapModel } from '../map/map.model';
import { Tile } from './tile.model';

export class GameModel {
  private score: number;
  private turn: number;
  private playerName: string;
  private maxUndoRedo: number;
  private undos: Array<GameMoveModel>;
  private redos: Array<GameMoveModel>;
  private inventory: InventoryModel;
  public readonly map: MapModel;
  private tiles: Array<Array<Tile>>;

  public constructor(map: MapModel, playerName: string) {
    this.score = 0;
    this.turn = 0;
    this.playerName = playerName;
    this.maxUndoRedo = 20;
    this.undos = new Array();
    this.redos = new Array();
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
    return 0;
  }

  public addToScore(points: number): void{
    this.score += points;
  }

  public getTiles(): Array<Array<Tile>> {
    return this.tiles;
  }

  public getInventory(): InventoryModel {
    return this.inventory;
  }

  public getScore(): number {
    return this.score;
  }
}
