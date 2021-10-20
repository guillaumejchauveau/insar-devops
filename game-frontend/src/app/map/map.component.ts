import { Component, OnInit } from '@angular/core';
import { Tile } from '../model/tile';
import { GameService } from '../service/game.service';

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
}
