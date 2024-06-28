package run;

import jdk.jfr.Category;
import ra.model.Catalog;
import ra.model.Product;
import ra.service.CatalogService;
import ra.service.ICatalogFuture;
import ra.service.IProductFuture;
import ra.service.ProductService;

import java.util.ArrayList;
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
               break;
            case 4:
               break;
            case 5:
               break;
            case 6:
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
   


}
