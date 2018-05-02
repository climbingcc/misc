package algorithm;

/**
 * 动态规划相关
 * https://blog.csdn.net/uestclr/article/details/50760563
 *
 * 1.子数组最大和
 *
 */
public class Dp {


    public static void main(String[] args) {

        int[] array = {1, -2, 3, 10, -4, 7, -2, -5};
        maxSubArraySum(array);
    }

    public static void maxSubArraySum(int[] array) {

        int length = array.length;
        int curSum = 0;
        int maxSum = array[0];
        int first = 0;
        int last = 0;

        for(int j = 0; j < length; j++) {
            if(curSum >= 0)  {
                curSum += array[j];
            }
            else {
                curSum = array[j];
                if (curSum > maxSum) {
                    first = j;
                }
            }
            if(curSum > maxSum) {
                maxSum = curSum;
                last = j;
            }
        }
        System.out.println("firtst:" + first + ", last:" + last + ", maxSum:" + maxSum);
    }
}
