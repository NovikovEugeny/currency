import {Component, ViewEncapsulation} from '@angular/core';
import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {User} from "../../_models/user";
import {UserService} from "../../_services/user.service";

@Component({
  selector: 'app-sign-in-modal',
  templateUrl: './sign-in-modal.component.html',
  encapsulation: ViewEncapsulation.None,
  styleUrls: ['./sign-in-modal.component.css']
})

export class SignInModalComponent {

  constructor(private modalService: NgbModal,
              private router: Router,
              private userService: UserService) {
  }

  dialog: NgbModalRef;

  open(content) {
    this.dialog = this.modalService.open(content, { windowClass: 'modal-window' });
  }

  close() {
    if (this.dialog) {
      this.dialog.dismiss();
      this.dialog = null;
    }
  }

  user: User = new User();
  message: string;

  signIn() {
    this.userService.signIn(this.user).subscribe(
      user => {
        localStorage.setItem('currentUser', JSON.stringify(user));
        this.router.navigate(['/user/course-dynamics']);
        this.close();
      },
      error => {
        this.message = 'incorrect login or password';
      }
    );
  }

}
