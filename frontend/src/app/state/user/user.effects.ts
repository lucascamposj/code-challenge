import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { login, loginUserSuccess, loginUserFailure } from './user.actions';
import { from, of } from 'rxjs';
import { switchMap, map, catchError } from 'rxjs/operators';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';

@Injectable()
export class UserEffects {
  constructor(
    private actions$: Actions,
    private userService: UserService,
    private router: Router
  ) {}

  loginUser$ = createEffect(() =>
    this.actions$.pipe(
      ofType(login),
      switchMap(({ option, content }) =>
        from(
          option === 'create'
            ? this.userService.createAccount(content)
            : this.userService.login(content)
        ).pipe(
          map((user) => {
            console.log(user);
            this.router.navigate([`/home`]);
            return loginUserSuccess({ user });
          }),
          catchError((error) => of(loginUserFailure({ error })))
        )
      )
    )
  );
}
