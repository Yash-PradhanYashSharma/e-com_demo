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
}

export class Lines {
  invoice_item: string;
  description: string;
  quantity: number;
  amount: number;
}

export class InvoiceRequest {
  invoiceId: number;
}

/*
{
  "id": "in_1Fjjd4LtNhj8WVidJeDHkKOP",
  "object": "invoice",
  "account_country": "US",
  "account_name": "Yash Invoice Account",
  "amount_due": 7200,
  "amount_paid": 0,
  "amount_remaining": 7200,
  "application_fee_amount": null,
  "attempt_count": 0,
  "attempted": false,
  "auto_advance": false,
  "billing": "charge_automatically",
  "billing_reason": "manual",
  "charge": null,
  "collection_method": "charge_automatically",
  "created": 1574934582,
  "currency": "inr",
  "custom_fields": null,
  "customer": "cus_GAxUJZ68UTDiWk",
  "customer_address": {
  "city": "indore",
    "country": null,
    "line1": "23",
    "line2": null,
    "postal_code": null,
    "state": null
},
  "customer_email": null,
  "customer_name": "kirti",
  "customer_phone": null,
  "customer_shipping": {
  "address": {
    "city": "bhopal",
      "country": null,
      "line1": "20",
      "line2": null,
      "postal_code": null,
      "state": null
  },
  "name": "aksh",
    "phone": null
},
  "customer_tax_exempt": "none",
  "customer_tax_ids": [],
  "default_payment_method": null,
  "default_source": null,
  "default_tax_rates": [],
  "description": null,
  "discount": null,
  "due_date": null,
  "ending_balance": 0,
  "footer": null,
  "hosted_invoice_url": "https://pay.stripe.com/invoice/invst_CaDVdVBphAOxOWp12HI3rEBiah",
  "invoice_pdf": "https://pay.stripe.com/invoice/invst_CaDVdVBphAOxOWp12HI3rEBiah/pdf",
  "lines": {
  "object": "list",
    "data": [
    {
      "id": "ii_1Fjjd3LtNhj8WVidkKTxXCJV",
      "object": "line_item",
      "amount": 2700,
      "currency": "inr",
      "description": "SHIRT-WHITE-XL",
      "discountable": true,
      "invoice_item": "ii_1Fjjd3LtNhj8WVidkKTxXCJV",
      "livemode": false,
      "metadata": {},
      "period": {
        "end": 1574934581,
        "start": 1574934581
      },
      "plan": null,
      "proration": false,
      "quantity": 3,
      "subscription": null,
      "tax_amounts": [],
      "tax_rates": [],
      "type": "invoiceitem"
    },
    {
      "id": "ii_1FjjZVLtNhj8WVidNJQGoQpW",
      "object": "line_item",
      "amount": 2700,
      "currency": "inr",
      "description": "SHIRT-WHITE-XL",
      "discountable": true,
      "invoice_item": "ii_1FjjZVLtNhj8WVidNJQGoQpW",
      "livemode": false,
      "metadata": {},
      "period": {
        "end": 1574934361,
        "start": 1574934361
      },
      "plan": null,
      "proration": false,
      "quantity": 3,
      "subscription": null,
      "tax_amounts": [],
      "tax_rates": [],
      "type": "invoiceitem"
    }
  ],
    "has_more": false,
    "total_count": 2,
    "url": "/v1/invoices/in_1Fjjd4LtNhj8WVidJeDHkKOP/lines"
},
  "livemode": false,
  "metadata": {},
  "next_payment_attempt": null,
  "number": "32032325-0056",
  "paid": false,
  "payment_intent": "pi_1Fjjd5LtNhj8WVid7LpZGHUG",
  "period_end": 1574934582,
  "period_start": 1574934582,
  "post_payment_credit_notes_amount": 0,
  "pre_payment_credit_notes_amount": 0,
  "receipt_number": null,
  "starting_balance": 1800,
  "statement_descriptor": null,
  "status": "open",
  "status_transitions": {
  "finalized_at": 1574934583,
    "marked_uncollectible_at": null,
    "paid_at": null,
    "voided_at": null
},
  "subscription": null,
  "subtotal": 5400,
  "tax": null,
  "tax_percent": null,
  "total": 5400,
  "total_tax_amounts": [],
  "webhooks_delivered_at": 1574934582
}*/
