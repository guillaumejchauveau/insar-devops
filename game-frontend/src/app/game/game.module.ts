import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InventoryComponent } from './inventory/inventory.component';
import { GameComponent } from './game.component';
import { ScoreComponent } from './score/score.component';
import { InteractoModule } from 'interacto-angular';
import { MapComponent } from './map/map.component';
import { ScoreModalComponent } from './score-modal/score-modal.component';

@NgModule({
  declarations: [
    MapComponent,
    InventoryComponent,
    GameComponent,
    ScoreComponent,
    ScoreModalComponent,
  ],
  imports: [
    CommonModule,
    InteractoModule
  ],
  providers: [
  ],
  exports: [
    GameComponent
  ]
})
export class GameModule { }
