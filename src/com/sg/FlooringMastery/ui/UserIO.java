package com.sg.FlooringMastery.ui;

import java.time.LocalDate;
import com.sg.FlooringMastery.dto.State;

public interface UserIO {
  void print(String msg);

  void printSameLine(String msg);

  void printMoney(double money);

  double readDouble(String prompt);

  double readDouble(String prompt, double min, double max);

  float readFloat(String prompt);

  float readFloat(String prompt, float min, float max);

  int readInt(String prompt);

  int readInt(String prompt, int min, int max);

  long readLong(String prompt);

  long readLong(String prompt, long min, long max);

  String readString(String prompt);

  LocalDate readDate(String prompt);

  boolean readYesOrNo(String prompt);

  State readState(String prompt);
}
