import { Injectable } from '@angular/core';
import { GameMap } from '../model/game-map';
import { Tile, NatureTile, CityTile } from '../model/tile';

@Injectable({
  providedIn: 'root'
})
export class GameMapManagerService {
  maps: Array<GameMap> = new Array();

  constructor() { }

  public generateMap(): GameMap {
    const tiles = [
      NatureTile.TREE, NatureTile.TREE, NatureTile.TREE, NatureTile.GRASS, NatureTile.GRASS,
        NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS,
      NatureTile.TREE, NatureTile.TREE, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS,
        NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS,
      NatureTile.TREE, NatureTile.TREE, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS,
         NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS, NatureTile.TREE, NatureTile.GRASS,
      NatureTile.TREE, NatureTile.TREE, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS,
         NatureTile.GRASS, NatureTile.GRASS, NatureTile.TREE, NatureTile.TREE, NatureTile.TREE,
      NatureTile.TREE, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS,
         NatureTile.GRASS, NatureTile.GRASS, NatureTile.TREE, NatureTile.TREE, NatureTile.TREE,
      NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS, NatureTile.GRASS, NatureTile.WATER,
         NatureTile.WATER, NatureTile.WATER, NatureTile.GRASS, NatureTile.TREE, NatureTile.TREE,
      NatureTile.GRASS, NatureTile.GRASS, NatureTile.WATER, NatureTile.WATER, NatureTile.WATER,
         NatureTile.WATER, NatureTile.WATER, NatureTile.WATER, NatureTile.TREE, NatureTile.TREE,
      NatureTile.GRASS, NatureTile.WATER, NatureTile.WATER, NatureTile.WATER, NatureTile.WATER,
         NatureTile.WATER, NatureTile.WATER, NatureTile.WATER, NatureTile.TREE, NatureTile.TREE,
      NatureTile.WATER, NatureTile.WATER, NatureTile.WATER, NatureTile.WATER, NatureTile.WATER,
         NatureTile.WATER, NatureTile.WATER, NatureTile.WATER, NatureTile.GRASS, NatureTile.TREE,
      NatureTile.WATER, NatureTile.WATER, NatureTile.WATER, NatureTile.WATER, NatureTile.WATER,
         NatureTile.WATER, NatureTile.WATER, NatureTile.WATER, NatureTile.GRASS, NatureTile.TREE
    ];

    const map = new GameMap('mapTest', 10, 10, tiles);
    this.maps.push(map);
    return map;
  }
}
