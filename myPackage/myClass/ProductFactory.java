package myPackage.myClass;

import java.math.BigDecimal;
import java.util.Date;

/**
 * class based on factory design pattern for creating objects
 */
public class ProductFactory {
    /**
     * method for creating products without values
     * @param type product type to be created
     * @return product of specified type
     */
    public Product createProduct(String type){
        if (type == null || type.isEmpty()){
            return null;
        }
        if ("Snack".equals(type)){
            return new Snack();
        }
        else if ("Drink".equals(type)){
            return new Drink();
        }
        return null;
    }

    /**
     * method for creating products with values
     * @param type product type
     * @param name product name
     * @param price product price
     * @param date product expiration date
     * @param size product size (weight, volume or anything else)
     * @param feature product feature (fiziness, calories or anything else)
     * @return product of specified type
     */
    public Product createProduct(String type, String name, BigDecimal price, Date date, float size, int feature){
        if (type == null || type.isEmpty()){
            return null;
        }
        if ("Snack".equals(type)){
            return new Snack(name, price, date, size, feature);
        }
        else if ("Drink".equals(type)){
            return new Drink(name, price, date, size, feature);
        }
        return null;
    }
}
