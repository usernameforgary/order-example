package org.cc.demon.axon.orderexample.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderView {
    @Id
    private String id;
    private String productId;
    private Integer amount;
}
