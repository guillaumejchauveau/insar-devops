import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MapComponent } from './map/map.component';
import { InventoryComponent } from './inventory/inventory.component';
import { GameComponent } from './game.component';
import { ScoreComponent } from './score/score.component';
import { InteractoModule } from 'interacto-angular';


@NgModule({
  declarations: [
    MapComponent,
    InventoryComponent,
    GameComponent,
    ScoreComponent
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
