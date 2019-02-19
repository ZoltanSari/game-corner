import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import {of, Subject} from "rxjs";
import { Game } from "../models/game.model";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private baseUrl = 'http://localhost:8080/games';
  searchResultGame = new Subject<Game[]>();

  constructor(private httpClient: HttpClient) { }

  getAllGames() {
    return this.httpClient.get<Game[]>(this.baseUrl);
  }

  addGamesToTheHomePage() {
    return this.httpClient.get<Game[]>(`${this.baseUrl}/top-5`);
  }

  getSingleGameById(gameId: number) {
    return this.httpClient.get<Game>(`${this.baseUrl}/${gameId}`);
  }

  searchGameByTitle(searchTerm: string) {
    return this.httpClient.get<Game[]>(`${this.baseUrl}/search?substring=${searchTerm}`).subscribe(
      (games: Game[]) => {
        this.searchResultGame.next(games);
      }
    );
  }

  private handleError<T> (error: HttpErrorResponse, result?: T) {
    console.error(error);
    console.error(error.error['error']);
    console.error(error.error['message']);
    return of(result as T);
  }
}
