import { createAction, props } from '@ngrx/store';
import { CartItem } from '../../pages/cart/cart.model';
import { IOrderInfo } from './cart.model';

export const addCartItem = createAction(
  '[CartItem Page] Add CartItem',
  props<{ content: CartItem }>()
);

export const removeCartItem = createAction(
  '[CartItem Page] Remove CartItem',
  props<{ id: number }>()
);

export const clearCart = createAction('[CartItem Page] Clear CartItem');

export const loadCartItems = createAction('[CartItem Page] Load CartItems');

export const loadCartItemsSuccess = createAction(
  '[CartItem API] CartItem Load Success',
  props<{ cartItems: CartItem[] }>()
);

export const loadCartItemsFailure = createAction(
  '[CartItem API] CartItem Load Failure',
  props<{ error: string }>()
);

export const purchase = createAction(
  '[Cart Page] purchase',
  props<{ purchaseData: IOrderInfo }>()
);

export const purchaseSuccess = createAction('[Cart API] Purchase Success');

export const purchaseFailure = createAction(
  '[Cart API] Purchase Failure',
  props<{ error: string }>()
);
