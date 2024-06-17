import { PaymentMethod } from '../types';

export const PaymentMethodMappper: { [key in PaymentMethod]: string } = {
  cash: 'Cash',
  credit_card: 'Credit Card',
  other: 'Other',
};
