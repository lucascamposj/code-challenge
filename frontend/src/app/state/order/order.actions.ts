import { createAction, props } from '@ngrx/store';
import { OrderHistoryModel } from './order.model';

export const loadOrders = createAction('[Order Page] Load Orders');

export const loadOrdersSuccess = createAction(
  '[Order API] Order Load Success',
  props<{ orders: OrderHistoryModel[] }>()
);

export const loadOrdersFailure = createAction(
  '[Order API] Order Load Failure',
  props<{ error: string }>()
);
