import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InteractoModule } from 'interacto-angular';
import { MenuComponent } from './menu.component';
import { MapMenuComponent } from './map-menu/map-menu.component';
import { ReplaysMenuComponent } from './replays-menu/replays-menu.component';


@NgModule({
  declarations: [
    MenuComponent,
    MapMenuComponent,
    ReplaysMenuComponent
  ],
  imports: [
    CommonModule,
    InteractoModule
  ],
  providers: [
  ],
  exports: [
    MenuComponent
  ]
})
export class MenuModule { }
