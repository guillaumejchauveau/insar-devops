import { Component, OnInit } from '@angular/core';
import { Tile, NatureTile, CityTile } from '../shared/tile.model';
import { GameService } from '../shared/game.service';
import { PartialPointBinder, AnonCmd, MouseEnter } from 'interacto';
import { GameMoveModel } from '../shared/game-move.model';
import { Expression } from '@angular/compiler';

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

  mouseEnter(x: number, y: number): void {
    const tile = this.gameService.game.getInventory().getSelectedTile();
    if (tile === undefined || this.gameService.game.getTiles()[x][y] !== NatureTile.GRASS) {
      return;
    }

    let div;
    let span;
    let score;

    for (let i = Math.max(x - tile.radius, 0); i <= Math.min(x + tile.radius, this.gameService.game.map.width - 1); i++){
      for (let j = Math.max(y - tile.radius, 0); j <= Math.min(y + tile.radius, this.gameService.game.map.height - 1); j++){
        div = document.getElementById(i + ' ' + j) as HTMLElement;
        span = div.getElementsByTagName('span')[0];
        score = tile.getNeighbourPointsFor(this.gameService.game.getTiles()[i][j]);
        if (score !== 0) {
          span.innerHTML = score.toString();
        }
      }
    }

    div = document.getElementById(x + ' ' + y) as HTMLElement;
    span = div.getElementsByTagName('span')[0];
    score = tile.points;
    span.innerHTML = score.toString();
  }

  mouseLeave(x: number, y: number): void {
    let div;
    let span;
    for (let i = Math.max(x - 3, 0); i <= Math.min(x + 3, this.gameService.game.map.width - 1); i++){
      for (let j = Math.max(y - 3, 0); j <= Math.min(y + 3, this.gameService.game.map.height - 1); j++){
        div = document.getElementById(i + ' ' + j) as HTMLElement;
        span = div.getElementsByTagName('span')[0];
        span.innerHTML = '';
      }
    }
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
