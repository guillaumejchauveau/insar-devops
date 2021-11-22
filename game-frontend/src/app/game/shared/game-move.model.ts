import { UndoableCommand } from 'interacto';
import { CityTile } from './tile.model';
import { GameModel } from './game.model';

export class GameMoveModel extends UndoableCommand {
  private x: number;
  private y: number;
  private gameModel: GameModel;
  private tile: CityTile | undefined;
  private turn: number;

  public constructor(x: number, y: number, gameModel: GameModel) {
    super();
    this.x = x;
    this.y = y;
    this.gameModel = gameModel;
    this.tile = this.gameModel.getInventory().getSelectedTile();
    this.turn = this.gameModel.getTurn();
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
    this.gameModel.getInventory().removeTile(this.tile as CityTile);
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

      const inventory = this.gameModel.getInventory();

      // The number of turns between the current one and the one when the move was made
      const nb = this.gameModel.getTurn() - this.turn;

      // If the turn when the move was done is lower than the current turn,
      // --> Go back to the previous turn and remove tiles from the inventory
      if (nb) {
        inventory.removeTile(CityTile.HOUSE, nb);
        inventory.removeTile(CityTile.CIRCUS, nb);
        inventory.removeTile(CityTile.WINDMILL, nb);
        inventory.removeTile(CityTile.FOUNTAIN, nb);
        this.gameModel.setTurn(this.turn);
      }
    }
  }
}
