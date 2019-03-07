import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from "@angular/forms";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registerForm: FormGroup;

  constructor() { }

  ngOnInit() {
    this.initForm();
  }

  onRegister() {
    const userDetails = {
      'username': this.registerForm.value['registerName'],
      'password': this.registerForm.value['registerPassword'],
    };
  }

  initForm() {
    this.registerForm = new FormGroup({
      'registerName': new FormControl(null, Validators.required),
      'registerPassword': new FormControl(null, Validators.required),
    })
  }
}
