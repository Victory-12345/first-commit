import static java.lang.Math.pow;

public class Shell3 extends SortAlgorithm {
    public void sort(Comparable[] objs) {
        int N = objs.length;
        // 计算Knuth间隔序列
        int[] gaps = new int[N];
        int k = 0;
        for (int gap = 1; gap < N; gap = (int) ((pow(3,gap) + 1) / 2)) {
            gaps[k++] = gap;
        }

        for (int gapIndex = k - 1; gapIndex >= 0; gapIndex--) {
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
            throw new RuntimeException("Shell3 sort failed");
        }
    }
}
