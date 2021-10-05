package com.sg.FlooringMastery;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.sg.FlooringMastery.controller.FlooringMasteryController;

public class App {

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
    appContext.scan("com.sg.FlooringMastery");
    appContext.refresh();
    FlooringMasteryController controller =
        appContext.getBean("flooringMasteryController", FlooringMasteryController.class);
    controller.run();
    appContext.close();
  }

}
