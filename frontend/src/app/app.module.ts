import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { GameDetailsComponent } from './components/game/game-details/game-details.component';
import {HttpClientModule} from "@angular/common/http";
import {GameService} from "./services/game.service";
import {AppRoutingModule} from "./app-routing.module";
import { GameListComponent } from './components/game/game-list/game-list.component';
import { SearchComponent } from './components/search/search.component';
import { GameItemComponent } from './components/game/game-list/game-item/game-item.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    GameDetailsComponent,
    GameListComponent,
    SearchComponent,
    GameItemComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [GameService],
  bootstrap: [AppComponent]
})
export class AppModule { }
