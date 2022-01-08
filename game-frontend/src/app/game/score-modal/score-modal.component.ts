import { Component, OnInit } from '@angular/core';
import { GameService, IGameMove } from '../shared/game.service';

@Component({
  selector: 'app-score-modal',
  templateUrl: './score-modal.component.html',
  styleUrls: ['./score-modal.component.css']
})
export class ScoreModalComponent implements OnInit {
  public scores: Array<[string, number]>;

  constructor(private gameService: GameService) {
    this.scores = new Array();
    this.getBestScores();
  }

  ngOnInit(): void {
  }

  public getBestScores(): Promise<Array<[string, number]>> {
    return this.getBestPlayers()
      .then(players => {
        this.scores.length = 0;
        players.forEach(p => {
          this.getScoreByPlayerName(p)
          .then(score => {
            this.scores.push([p, score]);
          });
        });
        console.log(this.scores);
        return this.scores;
      })
      .catch(_ => {
        return [];
      });
  }

  private getBestPlayers(): Promise<Array<string>> {
    return this.gameService.getHTTP()
      .get<Array<string>>(this.gameService.getURL() + 'maps/' + this.gameService.getGame().map.name + '/replays?sortBy=score&limit=5')
      .toPromise()
      .catch(_ => {
        return [];
      });
  }

  private getScoreByPlayerName(name: string): Promise<number> {
    const path = this.gameService.getURL() + 'maps/' + this.gameService.getGame().map.name + '/replays/' + name;
    return this.gameService.getHTTP()
      .get<IGameReplay>(path)
      .toPromise()
      .then(replay => replay.score)
      .catch(_ => 0);
  }
}

export interface IGameReplay {
  score: number;
  playerName: string;
  moves: Array<IGameMove>;
}
