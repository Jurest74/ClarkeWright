import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClarkeWright {

    public static List<Integer> clarkeWright(double[][] distances) {
        int n = distances.length;
        List<Saving> savingsList = new ArrayList<>();

        // Calcular los ahorros para todas las combinaciones de nodos
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double saving = distances[0][i] + distances[0][j] - distances[i][j];
                savingsList.add(new Saving(i, j, saving));
            }
        }

        // Ordenar la lista de ahorros en orden descendente
        savingsList.sort(Saving::compareTo);

        // Crear rutas iniciales para cada nodo (excepto el nodo 0)
        List<List<Integer>> routes = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            routes.add(new ArrayList<>(Arrays.asList(i))); // Cada nodo en su propia ruta inicial
        }

        // Fusionar las rutas según los ahorros
        for (Saving saving : savingsList) {
            List<Integer> routeI = null;
            List<Integer> routeJ = null;

            for (List<Integer> route : routes) {
                if (route.contains(saving.i) && route.contains(saving.j)) {
                    routeI = route;
                    routeJ = route;
                    break;
                } else if (route.contains(saving.i)) {
                    routeI = route;
                } else if (route.contains(saving.j)) {
                    routeJ = route;
                }
            }

            // Verificar si los nodos i y j están en rutas diferentes
            if (routeI != routeJ && routeI != null && routeJ != null) {
                // Verificar si los nodos i y j están al final de la ruta I y al principio de la ruta J, o viceversa
                if (routeI.get(routeI.size() - 1) == saving.i && routeJ.get(0) == saving.j) {
                    // Fusionar las rutas
                    routeI.addAll(routeJ);
                    routes.remove(routeJ);
                } else if (routeI.get(0) == saving.i && routeJ.get(routeJ.size() - 1) == saving.j) {
                    // Fusionar las rutas
                    routeJ.addAll(routeI);
                    routes.remove(routeI);
                }
            }
        }

        // Agregar el nodo 0 al principio y al final del recorrido final
        List<Integer> bestRoute = routes.get(0);
        bestRoute.add(0, 0);
        bestRoute.add(0);

        return bestRoute;
    }

    // Clase para representar el ahorro entre dos nodos
    public static class Saving implements Comparable<Saving> {
        int i, j;
        double saving;

        public Saving(int i, int j, double saving) {
            this.i = i;
            this.j = j;
            this.saving = saving;
        }

        @Override
        public int compareTo(Saving other) {
            return Double.compare(other.saving, this.saving); // Ordenar en orden descendente
        }
    }
}
