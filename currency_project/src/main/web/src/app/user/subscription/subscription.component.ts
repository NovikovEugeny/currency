import {Component} from '@angular/core';
import {SubscriptionService} from "../../_services/subscription.service";

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.css']
})
export class SubscriptionComponent {

  constructor(private subscriptionService: SubscriptionService) {
  }

  subscribtion: boolean;
  endDate: Date;
  cardNumberS: string;
  subscriptionType = 'week';
  isEnoughMoney: boolean = true;

  ngOnInit(): void {
    this.isEnoughMoney = true;
    this.subscriptionService.getCurrent().subscribe(
      data => {
        this.subscribtion = true;
        this.endDate = new Date(data.endDate);
      }
    );
  }

  unsubscribe(): void {
    this.subscriptionService.unsubscribe().subscribe();
    this.subscribtion = false;
  }

  createSubscription(): void {
    this.subscriptionService.createSubscription(this.subscriptionType).subscribe(
      ok => {
        this.ngOnInit();

        let cost;

        if (this.subscriptionType === 'week') {
          cost = 10;
        } else if (this.subscriptionType === 'month') {
          cost = 20;
        } else {
          cost = 50;
        }

        let user = JSON.parse(localStorage.getItem('currentUser'));
        user.balance = user.balance - cost;
        localStorage.setItem('currentUser', JSON.stringify(user));
      },
      error => {
        this.isEnoughMoney = false;
      }
    );
  }

}
