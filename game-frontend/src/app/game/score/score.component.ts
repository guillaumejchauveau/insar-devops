import { Component, OnInit } from '@angular/core';
import { GameService } from '../shared/game.service';
import { GameModel } from '../shared/game.model';

@Component({
  selector: 'app-score',
  templateUrl: './score.component.html',
  styleUrls: ['./score.component.css']
})
export class ScoreComponent implements OnInit {
  game: GameModel;

  constructor(gameService: GameService) {
    this.game = gameService.game;
  }

  ngOnInit(): void {
  }

}
