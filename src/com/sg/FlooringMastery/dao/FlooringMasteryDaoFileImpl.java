package com.sg.FlooringMastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.sg.FlooringMastery.dto.Order;
import com.sg.FlooringMastery.dto.Product;
import com.sg.FlooringMastery.dto.State;
import com.sg.FlooringMastery.dto.Tax;

@Component
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {

  Map<Integer, Order> orders = new HashMap<Integer, Order>();
  Map<State, Tax> taxes = new HashMap<State, Tax>();
  Map<Integer, Product> products = new HashMap<Integer, Product>();

  public List<Order> getOrders() {
    List<Order> orderList = new ArrayList<Order>();
    orders.forEach((k, v) -> {
      orderList.add(v);
    });
    return orderList;
  }

  public void addOrder(Order order) {
    boolean containsOrder = true;
    int increment = 0;
    while (containsOrder) {
      increment++;
      containsOrder = orders.containsKey(increment);
    }
    order.setOrderNumber(increment);
    orders.put(increment, order);
  }

  public Order getOrder(int orderNumber) {
    return orders.get(orderNumber);
  }

  public Order removeOrder(int orderNumber) {
    return orders.remove(orderNumber);
  }

  public void saveChanges() {
    orders.forEach((k, v) -> {
      LocalDate date = v.getDate();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
      String dateTimeStamp = date.format(formatter);
      Scanner sc;
      String oldOrder = "";
      try {
        sc = new Scanner(new BufferedReader(
            new FileReader("src/com/sg/FlooringMastery/data/Orders_" + dateTimeStamp + ".txt")));
        while (sc.hasNextLine()) {
          String currentLine = sc.nextLine();
          oldOrder += currentLine + "\n";
        }
      } catch (FileNotFoundException e) {
        PrintWriter out;
        try {
          out = new PrintWriter(
              new FileWriter("src/com/sg/FlooringMastery/data/Orders_" + dateTimeStamp + ".txt"));
          out.write("");
          out.close();
        } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }

      }

      PrintWriter out;
      try {
        out = new PrintWriter(
            new FileWriter("src/com/sg/FlooringMastery/data/Orders_" + dateTimeStamp + ".txt"));
        out.write(oldOrder);
        out.write(v.toFileString());
        out.close();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }



    });
  }

  public void getOrdersFromFiles() {
    try {
      Scanner sc = new Scanner(
          new BufferedReader(new FileReader("src/com/sg/FlooringMastery/data/Taxes.txt")));
      while (sc.hasNextLine()) {
        String currentLine = sc.nextLine();
        String abbreviation = currentLine.substring(0, currentLine.indexOf(","));
        currentLine = currentLine.substring(currentLine.indexOf(",") + 1, currentLine.length());
        String stateName = currentLine.substring(0, currentLine.indexOf(","));
        double taxRate = Double
            .parseDouble(currentLine.substring(currentLine.indexOf(",") + 1, currentLine.length()));
        Tax tax = new Tax();
        State state = null;
        abbreviation.trim().toUpperCase();
        for (State st : State.values()) {
          if (abbreviation.equalsIgnoreCase(st.getAbbreviation())) {
            state = st;
          }
        }
        tax.setState(state);
        tax.setTaxRate(BigDecimal.valueOf(taxRate));
        taxes.put(state, tax);
      }
    } catch (FileNotFoundException e) {
      System.out.println("Failed to read Taxes.txt file");
    }

    try {
      int counter = 1;
      Scanner sc = new Scanner(
          new BufferedReader(new FileReader("src/com/sg/FlooringMastery/data/Products.txt")));
      while (sc.hasNextLine()) {
        String currentLine = sc.nextLine();
        String productType = currentLine.substring(0, currentLine.indexOf(","));
        currentLine = currentLine.substring(currentLine.indexOf(",") + 1, currentLine.length());
        Double costPerSquareFoot =
            Double.parseDouble(currentLine.substring(0, currentLine.indexOf(",")));
        double labor = Double
            .parseDouble(currentLine.substring(currentLine.indexOf(",") + 1, currentLine.length()));
        Product product = new Product();
        product.setProductType(productType);
        product.setCostPerSquareFoot(new BigDecimal(costPerSquareFoot));
        product.setLaborCostPerSquareFoot(new BigDecimal(labor));
        products.put(counter, product);
        counter++;
      }
    } catch (FileNotFoundException e) {
      System.out.println("Failed to load file");
    }

  }

  @Override
  public void addTax(Tax tax) {
    taxes.put(tax.getState(), tax);

  }

  @Override
  public List<Tax> getTaxes() {
    List<Tax> taxList = new ArrayList<Tax>();
    taxes.forEach((k, v) -> {
      taxList.add(v);
    });
    return taxList;
  }

  @Override
  public Tax getTax(String stateAbbreviation) {
    return taxes.get(stateAbbreviation);
  }

  @Override
  public Tax removeTax(String stateAbbreviation) {
    return taxes.remove(stateAbbreviation);
  }

  @Override
  public List<Product> getProducts() {
    List<Product> productList = new ArrayList<Product>();
    products.forEach((k, v) -> {
      productList.add(v);
    });
    return productList;
  }

  @Override
  public void addProduct(Product product) {
    boolean containsOrder = true;
    int increment = 0;
    while (containsOrder) {
      increment++;
      containsOrder = products.containsKey(increment);
    }
    product.setProductId(increment);
    products.put(increment, product);
  }

  @Override
  public Product getProduct(int productId) {
    return products.get(productId);
  }

  @Override
  public Product removeProduct(int productId) {
    return products.remove(productId);
  }

  @Override
  public void editOrder(Order order) {
    System.out.println(order.getCustomerName());
    orders.remove(order.getOrderNumber());
    orders.put(order.getOrderNumber(), order);

  }


}
