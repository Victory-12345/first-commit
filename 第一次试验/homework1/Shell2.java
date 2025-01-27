public class Shell2 extends SortAlgorithm {
    public void sort(Comparable[] objs) {
        int N = objs.length;
        // 计算Hibbard间隔序列
        int[] gaps = new int[(int) (Math.log(N + 1) / Math.log(2))];
        for (int k = 0; k < gaps.length; k++) {
            gaps[k] = (1 << (k + 1)) - 1; // 2^(k+1) - 1
        }

        for (int gapIndex = gaps.length - 1; gapIndex >= 0; gapIndex--) {
            int gap = gaps[gapIndex];
            // 插入排序
            for (int i = gap; i < N; i++) {
                Comparable temp = objs[i];
                int j = i;
                while (j >= gap && less(temp, objs[j - gap])) {
                    objs[j] = objs[j - gap];
                    j -= gap;
                }
                objs[j] = temp;
            }
        }
        if (!isSorted(objs)) {
            throw new RuntimeException("Shell2 sort failed");
        }
    }
}
