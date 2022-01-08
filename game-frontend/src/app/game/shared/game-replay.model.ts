import { GameMoveModel } from './game-move.model';
import { MapModel } from '../map/map.model';

export class GameReplayModel {
  private readonly playerName: string;
  private readonly moves: Array<GameMoveModel>;
  private readonly score: number;
  private readonly map: MapModel;

  public constructor(playerName: string, map: MapModel, score: number, moves: Array<GameMoveModel>) {
    this.playerName = playerName;
    this.moves = moves;
    this.score = score;
    this.map = map;
  }

  public getScore(): number {
    return this.score;
  }

  public getPlayerName(): string {
    return this.playerName;
  }

  public getMoves(): Array<GameMoveModel> {
    return this.moves;
  }

  public getMap(): MapModel {
    return this.map;
  }
}
