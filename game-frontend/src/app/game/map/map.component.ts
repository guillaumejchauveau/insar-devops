import { Component, OnInit } from '@angular/core';
import { Tile, NatureTile } from '../shared/tile.model';
import { GameService } from '../shared/game.service';
import { PartialPointBinder, AnonCmd } from 'interacto';
import { GameMoveModel } from '../shared/game-move.model';

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})
export class MapComponent implements OnInit {
  tiles: Array<Array<Tile>>;

  constructor(private gameService: GameService) {
    this.tiles = this.gameService.game.getTiles();
  }

  ngOnInit(): void {
  }

  public defineClickBinding(binder: PartialPointBinder | undefined): void {
    if (binder === undefined) {
      return;
    }
    binder
      .toProduce(i => {
        const elt = i.currentTarget as Element;

        const x = parseInt(elt.getAttribute('data-x') ?? '0', 0);
        const y = parseInt(elt.getAttribute('data-y') ?? '0', 0);

        const tile = this.gameService.game.getInventory().getSelectedTile();
        if (tile !== undefined
            && this.gameService.game.getInventory().containsTile(tile)
            && this.gameService.game.getTiles()[x][y] === NatureTile.GRASS) {
          return new GameMoveModel(x, y, this.gameService.game);
        } else {
          return new AnonCmd(() => {});
        }
      })
      .bind();
  }
}
