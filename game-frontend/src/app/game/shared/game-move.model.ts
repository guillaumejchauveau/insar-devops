import { UndoableCommand } from 'interacto';
import { CityTile, NatureTile } from './tile.model';
import { GameModel } from './game.model';

export class GameMoveModel extends UndoableCommand {
  private x: number;
  private y: number;
  private gameModel: GameModel;
  private tile: CityTile | undefined;

  public constructor(x: number, y: number, gameModel: GameModel) {
    super();
    this.x = x;
    this.y = y;
    this.gameModel = gameModel;
    this.tile = this.gameModel.getInventory().getSelectedTile();
  }

  private calcScore(): number {
    let score = this.tile?.points ?? 0;
    for (let i = Math.max(this.x - 1, 0); i <= Math.min(this.x + 1, 9); i++){
      for (let j = Math.max(this.y - 1, 0); j <= Math.min(this.y + 1, 9); j++){
        score += this.tile?.getNeighbourPointsFor(this.gameModel.getTiles()[i][j]) ?? 0;
      }
    }
    return score;
  }

  protected execution(): void | Promise<void> {
    this.gameModel.getInventory().useTile(this.tile as CityTile);
    this.gameModel.addToScore(this.calcScore());
    this.gameModel.getTiles()[this.x][this.y] = this.tile as CityTile;
  }

  redo(): void {
    this.execution();
  }

  undo(): void {
    if (this.tile !== undefined) {
      this.gameModel.getTiles()[this.x][this.y] = this.gameModel.map.tiles[this.x * 10 + this.y];
      this.gameModel.addToScore(this.calcScore() * -1);
      this.gameModel.getInventory().addTile(this.tile);
    }
  }
}
