package com.sg.FlooringMastery.ui;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import com.sg.FlooringMastery.dto.State;

@Component
public class UserIOConsoleImpl implements UserIO {

  final private Scanner console = new Scanner(System.in);

  public void print(String msg) {
    System.out.println(msg);
  }

  public void printSameLine(String msg) {
    System.out.print(msg);
  }

  public void printMoney(double money) {
    System.out.printf("%.2f", money);
  }

  public String readString(String msgPrompt) {
    System.out.println(msgPrompt);
    return console.next();
  }

  public int readInt(String msgPrompt) {
    boolean invalidInput = true;
    int num = 0;
    while (invalidInput) {
      try {
        String stringValue = this.readString(msgPrompt);
        num = Integer.parseInt(stringValue);
        invalidInput = false;
      } catch (NumberFormatException e) {
        this.print("Input error. Please try again.");
      }
    }
    return num;
  }

  public int readInt(String msgPrompt, int min, int max) {
    int result;
    do {
      result = readInt(msgPrompt);
      if (result < min || result > max) {
        System.out.println("Enter an integer greater than " + min + " and smaller than " + max);
      }
    } while (result < min || result > max);

    return result;
  }

  public long readLong(String msgPrompt) {
    while (true) {
      try {
        return Long.parseLong(this.readString(msgPrompt));
      } catch (NumberFormatException e) {
        this.print("Input error. Please try again.");
      }
    }
  }

  public long readLong(String msgPrompt, long min, long max) {
    long result;
    do {
      result = readLong(msgPrompt);
    } while (result < min || result > max);

    return result;
  }

  public float readFloat(String msgPrompt) {
    while (true) {
      try {
        return Float.parseFloat(this.readString(msgPrompt));
      } catch (NumberFormatException e) {
        this.print("Input error. Please try again.");
      }
    }
  }

  public float readFloat(String msgPrompt, float min, float max) {
    float result;
    do {
      result = readFloat(msgPrompt);
    } while (result < min || result > max);

    return result;
  }

  public double readDouble(String msgPrompt) {
    while (true) {
      try {
        return Double.parseDouble(this.readString(msgPrompt));
      } catch (NumberFormatException e) {
        this.print("Input error. Please try again.");
      }
    }
  }

  public double readDouble(String msgPrompt, double min, double max) {
    double result;
    do {
      result = readDouble(msgPrompt);
    } while (result < min || result > max);
    return result;
  }

  public LocalDate readDate(String prompt) {
    int year = readInt("Enter the Year", Year.now().getValue(), 2050);
    int day = 0;
    int month;
    if (year == Year.now().getValue()) {
      LocalDate date = LocalDate.now();
      month = readInt("Enter the Month", date.getMonthValue(), 12);
      if (month == date.getMonthValue()) {
        YearMonth ym = YearMonth.of(year, month);
        day = readInt("Enter the day", date.getDayOfMonth(), ym.lengthOfMonth());
      }
    } else {
      month = readInt("Enter the Month", 1, 12);

      YearMonth ym = YearMonth.of(year, month); // Finds max value for the month in a certain year
      day = readInt("Enter the Date", 1, ym.lengthOfMonth());
    }

    LocalDate result = LocalDate.of(year, month, day);
    return result;
  }


  public boolean readYesOrNo(String prompt) {
    System.out.println(prompt);
    boolean goodInput = false;
    boolean output = false;
    while (!goodInput) {
      try {
        String s = console.nextLine();
        if (s.substring(0, 1).toLowerCase().contains("y")) {
          output = true;
          goodInput = true;
        } else if (s.substring(0, 1).toLowerCase().contains("n")) {
          output = false;
          goodInput = true;
        } else {
          System.out.println("Enter Y for Yes or N for No");
        }
      } catch (Exception e) {

      }
    }
    return output;
  }

  @Override
  public State readState(String prompt) {
    System.out.println(prompt);
    boolean goodInput = false;
    State state = null;
    while (!goodInput) {
      String s = console.nextLine();
      s = s.trim().toUpperCase();
      for (State st : State.values()) {
        if (s.equalsIgnoreCase(st.getAbbreviation())) {
          goodInput = true;
          state = st;
        }
      }
      if (!goodInput) {
        System.out.println("Enter a valid state abbreviaiton");
      }
    }
    return state;
  }
}
