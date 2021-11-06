import { Injectable } from '@angular/core';
import { MapModel } from '../map/map.model';
import { Tile, NatureTile, CityTile } from './tile.model';

@Injectable({
  providedIn: 'root'
})
export class GameMapManagerService {
  maps: Array<MapModel> = [];

  constructor() { }

  public generateMap(): MapModel {
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

    const map = new MapModel('mapTest', 10, 10, tiles);
    this.maps.push(map);
    return map;
  }
}
