package com.sg.FlooringMastery.testing;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.sg.FlooringMastery.dao.FlooringMasteryDaoFileImpl;
import com.sg.FlooringMastery.dto.Order;

class DaoTest {

  FlooringMasteryDaoFileImpl dao = new FlooringMasteryDaoFileImpl();

  @Test
  void test() {
    Order order = new Order();
    dao.addOrder(order);
    assertTrue(dao.getOrders().contains(order));
  }

}
