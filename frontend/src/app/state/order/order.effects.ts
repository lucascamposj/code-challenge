import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import {
  loadOrders,
  loadOrdersFailure,
  loadOrdersSuccess,
} from './order.actions';
import { from, of } from 'rxjs';
import { switchMap, map, catchError, withLatestFrom } from 'rxjs/operators';
import { OrderService } from '../../services/order.service';
import { Store } from '@ngrx/store';
import { AppState } from '../app.state';
import { selectPurchaseData } from '../cart/cart.selectors';

@Injectable()
export class OrdersEffects {
  constructor(
    private actions$: Actions,
    private orderService: OrderService,
    private store: Store<AppState>
  ) {}

  loadOrders$ = createEffect(() =>
    this.actions$.pipe(
      ofType(loadOrders),
      withLatestFrom(this.store.select(selectPurchaseData)),
      switchMap(([_, user]) => {
        console.log(user);
        return from(this.orderService.getAllOrders(user?.customerId!)).pipe(
          map((orders) => loadOrdersSuccess({ orders })),
          catchError((error) => of(loadOrdersFailure({ error })))
        );
      })
    )
  );
}
