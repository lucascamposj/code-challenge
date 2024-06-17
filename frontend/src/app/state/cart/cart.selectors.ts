import { createSelector } from '@ngrx/store';
import { AppState } from '../app.state';

export const selectCartItems = (state: AppState) => state.cart;
export const selectAllState = (state: AppState) => state;

export const selectAllCartItems = createSelector(
  selectCartItems,
  (state) => state.cartItems
);

export const selectAllCartItemsLength = createSelector(
  selectCartItems,
  (state) => state.cartItems.length
);

export const selectAllCartItemsPrice = createSelector(
  selectCartItems,
  (state) => state.cartItems.reduce((acc, item) => acc + item.price, 0)
);

export const selectAllProducts = createSelector(
  selectCartItems,
  (state) => state.products
);

export const isLoading = createSelector(
  selectCartItems,
  (state) => state.status === 'loading'
);

export const selectPurchaseData = createSelector(selectAllState, (state) => {
  return {
    customerId: state.user.user?.id,
    price: state.cart.cartItems.reduce((acc, item) => acc + item.price, 0),
    items: state.cart.cartItems.map((i) => i.title),
  };
});
