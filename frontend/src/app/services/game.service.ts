import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from "@angular/common/http";
import { catchError, tap } from "rxjs/operators";
import { of } from "rxjs";
import { Game } from "../models/game.model";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private baseUrl = 'http://localhost:8080/games';

  constructor(private httpClient: HttpClient) { }

  getAllGames() {
    return this.httpClient.get<Game[]>(this.baseUrl);
  }

  getTop5GameByRating() {
    return this.httpClient.get<Game[]>(`${this.baseUrl}/top-5`);
  }

  getSingleGameById(gameId: number) {
    return this.httpClient.get<Game>(`${this.baseUrl}/${gameId}`);
  }

  searchGameByTitle(searchTerm: string) {
    return this.httpClient.get<Game>(`${this.baseUrl}/search?substring=${searchTerm}`);
  }

  private handleError<T> (error: HttpErrorResponse, result?: T) {
    console.error(error);
    console.error(error.error['error']);
    console.error(error.error['message']);
    return of(result as T);
  }
}
