package rw.productant.v1.product;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.*;


@Getter
@Setter
public class ProductDto {
    @NotBlank(message = "Product Name should not be empty")
    @NotBlank(message = "Product should not be empty")
    private String name;
    @NotBlank(message = "Description should not be empty")
    @NotBlank(message = "Description should not be empty")
    private String description;
    @Positive(message = "The price should be a positive integer")
    @Range(min = 1, message = "Please select positive numbers Only")
    @NotNull(message = "The price is required")
    double price;
}
