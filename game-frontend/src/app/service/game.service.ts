import { Injectable } from '@angular/core';
import { Game } from '../model/game';
import { GameMap } from '../model/game-map';
import { GameMapManagerService } from './game-map-manager.service';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  public game: Game;

  constructor() {
    const gameMapManager = new GameMapManagerService();
    const gameMap = gameMapManager.generateMap();
    this.game = new Game(gameMap, 'player');
  }
}
