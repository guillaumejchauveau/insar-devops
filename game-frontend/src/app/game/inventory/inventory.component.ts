import { Component, OnInit } from '@angular/core';
import { GameService } from '../shared/game.service';
import { AnonCmd, PartialPointBinder, Undo } from 'interacto';
import { CityTile } from '../shared/tile.model';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {
  gameService: GameService;

  constructor(gameService: GameService) {
    this.gameService = gameService;
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
        const name = elt.getAttribute('data-name');

        let tile: CityTile;
        switch (name) {
          case 'circus' :
            tile = CityTile.CIRCUS;
            break;
          case 'fountain' :
            tile = CityTile.FOUNTAIN;
            break;
          case 'windmill' :
            tile = CityTile.WINDMILL;
            break;
          case 'house' :
            tile = CityTile.HOUSE;
            break;
          default:
            tile = CityTile.HOUSE;
        }

        return new AnonCmd(() => {
          this.gameService.game.getInventory().setSelectedTile(tile);
        });
      })
      .bind();
  }
}
