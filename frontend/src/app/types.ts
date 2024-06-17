import { HttpHeaders, HttpContext, HttpParams } from '@angular/common/http';

export interface Options {
  headers?:
    | HttpHeaders
    | {
        [header: string]: string | string[];
      };
  context?: HttpContext;
  observe?: 'body';
  params?:
    | HttpParams
    | {
        [param: string]:
          | string
          | number
          | boolean
          | ReadonlyArray<string | number | boolean>;
      };
  reportProgress?: boolean;
  responseType?: 'json';
  withCredentials?: boolean;
  transferCache?:
    | {
        includeHeaders?: string[];
      }
    | boolean;
}

export interface Product {
  id: number;
  title: string;
  price: number;
  category: string;
  image: string;
}


export interface Order {
  address: string;
  price: number;
  paymentMethod: string;
  items: string[];
  status: Status;
}

export type PaymentMethod = 'credit_card' |  'debit_card' | 'cash' | 'other';
export type Status = 'pending' |  'shipped' | 'delivered' | 'canceled';