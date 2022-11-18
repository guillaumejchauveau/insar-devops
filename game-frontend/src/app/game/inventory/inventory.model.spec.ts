import { InventoryModel } from './inventory.model';
import { CityTile } from '../shared/tile.model';

describe('GameInventory', () => {
  let inventory: InventoryModel;

  beforeEach(() => {
    inventory = new InventoryModel();
  });

  it('should create an instance', () => {
    expect(inventory).toBeTruthy();
  });

  it('create the necessary tiles', () => {
    const testTiles: Map<CityTile, number> = new Map();
    testTiles.set(CityTile.house, 1);
    testTiles.set(CityTile.windmill, 0);
    testTiles.set(CityTile.circus, 0);
    testTiles.set(CityTile.fountain, 0);

    const tiles = inventory.getTiles();
    expect(tiles).toEqual(testTiles);
  });

  it('add one tile', () => {
    inventory.addTile(CityTile.house);
    const expected = 2;
    const nbHouse = inventory.getTiles().get(CityTile.house);

    expect(nbHouse).toEqual(expected);
  });

  it('add several tiles', () => {
    inventory.addTile(CityTile.circus, 4);
    const expected = 4;
    const nbHouse = inventory.getTiles().get(CityTile.circus);

    expect(nbHouse).toEqual(expected);
  });

  it('remove one tile', () => {
    inventory.removeTile(CityTile.house);
    const expected = 0;
    const nbHouse = inventory.getTiles().get(CityTile.house);

    expect(nbHouse).toEqual(expected);
  });

  it('remove several tiles', () => {
    inventory.addTile(CityTile.circus, 12);
    inventory.removeTile(CityTile.circus, 7);
    const expected = 5;
    const nbHouse = inventory.getTiles().get(CityTile.circus);

    expect(nbHouse).toEqual(expected);
  });

  it('containsTile works', () => {
    let house = inventory.containsTile(CityTile.house);
    expect(house).toBeTrue();

    let circus = inventory.containsTile(CityTile.circus);
    expect(circus).toBeFalse();

    inventory.removeTile(CityTile.house);
    house = inventory.containsTile(CityTile.house);
    expect(house).toBeFalse();

    inventory.addTile(CityTile.circus);
    circus = inventory.containsTile(CityTile.circus);
    expect(circus).toBeTrue();
  });

  it('isEmpty works', () => {
    let empty = inventory.isEmpty();
    expect(empty).toBeFalse();

    inventory.removeTile(CityTile.house);
    empty = inventory.isEmpty();
    expect(empty).toBeTrue();
  });

  it('house selected when created', () => {
    const selected = inventory.getSelectedTile();
    const expected = CityTile.house;
    expect(selected).toEqual(expected);
  });

  it('cannot select unavailable tile', () => {
    inventory.setSelectedTile(CityTile.circus);
    const selected = inventory.getSelectedTile();
    const expected = CityTile.house;
    expect(selected).toEqual(expected);
  });

  it('change selected tile', () => {
    inventory.addTile(CityTile.circus);
    inventory.setSelectedTile(CityTile.circus);
    const selected = inventory.getSelectedTile();
    const expected = CityTile.circus;
    expect(selected).toEqual(expected);
  });
});
