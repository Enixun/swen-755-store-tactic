package product;


public class Product {
  private static int ID_ITERATOR = 1000;

  private static int getNextId() {
    return Product.ID_ITERATOR += 1;
  }

  private int id;
  private String name;
  private double baseCost;
  public long creationTimestamp;

  public Product (String name, double baseCost) {
    this.id = Product.getNextId();
    this.name = name;
    this.baseCost = baseCost;
    this.creationTimestamp = System.currentTimeMillis();
  }

  public int id() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getBaseCost() {
    return baseCost;
  }

  public void setBaseCost(double baseCost) {
    this.baseCost = baseCost;
  }

  public long getCreationTimeStamp(){
    return creationTimestamp;
  }

  @Override
  public String toString() {
    return (
      "Product(" + 
      "id:" + id + "," +
      "name:" + name + "," +
      "baseCost:" + baseCost + 
      ")"
    );
  }
}