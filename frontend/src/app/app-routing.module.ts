import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {GameDetailsComponent} from "./components/game/game-details/game-details.component";
import { GameListComponent } from "./components/game/game-list/game-list.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";


const routes: Routes = [
  { path: 'games', component: GameListComponent},
  {path:'games/search', component: GameListComponent},
  { path: 'not-found', component: PageNotFoundComponent, data: {message: 'Page not found!'} },
  { path: ':id', component: GameDetailsComponent },
  { path: '**', redirectTo: '/not-found' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
