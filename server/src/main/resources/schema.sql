CREATE TABLE Status
(
    statusId    VARCHAR(20),
    description VARCHAR(200),
    PRIMARY KEY (statusId)
);

CREATE TABLE Product_Type
(
    productTypeId    VARCHAR(20),
    parentTypeId     VARCHAR(20),
    childProductType VARCHAR(20),
    productId        VARCHAR(20),
    PRIMARY KEY (productTypeId)
);

CREATE TABLE Product_Price_Type
(
    productPriceTypeId VARCHAR(20),
    description        VARCHAR(250),
    PRIMARY KEY (productPriceTypeId)
);

CREATE TABLE Inventory_Item
(
    inventoryItemId VARCHAR(20),
    productId       VARCHAR(20),
    statusId        VARCHAR(20),
    quantity        NUMERIC default 0,
    PRIMARY KEY (inventoryItemId),
    FOREIGN KEY (statusId) REFERENCES Status (statusId)
);

CREATE TABLE Product
(
    productId       VARCHAR(20),
    productTypeId   VARCHAR(20),
    productName     VARCHAR(50) NOT NULL,
    description     VARCHAR(250),
    productWidth    DECIMAL(3, 2),
    productDepth    DECIMAL(3, 2),
    productHeight   DECIMAL(3, 2),
    productWeight   DECIMAL(3, 2),
    InventoryItemId VARCHAR(20),
    PRIMARY KEY (productId),
    FOREIGN KEY (productTypeId) REFERENCES Product_Type (productTypeId),
    FOREIGN KEY (InventoryItemId) REFERENCES Inventory_Item (inventoryItemId)
);

CREATE TABLE Product_Promo
(
    productPromoId      VARCHAR(20),
    promoName           VARCHAR(50),
    productPromoCode    VARCHAR(20),
    useLimitPerCode     VARCHAR(20),
    useLimitPerCustomer VARCHAR(20),
    fromDate            date,
    thruDate            date,
    productPromoCond    VARCHAR(50),
    productPromoAction  VARCHAR(50),
    PRIMARY KEY (productPromoId)
);

CREATE TABLE Product_Price
(
    productId          VARCHAR(20),
    productPriceTypeId VARCHAR(20),
    description        VARCHAR(250),
    fromDate           DATE,
    thruDate           DATE,
    price              DECIMAL(10, 2) DEFAULT 0.00,
    FOREIGN KEY (productPriceTypeId) REFERENCES Product_Price_Type (productPriceTypeId),
    FOREIGN KEY (productId) REFERENCES Product (productId),
    PRIMARY KEY (productId, productPriceTypeId)
);

CREATE TABLE Order_Status
(
    orderStatusId VARCHAR(20),
    orderId       VARCHAR(20),
    statusId      varchar(20),
    updatedDate   DATE,
    PRIMARY KEY (orderStatusId),
    FOREIGN KEY (statusId) REFERENCES Status (statusId)
);

CREATE TABLE Orders
(
    orderId       VARCHAR(20),
    orderDate     DATE,
    orderStatusId VARCHAR(20),
    total         DECIMAL(3, 2),
    PRIMARY KEY (orderId),
    FOREIGN KEY (orderStatusId) references Order_Status (orderStatusId)
);

CREATE TABLE Order_Item
(
    orderId        VARCHAR(20),
    orderItemSeqId NUMERIC,
    productId      VARCHAR(20) NOT NULL,
    quantity       VARCHAR(20) NOT NULL,
    unitPrice      DECIMAL(3, 2),
    PRIMARY KEY (orderId, orderItemSeqId),
    FOREIGN KEY (orderId) references Orders (orderId)
);

CREATE TABLE Order_Adjustment
(
    orderAdjustmentId VARCHAR(20),
    orderId           VARCHAR(20),
    orderItemSeqId    NUMERIC,
    amount            decimal(3, 2),
    productPromoId    VARCHAR(20),
    PRIMARY KEY (orderAdjustmentId),
    FOREIGN KEY (orderId, orderItemSeqId) REFERENCES Order_Item (orderId, orderItemSeqId)
);

CREATE TABLE Party
(
    partyId    VARCHAR(20),
    statusId   boolean,
    roleTypeId VARCHAR(20),
    PRIMARY KEY (partyId)
);

CREATE TABLE Person
(
    lastName       VARCHAR(30),
    middleName     VARCHAR(30),
    firstName      VARCHAR(30),
    email          VARCHAR(50),
    conatactNumber VARCHAR(20),
    personalTitle  VARCHAR(10),
    partyId        VARCHAR(20),
    PRIMARY KEY (email),
    FOREIGN KEY (partyId) REFERENCES Party (partyId)
);

CREATE TABLE Order_Role
(
    orderId    VARCHAR(20),
    partyId    VARCHAR(20),
    roleTypeId VARCHAR(20),
    FOREIGN KEY (orderId) REFERENCES Orders (orderId),
    FOREIGN KEY (partyId) REFERENCES Party (partyId),
    PRIMARY KEY (orderId, partyId)
);
