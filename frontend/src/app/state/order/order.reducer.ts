import { createReducer, on } from '@ngrx/store';
import {
  loadOrders,
  loadOrdersFailure,
  loadOrdersSuccess,
} from './order.actions';
import { OrderHistoryModel } from './order.model';

export type OrderStatusType = 'pending' | 'loading' | 'error' | 'success';

export interface OrderState {
  orders: OrderHistoryModel[];
  error: string | null;
  status: OrderStatusType;
}

export const initialState: OrderState = {
  orders: [],
  error: null,
  status: 'pending',
};

export const ordersReducer = createReducer(
  initialState,
  on(loadOrders, (state) => ({
    ...state,
    status: 'loading' as OrderStatusType,
  })),
  on(loadOrdersSuccess, (state, { orders }) => ({
    ...state,
    orders,
    error: null,
    status: 'success' as OrderStatusType,
  })),
  on(loadOrdersFailure, (state, { error }) => ({
    ...state,
    error: error,
    status: 'error' as OrderStatusType,
  }))
);
