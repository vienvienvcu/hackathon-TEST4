package ra.service;

import ra.model.Catalog;
import ra.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CatalogService implements ICatalogFuture {
    public static List<Catalog> categories = new ArrayList<>();
    @Override
    public List<Catalog> getAll() {
        return categories;
    }

    @Override
    public void save(Catalog catalog) {
         Catalog checkIndex = findById(catalog.getCatalogId());
        if (checkIndex == null) {
            categories.add(catalog);
        }else {
            categories.set(checkIndex.getCatalogId(), catalog);
        }

    }
    @Override
    public Catalog findById(Integer id) {
        for (Catalog catalog : categories) {
            if (catalog.getCatalogId() == id){
                return catalog;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        Catalog indexDelete = findById(id);
        if (indexDelete != null) {
            boolean isExist = false;
            for (int i = 0; i< ProductService.products.size(); i++){
                if (ProductService.products.get(i).getCatalog().getCatalogId()== id ){
                    isExist = true;
                    break;
                }
            }
            if (isExist){
                System.err.println("ko xóa khi có sản phẩm)");
            }else {
                categories.remove(indexDelete);
            }
        }else {
            System.err.println("No such class");
        }
    }

}
