import {Component} from '@angular/core';
import {UserService} from "../../_services/user.service";
import {User} from "../../_models/user";

@Component({
  selector: 'app-balance',
  templateUrl: './balance.component.html',
  styleUrls: ['./balance.component.css']
})
export class BalanceComponent {

  constructor(private userService: UserService) {
  }

  balance = JSON.parse(localStorage.getItem('currentUser')).balance;
  sum: string;
  cardNumberB: string;

  addFunds() {
    this.userService.addFunds(parseFloat(this.sum)).subscribe(
      ok => {
        let user: User = JSON.parse(localStorage.getItem('currentUser'));
        let newBalance = user.balance + parseFloat(this.sum);
        user.balance = newBalance;
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.balance = newBalance;
      }
    );
  }

  reset() {
    this.sum = '';
    this.cardNumberB = '';
  }

}
