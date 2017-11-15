import {Component} from '@angular/core';
import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {User} from "../../_models/user";
import {UserService} from "../../_services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-sign-up-modal',
  templateUrl: './sign-up-modal.component.html',
  styleUrls: ['./sign-up-modal.component.css']
})
export class SignUpModalComponent {

  constructor(private modalService: NgbModal,
              private userService: UserService,
              private router: Router) { }

  dialog: NgbModalRef;

  open(content) {
    this.dialog = this.modalService.open(content);
  }

  close() {
    if (this.dialog) {
      this.dialog.dismiss();
      this.dialog = null;
    }
  }

  user: User = new User();
  message: string;

  signUp () {
    this.userService.signUp(this.user).subscribe(
      user => {
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.router.navigate(['/user']);
        this.close();
      },
      error => {
        this.message = 'the email is already exists';
      }
    );
  }

}
