import { createReducer, on } from '@ngrx/store';
import {
  addCartItem,
  removeCartItem,
  loadCartItems,
  loadCartItemsSuccess,
  loadCartItemsFailure,
  purchase,
  purchaseSuccess,
  purchaseFailure,
  clearCart,
} from './cart.actions';
import { CartItem } from '../../pages/cart/cart.model';
import { Product } from '../../types';

export type PuchaseStatusType = 'pending' | 'loading' | 'error' | 'success';

export interface CartState {
  products: Product[];
  cartItems: CartItem[];
  error: string | null;
  status: PuchaseStatusType;
  purchaseStatus: PuchaseStatusType;
}

export const initialState: CartState = {
  products: [],
  cartItems: [],
  error: null,
  status: 'pending',
  purchaseStatus: 'pending',
};

export const cartItemReducer = createReducer(
  initialState,
  on(clearCart, () => initialState),
  on(addCartItem, (state, { content }) => {
    return {
      ...state,
      cartItems: [...state.cartItems, { ...content }],
      products: state.products.map((p) =>
        p.id === content.id ? { ...p, selected: true } : p
      ),
    };
  }),
  on(removeCartItem, (state, { id }) => ({
    ...state,
    cartItems: state.cartItems.filter((cartItem) => cartItem.id !== id),
    products: state.products.map((p) =>
      p.id === id ? { ...p, selected: false } : p
    ),
  })),
  on(loadCartItems, (state) => ({
    ...state,
    status: 'loading' as PuchaseStatusType,
  })),
  on(loadCartItemsSuccess, (state, { cartItems }) => ({
    ...state,
    products: cartItems,
    error: null,
    status: 'success' as PuchaseStatusType,
  })),
  on(loadCartItemsFailure, (state, { error }) => ({
    ...state,
    error: error,
    status: 'error' as PuchaseStatusType,
  })),

  on(purchase, (state) => ({
    ...state,
    purchaseStatus: 'loading' as PuchaseStatusType,
  })),
  on(purchaseSuccess, (state) => ({
    ...state,
    error: null,
    purchaseStatus: 'success' as PuchaseStatusType,
  })),
  on(purchaseFailure, (state, { error }) => ({
    ...state,
    error: error,
    purchaseStatus: 'error' as PuchaseStatusType,
  }))
);
