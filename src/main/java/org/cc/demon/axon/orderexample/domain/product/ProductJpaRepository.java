package org.cc.demon.axon.orderexample.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductView, String> {
}
