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
      NatureTile.tree, NatureTile.tree, NatureTile.tree, NatureTile.grass, NatureTile.grass,
        NatureTile.grass, NatureTile.grass, NatureTile.grass, NatureTile.grass, NatureTile.grass,
      NatureTile.tree, NatureTile.tree, NatureTile.grass, NatureTile.grass, NatureTile.grass,
        NatureTile.grass, NatureTile.grass, NatureTile.grass, NatureTile.grass, NatureTile.grass,
      NatureTile.tree, NatureTile.tree, NatureTile.grass, NatureTile.grass, NatureTile.grass,
         NatureTile.grass, NatureTile.grass, NatureTile.grass, NatureTile.tree, NatureTile.grass,
      NatureTile.tree, NatureTile.tree, NatureTile.grass, NatureTile.grass, NatureTile.grass,
         NatureTile.grass, NatureTile.grass, NatureTile.tree, NatureTile.tree, NatureTile.tree,
      NatureTile.tree, NatureTile.grass, NatureTile.grass, NatureTile.grass, NatureTile.grass,
         NatureTile.grass, NatureTile.grass, NatureTile.tree, NatureTile.tree, NatureTile.tree,
      NatureTile.grass, NatureTile.grass, NatureTile.grass, NatureTile.grass, NatureTile.water,
         NatureTile.water, NatureTile.water, NatureTile.grass, NatureTile.tree, NatureTile.tree,
      NatureTile.grass, NatureTile.grass, NatureTile.water, NatureTile.water, NatureTile.water,
         NatureTile.water, NatureTile.water, NatureTile.water, NatureTile.tree, NatureTile.tree,
      NatureTile.grass, NatureTile.water, NatureTile.water, NatureTile.water, NatureTile.water,
         NatureTile.water, NatureTile.water, NatureTile.water, NatureTile.tree, NatureTile.tree,
      NatureTile.water, NatureTile.water, NatureTile.water, NatureTile.water, NatureTile.water,
         NatureTile.water, NatureTile.water, NatureTile.water, NatureTile.grass, NatureTile.tree,
      NatureTile.water, NatureTile.water, NatureTile.water, NatureTile.water, NatureTile.water,
         NatureTile.water, NatureTile.water, NatureTile.water, NatureTile.grass, NatureTile.tree
    ];

    const map = new MapModel('mapTest', 10, 10, tiles);
    this.maps.push(map);
    return map;
  }
}
