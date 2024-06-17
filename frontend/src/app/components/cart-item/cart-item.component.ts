import { Component, inject, Input } from '@angular/core';
import { Product } from '../../types';
import { Store } from '@ngrx/store';
import { CartItem } from '../../pages/cart/cart.model';
import { removeCartItem } from '../../state/cart/cart.actions';

@Component({
  selector: 'app-cart-item',
  standalone: true,
  imports: [],
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.scss',
})
export class CartItemComponent {
  private readonly store = inject(Store);

  removeItem(item: CartItem): void {
    this.store.dispatch(removeCartItem({ id: item.id }));
  }

  @Input() product!: Product;
}
