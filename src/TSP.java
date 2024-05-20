import java.io.IOException;
import java.util.List;

public class TSP {
    public static void main(String[] args) {
        try {
            double[][] distances = Utils.readDistances("data/distancia.txt");
            List<Integer> bestPath = ClarkeWright.clarkeWright(distances);
            System.out.println("Mejor recorrido: " + bestPath);
            System.out.println("Distancia mínima: " + Utils.calculateTotalDistance(distances, bestPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}