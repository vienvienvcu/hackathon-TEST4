package ra.model;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Catalog {
    private int catalogId;
    private String catalogName;
    private String descriptions;

    public Catalog() {
    }

    public Catalog(int catalogId, String catalogName, String descriptions) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void inputCatalog(Scanner scanner) {
        System.out.print("Enter Catalog ID: ");
        this.catalogId = Integer.parseInt(scanner.nextLine());
        this.catalogName = inputCatalogName(scanner);
        this.descriptions = inputCatalogDescription(scanner);
    }

    public String inputCatalogName(Scanner scanner) {
        System.out.println("Enter catalog name");
        do {
            String catalogName = scanner.nextLine();
            if (catalogName.trim().isEmpty()) {
                System.out.println("Catalog name cannot be empty");
            }else {
               return this.catalogName = catalogName;
            }
        }while (true);
    }
    public String inputCatalogDescription(Scanner scanner) {
        System.out.println("Enter catalog description");
        do {
            String catalogDescription = scanner.nextLine();
            if (catalogDescription.trim().isEmpty()) {
                System.out.println("Catalog description cannot be empty");
            }else {
                return this.descriptions = catalogDescription;
            }
        }while (true);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "catalogId=" + catalogId +
                ", catalogName='" + catalogName + '\'' +
                '}';
    }
}
