import {Component} from '@angular/core';
import {UserService} from "../../_services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-header',
  templateUrl: './user-header.component.html',
  styleUrls: ['./user-header.component.css']
})
export class UserHeaderComponent {

  constructor(private userService: UserService,
              private router: Router,) {
  }

  username = JSON.parse(localStorage.getItem('currentUser')).name;

  logout() {
    this.userService.logout().subscribe();
    localStorage.removeItem('currentUser');
    this.router.navigate(['/']);
  }

}
