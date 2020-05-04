import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class Client {
    public static void menu(ProductManager pm) {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Option menu:");
        System.out.println("1. Display all products");
        System.out.println("2. Create a new product");
        System.out.println("3. Edit a product");
        System.out.println("4. Delete a product");
        System.out.println("5. Find product by name");
        System.out.println("6. Sort products by price");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.print("Your choose: ");
        Scanner input = new Scanner(System.in);
        int choose = input.nextInt();
        switch (choose){
            case 1:
                pm.displayAllProducts();
                break;
            case 2:
                pm.createProduct();
                break;
            case 3:
                System.out.print("Input product id to edit: ");
                int editId = input.nextInt();
                pm.editProduct(editId);
                break;
            case 4:
                System.out.print("Input product id to delete: ");
                int deleteId = input.nextInt();
                pm.deleteProduct(deleteId);
                break;
            case 5:
                System.out.print("Input product name: ");
                String searchName = input.next();
                pm.findProduct(searchName);
                break;
            case 6:
                System.out.println("Sort option:");
                System.out.println("1. Ascending");
                System.out.println("2. Descending ");
                System.out.print("Your choose: ");
                int sortOption = input.nextInt();
                switch (sortOption){
                    case 1:
                        pm.sort(true);
                        break;
                    case 2:
                        pm.sort(false);
                        break;
                }
                break;

            default:
                System.out.println("Don't have this option, please choose again !");
                break;
        }
        menu(pm);
    }

    public static void main(String[] args) throws IOException {
        Repository repo = new Repository();
        ProductManager pm = new ProductManager(repo);
        menu(pm);
    }
}
