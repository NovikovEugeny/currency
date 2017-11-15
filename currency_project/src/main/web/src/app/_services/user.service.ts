import {Injectable} from "@angular/core";
import {Headers, Http, RequestOptions} from "@angular/http";
import {User} from "../_models/user";
import 'rxjs/add/operator/toPromise';
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {Observable} from "rxjs/Observable";

@Injectable()
export class UserService {

  constructor(private http: Http) {
  }

  private signInUrl = 'http://localhost:8081/sign-in';
  private signUpUrl = 'http://localhost:8081/sign-up';
  private logoutUrl = 'http://localhost:8081/log-out';
  private addFundsUrl = 'http://localhost:8081/addFunds';


  signIn(user: User): Observable<User> {

    let body = JSON.stringify(user);
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers, withCredentials: true});

    return this.http.post(this.signInUrl, body, options)
      .map(response => response.json() as User);
  }

  signUp(user: User): Observable<User> {

    let body = JSON.stringify(user);
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers, withCredentials: true});

    return this.http.post(this.signUpUrl, body, options)
      .map(response => response.json() as User);
  }

  logout() {
    return this.http.get(this.logoutUrl, {withCredentials: true});
  }

  addFunds(sum: number) {

    let id = JSON.parse(localStorage.getItem('currentUser')).id;
    let data = { id: id, sum: sum};

    let body = JSON.stringify(data);
    let headers = new Headers({'Content-Type': 'application/json'});
    let options = new RequestOptions({headers: headers, withCredentials: true});

    return this.http.patch(this.addFundsUrl, body, options);
  }

}
