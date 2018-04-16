package algorithm;

/**
 * 基本排序算法
 */
public class BaseSort {


    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                System.out.print(",");
            }
            System.out.print(array[i]);
        }
    }

    public static void main(String[] args) {

        int[] array = null;
        array = new int[]{3, 1, 5, 2, 8, 9, 7, 6, 4, 0};
        System.out.println("\n\n直接插入排序O(n2)");
        insertSort(array);
        printArray(array);

        array = new int[]{3, 1, 5, 2, 8, 9, 7, 6, 4, 0};
        System.out.println("\n\n冒泡排序O(n2), j<length-1-i");
        bubleSort(array);
        printArray(array);

        array = new int[]{3, 1, 5, 2, 6, 4, 0, 7, 8, 9};
        System.out.println("\n\n冒泡排序O(n2), 记录有无交换(减少外循环次数)");
        bubleSort2(array);
        printArray(array);

        array = new int[]{3, 1, 5, 2, 6, 4, 0, 7, 8, 9};
        System.out.println("\n\n冒泡排序O(n2), 记录上次交换的边界(减少内循环次数)");
        bubleSort3(array);
        printArray(array);

        array = new int[]{3, 1, 5, 2, 6, 4, 0, 7, 8, 9};
        System.out.println("\n\n快速排序递归O(nlogn)");
        quickSortRecursive(array, 0 , array.length - 1);
        printArray(array);

        array = new int[]{3, 1, 5, 2, 6, 4, 0, 7, 8, 9};
        System.out.println("\n\n选择排序O(n2)");
        selectSort(array);
        printArray(array);

        array = new int[]{3, 1, 5, 2, 6, 4, 0, 7, 8, 9};
        System.out.println("\n\n二路归并排序O(nlogn)");
        int[] tmp = array.clone();
        mergeSortRecursive(tmp, 0, array.length -1, array);
        printArray(array);

        array = new int[]{3, 1, 5, 2, 6, 4, 0, 7, 8, 9};
        System.out.println("\n\n堆排序O(nlogn)");
        heapSort(array);
        printArray(array);

    }

    /**
     * 直接插入排序
     * 有序区,无序区
     * 将一张牌插入有序区, 这张牌小则后移有序区
     *
     * @param array
     */
    public static void insertSort(int[] array) {

        int length = array.length;
        for (int i = 1; i < length; i++) {
            int tmp = array[i];
            for (int j = i - 1; j >= 0; j--) {
                if (array[j] > tmp) {
                    array[j + 1] = array[j];
                    //todo 这个赋值可以优化
                    array[j] = tmp;
                }
            }
        }
    }

    /**
     * 希尔排序
     *
     * @param array
     */
    public static void shellSort(int[] array) {

    }

    /**
     * 冒泡排序
     * j<length-1-i
     * 邻间比较交换, 最大的往后走
     *
     * @param array
     */
    public static void bubleSort(int[] array) {

        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
    }

    /**
     * 冒泡排序
     * 记录有无交换(减少外循环次数)
     * 邻间比较交换, 最大的往后走
     *
     * @param array
     */
    public static void bubleSort2(int[] array) {

        //仅用来分析效率
        int i = 0;

        int length = array.length;
        boolean exchange = true;
        int jj = length - 1;
        while (exchange) {
            exchange = false;
            for (int j = 0; j < jj; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;

                    exchange = true;
                }
                //System.out.println("j=" + j); //可以看到内循环次数
            }

            jj--; //下面的方法会优化这里

            i++;
        }
        //System.out.println("i=" + i); //可以看到外循环次数
    }

    /**
     * 冒泡排序
     * 记录上次交换的边界(减少外循环)
     * 邻间比较交换, 最大的往后走
     *
     * @param array
     */
    public static void bubleSort3(int[] array) {

        //仅用来分析效率
        int i = 0;

        int length = array.length;
        boolean exchange = true;
        int bound = length - 1;
        while (exchange) {
            exchange = false;
            int jj = bound;
            bound = 0;
            for (int j = 0; j < jj; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;

                    exchange = true;
                    bound = j;
                }
                //System.out.println("j=" + j); //可以看到内循环次数
            }
            i++;
        }
        //System.out.println("i=" + i); //可以看到外循环次数
    }

    /**
     * 快速排序一个划分
     * 目标是:
     * 将轴值povit(可选取low位置的数据)放到合适的位置, 保证左边的小于povit, 右边的大于povit
     */
    private static int partion(int[] array, int low, int high) {
        int povit = array[low];

        int i = low;
        int j = high;

        while (i < j) {
            //扫描右侧, 直到第一个小于povit
            while (i < j && array[j] >= povit) {
                j--;
            }
            if (i < j) {
                array[i] = array[j]; //不需要交换, 因为povit不在最终位置i=j
                i++; //不变也不会错, 但是增加了不必要的比较
            }

            //扫描左侧
            while (i < j && array[i] <= povit) {
                i++;
            }
            if (i < j) {
                array[j] = array[i]; //不需要交换, 因为povit不在最终位置i=j
                j--; //不变也不会错, 但是增加了不必要的比较
            }
        }

        array[i] = povit;
        return i;
    }

    /**
     * 快速排序(递归)
     *
     * @param array
     */
    public static void quickSortRecursive(int[] array, int low, int high) {

        if (low < high) {
            int locate = partion(array, low, high);
            quickSortRecursive(array, low, locate - 1);
            quickSortRecursive(array, locate + 1, high);
        }
    }

    /**
     * 快速排序(非递归)
     *
     * @param array
     */
    public static void quickSortNoRecursive(int[] array) {

    }

    /**
     * 选择排序
     * 有序区,无序区
     * 每一轮在无序区选出最小的放在有序区最后
     *
     * @param array
     */
    public static void selectSort(int[] array) {

        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < length; j ++) {
                //只是找出最小所在的位置
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int tmp = array[min];
                array[min] = array[i];
                array[i] = tmp;
            }
        }
    }

    private static void makeMinHeap(int[] array, int length) {
        //完全二叉树后半部分都是叶子, 只需要调整前半部分
        for (int i = (length - 1)/ 2; i >= 0; i--) {
            adjustHeap(array, length, i);
        }
    }

    private static void adjustHeap(int[] array, int length, int root) {
        int i = root;
        int j = 2 * i + 1;

        while (j < length) {
            //左右较小
            if ( (j + 1) < length && array[j + 1] < array[j]) {
                j++;
            }

            //交换
            if (array[j] < array[i]) {
                int tmp = array[j];
                array[j] = array[i];
                array[i] = tmp;
            }

            //向下层延伸直到叶子
            i = j;
            j = 2 * i + 1;
        }
    }

    /**
     * 堆排序
     *
     * @param array
     */
    public static void heapSort(int[] array) {

        int tmp =0;

        //构建最小堆
        makeMinHeap(array, array.length);

        //拿出最小, 剩下的只需要重新调整堆的根节点(不需要重新建堆, 因为左右子树都是堆)
        for (int i = array.length - 1; i > 0; i--) {
            tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;

            adjustHeap(array, i, 0);
        }
    }

    /**
     * 将源数组src的[l, m]与[m+1, n]合并至目标数组dst的[l, n]
     * 为了简化外部调用逻辑, 这里将dst再拷贝会src
     *
     */
    private static void merge(int src[], int l, int m, int n, int dst[]) {

        int i = l;
        int j = m + 1;
        int k = l;

        while (i <= m && j <= n) {
            if (src[i] < src[j]) {
                dst[k++] = src[i++];
            }
            else {
                dst[k++] = src[j++];
            }
        }
        while (i <= m) {
            dst[k++] = src[i++];
        }
        while (j <= n) {
            dst[k++] = src[j++];
        }

//        //然后拷贝回去, 如果不拷贝需要外部逻辑配合翻转
//        for (int d = l; d <= n; d++) {
//            src[d] = dst[d];
//        }
    }

    /**
     * 二路归并排序(递归)
     *
     */
    public static void mergeSortRecursive(int[] array, int low, int high, int tmp[]) {

//        //merge内部有拷贝
//        if (low < high) {
//            mergeSortRecursive(array, low, (low + high) / 2, tmp);
//            mergeSortRecursive(array, (low + high) / 2 + 1, high, tmp);
//            merge(array, low, (low + high) / 2, high, tmp);
//        }

        //merge内部无拷贝
        int m = (low + high) / 2;
        if (low < high) {
            mergeSortRecursive(tmp, low, m, array); //一定要翻转
            mergeSortRecursive(tmp, m + 1, high, array); //一定要翻转
            merge(array, low, m, high, tmp); //merge会更换数组, 所以上面递归的输出在array, 最终结果在tmp
        }
    }

    /**
     * 二路归并排序(非递归)
     *
     * @param array
     */
    public static void mergeSortNoRecursive(int[] array) {

    }
}
