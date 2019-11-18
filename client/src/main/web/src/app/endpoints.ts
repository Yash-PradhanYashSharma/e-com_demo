export class Endpoints {
  private static host = 'localhost';
  private static hostExternal = '10.26.9.41';
  private static OKTA = 'dev-589498.okta.com';

  public static party = `http://${Endpoints.host}:8080/party/all`;
  public static cartUrl = `http://${Endpoints.host}:8081/v1/api/shipments/cart`;
  public static orderUrl = `http://${Endpoints.host}:8081/v1/api/shipments/order`;
  public static invoiceUrl = `http://${Endpoints.host}:8081/v1/api/shipments/invoice`;
  public static serverLogin = `http://${Endpoints.host}:8080/login`;
  public static partyDetails = `http://${Endpoints.host}:8080/api/party/all`;
  public static initializeCart = `http://${Endpoints.host}:8080/cart/initialize`;
  public static updateCart = `http://${Endpoints.host}:8080/cart/update`;
  public static productSearch = `http://${Endpoints.host}:8080/product/search`;
  public static createOrder = `http://${Endpoints.host}:8081/order/createOrder`;

  public static userDetails = `http://${Endpoints.OKTA}/api/v1/users/me`;

}
