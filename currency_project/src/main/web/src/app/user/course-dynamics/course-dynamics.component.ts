import {Component, OnInit} from '@angular/core';
import {CourseDynamicsService} from "../../_services/course-dynamics.service";
import {CourseDynamic} from "../../_models/course-dynamic";

@Component({
  selector: 'app-course-dynamics',
  templateUrl: './course-dynamics.component.html',
  styleUrls: ['./course-dynamics.component.css']
})
export class CourseDynamicsComponent implements OnInit {

  constructor(private courseDynamicService: CourseDynamicsService) {
  }

  list = [];

  lineChartData: Array<any> = [];

  lineChartLabels: Array<string>;

  lineChartOptions: any = {
    responsive: true
  };

  lineChartColors: Array<any> = [
    {
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    }
  ];

  lineChartLegend: boolean = false;
  lineChartType: string = 'line';


  showValues(): void {
    this.courseDynamicService.getData().subscribe(
      data => {
        this.processData(data);
      }
    );
  }


  private processData(data: any): void {
    let values = [];
    let dates = [];

    for (let i = 0; i < data.length; i++) {
      values.push(data[i].Cur_OfficialRate);
      let date = new Date(data[i].Date);
      let shortDate = date.getDate() + '-' + (date.getMonth() + 1);
      dates.push(shortDate);
    }

    let obj = {data: values, label: ''};
    this.lineChartData.push(obj);
    this.lineChartLabels = dates;


    data = data.reverse();

    for (let i = 0; i < data.length; i++) {
      if (i < data.length - 1) {
        let courseDynamic = new CourseDynamic();
        courseDynamic.date = data[i].Date;
        courseDynamic.value = data[i].Cur_OfficialRate;
        courseDynamic.relativity = (data[i].Cur_OfficialRate - data[i + 1].Cur_OfficialRate).toFixed(4);
        this.list.push(courseDynamic);
      }
    }
  }


  ngOnInit(): void {
    this.showValues();
  }

}
