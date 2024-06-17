import { PaymentMethod, Status } from '../../types';

export interface OrderHistoryModel {
  createdAt: string;
  items: string[];
  price: number;
  address: string;
  paymentMethod: PaymentMethod;
  status: Status;
}
