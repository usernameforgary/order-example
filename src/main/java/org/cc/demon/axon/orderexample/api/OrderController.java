package org.cc.demon.axon.orderexample.api;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.cc.demon.axon.orderexample.api.vo.CreateOrderVo;
import org.cc.demon.axon.orderexample.domain.order.CreateOrderCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("order")
public class OrderController {
    private CommandGateway commandGateway;

    public OrderController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public void createOrder(@RequestBody CreateOrderVo vo) {
        String id = UUID.randomUUID().toString();

        CreateOrderCommand command = new CreateOrderCommand(
                id,
                vo.getProductId(),
                vo.getAmount()
        );
        commandGateway.send(command);
    }
}
