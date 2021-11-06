import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MapComponent } from './map/map.component';
import { InventoryComponent } from './inventory/inventory.component';
import { GameComponent } from './game.component';
import { GameService } from './shared/game.service';
import { ScoreComponent } from './score/score.component';

@NgModule({
  declarations: [
    MapComponent,
    InventoryComponent,
    GameComponent,
    ScoreComponent
  ],
  imports: [
    CommonModule
  ],
  providers: [
  ],
  exports: [
    GameComponent
  ]
})
export class GameModule { }
