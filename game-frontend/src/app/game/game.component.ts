import { Component, OnInit } from '@angular/core';
import { GameService } from './shared/game.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  constructor(public gameService: GameService) {
  }

  ngOnInit(): void {
  }

  public endGame(): void {
    this.gameService.endGame();
  }
}
