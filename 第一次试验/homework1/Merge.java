public class Merge extends SortAlgorithm {
    private void sort(Comparable[] objs, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(objs, left, mid);
            sort(objs, mid + 1, right);
            merge(objs, left, mid, right);
        }
    }
    private void merge(Comparable[] objs, int left, int mid, int right) {
        Comparable[] temp = new Comparable[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (less(objs[i], objs[j])) {
                temp[k++] = objs[i++];
            } else {
                temp[k++] = objs[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = objs[i++];
        }
        while (j <= right) {
            temp[k++] = objs[j++];
        }
        for (i = left, k = 0; i <= right; i++, k++) {
            objs[i] = temp[k];
        }
    }

    public void sort(Comparable[] objs) {
        sort(objs, 0, objs.length - 1);
        if (!isSorted(objs)) {
            throw new RuntimeException("Merge sort failed");
        }
    }
}