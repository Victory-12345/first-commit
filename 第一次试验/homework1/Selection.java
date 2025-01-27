public class Selection extends SortAlgorithm {
    public void sort(Comparable[] objs) {
        int N = objs.length;
        for (int i = 0; i < N - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < N; j++) {
                if (less(objs[j], objs[minIndex])) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                exchange(objs, i, minIndex);
            }
        }
        if (!isSorted(objs)) {
            throw new RuntimeException("Selection sort failed");
        }
    }
}