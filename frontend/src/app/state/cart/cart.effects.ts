import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import {
  loadCartItems,
  loadCartItemsFailure,
  loadCartItemsSuccess,
  purchase,
  purchaseFailure,
  purchaseSuccess,
} from './cart.actions';
import { from, of } from 'rxjs';
import { switchMap, map, catchError, withLatestFrom } from 'rxjs/operators';
import { Store } from '@ngrx/store';
import { AppState } from '../app.state';
import { ApiService } from '../../services/api.service';
import { ProductsService } from '../../services/products.service';
import { OrderService } from '../../services/order.service';
import { Router } from '@angular/router';
import { selectPurchaseData } from './cart.selectors';

@Injectable()
export class CartItemEffects {
  constructor(
    private actions$: Actions,
    private store: Store<AppState>,
    private productsService: ProductsService,
    private apiSevice: ApiService,
    private orderService: OrderService,
    private router: Router
  ) {}

  loadCart$ = createEffect(() =>
    this.actions$.pipe(
      ofType(loadCartItems),
      switchMap(() =>
        from(
          this.productsService.getProducts('https://fakestoreapi.com/products')
        ).pipe(
          map((items) => loadCartItemsSuccess({ cartItems: items })),
          catchError((error) => of(loadCartItemsFailure({ error })))
        )
      )
    )
  );

  purchase$ = createEffect(() =>
    this.actions$.pipe(
      ofType(purchase),
      withLatestFrom(this.store.select(selectPurchaseData)),
      switchMap(([{ purchaseData }, orderData]) =>
        from(
          this.orderService.purchaseCart({
            customerId: orderData.customerId!,
            items: orderData.items,
            price: orderData.price,
            status: 'pending',
            address: purchaseData.address || 'standart address',
            paymentMethod: purchaseData.paymentMethod || 'cash',
          })
        ).pipe(
          map(() => {
            this.router.navigate([`/orders`]);
            return purchaseSuccess();
          }),
          catchError((error) => of(purchaseFailure({ error })))
        )
      )
    )
  );
}
