export class PlayerModel {
  private name: string;

  public constructor(name: string) {
    this.name = name;
  }

  public setName(name: string): void {
    this.name = name;
  }

  public getName(): string {
    return this.name;
  }
}
