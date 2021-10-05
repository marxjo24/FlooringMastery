package com.sg.FlooringMastery.dto;

import java.math.BigDecimal;

public class Product {
  private int productId;
  private String productType;
  private BigDecimal costPerSquareFoot;
  private BigDecimal laborCostPerSquareFoot;

  public String getProductType() {
    return productType;
  }

  public void setProductType(String productType) {
    this.productType = productType;
  }

  public BigDecimal getCostPerSquareFoot() {
    return costPerSquareFoot;
  }

  public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
    this.costPerSquareFoot = costPerSquareFoot;
  }

  public String toString() {
    return "Product Type: " + productType + " Cost Per Square Foot: $"
        + String.format("%.2f", costPerSquareFoot) + " Labor Cost Per Square Foot: $"
        + String.format("%.2f", laborCostPerSquareFoot);
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
  }

  public BigDecimal getLaborCostPerSquareFoot() {
    return laborCostPerSquareFoot;
  }

  public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
    this.laborCostPerSquareFoot = laborCostPerSquareFoot;
  }

  public String toFileString() {
    return productType + "," + costPerSquareFoot + "," + laborCostPerSquareFoot;
  }
}
