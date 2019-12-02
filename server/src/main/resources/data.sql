insert into public.party (partyid, roletypeid, statusid)
values ('yash.sharma', 'USER', true);
insert into public.person (email, conatactnumber, firstname, lastname, middlename, personaltitle, partyid)
values ('yash.sharma@yash.com', '8989006625', 'Yash', 'Sharma', '', 'Mr.', 'yash.sharma');

insert into public.party (partyid, roletypeid, statusid)
values ('kirit.gurjar', 'USER', true);
insert into public.person (email, conatactnumber, firstname, lastname, middlename, personaltitle, partyid)
values ('kirti.gurjar@yash.com', '8989006625', 'Kirti', 'Gujar', '', 'Ms.', 'kirit.gurjar');

insert into public.status (statusid, description)
values ('Created', 'Order Created');
insert into public.status (statusid, description)
values ('In-Process', 'Order In-Process');
insert into public.status (statusid, description)
values ('Completed', 'Order Completed');
insert into public.status (statusid, description)
values ('Available', 'Item Available');
insert into public.status (statusid, description)
values ('Un-available', 'Item Un-Available');

insert into public.product_price_type (productPriceTypeId, description)
values ('DEFAULT_PRICE', 'Default Price');
insert into public.product_price_type (productPriceTypeId, description)
values ('LIST_PRICE', 'List Price');
insert into public.product_price_type (productPriceTypeId, description)
values ('PROMO_PRICE', 'Promotional Price');
insert into public.product_price_type (productPriceTypeId, description)
values ('SPECIAL_PROMO_PRICE', 'Special Promo Price');

insert into public.product_promo (productpromoid, fromdate, productpromoaction, productpromocode, productpromocond,
                                  promoname, thrudate, uselimitpercode, uselimitpercustomer)
values (1, '2019-11-06', '20', 'DISC20', 'ALL_ITEM', '20 Percent Discount', '2020-11-06', 10, 100);

insert into public.product_type (productTypeId, parentTypeId, childProductType, productId)
values ('SHIRT', null, 'Shirt', null);

insert into public.product_type (productTypeId, parentTypeId, childProductType, productId)
values ('SHIRT-BLACK-M', 'SHIRT', 'Black', 'SHIRT-BLACK-M');
insert into public.inventory_item (inventoryItemId, productId, statusid, quantity)
values ('50001', 'SHIRT-BLACK-M', 'Available', 100);
insert into public.product (productid, description, productdepth, productheight, productname, productweight,
                            productwidth, inventoryitemid)
values ('SHIRT-BLACK-M', 'Mens Cotton Poplin Field Shirt', '1', '1', 'Mens Cotton Poplin', '1', '1',
        '50001');
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-BLACK-M', 'DEFAULT_PRICE', null, null, null, 1000.00);
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-BLACK-M', 'LIST_PRICE', null, null, null, 800.00);

insert into public.product_type (productTypeId, parentTypeId, childProductType, productId)
values ('SHIRT-BLACK-S', 'SHIRT', 'Black', 'SHIRT-BLACK-S');
insert into public.inventory_item (inventoryItemId, productId, statusid, quantity)
values ('50002', 'SHIRT-BLACK-S', 'Available', 99.00);
insert into public.product (productid, description, productdepth, productheight, productname, productweight,
                            productwidth, inventoryitemid)
values ('SHIRT-BLACK-S', 'Ultimate Fishing Shirt, Long-Sleeve', '1', '1', 'Ultimate Fishing Shirt, Long-Sleeve', '1',
        '1', '50002');
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-BLACK-S', 'DEFAULT_PRICE', null, null, null, 2000);
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-BLACK-S', 'LIST_PRICE', null, null, null, 1800);

insert into public.product_type (productTypeId, parentTypeId, childProductType, productId)
values ('SHIRT-BLACK-XL', 'SHIRT', 'Black', 'SHIRT-BLACK-XL');
insert into public.inventory_item (inventoryItemId, productId, statusId, quantity)
values ('50003', 'SHIRT-BLACK-XL', 'Available', 100);
insert into public.product (productid, description, productdepth, productheight, productname, productweight,
                            productwidth, inventoryitemid)
values ('SHIRT-BLACK-XL', 'Womens Tropicwear Shirt, Sleeveless', '1', '1', 'Womens Tropicwear Shirts', '1', '1',
        '50003');
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-BLACK-XL', 'DEFAULT_PRICE', null, null, null, 3000);
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-BLACK-XL', 'LIST_PRICE', null, null, null, 2800);

insert into public.product_type (productTypeId, parentTypeId, childProductType, productId)
values ('SHIRT-WHITE-M', 'SHIRT', 'White', 'SHIRT-WHITE-M');
insert into public.inventory_item (inventoryItemId, productId, statusId, quantity)
values ('50004', 'SHIRT-WHITE-M', 'Available', 100);
insert into public.product (productid, description, productdepth, productheight, productname, productweight,
                            productwidth, inventoryitemid)
values ('SHIRT-WHITE-M', 'Tropicwear Shirt, Short-Sleeve', '1', '1', 'Tropicwear Shirt, Short-Sleeve', '1', '1',
        '50004');
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-WHITE-M', 'DEFAULT_PRICE', null, null, null, 1000);
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-WHITE-M', 'LIST_PRICE', null, null, null, 900);

insert into public.product_type (productTypeId, parentTypeId, childProductType, productId)
values ('SHIRT-WHITE-S', 'SHIRT', 'White', 'SHIRT-WHITE-S');
insert into public.inventory_item (inventoryItemId, productId, statusId, quantity)
values ('50005', 'SHIRT-WHITE-S', 'Available', 100);
insert into public.product (productid, description, productdepth, productheight, productname, productweight,
                            productwidth, inventoryitemid)
values ('SHIRT-WHITE-S', 'Womens No Fly Zone Shirt', '1', '1', 'Womens No Fly Zone Shirt', '1', '1',
        '50005');
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-WHITE-S', 'DEFAULT_PRICE', null, null, null, 1500);
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-WHITE-S', 'LIST_PRICE', null, null, null, 1200);

insert into public.product_type (productTypeId, parentTypeId, childProductType, productId)
values ('SHIRT-WHITE-XL', 'SHIRT', 'White', 'SHIRT-WHITE-XL');
insert into public.inventory_item (inventoryItemId, productId, statusId, quantity)
values ('50006', 'SHIRT-WHITE-XL', 'Available', 100);
insert into public.product (productid, description, productdepth, productheight, productname, productweight,
                            productwidth, inventoryitemid)
values ('SHIRT-WHITE-XL', 'Mens Cotton Poplin Field Shirt', '1', '1', 'Mens Cotton Poplin Field Shirt', '1', '1',
        '50006');
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-WHITE-XL', 'DEFAULT_PRICE', null, null, null, 900);
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-WHITE-XL', 'LIST_PRICE', null, null, null, 700);

insert into public.product_type (productTypeId, parentTypeId, childProductType, productId)
values ('SHIRT-GREEN-M', 'SHIRT', 'Green', 'SHIRT-GREEN-M');
insert into public.inventory_item (inventoryItemId, productId, statusId, quantity)
values ('50007', 'SHIRT-GREEN-M', 'Available', 100);
insert into public.product (productid, description, productdepth, productheight, productname, productweight,
                            productwidth, inventoryitemid)
values ('SHIRT-GREEN-M', 'Infant and Toddler Girls Sea Spray Ruffle Surf Shirt', '1', '1', 'Infant and Toddler Girls ',
        '1', '1', '50007');
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-GREEN-M', 'DEFAULT_PRICE', null, null, null, 1700);
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-GREEN-M', 'LIST_PRICE', null, null, null, 1500);

insert into public.product_type (productTypeId, parentTypeId, childProductType, productId)
values ('SHIRT-GREEN-S', 'SHIRT', 'Green', 'SHIRT-GREEN-S');
insert into public.inventory_item (inventoryItemId, productId, statusId, quantity)
values ('50008', 'SHIRT-GREEN-S', 'Available', 100);
insert into public.product (productid, description, productdepth, productheight, productname, productweight,
                            productwidth, inventoryitemid)
values ('SHIRT-GREEN-S', 'Infants and Toddlers BeanSport Surf Shirt, Long-Sleeve', '1', '1',
        'Infants and Toddlers , Long-Sleeve', '1', '1', '50008');
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-GREEN-S', 'DEFAULT_PRICE', null, null, null, 6000);
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-GREEN-S', 'LIST_PRICE', null, null, null, 5500);

insert into public.product_type (productTypeId, parentTypeId, childProductType, productId)
values ('SHIRT-GREEN-XL', 'SHIRT', 'Green', 'SHIRT-GREEN-XL');
insert into public.inventory_item (inventoryItemId, productId, statusId, quantity)
values ('50009', 'SHIRT-GREEN-XL', 'Available', 100);
insert into public.product (productid, description, productdepth, productheight, productname, productweight,
                            productwidth, inventoryitemid)
values ('SHIRT-GREEN-XL', 'Girls Sun-and-Surf Shirt, Long-Sleeve Stripe', '1', '1', 'Girls Sun-and-Surf Shirt', '1',
        '1', '50009');
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-GREEN-XL', 'DEFAULT_PRICE', null, null, null, 1900);
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-GREEN-XL', 'LIST_PRICE', null, null, null, 1700);

insert into public.product_type (productTypeId, parentTypeId, childProductType, productId)
values ('SHIRT-YELLOW-M', 'SHIRT', 'Yellow', 'SHIRT-YELLOW-M');
insert into public.inventory_item (inventoryItemId, productId, statusId, quantity)
values ('50010', 'SHIRT-YELLOW-M', 'Available', 100);
insert into public.product (productid, description, productdepth, productheight, productname, productweight,
                            productwidth, inventoryitemid)
values ('SHIRT-YELLOW-M', 'Kids Sun-and-Surf Shirt, Short Sleeve, Graphic', '1', '1', 'Kids Sun-and-Surf Shirt', '1',
        '1', '50010');
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-YELLOW-M', 'DEFAULT_PRICE', null, null, null, 1900);
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-YELLOW-M', 'LIST_PRICE', null, null, null, 1800);

insert into public.product_type (productTypeId, parentTypeId, childProductType, productId)
values ('SHIRT-YELLOW-S', 'SHIRT', 'Yellow', 'SHIRT-YELLOW-S');
insert into public.inventory_item (inventoryItemId, productId, statusId, quantity)
values ('50011', 'SHIRT-YELLOW-S', 'Available', 100);
insert into public.product (productid, description, productdepth, productheight, productname, productweight,
                            productwidth, inventoryitemid)
values ('SHIRT-YELLOW-S', 'Cresta Trail Shirt', '1', '1', 'Cresta Trail Shirt', '1', '1',
        '50011');
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-YELLOW-S', 'DEFAULT_PRICE', null, null, null, 5700);
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-YELLOW-S', 'LIST_PRICE', null, null, null, 4200);

insert into public.product_type (productTypeId, parentTypeId, childProductType, productId)
values ('SHIRT-YELLOW-XL', 'SHIRT', 'Yellow', 'SHIRT-YELLOW-XL');
insert into public.inventory_item (inventoryItemId, productId, statusId, quantity)
values ('50012', 'SHIRT-YELLOW-XL', 'Available', 100);
insert into public.product (productid, description, productdepth, productheight, productname, productweight,
                            productwidth, inventoryitemid)
values ('SHIRT-YELLOW-XL', 'Mens NRS HydroSkin .5mm Shirt, Short-Sleeve', '1', '1', 'Mens NRS HydroSkin .5mm Shirt',
        '1', '1', '50012');
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-YELLOW-XL', 'DEFAULT_PRICE', null, null, null, 4200);
insert into public.product_price (productId, productPriceTypeId, description, fromDate, thruDate, price)
values ('SHIRT-YELLOW-XL', 'LIST_PRICE', null, null, null, 4100);
