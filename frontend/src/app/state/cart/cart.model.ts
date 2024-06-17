export interface IPurchase {
  price: number;
  items: string[];
  address?: string;
  customerId: string;
  paymentMethod?: string;
  status: string;
}

export interface ICartPurchase {
  price: number;
  items: string[];
  customerId: string;
}

export interface IOrderInfo {
  address?: string;
  paymentMethod?: string;
}
