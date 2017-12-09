import {Component} from '@angular/core';
import {AnalyticsOrderService} from "../../_services/analytics-order.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-analytics',
  templateUrl: './analytics.component.html',
  styleUrls: ['./analytics.component.css']
})
export class AnalyticsComponent {

  constructor(private analyticsOrderService: AnalyticsOrderService,
              private router: Router
  ) {
  }

  type = 'day';
  cardNumberA: string;
  isEnoughMoney: boolean = true;
  report = new Map<Date, number>();


  private createReport(data) {
    this.report.clear();

    for (let i = 0; i < data.length; i++) {
      let date = new Date();
      date.setDate(date.getDate() + i + 1);
      this.report.set(date, data[i]);
    }
  }


  pay(): void {
    this.isEnoughMoney = true;
    this.analyticsOrderService.order(this.type).subscribe(
      data => {
        this.createReport(data);

        let cost;

        if (this.type === 'day') {
          cost = 100;
        } else if (this.type === 'week') {
          cost = 250;
        } else {
          cost = 500;
        }

        let user = JSON.parse(localStorage.getItem('currentUser'));
        user.balance = user.balance - cost;
        localStorage.setItem('currentUser', JSON.stringify(user));
      },
      error => {
        if (error.status == 500) {
          this.router.navigate(['/500']);
        }
        this.isEnoughMoney = false;
      }
    );
  }

}
