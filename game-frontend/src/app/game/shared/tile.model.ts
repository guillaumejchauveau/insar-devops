export interface Tile {
  readonly name: string;
}

export class NatureTile implements Tile {
  public static readonly water: NatureTile = new NatureTile('water');
  public static readonly grass: NatureTile = new NatureTile('grass');
  public static readonly tree: NatureTile = new NatureTile('tree');
  public readonly name: string;

  private constructor(name: string) {
    this.name = name;
  }
}

export class CityTile implements Tile {
  public static readonly circus: CityTile = new CityTile('circus', 1, 3);
  public static readonly fountain: CityTile = new CityTile('fountain', 2, 1);
  public static readonly house: CityTile = new CityTile('house', 6, 1);
  public static readonly windmill: CityTile = new CityTile('windmill', 4, 1);
  private static readonly neighbourPointsMapping: Map<CityTile, Map<Tile, number>> = new Map([
    [CityTile.circus, new Map([
      [CityTile.circus, -25],
      [CityTile.house, 15]
    ])],
    [CityTile.fountain, new Map([
      [CityTile.circus, 6],
      [CityTile.house, 8]
    ])],
    [CityTile.house, new Map([
      [CityTile.circus, 10],
      [CityTile.fountain, 8],
      [CityTile.house, -1],
      [CityTile.windmill, -12],
      [NatureTile.tree, 5]
    ])],
    [CityTile.windmill, new Map([
      [CityTile.house, -8],
      [NatureTile.tree, -4],
      [NatureTile.water, 10]
    ])]
  ]);
  public readonly name: string;
  public readonly points: number;
  public readonly radius: number;

  private constructor(name: string, points: number, radius: number) {
    this.name = name;
    this.points = points;
    this.radius = radius;
  }

  public getNeighbourPointsFor(tile: Tile): number {
    return CityTile.neighbourPointsMapping.get(this)?.get(tile) || 0;
  }
}
