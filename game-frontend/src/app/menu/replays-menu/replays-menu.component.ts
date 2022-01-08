import { Component, OnInit } from '@angular/core';
import { GameModel } from '../../game/shared/game.model';
import { GameService } from '../../game/shared/game.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-replays-menu',
  templateUrl: './replays-menu.component.html',
  styleUrls: ['./replays-menu.component.css']
})
export class ReplaysMenuComponent implements OnInit {
  mapNames: string[] = [];

  constructor(private gameService: GameService, private router: Router) {
    gameService.getMapNames().then(map => map.forEach(name => this.mapNames.push(name.toString())));
  }

  ngOnInit(): void {
  }

  selectMap(name: string): void {
    this.gameService.getMapByName(name).then(map => {
      this.gameService.changeGame(new GameModel(map, 'player2'));
      this.router.navigate(['/game']);
    });
  }
}

