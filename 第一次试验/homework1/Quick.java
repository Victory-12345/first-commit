public class Quick extends SortAlgorithm {
  //  private static final int INSERTION_SORT_THRESHOLD = 9;

    public void sort(Comparable[] objs) {
        sort(objs, 0, objs.length - 1);
        if (!isSorted(objs)) {
            throw new RuntimeException("Quick sort failed");
        }
    }

    private void sort(Comparable[] objs, int left, int right) {
        while (left < right) {
         //  if (right - left < INSERTION_SORT_THRESHOLD) {
         //       insertionSort(objs, left, right);
          //      break; // 使用插入排序后直接返回
          //  }
            int pivotIndex = medianOfThree(objs, left, right);
            swap(objs, pivotIndex, right); // 将枢轴移到末尾
            int newPivotIndex = partition(objs, left, right);
            // 递归较小的部分
            if (newPivotIndex - left < right - newPivotIndex) {
                sort(objs, left, newPivotIndex - 1);
                left = newPivotIndex + 1; // 处理右边部分
            } else {
                sort(objs, newPivotIndex + 1, right);
                right = newPivotIndex - 1; // 处理左边部分
            }
        }
    }

    private int medianOfThree(Comparable[] objs, int left, int right) {
        int center = (left + right) / 2;
        if (less(objs[center], objs[left])) swap(objs, left, center);
        if (less(objs[right], objs[left])) swap(objs, left, right);
        if (less(objs[right], objs[center])) swap(objs, center, right);
        return center; // 返回中位数的索引
    }

    private int partition(Comparable[] objs, int left, int right) {
        Comparable pivot = objs[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (less(objs[j], pivot)) {
                swap(objs, i, j);
                i++;
            }
        }
        swap(objs, i, right);
        return i;
    }

    private void insertionSort(Comparable[] objs, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            Comparable tmp = objs[i];
            int j = i - 1;
            while (j >= left && less(tmp, objs[j])) {
                objs[j + 1] = objs[j];
                j--;
            }
            objs[j + 1] = tmp;
        }
    }

    private void swap(Comparable[] objs, int i, int j) {
        Comparable temp = objs[i];
        objs[i] = objs[j];
        objs[j] = temp;
    }


}
