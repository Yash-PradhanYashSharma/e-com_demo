import {Product} from './Product';

export class Cart {
  userId: string;
  productStoreId: string;
  orderName: string;
  createdBy: string;
  orderDate: string;
  products: Product[];
}
