import { createAction, props } from '@ngrx/store';
import { User } from './user.model';

export type loginOption = 'create' | 'login';

export const logout = createAction('[User Page] Remove User');

export const login = createAction(
  '[User Page] login',
  props<{ content: User; option: loginOption }>()
);

export const loginUserSuccess = createAction(
  '[User API] User Load Success',
  props<{ user: User }>()
);

export const loginUserFailure = createAction(
  '[User API] User Load Failure',
  props<{ error: string }>()
);
