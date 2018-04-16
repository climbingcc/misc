package algorithm;

/**
 * 二叉树
 *
 * 前序扩展建立
 * 前中后层序遍历
 */
public class BinaryTree {

    public static class Node {
        String data;
        Node leftChild;
        Node rightChild;
    }

    public static Node treeRoot;
    public static volatile int next = 0;

    public static void main(String[] args) {
        System.out.println("Hello World!");

        //建立二叉树
        String inputByPreOrder = "ABC#D###EF##GH###";
        //String inputByPreOrder = "AB#D##C##";
        treeRoot = createTreeByPreOrder(treeRoot, inputByPreOrder, 0);

        //前序遍历
        System.out.println();
        System.out.println("preOrder: ");
        preOrder(treeRoot);

        //前序遍历
        System.out.println();
        System.out.println("preOrderNoRecursive: ");
        preOrderNoRecursive(treeRoot);

        //中序遍历
        System.out.println();
        System.out.println("inOrder: ");
        inOrder(treeRoot);

        //中序遍历
        System.out.println();
        System.out.println("inOrderNoRecursive: ");
        inOrderNoRecursive(treeRoot);

        //后序遍历
        System.out.println();
        System.out.println("postOrder: ");
        postOrder(treeRoot);

        //层序遍历
        System.out.println();
        System.out.println("levelOrder: ");
        levelOrder(treeRoot);

        //高度
        System.out.println();
        System.out.println("deep: " + maxDeep(treeRoot));
    }

    /**
     * 根据扩展前序建立二叉树
     *
     * @param root
     * @param input
     * @param index
     * @return
     */
    public static Node createTreeByPreOrder(Node root, String input, int index) {
        if (index >= input.length()) {
            return null;
        }

        String data = String.valueOf(input.charAt(index));
        if (data.equals("#")) {
            return null;
        }
        else {
            root = new Node();
            root.data = data;
            root.leftChild = createTreeByPreOrder(root.leftChild, input, ++next);
            root.rightChild = createTreeByPreOrder(root.rightChild, input, ++next);
            return root;
        }
    }

    /**
     * 前序遍历(递归)
     *
     * @param treeRoot
     */
    public static void preOrder(Node treeRoot) {
        if (treeRoot == null) {
            return;
        }
        System.out.print(treeRoot.data);
        preOrder(treeRoot.leftChild);
        preOrder(treeRoot.rightChild);
    }

    /**
     * 前序遍历(非递归, 左子压栈)
     *
     * @param treeRoot
     */
    public static void preOrderNoRecursive(Node treeRoot) {
        if (treeRoot == null) {
            return;
        }

        Node[] stack = new Node[128];
        int top = -1;

        Node root = treeRoot;
        while (top >= 0 || root != null) {

            //尽力遍历左子树
            //为了在完成左子树遍历后,能够找到右子树的入口,需要将根入栈
            while (root != null) {
                //输出根
                System.out.print(root.data);
                stack[++top] = root;
                root = root.leftChild;
            }

            //遍历右子树
            root = stack[top--].rightChild;
        }
    }

    /**
     * 中序遍历(递归)
     *
     * @param treeRoot
     */
    public static void inOrder(Node treeRoot) {
        if (treeRoot == null) {
            return;
        }
        inOrder(treeRoot.leftChild);
        System.out.print(treeRoot.data);
        inOrder(treeRoot.rightChild);
    }

    /**
     * 中序遍历(非递归, 左子压栈)
     *
     * @param treeRoot
     */
    public static void inOrderNoRecursive(Node treeRoot) {
        if (treeRoot == null) {
            return;
        }

        Node[] stack = new Node[128];
        int top = -1;

        Node root = treeRoot;
        while (top >= 0 || root != null) {

            //虚拟遍历左子树
            //为了在完成左子树遍历后,能够找到右子树的入口,需要将根入栈
            while (root != null) {
                stack[++top] = root;
                root = root.leftChild;
            }

            //左子树为null, 出栈输出根、右子树
            if (top >=0 ) {
                //输出根
                root = stack[top--];
                System.out.print(root.data);
                root = root.rightChild;
            }
        }
    }

    /**
     * 后序遍历(递归)
     *
     * @param treeRoot
     */
    public static void postOrder(Node treeRoot) {
        if (treeRoot == null) {
            return;
        }
        postOrder(treeRoot.leftChild);
        postOrder(treeRoot.rightChild);
        System.out.print(treeRoot.data);
    }

    /**
     * 层序遍历(队列)
     *
     * @param treeRoot
     */
    public static void levelOrder(Node treeRoot) {
        if (treeRoot == null) {
            return;
        }

        int head = -1;
        int tail = -1;
        Node[] queue = new Node[128];

        queue[++tail] = treeRoot;
        while (head != tail) {
            Node current = queue[++head];
            System.out.print(current.data);

            if (current.leftChild != null) {
                queue[++tail] = current.leftChild;
            }
            if (current.rightChild != null) {
                queue[++tail] = current.rightChild;
            }
        }
    }

    public static int maxDeep(Node root) {
        if (root == null) {
            return 0;
        }
        int l = maxDeep(root.leftChild);
        int r = maxDeep(root.rightChild);
        return Math.max(l, r) + 1;
    }
}
