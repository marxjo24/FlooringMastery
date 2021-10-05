package com.sg.FlooringMastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
  private Product product;
  private Tax tax;
  private int orderNumber;
  private String customerName;
  private BigDecimal area;
  private BigDecimal materialCost;
  private BigDecimal laborCost;
  private BigDecimal taxTotal;
  private BigDecimal total;
  private LocalDate date;

  public Order(Product product, Tax tax, String customerName, BigDecimal area) {
    this.product = product;
    this.tax = tax;
    this.customerName = customerName;
    this.area = area;
    calculateMaterialCost();
    calculateLaborCost();
    calculateTax();
    calculateTotal();
  }

  public Order() {
    // Used for Testing only
  }

  public void calculateMaterialCost() {
    materialCost = area.multiply(product.getCostPerSquareFoot());
  }

  public void calculateLaborCost() {
    laborCost = area.multiply(product.getLaborCostPerSquareFoot());
  }

  public void calculateTax() {
    BigDecimal part1 = materialCost.add(laborCost);
    BigDecimal part2 = tax.getTaxRate().divide(new BigDecimal(100));
    taxTotal = part1.multiply(part2);
  }

  public void calculateTotal() {
    BigDecimal part1 = materialCost.add(laborCost);
    total = part1.add(taxTotal);
  }

  public String toString() {
    return "Order Number: " + orderNumber + " " + product.toString() + " " + tax.toString()
        + " Cutomer Name: " + customerName + " Area: " + area;
  }

  public Product getProduct() {
    return product;
  }

  public Tax getTax() {
    return tax;
  }

  public int getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(int orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getCustomerName() {
    return customerName;
  }

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public BigDecimal getMaterialCost() {
    return materialCost;
  }

  public BigDecimal getLaborCost() {
    return laborCost;
  }

  public BigDecimal getTaxTotal() {
    return taxTotal;
  }


  public BigDecimal getTotal() {
    return total;
  }

  public BigDecimal getArea() {
    return area;
  }

  public void setArea(BigDecimal area) {
    this.area = area;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setTax(Tax tax) {
    this.tax = tax;
  }

  public String toFileString() {
    String laborSquare = String.format("%.2f", product.getLaborCostPerSquareFoot());
    String costSquare = String.format("%.2f", product.getCostPerSquareFoot());
    String material = String.format("%.2f", materialCost);
    String labor = String.format("%.2f", laborCost);
    String taxString = String.format("%.2f", taxTotal);
    String totalString = String.format("%.2f", total);

    return orderNumber + "," + customerName + "," + tax.getState().getAbbreviation() + ","
        + tax.getTaxRate() + "," + product.getProductType() + "," + area + "," + costSquare + ","
        + laborSquare + "," + material + "," + labor + "," + taxString + "," + totalString;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date2) {
    this.date = date2;
  }



}
