package algorithm;

/**
 * 二分查找
 */
public class FindAlgorithm {


    public static void main(String[] args) {


        int[] array = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 11;

        System.out.println(binSearch(array, target));
        System.out.println(binSearchRecursive(array, 0, array.length - 1, target));
    }

    /**
     * 二分查找(非递归)
     *
     * @param array
     * @param target
     * @return
     */
    public static int binSearch(int[] array, int target) {

        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] > target) {
                high = mid - 1;
            }
            else if (array[mid] < target) {
                low = mid + 1;
            }
            else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 二分查找(递归)
     *
     * @param array
     * @param low
     * @param high
     * @param target
     * @return
     */
    public static int binSearchRecursive(int[] array, int low, int high, int target) {

        if (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] > target) {
                return binSearchRecursive(array, low, mid - 1, target);
            }
            else if (array[mid] < target) {
                return binSearchRecursive(array, mid + 1, high, target);
            }
            return mid;
        }

        return -1;
    }
}
