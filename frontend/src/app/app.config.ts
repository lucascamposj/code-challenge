import { ApplicationConfig } from '@angular/core';
import {
  InMemoryScrollingFeature,
  InMemoryScrollingOptions,
  provideRouter,
  withInMemoryScrolling,
} from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient } from '@angular/common/http';
import { provideState, provideStore } from '@ngrx/store';
import { provideEffects } from '@ngrx/effects';
import { cartItemReducer } from './state/cart/cart.reducer';
import { CartItemEffects } from './state/cart/cart.effects';
import { userReducer } from './state/user/user.reducer';
import { UserEffects } from './state/user/user.effects';
import { ordersReducer } from './state/order/order.reducer';
import { OrdersEffects } from './state/order/order.effects';

const scrollConfig: InMemoryScrollingOptions = {
  scrollPositionRestoration: 'top',
};

const inMemoryScrollingFeature: InMemoryScrollingFeature =
  withInMemoryScrolling(scrollConfig);

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes, inMemoryScrollingFeature),
    provideHttpClient(),
    provideState({ name: 'cart', reducer: cartItemReducer }),
    provideState({ name: 'user', reducer: userReducer }),
    provideState({ name: 'order', reducer: ordersReducer }),
    provideStore(),
    provideEffects([CartItemEffects, UserEffects, OrdersEffects]),
  ],
};
