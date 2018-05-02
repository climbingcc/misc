package algorithm;

import java.util.Random;

/**
 * 经典三门问题
 *
 * Created by didi on 18/5/2.
 */
public class ThreeDoors {

    Random random = new Random();

    /**
     * 0表示关门
     * -1表示主持人打开门并排除无车
     */
    private int[] doors;

    /**
     * 实际有车的下标
     */
    private int success;

    /**
     * 嘉宾选择下标
     */
    private int choice;

    public ThreeDoors(int numOfDoors) {
        doors = new int[numOfDoors];
    }

    //初始化
    private void init() {
        for (int i=0; i < doors.length; i++) {
            doors[i] = 0;
        }

        success = random.nextInt(doors.length);
    }

    //嘉宾随机做出选择
    private void makeChoice() {
        choice = random.nextInt(doors.length);
    }

    //主持人排除无车
    private void exclude(int numOfExclude) {
        if (numOfExclude > (doors.length -2)) {
            throw new RuntimeException("error numOfExclude");
        }

        //随机排除
        int num = 0;
        while (num < numOfExclude) {
            int r = random.nextInt(doors.length);
            if (r != choice && r != success && doors[r] == 0) {
                doors[r] = -1;
                num ++;
            }
        }
    }

    //嘉宾随机更换
    private void changeChoice() {
        while(true) {
            int r = random.nextInt(doors.length);
            if (r != choice && doors[r] != -1) {
                choice = r;
                break;
            }
        }
    }

    private int isSuccess() {
        return success == choice ? 1 : 0;
    }

    //一次测试, 返回成功
    public static int doTest(int numOfDoors, int numOfExclude, boolean doChange) {
        ThreeDoors threeDoors = new ThreeDoors(numOfDoors);
        threeDoors.init();
        threeDoors.makeChoice();
        threeDoors.exclude(numOfExclude);
        if (doChange) {
            threeDoors.changeChoice();
        }

        return threeDoors.isSuccess();
    }


    public static void main(String[] args) {

        //做N次测试
        int N = 1000 * 1000 * 10;
        int numOfSuccess = 0;
        int i= 0;

        numOfSuccess = 0;
        i=0;
        while (i < N) {
            numOfSuccess += doTest(4, 1, true);
            i++;
        }
        System.out.println("doChange, N:" + N + ", numOfSuccess:" + numOfSuccess + ",ratio:" + numOfSuccess*1.00/N);

        numOfSuccess = 0;
        i= 0;
        while (i < N) {
            numOfSuccess += doTest(4, 1, false);
            i++;
        }
        System.out.println("noChange, N:" + N + ", numOfSuccess:" + numOfSuccess + ",ratio:" + numOfSuccess*1.00/N);
    }
}
