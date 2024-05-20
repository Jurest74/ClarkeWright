import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClarkeWright {

    public static List<Integer> clarkeWright(double[][] distances) {
        int n = distances.length;
        List<Saving> savingsList = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double saving = distances[0][i] + distances[0][j] - distances[i][j];
                savingsList.add(new Saving(i, j, saving));
            }
        }

        savingsList.sort(Saving::compareTo);

        List<List<Integer>> routes = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            routes.add(new ArrayList<>(Arrays.asList(0, i, 0)));
        }

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

            if (routeI != routeJ && routeI != null && routeJ != null) {
                if (routeI.get(routeI.size() - 2) == saving.i && routeJ.get(1) == saving.j) {
                    routeI.remove(routeI.size() - 1);
                    routeI.addAll(routeJ.subList(1, routeJ.size()));
                    routes.remove(routeJ);
                } else if (routeI.get(routeI.size() - 2) == saving.j && routeJ.get(1) == saving.i) {
                    routeI.remove(routeI.size() - 1);
                    routeI.addAll(routeJ.subList(1, routeJ.size()));
                    routes.remove(routeJ);
                }
            }
        }

        List<Integer> bestRoute = routes.get(0);
        for (int i = 1; i < routes.size(); i++) {
            bestRoute.addAll(routes.get(i).subList(1, routes.get(i).size() - 1));
        }
        return bestRoute;
    }

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
