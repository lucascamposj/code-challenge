import { Component, Input } from '@angular/core';
import {
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../../state/user/user.model';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { CardModule } from 'primeng/card';
import { Store } from '@ngrx/store';
import { login } from '../../state/user/user.actions';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ButtonModule,
    CardModule,
    InputTextModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private store: Store
  ) {}

  @Input() user = {
    username: '',
    email: '',
  };

  loginForm = this.formBuilder.group({
    username: ['', [Validators.required, Validators.minLength(1)]],
    email: ['', [Validators.required, Validators.email]],
  });

  ngOnChanges() {
    this.loginForm.patchValue(this.user);
  }

  onLogin() {
    const { username, email } = this.loginForm.value;
    this.store.dispatch(
      login({ content: { name: username!, email: email! }, option: 'login' })
    );
  }

  onCreateAccount() {
    const { username, email } = this.loginForm.value;
    this.store.dispatch(
      login({ content: { name: username!, email: email! }, option: 'create' })
    );
  }
}
