import { Component } from '@angular/core';
import { ProductsService } from '../../services/products.service';
import { Product } from '../../types';
import { ProductComponent } from '../../components/product/product.component';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ProductComponent, CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  constructor(
    private productsService: ProductsService,
    private router: Router
  ) {}

  products: Product[] = [];

  gotoCart(): void {
    const navigationDetails = ['/cart'];
    this.router.navigate(navigationDetails);
  }

  ngOnInit() {
    this.productsService
      .getProducts('https://fakestoreapi.com/products')
      .subscribe((products) => {
        this.products = products;
      });
  }
}
