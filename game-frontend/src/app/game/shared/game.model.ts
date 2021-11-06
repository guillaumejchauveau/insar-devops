import { GameMoveModel } from './game-move.model';
import { InventoryModel } from '../inventory/inventory.model';
import { MapModel } from '../map/map.model';
import { Tile, CityTile, NatureTile } from './tile.model';

export class GameModel {
  private score: number;
  private turn: number;
  private playername: string;
  private maxUndoRedo: number;
  private undos: Array<GameMoveModel>;
  private redos: Array<GameMoveModel>;
  private inventory: InventoryModel;
  private readonly map: MapModel;
  private tiles: Array<Array<Tile>>;

  public constructor(map: MapModel, playername: string) {
    this.score = 0;
    this.turn = 0;
    this.playername = playername;
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

  private addToScore(points: number): void{
    this.score += points;
  }

  public play(move: GameMoveModel): void {
    this.redos.length = 0; // Empty the array
    if (this.undos.length === this.maxUndoRedo) { // Remove the first move if the array is too long
      this.undos.shift();
    }
    // TODO : do the move and change the score
    this.undos.push(move);
    this.turn ++;
  }

  public undo(): void {
    if (!this.undos.length) { // Undo the last move if the array is not empty
      const move = this.undos.pop() as GameMoveModel;
      // TODO : undo the move and change the score
      if (this.redos.length === this.maxUndoRedo) { // Remove the first move to redo if the array is too long
        this.redos.shift();
      }
      this.redos.push(move);
      this.turn ++;
    }
  }

  public redo(): void {
    if (!this.redos.length) { // Redo the last move if the array is not empty
      const move = this.redos.pop() as GameMoveModel;
      // TODO : redo the move and change the score
      if (this.undos.length === this.maxUndoRedo) { // Remove the first move to undo if the array is too long
        this.undos.shift();
      }
      this.undos.push(move);
      this.turn ++;
    }
  }

  public getTiles(): Array<Array<Tile>> {
    return this.tiles;
  }
}
