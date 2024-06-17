import { Component } from '@angular/core';
import { Order } from '../../types';
import { OrderItemComponent } from '../../components/order-item/order-item.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [OrderItemComponent, CommonModule],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.scss',
})
export class OrdersComponent {
  orders: Order[] = [
    {
      address: 'Street 27',
      price: 130,
      paymentMethod: 'credit_card',
      items: [
        'Mens Cotton Jacket',
        'White Gold Plated Princess',
        'Solid Gold Petite Micropave',
      ],
      status: 'pending',
    },
    {
      items: [
        'Mens Casual Slim Fit',
        'John Hardy Womens Legends Naga Gold & Silver Dragon Station Chain Bracelet',
      ],
      price: 150.99,
      address: 'Park Avenue 27',
      paymentMethod: 'cash',
      status: 'shipped',
    },
  ];
}
