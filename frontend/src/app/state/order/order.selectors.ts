import { createSelector } from '@ngrx/store';
import { AppState } from '../app.state';

export const selectOrder = (state: AppState) => state.order;

export const selectOrderState = createSelector(selectOrder, (state) => state);

export const selectAllOrders = createSelector(
  selectOrder,
  (state) => state.orders
);
