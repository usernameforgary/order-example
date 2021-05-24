package org.cc.demon.axon.orderexample.domain.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductView {
    @Id
    private String id;
    private Integer amount;
    private Double price;
    private String name;
    private String description;
}
