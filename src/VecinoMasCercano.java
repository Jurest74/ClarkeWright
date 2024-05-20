import java.io.IOException;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class TSP {
    public static void main(String[] args) {
        // Lee la matriz de distancias desde el archivo
        double[][] distances = readDistancesFromFile("data/Distancia.txt");

        // Calcula el recorrido óptimo y la distancia total
        double totalDistance;
        List<Integer> optimalPath = nearestNeighbor(distances);
        totalDistance = calculateTotalDistance(optimalPath, distances);

        // Imprime el recorrido óptimo y la distancia total
        System.out.println("Recorrido óptimo: " + optimalPath);
        System.out.println("Distancia total: " + totalDistance);
    }

    // Lee la matriz de distancias desde un archivo
    private static double[][] readDistancesFromFile(String filename) {
        List<double[]> distancesList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                double[] distancesRow = new double[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    distancesRow[i] = Double.parseDouble(parts[i]);
                }
                distancesList.add(distancesRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        double[][] distances = new double[distancesList.size()][];
        for (int i = 0; i < distancesList.size(); i++) {
            distances[i] = distancesList.get(i);
        }
        return distances;
    }

    // Algoritmo del vecino más cercano para encontrar el recorrido óptimo
    private static List<Integer> nearestNeighbor(double[][] distances) {
        List<Integer> path = new ArrayList<>();
        int n = distances.length;
        boolean[] visited = new boolean[n];
        int current = 0; // Empezamos desde el nodo 0
        visited[current] = true;
        path.add(current);
        for (int i = 0; i < n - 1; i++) {
            double minDistance = Double.MAX_VALUE;
            int next = -1;
            for (int j = 0; j < n; j++) {
                if (!visited[j] && distances[current][j] < minDistance) {
                    minDistance = distances[current][j];
                    next = j;
                }
            }
            visited[next] = true;
            path.add(next);
            current = next;
        }
        return path;
    }

    // Calcula la distancia total del recorrido
    private static double calculateTotalDistance(List<Integer> path, double[][] distances) {
        double totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            totalDistance += distances[path.get(i)][path.get(i + 1)];
        }
        // Suma la distancia desde el último nodo de regreso al primero
        totalDistance += distances[path.get(path.size() - 1)][path.get(0)];
        return totalDistance;
    }
}
