import { createSelector } from '@ngrx/store';
import { AppState } from '../app.state';

export const selectUserState = (state: AppState) => state.user;

export const selectUser = createSelector(
  selectUserState,
  (state) => state.user
);

export const selectUserName = createSelector(
  selectUserState,
  (state) => state.user?.name
);
