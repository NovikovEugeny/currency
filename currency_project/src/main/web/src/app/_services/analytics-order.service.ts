import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions} from "@angular/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AnalyticsOrderService {

  constructor(private http: Http) {
  }

  private url = '/api/order-analytics';

  order(type: string): Observable<any> {

    let id = JSON.parse(localStorage.getItem('currentUser')).id;
    let data = { id: id, type: type};

    let body = JSON.stringify(data);
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({withCredentials: true, headers: headers});

    return this.http.post(this.url, body, options).map(response => response.json());
  }

}
