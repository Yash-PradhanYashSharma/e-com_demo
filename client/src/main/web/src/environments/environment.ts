export const environment = {
  production: false,
  /*    initializeCart: 'http://localhost:8080/cart/initialize',
      updateCart: 'http://localhost:8080/cart/update',
      getShipmentDetails: 'http://localhost:8080/shipment/getFreight',
      productSearch: 'http://localhost:8080/product/search',
      serverLogin: `http://localhost:8080/login`,
      userDetails: `https://dev-589498.okta.com/oauth2/default/v1/userinfo`,
      party: 'http://localhost:8080/party/all',
      partyDetails: 'http://localhost:8080/api/party/all',
      order: 'http://localhost:8080/order/create',
      getUserOrder: 'http://localhost:8080/order/getOrders',
      getOrder: 'http://localhost:8080/order/getOrder',
      cartUrl: 'http://10.26.9.41:8081/v1/api/shipments/cart',
      orderUrl: 'http://10.26.9.41:8081/v1/api/shipments/order',
      invoiceUrl: 'http://10.26.9.41:8081/v1/api/shipments/invoice',*/

  host: '10.26.9.41',
  initializeCart: 'http://10.26.9.41:8081/api/cart/init',
  productSearch: 'http://10.26.9.41:8081/api/cart/search',
  updateCart: 'http://10.26.9.41:8081/api/cart/refresh',
  getShipmentDetails: 'http://10.26.9.41:8081/api/cart/freight',
  order: 'http://10.26.9.41:8081/api/order/create',
  /*getOrder: 'http://10.26.9.41:8081/api/order',*/
  getOrder: 'http://10.26.9.41:8083/v1/sys/api/order',
  /*getOrder: 'http://localhost:8080/order/getOrderSpring',*/
  getUserOrder: 'http://10.26.9.41:8081/api/order/list',
  invoiceUrl: 'http://10.26.9.41:8081/api/invoice',
  serverLogin: `http://10.26.9.41:8080/login`,
  partyDetails: 'http://10.26.9.41:8080/api/party/all',
  party: 'http://10.26.9.41:8080/party/all',
  userDetails: `https://dev-589498.okta.com/oauth2/default/v1/userinfo`,
};
