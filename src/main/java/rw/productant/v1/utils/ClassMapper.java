package rw.productant.v1.utils;

import org.modelmapper.ModelMapper;
import rw.productant.v1.product.Product;
import rw.productant.v1.user.entities.User;

public class ClassMapper {
    public static ModelMapper modelMapper = new ModelMapper();

    public static User getUserFromDTO(Object object){
        return modelMapper.map(object,User.class);
    }
    public static Product getProductFromDTO(Object object){
        return modelMapper.map(object, Product.class);
    }

}
