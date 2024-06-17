import { createReducer, on } from '@ngrx/store';
import {
  login,
  loginUserFailure,
  loginUserSuccess,
  logout,
} from './user.actions';
import { User } from './user.model';

export type LoginStatusType = 'pending' | 'loading' | 'error' | 'success';

export interface UserState {
  user?: User;
  error?: string;
  status: LoginStatusType;
}

export const initialState: UserState = {
  status: 'pending',
};

export const userReducer = createReducer(
  initialState,
  on(logout, () => ({
    ...initialState,
  })),
  on(login, (state) => ({
    ...state,
    status: 'loading' as LoginStatusType,
  })),
  on(loginUserSuccess, (state, { user }) => ({
    ...state,
    user,
    error: undefined,
    status: 'success' as LoginStatusType,
  })),
  on(loginUserFailure, (state, { error }) => ({
    ...state,
    error: error,
    status: 'error' as LoginStatusType,
  }))
);
