import { CartState } from './cart/cart.reducer';
import { UserState } from './user/user.reducer';
import { OrderState } from './order/order.reducer';

export interface AppState {
  cart: CartState;
  user: UserState;
  order: OrderState;
}
