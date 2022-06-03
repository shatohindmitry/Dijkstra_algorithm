//Реализация алгоритма для прохождения графа
//Код взял тут http://neerc.secna.ru/Algor/algo_base_graph_spath.html

import java.util.Arrays;

import static java.lang.Math.min;
import static java.util.Arrays.fill;

public class Main {
    public static void main(String[] args) {

        //взял матрицу 3х3
        //Буква - имя графа, цифра - вес графа
//        A1 B2 C3
//        D4 E5 F6
//        G7 J8 K9
//
        //Матрица смежности получилась такая
//            A	B	C	D	E	F	G	J	K
//        A	1	2	99	4	99	99	99	99	99
//        B	1	99	3	99	5	99	99	99	99
//        C	99	2	99	99	99	6	99	99	99
//        D	1	99	99	99	5	99	7	99	99
//        E	99	2	99	4	99	6	99	8	99
//        F	99	99	3	99	5	99	99	99	9
//        G	99	99	99	4	99	99	99	8	99
//        J	99	99	99	99	5	99	7	99	9
//        K	99	99	99	99	99	6	99	8	9


        int INF = Integer.MAX_VALUE / 2; // "Бесконечность"
        int vNum = 9; // количество вершин
        int[][] graph = new int[][]{{1, 2, 99, 4, 99, 99, 99, 99, 99}, {1, 99, 3, 99, 5, 99, 99, 99, 99}, {99, 2, 99, 99, 99, 6, 99, 99, 99}, {1, 99, 99, 99, 5, 99, 7, 99, 99}, {99, 2, 99, 4, 99, 6, 99, 8, 99}, {99, 99, 3, 99, 5, 99, 99, 99, 9}, {99, 99, 99, 4, 99, 99, 99, 8, 99}, {99, 99, 99, 99, 5, 99, 7, 99, 9}, {99, 99, 99, 99, 99, 6, 99, 8, 9}}; // матрица смежности

        int start = 0;
        /* Алгоритм Дейкстры за O(V^2) */

        boolean[] used = new boolean[vNum]; // массив пометок
        int[] dist = new int[vNum]; // массив расстояния. dist[v] = минимальное_расстояние(start, v)

        fill(dist, INF); // устанаавливаем расстояние до всех вершин INF
        dist[start] = 0; // для начальной вершины положим 0

        for (; ; ) {
            int v = -1;
            for (int nv = 0; nv < vNum; nv++) // перебираем вершины
                if (!used[nv] && dist[nv] < INF && (v == -1 || dist[v] > dist[nv])) // выбираем самую близкую непомеченную вершину
                    v = nv;
            if (v == -1) break; // ближайшая вершина не найдена
            used[v] = true; // помечаем ее
            //System.out.println("userd: " + Arrays.toString(used));
            for (int nv = 0; nv < vNum; nv++)
                if (!used[nv] && graph[v][nv] < INF) { // для всех непомеченных смежных
                    dist[nv] = min(dist[nv], dist[v] + graph[v][nv]); // улучшаем оценку расстояния (релаксация)
                    //System.out.println("dist: " + Arrays.toString(dist));
                }
        }
        System.out.println("Расстояние до каждого графа от А до К (от А до B = 2, от A до F = 11 (A+растB+растC+растF))");
        System.out.println(" A  B  C  D  E  F   G   J   K");
        System.out.println("" + Arrays.toString(dist));

    }
}
