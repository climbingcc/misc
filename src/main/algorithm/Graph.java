package algorithm;

/**
 * 图
 * 邻接矩阵
 * DFS深度优先遍历
 * BFS广度优先遍历
 *
 */
public class Graph {

    //邻接矩阵表示
    //v0-v1, v0-v3, v1-v2, v1-v3

    static String[] vertex = new String[] {"v0", "v1", "v2", "v3"};
    static int[][] arc = new int[][] {
            {0, 1, 0, 1},
            {1, 0, 1, 1},
            {0, 1, 0, 0},
            {1, 1, 0, 0}
    };

    public static void main(String[] args) {

        System.out.println("DFS深度优先遍历:");
        DFSTraverse(0);

        System.out.println("BFS广度优先遍历:");
        BFSTraverse(0);
    }

    /**
     * DFS深度优先遍历
     */
    static int visited1[] = new int[] {0, 0, 0, 0};
    public static void DFSTraverse(int entry) {

        System.out.println(vertex[entry]);
        visited1[entry] = 1;
        for (int j = 0; j < vertex.length; j++) {
            if (arc[entry][j] == 1 && visited1[j] == 0) {
                DFSTraverse(j);
            }
        }
    }


    /**
     * BFS广度优先遍历
     * 类似树的层序遍历, 需要队列支持
     */
    static int visited2[] = new int[] {0, 0, 0, 0};
    public static void BFSTraverse(int entry) {

        int[] queue = new int[128];
        int head = -1;
        int tail = -1;

        System.out.println(vertex[entry]);
        visited2[entry] = 1;
        queue[++tail] = entry;

        while (head != tail) {
            int first = queue[++head];
            for (int j = 0; j < vertex.length; j++) {
                if (arc[first][j] == 1 && visited2[j] == 0) {
                    System.out.println(vertex[j]);
                    visited2[j] = 1;
                    queue[++tail] = j;
                }
            }
        }
    }
}
