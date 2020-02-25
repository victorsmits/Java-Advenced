import javax.persistence.*;

@Entity
@Table(name = "Product")
public class Product {
  @Id
  @GeneratedValue
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "price")
  private int price;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getId() {
    return id;
  }
}
