package com.sg.FlooringMastery.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sg.FlooringMastery.dto.Order;
import com.sg.FlooringMastery.dto.Product;
import com.sg.FlooringMastery.dto.Tax;
import com.sg.FlooringMastery.service.FlooringMasteryServiceLayerImpl;
import com.sg.FlooringMastery.ui.FlooringMasteryView;

@Component
public class FlooringMasteryController {
  FlooringMasteryView view;
  FlooringMasteryServiceLayerImpl service;

  @Autowired
  public FlooringMasteryController(FlooringMasteryView view,
      FlooringMasteryServiceLayerImpl service) {
    this.view = view;
    this.service = service;
  }

  public void run() {
    boolean keepGoing = true;
    service.getOrdersFromFiles();
    while (keepGoing) {
      int selection = view.printMenuAndGetSelection();
      switch (selection) {
        case 1:
          displayOrders();
          break;
        case 2:
          addOrder();
          break;
        case 3:
          editOrder();
          break;
        case 4:
          removeOrder();
          break;
        case 5:
          saveChanges();
          break;
        case 6:
          System.out.println("quit");
          keepGoing = false;
          break;
      }
    }
  }

  private void displayOrders() {
    List<Order> orderList = service.getOrders();
    view.displayOrders(orderList);
  }

  private void addOrder() {
    List<Tax> taxList = service.getTaxes();
    List<Product> productList = service.getProducts();
    Product selectedProduct = view.selectProduct(productList);

    Tax selectedTax = view.selectTax(taxList);
    service.addOrder(view.addOrder(selectedTax, selectedProduct));
  }

  private void removeOrder() {
    service.removeOrder(view.removeOrder(service.getOrders()).getOrderNumber());
  }

  private void editOrder() {
    Order order = view.editOrder(service.getOrders(), service.getProducts(), service.getTaxes());
    if (order != null) {
      System.out.println(order.getCustomerName());
      service.editOrder(order);
    }

  }

  private void saveChanges() {
    service.saveChanges();
  }

}
