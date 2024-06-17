import { Component } from '@angular/core';
import { Product } from '../../types';
import { CartItemComponent } from '../../components/cart-item/cart-item.component';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CartItemComponent, CommonModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss',
})
export class CartComponent {
  constructor(private router: Router) {}

  goToOrders(): void {
    const navigationDetails = ['/orders'];
    this.router.navigate(navigationDetails);
  }

  products: Product[] = [
    {
      title: 'Mens Cotton Jacket',
      id: 3,
      price: 55.99,
      category: "men's clothing",
      image: 'https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg',
    },
    {
      id: 4,
      title: 'Mens Casual Slim Fit',
      price: 15.99,
      category: "men's clothing",
      image: 'https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_.jpg',
    },
  ];
}
