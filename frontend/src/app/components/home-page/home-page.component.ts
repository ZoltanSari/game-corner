import { Component, OnInit } from '@angular/core';
import {Game} from "../../models/game.model";
import {GameService} from "../../services/game.service";

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  gamesOnHomePage: Game[];
  gamesInTheShowList: string[] = [];
  private isAllStoryLine: boolean;

  constructor(private gameService: GameService) {
  }

  ngOnInit() {
    this.gameService.addGamesToTheHomePage().subscribe(
      (games: Game[]) => this.gamesOnHomePage = games
    )
  }

  getShorterStoryLine(storyline: string) {
    if (storyline.length > 530) {
      return storyline.substring(0, 65) + '...';
    } else {
      return storyline;
    }
  }

  isStoryLineOpen(name: string) {
    if (this.gamesInTheShowList.includes(name)) {
      this.gamesInTheShowList = this.gamesInTheShowList.filter(name => name != name);
      return;
    }
      this.gamesInTheShowList.push(name);
  }

  isGameStoryLineIsOpen(name: string) {
    return this.gamesInTheShowList.includes(name)
  }
}

