package ra.model;

import ra.service.CatalogService;
import ra.service.IGenericService;
import ra.service.ProductService;
import run.BookManagement;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Product implements Comparable<Product> {
    private String productId;
    private String productName;
    private double productPrice;
    private String description;
    private int stock;
    private Catalog catalog;
    private boolean status;

    public Product() {
    }

    public Product(String productId, String productName, double productPrice,
                   String description, int stock, Catalog catalog, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.description = description;
        this.stock = stock;
        this.catalog = catalog;
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public void inputData(Scanner scanner){
// 1.productId - String (bắt đầu bằng chữ P và thêm 4 ký tự số , không được trùng lặp );
        this.productId = inputProductId(scanner);
// 2.productName - String (Không được để trống )
        this.productName = inputProductName(scanner);
// 3.productPrice - double (phải lớn hơn 0)
        this.productPrice = inputProductPrice(scanner);
// 4.description - String
        this.description = inputDescription(scanner);
// 5.stock - int (ít nhất là 10)
        this.stock = inputStock(scanner);
//6.catalog - Catalog (không được để null)
        this.catalog = inputCatalog(scanner);
// 7.status - boolean (mặc định là true )
        this.status = true;

    }

    public String inputProductId(Scanner scanner){
        System.out.println("Enter product ID");
        do {
            String productId = scanner.nextline();
            String productRegex = "^P\\d{4}$";
            if (Pattern.matches(productRegex, productId)){
                boolean isExits = false;
                for (Product p : ProductService.products){
                    if (p.getProductId().equals(productId)) {
                        isExits = true;
                        break;
                    }
                }
                if (!isExits){
                    return this.productId = productId;
                }else {
                    System.err.println("product ID is exits, please try again");
                }

            }else {
                System.err.println("product ID must 4chars and start with 'P'");
            }

        }while (true);
    }

    public String inputProductName(Scanner scanner){
        System.out.println("Enter product name");
        do {
            String productName = scanner.nextline();
            if (productName.isEmpty()){
                System.err.println("product name cannot empty");
            }else {
                return this.productName = productName;
            }

        }while (true);
    }

    public Double inputProductPrice(Scanner scanner){
        System.out.println("Enter product price");
        do {
            Double productPrice = Double.parseDouble(scanner.nextline());
            if (productPrice > 0){
                return this.productPrice = productPrice;
            }else {
                System.err.println("product price cannot greater than 0");
            }

        }while (true);
    }

    public String inputDescription(Scanner scanner){
        System.out.println("Enter description");
        String description = scanner.next();
        return this.description = description;
    }
    public int inputStock(Scanner scanner){
        System.out.println("Enter stock");

        do {
            int stock = Integer.parseInt(scanner.nextline());
            if (stock >=10){
                return this.stock = stock;
            }else {
                System.err.println("stock is greater than 10");
            }
        }while (true);
    }


    public Catalog inputCatalog(Scanner scanner){
        System.out.println("Enter Catalog Name : ");
        do {
            for (int i = 0; i < CatalogService.categories.size(); i++){
                System.out.println((i+1)+ ". " + CatalogService.categories.get(i).getCatalogName());
            }
            System.out.println("enter your choice");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice >0 && choice <= CatalogService.categories.size()){
                return this.catalog =  CatalogService.categories.get(choice-1);
            }else {
                System.err.println("Class Name is not exist,please try again");
            }
        }while (true);
    }
    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", catalog=" + this.catalog.getCatalogName() +
                ", status=" +status +
                '}';
    }
      @Override
    public int compareTo(Product o) {
        return Double.compare(o.productPrice,this.productPrice);
    }
}
