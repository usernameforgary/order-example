package org.cc.demon.axon.orderexample.domain.product;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductEventListener {
    private final ProductJpaRepository productRepository;

    @Autowired
    public ProductEventListener(ProductJpaRepository repository) {
        this.productRepository= repository;
    }

    @EventHandler
    public void on(ProductAddedEvent evt) {
        ProductView productView = new ProductView(evt.getId(), evt.getAmount(), evt.getPrice(), evt.getName(), evt.getDescription());
        productRepository.save(productView);
    }

    @EventHandler
    public void on(AmountDecreasedEvent evt) {
        ProductView productView = productRepository.getById(evt.getProductId());
        Integer newAmount = productView.getAmount() - evt.getAmount();
        productView.setAmount(newAmount);
        productRepository.save(productView);
    }
}
