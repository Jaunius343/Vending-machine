package myPackage.myClass;

import java.math.BigDecimal;
import java.util.Date;

public class ProductFactory {
    public Product createProduct(String type){
        if (type == null || type.isEmpty()){
            return null;
        }
        if ("SNACK".equals(type)){
            return new Snack();
        }
        else if ("DRINK".equals(type)){
            return new Drink();
        }
        return null;
    }

    public Product createProduct(String type, String name, BigDecimal price, Date date, float size, int feature){
        if (type == null || type.isEmpty()){
            return null;
        }
        if ("SNACK".equals(type)){
            return new Snack(name, price, date, size, feature);
        }
        else if ("DRINK".equals(type)){
            return new Drink(name, price, date, size, feature);
        }
        return null;
    }
}
