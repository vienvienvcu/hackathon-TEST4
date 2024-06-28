package ra.service;

import ra.model.Catalog;
import ra.model.Product;
import run.BookManagement;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductFuture{
    public static List<Product> products = new ArrayList<>();
    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        Product checkIndex = findById(product.getProductId());
        if (checkIndex == null) {
            products.add(product);
        }else {
            products.set(Integer.parseInt(checkIndex.getProductId()), product);
        }

    }

    @Override
    public Product findById(String id) {
        for (Product product : products) {
            if (product.getProductId() == id){
                return product;
            }
        }
        return null;

    }

    @Override
    public void delete(String id) {
        Product indexDelete = findById(id);
        if (indexDelete == null) {
            products.remove(indexDelete);
        }else {
            System.err.println("student such not,cannot delete");
        }
    }

}

