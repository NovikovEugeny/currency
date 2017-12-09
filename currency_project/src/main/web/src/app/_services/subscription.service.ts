import {Injectable} from "@angular/core";
import {Http, RequestOptions, Headers} from "@angular/http";
import {Observable} from "rxjs/Observable";

@Injectable()
export class SubscriptionService {

  constructor(private http: Http) {
  }

  private getUrl = '/api/get-subscription/user-id/';
  private deleteUrl = '/api/unsubscribe/user-id/';
  private createUrl = '/api/subscribe';


  getCurrent(): Observable<any> {
    let id = JSON.parse(localStorage.getItem('currentUser')).id;
    return this.http.get(this.getUrl + id, {withCredentials: true})
      .map(response => response.json());
  }

  unsubscribe() {
    let id = JSON.parse(localStorage.getItem('currentUser')).id;
    return this.http.patch(this.deleteUrl + id, {withCredentials: true});
  }

  createSubscription(type: string) {
    let id = JSON.parse(localStorage.getItem('currentUser')).id;
    let data = { id: id, type: type};

    let body = JSON.stringify(data);
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers, withCredentials: true});

    return this.http.post(this.createUrl, body, options);
  }

}
