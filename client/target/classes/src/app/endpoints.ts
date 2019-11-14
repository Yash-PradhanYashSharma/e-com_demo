export class Endpoints {
  private static host = 'localhost';
  /*private static host = '10.26.8.107';*/
  public static party = `http://${Endpoints.host}:8080/party/all`;
  public static cartUrl = `http://${Endpoints.host}:8081/v1/api/shipments/cart`;
  public static orderUrl = `http://${Endpoints.host}:8081/v1/api/shipments/order`;
  public static invoiceUrl = `http://${Endpoints.host}:8081/v1/api/shipments/invoice`;
}
