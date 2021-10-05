package com.sg.FlooringMastery.dto;

import java.math.BigDecimal;

public class Tax {
  private State state;
  private BigDecimal taxRate;

  public String toString() {
    return "State Name: " + state.getName() + " Tax Rate: " + String.format("%.2f", taxRate) + "%";
  }

  public BigDecimal getTaxRate() {
    return taxRate;
  }

  public void setTaxRate(BigDecimal taxRate) {
    this.taxRate = taxRate;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  public String toFileString() {
    return state.getAbbreviation() + "," + state.getName() + "," + taxRate;
  }

}
