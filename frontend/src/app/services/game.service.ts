import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpResponse} from "@angular/common/http";
import {catchError, tap} from "rxjs/operators";
import {of} from "rxjs";
import {Game} from "../models/Game";

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private baseUrl = 'http://localhost:8080/games';

  constructor(private httpClient: HttpClient) { }

  getAllGames() {
    return this.httpClient.get<Game[]>(this.baseUrl)
      .pipe(
        tap( () => console.log('All games found!')),
        catchError(response => this.handleError(response))
      )
  }

  getTop5GameByRating() {
    return this.httpClient.get<Game[]>(`${this.baseUrl}/top-5`)
      .pipe(
        tap( () => console.log('All games found!')),
        catchError(response => this.handleError(response))
      )
  }

  getSingleGameById(gameId: number) {
    return this.httpClient.get<Game>(`${this.baseUrl}/${gameId}`)
      .pipe(
        tap( () => console.log('The game found!')),
        catchError(response => this.handleError(response))
      )
  }

  searchGameByTitle(searchTerm: string) {
    return this.httpClient.get<Game>(`${this.baseUrl}/search?substring=${searchTerm}`)
      .pipe(
        tap( () => console.log('The game found!')),
        catchError(response => this.handleError(response))
      )
  }

  private handleError<T> (error: HttpErrorResponse, result?: T) {
    console.error(error);
    console.error(error.error['error']);
    console.error(error.error['message']);
    return of(result as T);
  }
}
