import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductManager implements Function {
    ArrayList<Product> list = new ArrayList<>();

    public ProductManager(Repository productList) {
        this.list = productList.repoList;
    }

    @Override
    public void displayAllProducts() {
        for (int i = 0; i < this.list.size(); i++) {
            System.out.print("Product " + (Integer) (i + 1) + ": ");
            System.out.println(this.list.get(i).toString());
        }
    }

    @Override
    public void createProduct() {
        Product a = new Product();
        Scanner input = new Scanner(System.in);

        System.out.print("Product id: ");
        int id = input.nextInt();

        if (this.list.size() == 0) {
            a.setId(id);

            System.out.print("Product name: ");
            String name = input.next();
            a.setName(name);

            System.out.print("Product price: ");
            int price = input.nextInt();
            a.setPrice(price);

            System.out.print("Product status: ");
            String status = input.next();
            a.setStatus(status);

            System.out.print("Product description: ");
            String description = input.next();
            a.setDescription(description);

            this.list.add(a);
            update();
            return;
        } else {
            for (int i = 0; i < this.list.size(); i++) {
                if (this.list.get(i).getId() == id) {
                    System.out.println("The product already exists !");
                    return;
                } else {
                    a.setId(id);

                    System.out.print("Product name: ");
                    String name = input.next();
                    a.setName(name);

                    System.out.print("Product price: ");
                    int price = input.nextInt();
                    a.setPrice(price);

                    System.out.print("Product status: ");
                    String status = input.next();
                    a.setStatus(status);

                    System.out.print("Product description: ");
                    String description = input.next();
                    a.setDescription(description);

                    this.list.add(a);
                    break;
                }
            }
        }
        update();
    }

    @Override
    public void deleteProduct(int id) {
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getId() == id) {
                this.list.remove(i);
            }
        }
        update();
    }

    @Override
    public void editProduct(int id) {
        Scanner edit = new Scanner(System.in);
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getId() == id) {
                System.out.print("New product name: ");
                String newName = edit.next();
                this.list.get(i).setName(newName);

                System.out.print("New product price: ");
                int newPrice = edit.nextInt();
                this.list.get(i).setPrice(newPrice);

                System.out.print("New product status: ");
                String newStatus = edit.next();
                this.list.get(i).setStatus(newStatus);

                System.out.print("New product description: ");
                String newDescription = edit.next();
                this.list.get(i).setDescription(newDescription);
                break;
            }
        }
        update();
    }

    @Override
    public void sort(boolean type) {
        ArrayList<Product> sortList = this.list;

        if (type) {
            for (int i = 0; i < sortList.size(); i++) {
                int temp = sortList.get(i).getPrice();
                int j = i - 1;
                while (j >= 0 && sortList.get(j).getPrice() > sortList.get(i).getPrice()) {
                    sortList.get(j + 1).setPrice(sortList.get(j).getPrice());
                    j = j - 1;
                }
                sortList.get(j + 1).setPrice(temp);
            }
        } else {
            for (int i = 0; i < sortList.size() - 1; i++) {
                Product currentProduct = sortList.get(i);
                int currentMax = sortList.get(i).getPrice();
                int currentMaxIndex = i;

                for (int j = i + 1; j < sortList.size(); j++) {
                    if (currentMax < sortList.get(j).getPrice()) {
                        currentMax = sortList.get(j).getPrice();
                        currentProduct = sortList.get(j);
                        currentMaxIndex = j;
                    }
                }
                sortList.get(currentMaxIndex).setPrice(sortList.get(i).getPrice());

                sortList.get(currentMaxIndex).setName(sortList.get(i).getName());
                sortList.get(currentMaxIndex).setId(sortList.get(i).getId());
                sortList.get(currentMaxIndex).setStatus(sortList.get(i).getStatus());
                sortList.get(currentMaxIndex).setDescription(sortList.get(i).getDescription());

                sortList.get(i).setPrice(currentMax);
                sortList.get(i).setName(currentProduct.getName());
                sortList.get(i).setId(currentProduct.getId());
                sortList.get(i).setStatus(currentProduct.getStatus());
                sortList.get(i).setDescription(currentProduct.getDescription());
            }
        }
        for (int i = 0; i < sortList.size(); i++) {
            System.out.print("Product " + (Integer) (i + 1) + ": ");
            System.out.println(sortList.get(i).toString());

        }
    }

    @Override
    public void findProduct(String productName) {
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).getName().equals(productName)) {
                System.out.println(this.list.get(i).toString());
            }
        }
    }

    private void update() {


            // If the file doesn't exists, create and write to it
            // If the file exists, truncate (remove all content) and write to it
            try {
                FileWriter writer = new FileWriter("ProductList.txt");
                BufferedWriter bw = new BufferedWriter(writer);
                for (int i = 0; i < this.list.size(); i++) {
                    Product updateProduct = this.list.get(i);
                    String content = updateProduct.getId() + ", " + updateProduct.getName() + ", " + updateProduct.getPrice() + ", " + updateProduct.getStatus() + ", " + updateProduct.getDescription();
                    bw.write(content);
                    bw.newLine();
                }
//                bw.flush();
                    bw.close();
            } catch (IOException e) {
                System.err.format("IOException: %s%n", e);
            }
        }

    }

