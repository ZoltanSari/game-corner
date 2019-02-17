export class Game {

  id: number;
  name: string;
  storyline: string;
  summary: string;
  trailer: string;
  genres: string[];
  gameModes: string[];
  platforms: string[];
  coverUrl: string;
  firstReleaseDate: string;
  company: string;
  rating: string;


  constructor(id: number, name: string, storyline: string, summary: string, trailer: string, genres: string[], gameModes: string[], platforms: string[], coverUrl: string, firstReleaseDate: string, company: string, rating: string) {
    this.id = id;
    this.name = name;
    this.storyline = storyline;
    this.summary = summary;
    this.trailer = trailer;
    this.genres = genres;
    this.gameModes = gameModes;
    this.platforms = platforms;
    this.coverUrl = coverUrl;
    this.firstReleaseDate = firstReleaseDate;
    this.company = company;
    this.rating = rating;
  }
}
