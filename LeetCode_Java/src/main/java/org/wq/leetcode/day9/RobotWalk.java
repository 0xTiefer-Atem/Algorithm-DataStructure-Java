package org.wq.leetcode.day9;

// 机器人走路
// 给定 N个 格子
// 设置机器人初始位置 S   终止位置 E
// 设置移动步数 K
// 求机器人由 S -> E 有几种走法
public class RobotWalk {

    /**
     *
     * @param N 一共有多少格子
     * @param K 机器人能走几步
     * @param S 机器人起始位置
     * @param E 机器人结束位置
     * @return 返回走法
     */
    public static int robotWalk1(int N, int K, int S, int E) {
        return f1(N, K, S, E);
    }

    private static int f1(int n, int k, int s, int e) {
        if (k == 0) {
            return s == e ? 1: 0;
        }

        if (s == 1) {
            return f1(n, k-1, 2, e);
        }

        if (s == n) {
            return f1(n, k-1, s-1, e);
        }
        return f1(n, k-1, s-1, e) + f1(n, k-1, s+1, e);
    }

    public static int robotWalk2(int N, int K, int S, int E) {
        int[][] dp = new  int[K + 1][N + 1];

        for (int i = 0; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = -1;
            }
        }

        return f2(N, K, S, E, dp);
    }

    private static int f2(int n, int k, int s, int e, int[][] dp) {

        if (dp[k][s] != -1) {
            return dp[k][s];
        }

        if (k == 0) {
            dp[k][s] = s == e ? 1: 0;
            return dp[k][s];
        }

        if (s == 1) {
            dp[k][s] = f1(n, k-1, 2, e);
        } else if (s == n) {
            dp[k][s] = f1(n, k-1, s-1, e);
        }else{
            dp[k][s] = f1(n, k-1, s-1, e) + f1(n, k-1, s+1, e);
        }


        return dp[k][s];
    }



    public static void main(String[] args) {
        int i = robotWalk2(5, 4, 2, 4);
        System.out.println(i);
    }
}
