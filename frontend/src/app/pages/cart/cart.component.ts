import { Component, inject, Input } from '@angular/core';
import { Product } from '../../types';
import { CartItemComponent } from '../../components/cart-item/cart-item.component';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import {
  FormBuilder,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import {
  selectAllCartItems,
  selectAllCartItemsPrice,
  selectPurchaseData,
} from '../../state/cart/cart.selectors';
import { clearCart, purchase } from '../../state/cart/cart.actions';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { ICartPurchase, IOrderInfo } from '../../state/cart/cart.model';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [
    CartItemComponent,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    ButtonModule,
    InputTextModule,
  ],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss',
})
export class CartComponent {
  constructor(private router: Router, private formBuilder: FormBuilder) {}

  private readonly store = inject(Store);

  public cartItems$ = this.store.select(selectAllCartItems);
  public cartPrice$ = this.store.select(selectAllCartItemsPrice);
  public purchaseData$ = this.store.select(selectPurchaseData);

  @Input() orderInfo = {
    adress: '',
    paymentMethod: '',
  };

  goToOrders(): void {
    const navigationDetails = ['/orders'];
    this.router.navigate(navigationDetails);
  }

  goBack(): void {
    const navigationDetails = ['/home'];
    this.router.navigate(navigationDetails);
  }

  orderForm = this.formBuilder.group({
    address: ['', [Validators.required]],
    paymentMethod: ['', [Validators.required]],
  });

  ngOnChanges() {
    this.orderForm.patchValue(this.orderInfo);
  }

  onPurchase(): void {
    const { value } = this.orderForm;
    const purchaseData: IOrderInfo = {
      address: value.address!,
      paymentMethod: value.paymentMethod!,
    };
    this.store.dispatch(purchase({ purchaseData }));
    this.store.dispatch(clearCart());
  }
}
