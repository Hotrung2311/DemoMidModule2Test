import java.io.*;
import java.util.ArrayList;

public class Repository {
    // lưu trữ dữ liệu là 1 ArrayList
    public ArrayList<Product> repoList;

    public Repository() throws IOException {
        repoList = new ArrayList();
        BufferedReader bufReader = new BufferedReader(new FileReader("ProductList.txt"));
        String line = bufReader.readLine();

        while (line != null) {
            Product a = new Product();
            String[] data = line.split(", ");
            a.setId(Integer.parseInt(data[0]));
            a.setName(data[1]);
            a.setPrice(Integer.parseInt(data[2]));
            a.setStatus(data[3]);
            a.setDescription(data[4]);
            repoList.add(a);
            line = bufReader.readLine();
        }
        bufReader.close();
    }
}
