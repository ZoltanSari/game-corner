import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {GameDetailsComponent} from "./components/game/game-details/game-details.component";
import { GameListComponent } from "./components/game/game-list/game-list.component";


const routes: Routes = [
  { path: 'games', component: GameListComponent},
  {path:'games/search', component: GameListComponent},
  { path: ':id', component: GameDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
