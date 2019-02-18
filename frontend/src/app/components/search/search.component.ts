import { Component, OnInit } from '@angular/core';
import {GameService} from "../../services/game.service";
import {Game} from "../../models/game.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor(private gameService: GameService,
              private router: Router) { }

  ngOnInit() {
  }

  onSearch(searchTerm: string) {
    this.gameService.searchGameByTitle(searchTerm);
    this.router.navigate(["games/search"])
  }
}
