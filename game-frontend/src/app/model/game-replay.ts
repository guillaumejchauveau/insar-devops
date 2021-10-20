import { Player } from './player';
import { GameMove } from './game-move';
import { GameMap } from './game-map';

export class GameReplay {
  private readonly player: Player;
  private readonly playerMoves: Array<GameMove>;
  private readonly map: GameMap;

  public constructor(player: Player, map: GameMap) {
    this.player = player;
    this.playerMoves = new Array();
    this.map = map;
  }

  public getScore(): number {
    return 0; // TODO : calculer le score
  }

  public getPlayer(): Player {
    return this.player;
  }

  public getMoves(): Array<GameMove> {
    return this.playerMoves;
  }

  public getMap(): GameMap {
    return this.map;
  }
}
