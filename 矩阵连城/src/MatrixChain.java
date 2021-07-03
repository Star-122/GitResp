import java.util.Scanner;

public class MatrixChain {
    public static void MatrixChain(int[] p, int n, int[][] m, int[][] s) {
        /*①p为矩阵的行与列数，注意若矩阵的个数为n个，则p.length=n+1,两两相乘，中间有n-1个，加上两边2个，共n+1个，
        即数组p的长度为n+1。这里p[]存储p[0]、p[1]、p[2]......p[n]。
        ②n为矩阵的个数。
        ③m[i][j]为A[i:j]的最少数乘次数，即AiAi+1......Aj连乘的最少次数。
        ④s[][]为存放插入点位置的*/
        for (int i = 1; i <= n; i++) {//把对角线的都设为0，因为单个矩阵与自己无法相乘。
            m[i][i] = 0;
        }
        for (int r = 2; r <= n; r++) {//① r为矩阵连乘的长度，从2开始，好戏开始了。② r的引入也改变了i与j的下标。
            for (int i = 1; i <= n - r + 1; i++) {//确定i的下标，这里减一是因为r=2。
                int j = i + r - 1;//确定j的下标，注意i+j=i+n。
                m[i][j] = m[i][i] + m[i + 1][j] + p[i - 1] * p[i] * p[j];//先确定一个m[i][j]，备忘录法
                s[i][j] = i;//插入点位置
                for (int k = i; k < j; k++)
                    /*①书本上写k=i+1开始，但注意书上还说k的位置为i<=k<j，所以写k=i开始也对。
                     * ②注意这里没有r的出现，只是插入点位置k，故只和i和j有关。*/ {
                    int t = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (t < m[i][j]) {//与备忘录比较，有更小的就替换备忘录的
                        m[i][j] = t;
                        s[i][j] = k;
                    }
                }
            }
        }
    }
    public static void Traceback(int i, int j, int[][] s) {//可以不写Traceback方法
        if (i == j) return;
        Traceback(i, s[i][j], s);//这里既包括开始的整个数组递归找k的位置，又包括之后的前半部分的递归找k位置。
        Traceback(s[i][j] + 1, j, s);//这里是后半部分递归找k的位置，+1很明显是后半部分了。
        System.out.println("Multiply  A" + i + "," + s[i][j] + "and A" + (s[i][j] + 1) + "," + j);
    }
    public static void main(String[] args) {
        MatrixChain YG = new MatrixChain();
        System.out.println("请输入矩阵连乘数组元素个数：");
        Scanner in = new Scanner(System.in);
        int shumu=in.nextInt();
        int[] p=new int[shumu];
        System.out.println("请输入元素 空格隔开");
        for(int i=0;i<p.length;i++){
            p[i]=in.nextInt();}
       // int p[] = {3,4,6,5,7,2};
        int n = p.length;/*①设置n=7是因为矩阵下标从1开始，其实最大也就只有m[6][6]。即矩阵数加1
        ②这里的n与开始的n意思不太一样，这里是舍弃了[0][0]，而开始的n是不从数组角度考虑，从纯数学角度考虑*/
        int[][] m = new int[n][n];//舍弃[0][0]，所以设置n=7，其实最大也就只有m[6][6]
        int[][] s = new int[n][n];
        YG.MatrixChain(p, n - 1, m, s);
        for ( int i = 1; i < n; i++) {
            {
                for (int j = 1; j < n; j++) {
                    System.out.print(m[i][j] + "   ");
                }
                System.out.println();
            }
        }
        System.out.println();
        for (int i = 1; i < n; i++) {
            {
                for (int j = 1; j < n; j++) {
                    System.out.print(s[i][j] + "\t");
                }
                System.out.println();
            }
        }
        System.out.println();
        YG.Traceback(1, n-1, s);
    }
}
