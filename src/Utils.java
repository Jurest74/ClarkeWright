import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class Utils {

    public static double[][] readDistances(String filePath) throws IOException {
        double[][] distances = new double[25][25];
        System.out.println(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int row = 0;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split("\t");
            for (int col = 0; col < values.length; col++) {
                distances[row][col] = Double.parseDouble(values[col]);
            }
            row++;
        }
        reader.close();
        return distances;
    }

    public static double[][] readCoordinates(String filePath) throws IOException {
        double[][] coordinates = new double[25][2];
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int row = 0;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split("\t");
            for (int col = 0; col < values.length; col++) {
                coordinates[row][col] = Double.parseDouble(values[col]);
            }
            row++;
        }
        reader.close();
        return coordinates;
    }

    public static double[][] calculateDistances(double[][] coordinates) {
        int n = coordinates.length;
        double[][] distances = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    double x1 = coordinates[i][0];
                    double y1 = coordinates[i][1];
                    double x2 = coordinates[j][0];
                    double y2 = coordinates[j][1];
                    distances[i][j] = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                }
            }
        }
        return distances;
    }

    public static double calculateTotalDistance(double[][] distances, List<Integer> path) {
        double totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            totalDistance += distances[path.get(i)][path.get(i + 1)];
        }
        totalDistance += distances[path.get(path.size() - 1)][path.get(0)];
        return totalDistance;
    }
}