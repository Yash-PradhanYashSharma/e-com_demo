import {Lines} from './Lines';

export class Invoice {
  id: number;
  customer_email: string;
  customer_name: string;
  amount_due: number;
  amount_remaining: number;
  amount_paid: number;
  currency: string;
  subtotal: number;
  tax: number;
  tax_percent: number;
  total: number;
  lines: Lines;
}
