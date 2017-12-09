import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class CourseDynamicsService {

  constructor(private http: Http) {
  }

  private url = 'http://www.nbrb.by/API/ExRates/Rates/Dynamics/145?startDate=' +
    this.startDate() + '&endDate=' + this.endDate();

  private startDate(): string {
    let date = new Date();

    let year = date.getFullYear();
    let month = date.getMonth();
    let day = date.getDate();

    return year + '-' + month + '-' + day;
  }

  private endDate(): string {
    let date = new Date();

    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();

    return year + '-' + month + '-' + day;
  }

  getData(): Observable<any> {
    return this.http.get(this.url).map(response => response.json());
  }

}
