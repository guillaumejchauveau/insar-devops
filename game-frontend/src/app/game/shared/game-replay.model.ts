import { GameMoveModel } from './game-move.model';
import { MapModel } from '../map/map.model';

export class GameReplayModel {
  private readonly playerName: string;
  private readonly playerMoves: Array<GameMoveModel>;
  private readonly map: MapModel;

  public constructor(playerName: string, map: MapModel) {
    this.playerName = playerName;
    this.playerMoves = [];
    this.map = map;
  }

  public getScore(): number {
    return 0; // TODO : calculer le score
  }

  public getPlayerName(): string {
    return this.playerName;
  }

  public getMoves(): Array<GameMoveModel> {
    return this.playerMoves;
  }

  public getMap(): MapModel {
    return this.map;
  }
}
