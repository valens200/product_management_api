package rw.productant.v1.product;

import lombok.Getter;
import lombok.Setter;
import rw.productant.v1.common.entities.BaseEntity;
import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "product")
public class Product extends BaseEntity {
    private String name;
    private String description;
    private double price;
}
