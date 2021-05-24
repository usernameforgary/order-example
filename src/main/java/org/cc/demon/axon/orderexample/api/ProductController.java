package org.cc.demon.axon.orderexample.api;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.cc.demon.axon.orderexample.api.vo.AddProductVo;
import org.cc.demon.axon.orderexample.domain.product.AddProductCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("product")
public class ProductController {
    private CommandGateway commandGateway;

    public ProductController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/add")
    public void addProduct(@RequestBody AddProductVo vo) {
        String id = UUID.randomUUID().toString();
        AddProductCommand command = new AddProductCommand(id, vo.getAmount(), vo.getPrice(), vo.getName(), vo.getDescription());

        commandGateway.send(command);
    }
}
