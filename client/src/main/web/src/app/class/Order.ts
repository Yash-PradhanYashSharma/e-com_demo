export class OrderDetails {
  orders: Order;
  orderItems: OrderItem[] = new Array<OrderItem>();
  adjustments: Adjustment[] = new Array<Adjustment>();
  roles: Role[] = new Array<Role>();
  orderStatuses: OrderStatus[] = new Array<OrderStatus>();
}

export class Order {
  orderId: number;
  orderDate: Date;
  total: number;
  orderStatusId: string;
  user_id: string;
  invoiceId: string;
}

export class OrderItem {
  orderId: string;
  orderItemSeqId: number;
  productId: string;
  quantity: number;
  unitPrice: number;
}

export class Adjustment {
  orderAdjustmentId: string;
  amount: number;
  productPromoId: string;
  userId: string;
  orderId: string;
  adjustmentType: string;
  id: string;
}

export class Role {
  orderId: string;
  partyId: string;
  roleTypeId: string;
}

export class OrderStatus {
  orderId: string;
  updatedDate: Date;
  orderStatusId: string;
}

export class OrderResponse {
  orderId: number;
  message: string
}
