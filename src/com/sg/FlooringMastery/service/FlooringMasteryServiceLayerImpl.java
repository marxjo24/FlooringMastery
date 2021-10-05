package com.sg.FlooringMastery.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sg.FlooringMastery.dao.FlooringMasteryDaoFileImpl;
import com.sg.FlooringMastery.dto.Order;
import com.sg.FlooringMastery.dto.Product;
import com.sg.FlooringMastery.dto.Tax;

@Component
public class FlooringMasteryServiceLayerImpl implements FlooringMasteryServiceLayer {

  private FlooringMasteryDaoFileImpl dao;

  @Autowired
  public FlooringMasteryServiceLayerImpl(FlooringMasteryDaoFileImpl dao) {
    this.dao = dao;
  }

  public List<Order> getOrders() {
    return dao.getOrders();
  }

  public void addOrder(Order order) {
    dao.addOrder(order);

  }

  public Order getOrder(int orderNumber) {
    return dao.getOrder(orderNumber);
  }

  public Order removeOrder(int orderNumber) {
    return dao.removeOrder(orderNumber);
  }

  public void saveChanges() {
    dao.saveChanges();
  }

  public void getOrdersFromFiles() {
    dao.getOrdersFromFiles();
  }

  @Override
  public boolean addTax(Tax tax) {
    if (dao.getTaxes().contains(tax)) {
      return false;
    } else {
      dao.addTax(tax);
      return true;
    }
  }

  @Override
  public List<Tax> getTaxes() {
    return dao.getTaxes();
  }

  @Override
  public Tax getTax(String stateAbbreviation) {
    return dao.getTax(stateAbbreviation);
  }

  @Override
  public Tax removeTax(String stateAbbreviation) {
    return dao.removeTax(stateAbbreviation);
  }

  @Override
  public List<Product> getProducts() {
    return dao.getProducts();
  }

  @Override
  public void addProduct(Product product) {
    dao.addProduct(product);
  }

  @Override
  public Product getProduct(int productId) {
    return dao.getProduct(productId);
  }

  @Override
  public Product removeProduct(int productId) {
    return dao.removeProduct(productId);
  }

  @Override
  public void editOrder(Order order) {
    dao.editOrder(order);

  }

}
