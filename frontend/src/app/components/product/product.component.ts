import { Component, Input } from '@angular/core';
import { Product } from '../../types';
import { Store } from '@ngrx/store';
import { CartItem } from '../../pages/cart/cart.model';
import { addCartItem, removeCartItem } from '../../state/cart/cart.actions';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product.component.html',
  styleUrl: './product.component.scss',
})
export class ProductComponent {
  constructor(private store: Store) {}

  addCartItem(cartItem: CartItem) {
    this.store.dispatch(addCartItem({ content: cartItem }));
  }

  removeCartItem(cartItem: CartItem) {
    this.store.dispatch(removeCartItem({ id: cartItem.id }));
  }

  @Input() product!: Product;
}
