package com.practice.datastructure.dynamicplan;

public class Solution650TwoKey {
    public static void main(String[] args) {
        Solution650TwoKey s = new Solution650TwoKey();
        System.out.println(s.minSteps(6));
    }

    private int result=Integer.MAX_VALUE;;

    public  int minSteps(int n) {
        if (n == 1) {
            return 0;
        }
        //result用来统计最终结果...因为要统计最小值，因此初始化为Integer.MAX_VLAUE

        //初始情况下剪切板current中已经有一个A了，总共sum的数量也是一个，步数是1(复制)...
        dfs(n, 1, 1, 1);
        return result;
    }

    //n表示目标的A的数量，current表示你当前剪切板中A的数量，sum表示当前路径中已经产生的A的数量
    //step表示当前你已经走的步数，result就是统计最终的结果的
    public  void dfs(int n, int current, int sum, int step) {
        //如果当前的A的数量已经超过了目标数量需要剪枝...直接return
        //如果当前A的数量达到了目标数量，那么需要统计一下最小的step数量...再return
        if (sum >= n) {
            result = sum == n ? Math.min(result, step) : result;
            return;
        }
        //如果我直接将就之前拷贝的，那么step只需要+1，因为只需要粘贴就行了...
        //只需要粘贴的话，下一次要进行操作的元素还是current，sum变成了sum+current，step++
        dfs(n, current, sum + current, step + 1);

        //如果不想将就之前拷贝的，我想要重新拷贝，那么你即将要粘贴的元素就变成了sum
        //你即将产生的结果就是sum*2...需要先复制再张贴，所以step+=2...
        dfs(n, sum, sum << 1, step + 2);
    }
}
