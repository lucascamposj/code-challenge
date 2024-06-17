import { Injectable } from '@angular/core';
import { CartItem } from './cart.model';
import { ApiService } from '../../services/api.service';

@Injectable({ providedIn: 'root' })
export class CartService {
  constructor(private apiService: ApiService) {}

  async puchaseCartItems(cart: CartItem[]) {
    return this.apiService.post('endpoint/');
  }
}
