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
    this.game = gameService.getGame();
  }

  getScorePercentage(): number {
    const previousThreshold = this.game.getPreviousScoreThreshold();
    return Math.round( (this.game.getScore() - previousThreshold) / (this.game.getScoreThreshold() - previousThreshold) * 100);
  }

  ngOnInit(): void {
  }

}
