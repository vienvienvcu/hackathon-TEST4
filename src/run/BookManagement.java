package run;

import jdk.jfr.Category;
import ra.model.Catalog;
import ra.model.Product;
import ra.service.CatalogService;
import ra.service.ICatalogFuture;
import ra.service.IProductFuture;
import ra.service.ProductService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
   public static final ICatalogFuture catalogFuture = new CatalogService();
   public static final IProductFuture productFuture = new ProductService();


   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      do {
         System.out.println("******************BASIC MENU****************************");
         System.out.println("1. Quản lý danh mục");
         System.out.println("2. Quản lý sản phẩm");
         System.out.println("3. Thoát");
         System.out.println("your choice 1- 3");
         int choice = Integer.parseInt(scanner.nextLine());
         switch (choice) {
            case 1:
               menuCatalog();
               break;
            case 2:
               menuProduct();
               break;
            case 3:
               System.exit(0);
               break;
            default:
               System.err.println("Invalid choice, please try again choice 1-3");
         }

      }while (true);
   }


   public static void menuCatalog(){
      Scanner scanner = new Scanner(System.in);
      boolean isExit = true;
      do {
         System.out.println("********************CATALOG-MANAGEMENT********************");
         System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
         System.out.println("2. Hiển thị thông tin tất cả các danh mục");
         System.out.println("3.Sửa tên danh mục theo mã danh mục");
         System.out.println("4.Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm)");
         System.out.println("5.Quay lại");
         System.out.println("your choice 1-5");
         int choice = Integer.parseInt(scanner.nextLine());
         switch (choice) {
            case 1:
               addCatalog(scanner);
               break;
            case 2:
               showCatalog();
               break;
            case 3:
               updateCatalogName(scanner);
               break;
            case 4:
               deleteCatalog(scanner);
               break;
            case 5:
               isExit = false;
               break;
            default:
               System.err.println("Invalid choice, please try again choice 1-5");
         }
      }while (isExit);
   }

   public static void addCatalog(Scanner scanner){
      System.out.println("input number catalog you want to add: ");
      int n = Integer.parseInt(scanner.nextLine());
      for (int i = 0; i < n; i++) {
         Catalog catalog = new Catalog();
         catalog.inputCatalog(scanner);
         catalogFuture.save(catalog);
      }

   }
   public static void showCatalog(){
      if (catalogFuture.getAll().isEmpty()){
         System.err.println("There are no product in the system");
      }
      for (Catalog catalog : CatalogService.categories){
         System.out.println(catalog.toString());
      }
   }

   public static void updateCatalogName(Scanner scanner){
      System.out.println("input catalog id you want to update: ");
      int catalogid =Integer.parseInt(scanner.nextLine());
      Catalog indexUpdate = catalogFuture.findById(catalogid);
      if(indexUpdate != null){
      
         System.out.println("update catalog name: ");
          indexUpdate.setCatalogName(scanner.nextLine());
      }else {
         System.err.println("catalog id not found");
      }
   }
   public static void deleteCatalog(Scanner scanner) {
      System.out.println("Enter catalog id want delete");
      int catalogId = Integer.parseInt(scanner.nextLine());
      catalogFuture.delete(catalogId);
   }
   public static void menuProduct(){
      Scanner scanner = new Scanner(System.in);
      boolean isExit = true;
      do {
         System.out.println("********************PRODUCT-MANAGEMENT********************");
         System.out.println("1. Nhập số sản sản phẩm và nhập thông tin sản phẩm");
         System.out.println("2. Hiển thị thông tin các sản phẩm");
         System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
         System.out.println("4. Xóa sản phẩm theo mã");
         System.out.println("5. Tìm kiếm sách theo tên sách");
         System.out.println("6. Thay đổi thông tin của sách theo mã sách");
         System.out.println("7. quay lai");
         System.out.println("your choice 1-7");
         int choice = Integer.parseInt(scanner.nextLine());
         switch (choice) {
            case 1:
               addProduct(scanner);
               break;
            case 2:
               showProduct();
               break;
            case 3:
               sortByProductPrice();
               break; 
            case 4:
                deleteProduct(scanner);
               break;
            case 5:
               searchProduct(scanner);
               break;
            case 6:
               updateProduct(scanner);
               break;
            case 7:
               isExit = false;
               break;
            default:
               System.err.println("Invalid choice, please try again choice 1-7");
         }
      }while (isExit);
   }

   public static void addProduct(Scanner scanner){
      System.out.println("input number catalog you want to add: ");
      int n = Integer.parseInt(scanner.nextLine());
      for (int i = 0; i < n; i++) {
         Product product = new Product();
         product.inputData(scanner);
         productFuture.save(product);
      }

   }
   public static void showProduct(){
      if (productFuture.getAll().isEmpty()){
         System.err.println("There are no product in the system");
      }
      for (Product product : ProductService.products){
        System.out.println(product.toString());
      }
   }
   public static void deleteProduct(Scanner scanner){
      System.out.println("Enter product id want delete");
      String deleteId = scanner.nextLine();
      productFuture.delete(deleteId);
   }
    public static void sortByProductPrice(){
      Collections.sort(productFuture.getAll());
      for (Product product : ProductService.products){
         System.out.println(product.toString());
      }

   }
   public static void searchProduct(Scanner scanner){
      System.out.println("Enter product name want search");
      String searchName = scanner.nextLine();
      for (Product product : ProductService.products){
         if (product.getProductName().toLowerCase().contains(searchName.toLowerCase())){
            System.out.println(product.toString());
         }
      }
      System.out.println("Search product successfully");
   }
   public static void updateProduct(Scanner scanner){
      System.out.println("Enter product id want update");
      String updateId = scanner.nextLine();
      Product indexProductUpdate = productFuture.findById(updateId);
      if (indexProductUpdate != null){
         boolean isExit = true;
         do {
            System.out.println("1. Update product name ");
            System.out.println("2. Update product price ");
            System.out.println("3. Update product description ");
            System.out.println("4. Update product category ");
            System.out.println("5. Update product stock ");
            System.out.println("6. EXIT");
            System.out.println("your choice 1-6");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
               case 1:
                  indexProductUpdate.setProductName(indexProductUpdate.inputProductName(scanner));
                  break;
               case 2:
                  indexProductUpdate.setProductPrice(indexProductUpdate.inputProductPrice(scanner));
                  break;
               case 3:
                  indexProductUpdate.setDescription(scanner.nextLine());
                  break;
               case 4:
                  indexProductUpdate.setCatalog(indexProductUpdate.inputCatalog(scanner));
                  break;
               case 5:
                  indexProductUpdate.setStatus(Boolean.parseBoolean(scanner.nextLine()));
                  break;
               case 6:
                  isExit = false;
                  break;
               default:
                  System.err.println("Invalid choice, please try again choice 1-6");
            }
         }while (isExit);
      }else {
         System.err.println("Product not found");
      }
   }

   


}
