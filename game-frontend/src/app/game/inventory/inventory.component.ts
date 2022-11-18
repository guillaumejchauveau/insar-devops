import { Component, OnInit } from '@angular/core';
import { GameService } from '../shared/game.service';
import { AnonCmd, PartialPointBinder } from 'interacto';
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
            tile = CityTile.circus;
            break;
          case 'fountain' :
            tile = CityTile.fountain;
            break;
          case 'windmill' :
            tile = CityTile.windmill;
            break;
          case 'house' :
            tile = CityTile.house;
            break;
          default:
            tile = CityTile.house;
        }

        return new AnonCmd(() => {
          this.gameService.getGame().getInventory().setSelectedTile(tile);
        });
      })
      .bind();
  }
}
