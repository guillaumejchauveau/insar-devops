export interface Tile {
  readonly name: string;
}

export class NatureTile implements Tile {
  public static readonly WATER: NatureTile = new NatureTile('water');
  public static readonly GRASS: NatureTile = new NatureTile('grass');
  public static readonly TREE: NatureTile = new NatureTile('tree');
  readonly name: string;

  private constructor(name: string) {
    this.name = name;
  }
}

export class CityTile implements Tile {
  public static readonly CIRCUS: CityTile = new CityTile('circus', 1);
  public static readonly FOUNTAIN: CityTile = new CityTile('fountain', 2);
  public static readonly HOUSE: CityTile = new CityTile('house', 6);
  public static readonly WINDMILL: CityTile = new CityTile('windmill', 4);
  private static readonly neighbourPointsMapping: Map<CityTile, Map<Tile, number>> = new Map([
    [CityTile.CIRCUS, new Map([
      [CityTile.CIRCUS, -25],
      [CityTile.HOUSE, 15]
    ])],
    [CityTile.FOUNTAIN, new Map([
      [CityTile.CIRCUS, 6],
      [CityTile.HOUSE, 8]
    ])],
    [CityTile.HOUSE, new Map([
      [CityTile.CIRCUS, 10],
      [CityTile.FOUNTAIN, 8],
      [CityTile.HOUSE, -1],
      [CityTile.WINDMILL, -12],
      [NatureTile.TREE, 5]
    ])],
    [CityTile.WINDMILL, new Map([
      [CityTile.HOUSE, -8],
      [NatureTile.TREE, -4],
      [NatureTile.WATER, 10]
    ])]
  ]);
  readonly name: string;
  readonly points: number;

  private constructor(name: string, points: number) {
    this.name = name;
    this.points = points;
  }

  getNeighbourPointsFor(tile: Tile): number {
    return CityTile.neighbourPointsMapping.get(this)?.get(tile) || 0;
  }
}
