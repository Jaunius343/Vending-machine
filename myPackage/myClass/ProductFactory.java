package myPackage.myClass;

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
}
