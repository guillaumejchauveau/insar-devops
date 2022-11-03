import { Injectable } from '@angular/core';
import { GameModel } from './game.model';
import { GameMapManagerService } from './game-map-manager.service';
import { HttpClient } from '@angular/common/http';
import { Tile, NatureTile } from './tile.model';
import { MapModel } from '../map/map.model';
import { UndoHistory } from 'interacto';
import { GameMoveModel } from './game-move.model';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class GameService {
  public playername: string;
  private game: GameModel;
  private url = '/api/';

  constructor(private http: HttpClient, private undoHistory: UndoHistory, private router: Router) {
    const gameMapManager = new GameMapManagerService();
    const gameMap = gameMapManager.generateMap();
    this.playername = this.generateRandomName();
    this.game = new GameModel(gameMap, this.playername);
    this.undoHistory.setSizeMax(this.game.map.height * this.game.map.width);
  }

  public getMapNames(): Promise<string[]> {
    return this.http
      .get<string[]>(this.url + 'maps')
      .toPromise()
      .catch(_ => []);
  }

  public generateMap(): Promise<MapModel> {
    return this.http
      .post<IMapModel>(this.url + 'maps', undefined)
      .toPromise()
      .then(
        map => this.convertIMapModel(map)
      );
  }

  public getMapByName(name: string): Promise<MapModel> {
    return this.http
      .get<IMapModel>(this.url + 'maps/' + name)
      .toPromise()
      .then(
        map => this.convertIMapModel(map)
      );
  }

  public endGame(): void {
    const path = this.url + 'maps/' + this.game.map.name + '/replays/' + this.game.getPlayerName();
    const undohist = this.undoHistory
      .getUndo()
      .map(m => {
        const gamemove = m as GameMoveModel;
        const igamemove: IGameMove = {
          x: gamemove.getX(),
          y: gamemove.getX(),
          tile: gamemove.getTile().name.toUpperCase()
        };
        return igamemove;
      });
    const replay: IReplay = {
      moves: undohist
    };
    this.http
      .put(path, replay)
      .toPromise()
      .catch(_ => []);
    this.router.navigate(['']);
  }

  public checkEndGame(): void {
    if (this.game.noMoreSpace() || this.game.getInventory().isEmpty()) {
      window.alert('Partie terminée');
      setTimeout(() =>
      {
        this.endGame();
      },
      100);
    }
  }

  public changeGame(game: GameModel): void {
    this.game = game;
    this.undoHistory.clear();
    this.undoHistory.setSizeMax(this.game.map.height * this.game.map.width);
  }

  public getGame(): GameModel {
    return this.game;
  }

  public getHTTP(): HttpClient {
    return this.http;
  }

  public getURL(): string {
    return this.url;
  }

  public generateRandomName(): string {
    return 'player' + Math.floor(1000000 + Math.random() * 8999999);
  }

  private convertIMapModel(map: IMapModel): MapModel {
    const tiles: Tile[] = new Array();
    map.startTiles.forEach(tile =>
      {
        switch (tile.toString()) {
          case 'WATER': {
            tiles.push(NatureTile.water);
            break;
          }
          case 'TREE': {
            tiles.push(NatureTile.tree);
            break;
          }
          default: {
            tiles.push(NatureTile.grass);
            break;
          }
        }
      }
    );
    return new MapModel(map.name, map.width, map.height, tiles);
  }
}

export interface IMapModel {
  name: string;
  width: number;
  height: number;
  startTiles: Array<Tile>;
}

export interface IGameMove {
  x: number;
  y: number;
  tile: string;
}

export interface IReplay {
  moves: Array<IGameMove>;
}
