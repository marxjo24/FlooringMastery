package com.sg.FlooringMastery.dao;

import java.io.FileNotFoundException;
import java.util.List;
import com.sg.FlooringMastery.dto.Order;
import com.sg.FlooringMastery.dto.Product;
import com.sg.FlooringMastery.dto.Tax;

public interface FlooringMasteryDao {
  public List<Order> getOrders();

  public void addOrder(Order order);

  public Order getOrder(int orderNumber);

  public Order removeOrder(int orderNumber);

  public void saveChanges() throws FileNotFoundException, Exception;

  public void getOrdersFromFiles();

  public void addTax(Tax tax);

  public List<Tax> getTaxes();

  public Tax getTax(String stateAbbreviation);

  public Tax removeTax(String stateAbbreviation);

  public List<Product> getProducts();

  public void addProduct(Product product);

  public Product getProduct(int productId);

  public Product removeProduct(int productId);

  public void editOrder(Order order);
}
