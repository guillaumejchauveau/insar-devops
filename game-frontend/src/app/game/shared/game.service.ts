import { Injectable } from '@angular/core';
import { GameModel } from './game.model';
import { MapModel } from '../map/map.model';
import { GameMapManagerService } from './game-map-manager.service';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  public game: GameModel;

  constructor() {
    const gameMapManager = new GameMapManagerService();
    const gameMap = gameMapManager.generateMap();
    this.game = new GameModel(gameMap, 'player');
  }
}
