public class Shell1 extends SortAlgorithm {
    public void sort(Comparable[] objs) {
        int N = objs.length;
        int gap = N/2;
        // 使用不同的间隔进行排序
        while (gap > 0) {
            for (int i = gap; i < N; i++) {
                Comparable temp = objs[i];
                int j;
                for (j = i; j >= gap && less(temp, objs[j - gap]); j -= gap) {
                    objs[j] = objs[j - gap];
                }
                objs[j] = temp;
            }
            gap = gap / 2; // 更新间隔
        }
        if (!isSorted(objs)) {
            throw new RuntimeException("Shell1 sort failed");
        }
    }
}
