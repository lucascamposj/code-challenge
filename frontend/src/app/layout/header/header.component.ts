import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { selectUserName } from '../../state/user/user.selectors';
import { CommonModule } from '@angular/common';
import { logout } from '../../state/user/user.actions';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  constructor(private router: Router) {}

  private readonly store = inject(Store);
  public username$ = this.store.select(selectUserName);

  logout(): void {
    const navigationDetails = ['/'];
    this.store.dispatch(logout());
    this.router.navigate(navigationDetails);
  }
}
