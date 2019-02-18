import { Component, OnInit } from '@angular/core';
import {GameService} from "../../../services/game.service";
import { Game } from "../../../models/game.model";
import {DomSanitizer} from "@angular/platform-browser";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-game-details',
  templateUrl: './game-details.component.html',
  styleUrls: ['./game-details.component.css']
})
export class GameDetailsComponent implements OnInit {
  game: Game;
  id: number;

  constructor(private gameService: GameService,
              private sanitizer: DomSanitizer,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.gameService.getSingleGameById(this.id).subscribe(
      game => {
        this.game = game;
      });
  }

  sanitizeUrl(url: string) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

}
