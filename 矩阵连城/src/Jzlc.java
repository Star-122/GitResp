import java.util.Scanner;

public class Jzlc {
    public static void lc(int m[][],int n,int s[][],int p[])
    {
        for(int i=1;i<=n;i++)
        {//对角线上的值均为0,初始化
            m[i][i]=0; }
        for(int r=2;r<=n;r++)//相乘的矩阵的个数
        {
            for(int i=1;i<=n-r+1;i++)//i+r-1最大为n,当r=2时为2个矩阵相乘，i从1到5.当r=6时，i只能为1
            {
                int j=i+r-1;
                m[i][j]=m[i+1][j]+p[i-1]*p[i]*p[j];//从第i处断开
                s[i][j]=i;
                for(int k=i+1;k<j;k++)
                {
                    int t=m[i][k]+m[k+1][j]+p[i-1]*p[k]*p[j];//从第i个点以后逐个断开
                    if(t<m[i][j])
                    {
                        m[i][j]=t;
                        s[i][j]=k;
                    } } } }
        System.out.println(m[1][n]); }
    public static void traceback(int s[][],int i,int j)
    {
        if(i==j)System.out.print("A"+i);
        else
        {
            System.out.print("(");
            traceback(s,i,s[i][j]);
            traceback(s,s[i][j]+1,j);
            System.out.print(")"); } }
    public static void main(String[] args) {
        int n;
        Scanner rd=new Scanner(System.in);
        System.out.println("请输入矩阵的个数:");
        n=rd.nextInt();
        int p[]=new int[n+1];
        System.out.println("请输入矩阵具体信息:");
        for(int i=0;i<n+1;i++)
        { p[i]=rd.nextInt(); }
        int m[][]=new int[n+1][n+1];
        int s[][]=new int[n+1][n+1];
        System.out.println("最小值为:");lc(m,n,s,p);
    }

}
