package sia.tacocloud.web.controller.tacos.data;

import sia.tacocloud.web.controller.tacos.Order;

public interface OrderRepository {
    Order save(Order order);
}
