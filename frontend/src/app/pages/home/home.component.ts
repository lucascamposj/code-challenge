import { Component, inject } from '@angular/core';
import { ProductComponent } from '../../components/product/product.component';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import {
  isLoading,
  selectAllCartItemsLength,
  selectAllProducts,
} from '../../state/cart/cart.selectors';
import { loadCartItems } from '../../state/cart/cart.actions';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ProductComponent, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  constructor(private router: Router) {}

  private readonly store = inject(Store);

  public allCartItems$ = this.store.select(selectAllCartItemsLength);
  public allProducts$ = this.store.select(selectAllProducts);
  public isLoading$ = this.store.select(isLoading);
  public cartQuantity$ = this.store.select(selectAllCartItemsLength);

  gotoCart(): void {
    const navigationDetails = ['/cart'];
    this.router.navigate(navigationDetails);
  }

  ngOnInit() {
    this.allProducts$.subscribe((items) => {
      if (items.length == 0) {
        this.store.dispatch(loadCartItems());
      }
    });
  }
}
