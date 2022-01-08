import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { GameModule } from './game/game.module';
import { InteractoModule } from 'interacto-angular';
import { MenuModule } from './menu/menu.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    GameModule,
    MenuModule,
    InteractoModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
