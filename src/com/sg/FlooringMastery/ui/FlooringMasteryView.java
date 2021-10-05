package com.sg.FlooringMastery.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sg.FlooringMastery.dto.Order;
import com.sg.FlooringMastery.dto.Product;
import com.sg.FlooringMastery.dto.Tax;

@Component
public class FlooringMasteryView {
  private UserIOConsoleImpl io;

  @Autowired
  public FlooringMasteryView(UserIOConsoleImpl io) {
    this.io = io;
  }

  public int printMenuAndGetSelection() {
    io.print("Flooring Program");
    io.print("1. Display Orders");
    io.print("2. Add an Order");
    io.print("3. Edit an Order");
    io.print("4. Remove an Order");
    io.print("5. Export All Data");
    io.print("6. Quit/n");

    int selection = io.readInt("Select an option", 1, 6);
    return selection;
  }

  public void displayOrders(List<Order> orderList) {
    io.print("Orders");
    for (Order order : orderList) {
      io.print(order.toString());
    }
    if (orderList.isEmpty()) {
      io.print("There are No Orders");
    }
  }

  public Product selectProduct(List<Product> productList) {
    if (productList.isEmpty()) {
      io.print("There are no Available Products.");
      return null;
    } else {
      io.print("Available Products");
      int counter = 1;
      for (Product product : productList) {
        io.print(counter + ". " + product.toString());
        counter++;
      }
      int selection =
          io.readInt("Select a Product or " + counter + " to add another product", 1, counter);
      if (selection == counter) {
        return null;
      } else {
        return productList.get(selection - 1);
      }
    }
  }

  public Tax selectTax(List<Tax> taxList) {
    if (taxList.isEmpty()) {
      io.print("There are no Available State Taxes.");
      return null;
    } else {
      io.print("Available State Taxes");
      int counter = 1;
      for (Tax tax : taxList) {
        io.print(counter + ". " + tax.toString());
        counter++;
      }
      int selection =
          io.readInt("Select a State Tax or " + counter + " to add another state", 1, counter);
      if (selection == counter) {
        return null;
      } else {
        return taxList.get(selection - 1);
      }
    }
  }

  public Order addOrder(Tax tax, Product product) {
    io.print("Creating New Order");
    String customerName;
    BigDecimal area;
    customerName = io.readString("Enter the customer's name");
    area = new BigDecimal(io.readDouble("Enter the area", 1, Double.MAX_VALUE));
    LocalDate date = io.readDate("Enter the date for when the order will take place");
    Order newOrder = new Order(product, tax, customerName, area);
    newOrder.setDate(date);
    return newOrder;
  }

  /*
   * public Tax addTax() { io.print("Creating New Tax Information"); boolean stateIsCorrect = false;
   * State state = null; while (!stateIsCorrect) { state =
   * io.readState("Enter the abbreviation for the state"); io.print("Selected State: " +
   * state.getName()); stateIsCorrect = io.readYesOrNo("Is the Selected State Correct?"); }
   * BigDecimal taxRate = new BigDecimal(io.readDouble("Enter the Tax Rate", 0.01, 100)); Tax tax =
   * new Tax(); tax.setState(state); tax.setTaxRate(taxRate); return tax; }
   * 
   * public Product addProduct() { io.print("Creating New Prouct"); String productType =
   * io.readString("Enter the Prouct Type"); BigDecimal costPerSquareFoot = new
   * BigDecimal(io.readDouble("Enter the Cost Per Square Foot", 0.01, Double.MAX_VALUE)); BigDecimal
   * LaborCostPerSquareFoot = new BigDecimal(io.readDouble("Enter the Labor Cost Per Square Foot"));
   * Product product = new Product(); product.setProductType(productType);
   * product.setCostPerSquareFoot(costPerSquareFoot);
   * product.setLaborCostPerSquareFoot(LaborCostPerSquareFoot); return product; }
   */
  public Order removeOrder(List<Order> orderList) {
    io.print("=== Remove Order ===");
    if (orderList.isEmpty()) {
      io.print("There are no Available Orders to Remove");
      return null;
    } else {
      int count = 1;
      for (Order order : orderList) {
        io.print(count + ". " + order.toString());
        count++;
      }
      int selection =
          io.readInt("Select an order to remove. Select " + count + " to cancel.", 1, count);
      if (selection == count) {
        return null;
      } else {
        return orderList.get(selection - 1);
      }
    }
  }

  public Order editOrder(List<Order> orderList, List<Product> productList, List<Tax> taxList) {
    io.print("=== Edit Order ===");
    if (orderList.isEmpty()) {
      io.print("There are no Available Order to Edit");
      return null;
    } else {
      int count = 1;
      for (Order order : orderList) {
        io.print(count + ". " + order.toString());
        count++;
      }
      int selection =
          io.readInt("Select an Order to Edit. Select " + count + " to cancel", 1, count);
      if (selection == count) {
        return null;
      } else {
        Order selectedOrder = orderList.get(selection - 1);
        Product product = null;
        while (product == null) {
          product = selectProduct(productList);
        }
        selectedOrder.setProduct(product);
        Tax tax = null;
        while (tax == null) {
          tax = selectTax(taxList);
        }
        if (io.readYesOrNo("Would you like to change the customer name?\nCurrent Customer Name: "
            + selectedOrder.getCustomerName())) {
          selectedOrder.setCustomerName(io.readString("Enter the customer's name"));
          System.out.println(selectedOrder.getCustomerName());
        }
        if (io.readYesOrNo(
            "Would you like to change the area?\nCurrent Area: " + selectedOrder.getArea())) {
          selectedOrder
              .setArea(new BigDecimal(io.readDouble("Enter the new Area", 1, Double.MAX_VALUE)));
        }
        return selectedOrder;
      }
    }
  }
}
