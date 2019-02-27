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
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { FooterComponent } from './components/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    GameDetailsComponent,
    GameListComponent,
    SearchComponent,
    GameItemComponent,
    PageNotFoundComponent,
    HomePageComponent,
    FooterComponent,
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
