import { PlayerModel } from '../../shared/player.model';
import { GameMoveModel } from './game-move.model';
import { MapModel } from '../map/map.model';

export class GameReplayModel {
  private readonly player: PlayerModel;
  private readonly playerMoves: Array<GameMoveModel>;
  private readonly map: MapModel;

  public constructor(player: PlayerModel, map: MapModel) {
    this.player = player;
    this.playerMoves = [];
    this.map = map;
  }

  public getScore(): number {
    return 0; // TODO : calculer le score
  }

  public getPlayer(): PlayerModel {
    return this.player;
  }

  public getMoves(): Array<GameMoveModel> {
    return this.playerMoves;
  }

  public getMap(): MapModel {
    return this.map;
  }
}
