import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { IPurchase } from '../state/cart/cart.model';
import { OrderHistoryModel } from '../state/order/order.model';

const url = 'http://localhost:8080/v1/order';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  constructor(private apiService: ApiService) {}

  purchaseCart = (purchaseData: IPurchase): Observable<{}> => {
    return this.apiService.post(url, purchaseData);
  };

  getAllOrders = (customerId: string): Observable<OrderHistoryModel[]> => {
    console.log('API CALLED: ' + customerId);
    return this.apiService.get(url + '?customer-id=' + customerId);
  };
}
