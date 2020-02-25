import com.fasterxml.classmate.AnnotationConfiguration;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.metamodel.EntityType;

import java.util.Map;

public class Main {
  private static SessionFactory factory;

  static {
    try {
      Configuration configuration = new Configuration();
      configuration.configure();
      configuration.addAnnotatedClass(Product.class);

      factory = configuration.buildSessionFactory();
    } catch (Throwable ex) {
      throw new ExceptionInInitializerError(ex);
    }
  }

  public static void main(final String[] args) throws Exception {

    Main ME = new Main();

    /* Add few employee records in database */
//    Integer prodID1 = ME.addProduct("IPhone", 1000);
//    Integer prodID2 = ME.addProduct("Mac", 5000);

    /* List down all the products */
    ME.listProduct();

    /* Update employee's records */
//    ME.updatePrice(prodID1, 5000);

    /* Delete an employee from the database */
//    ME.deleteProduct(prodID2);

    /* List down new list of the products */
//    ME.listProduct();

    ME.getProduct(2);

    ME.getProductCheaperThan(2000);
  }

  /* Method to CREATE an employee in the database */
  public Integer addProduct(String name, int price){
    Session session = factory.openSession();
    Transaction tx = null;
    Integer employeeID = null;

    try {
      tx = session.beginTransaction();
      Product product = new Product();
      product.setName(name);
      product.setPrice(price);
      employeeID = (Integer) session.save(product);
      tx.commit();
    } catch (HibernateException e) {
      if (tx!=null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
    return employeeID;
  }

  /* Method to  READ all the products */
  public void listProduct( ){
    Session session = factory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();
      List products = session.createQuery("FROM Product").list();
      for (Iterator iterator = products.iterator(); iterator.hasNext();){
        Product product = (Product) iterator.next();
        System.out.print("\nName: " + product.getName());
        System.out.print("  Price: " + product.getPrice());
      }
      tx.commit();
    } catch (HibernateException e) {
      if (tx!=null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  /* Method to UPDATE salary for an employee */
  public void updatePrice(Integer EmployeeID, int salary ){
    Session session = factory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();
      Product product = (Product)session.get(Product.class, EmployeeID);
      product.setPrice( salary );
      session.update(product);
      tx.commit();
    } catch (HibernateException e) {
      if (tx!=null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  /* Method to DELETE an employee from the records */
  public void deleteProduct(Integer EmployeeID){
    Session session = factory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();
      Product product = (Product)session.get(Product.class, EmployeeID);
      session.delete(product);
      tx.commit();
    } catch (HibernateException e) {
      if (tx!=null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  /* Method to  READ one the product */
  public void getProduct(Integer id ){
    Session session = factory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();
      List products =
          session.createQuery("FROM Product p WHERE p.id = "+ id).list();
      for (Iterator iterator = products.iterator(); iterator.hasNext();){
        Product product = (Product) iterator.next();
        System.out.print("\nName: " + product.getName());
        System.out.print("  Price: " + product.getPrice());
      }
      tx.commit();
    } catch (HibernateException e) {
      if (tx!=null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }

  /* Method to READ one the product which price is less than*/
  public void getProductCheaperThan(Integer price ){
    Session session = factory.openSession();
    Transaction tx = null;

    try {
      tx = session.beginTransaction();
      List products =
          session.createQuery("FROM Product p WHERE p.price < "+ price).list();
      for (Iterator iterator = products.iterator(); iterator.hasNext();){
        Product product = (Product) iterator.next();
        System.out.print("\nName: " + product.getName());
        System.out.print("  Price: " + product.getPrice());
      }
      tx.commit();
    } catch (HibernateException e) {
      if (tx!=null) tx.rollback();
      e.printStackTrace();
    } finally {
      session.close();
    }
  }
}
