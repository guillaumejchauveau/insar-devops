import { Component, OnInit } from '@angular/core';
import { GameModel } from '../../game/shared/game.model';
import { GameService } from '../../game/shared/game.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-map-menu',
  templateUrl: './map-menu.component.html',
  styleUrls: ['./map-menu.component.css']
})
export class MapMenuComponent implements OnInit {
  mapNames: string[] = [];

  constructor(public gameService: GameService, private router: Router) {
    gameService.getMapNames().then(map => map.forEach(name => this.mapNames.push(name.toString())));
  }

  ngOnInit(): void {
  }

  randomMap(): void {
    const pseudo = document.getElementById('playername') as HTMLTextAreaElement;
    this.gameService.playername = pseudo.value || this.gameService.playername;
    this.gameService.generateMap().then(map => {
      this.gameService.changeGame(new GameModel(map, this.gameService.playername));
      this.router.navigate(['/game']);
    });
  }

  public selectMap(name: string): void {
    const pseudo = document.getElementById('playername') as HTMLTextAreaElement;
    this.gameService.playername = pseudo.value || this.gameService.playername;
    this.gameService.getMapByName(name).then(map => {
      this.gameService.changeGame(new GameModel(map, this.gameService.playername));
      this.router.navigate(['/game']);
    });
  }
}
