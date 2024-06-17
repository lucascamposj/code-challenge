import { Component, inject } from '@angular/core';
import { OrderItemComponent } from '../../components/order-item/order-item.component';
import { CommonModule } from '@angular/common';
import { Store } from '@ngrx/store';
import { loadOrders } from '../../state/order/order.actions';
import { selectAllOrders } from '../../state/order/order.selectors';
import { Router } from '@angular/router';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [OrderItemComponent, CommonModule],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.scss',
})
export class OrdersComponent {
  constructor(private router: Router) {}
  private readonly store = inject(Store);
  public orders$ = this.store.select(selectAllOrders);

  ngOnInit() {
    this.orders$.subscribe((items) => {
      this.store.dispatch(loadOrders());
    });
  }

  goBack(): void {
    const navigationDetails = ['/home'];
    this.router.navigate(navigationDetails);
  }
}
