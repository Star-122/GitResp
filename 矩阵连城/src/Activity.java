public class Activity {
    public static void GreedySelector(int[] s, int[] f, int[] A) {//s是开始(start)，f是结束(finally)
        int n = s.length - 1;
        A[0] = 1;//最先那个最优
        int j = 0;
        for (int i = 1; i <= n; i++) { //下一活动的开始时间s[i]不早于(即不小于,即>=)上一活动的结束时间f[j]，所以有j<i
            if (s[i] >= f[j]) { //如果>=，则表示两个活动相互兼容，将i活动标记为1
                A[i] = 1;
                j = i;
            } else { //与已安排活动不相容，标记此活动未安排
                A[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        //初始化数据s数组记录活动开始时间，f数组记录活动结束时间，//A[]记录为1的活动，即相容的活动
        int[] s = {1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};//s[ ]的长度为11
        int[] f = {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        int[] A = new int[s.length];//思考这里为什么是s.length的长度，为什么不用减1呢？而且减1会报错，说越界了？没错啊，就是不能-1，因为申请的数组长度是s.length，即11个，与s[ ]一样也是从0开始数数。所以减1则A[ ]的长度只有10了，肯定越界。
        int count = 0;//记录满足的活动(==1)的个数，初始令为0
        GreedySelector(s, f, A);
        for (int i = 0; i <= s.length - 1; i++) {
            if (A[i] == 1) {
                count++;
                System.out.println("第" + (i + 1) + "个活动被选中， 其开始时间为：" + s[i] + "， 结束时间为：" + f[i]);
            }
        }
        System.out.println("活动数量为 " + count);
    }
}

