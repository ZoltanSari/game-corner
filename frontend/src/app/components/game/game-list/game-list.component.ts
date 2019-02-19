import { Component, OnInit } from '@angular/core';
import {GameService} from "../../../services/game.service";
import {Game} from "../../../models/game.model";

@Component({
  selector: 'app-game-list',
  templateUrl: './game-list.component.html',
  styleUrls: ['./game-list.component.css']
})
export class GameListComponent implements OnInit {
  games: Game[];
  searchGames: Game[];
  searchGame: boolean;

  constructor(private gameService: GameService) { }

  ngOnInit() {
    this.gameService.getAllGames().subscribe(
      (games: Game[]) => {
        this.games = games;
        this.searchGame = false;
      }
    );
    this.gameService.searchResultGame.subscribe(
      (games: Game[]) => {
        this.searchGames = games;
        this.searchGame = true;
      }
    )
  }

}
