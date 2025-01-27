public class Quicktest extends SortAlgorithm {
    private int maxDepth = 0; // 用于记录最大深度

    public void sort(Comparable[] objs) {
        sort(objs, 0, objs.length - 1, 1); // 从1开始，因为初始深度为1
        if (!isSorted(objs)) {
            throw new RuntimeException("Quicktest sort failed");
        }
        System.out.println("Maximum recursion depth: " + maxDepth);
    }

    private void sort(Comparable[] objs, int left, int right, int depth) {
        // 更新最大深度
        maxDepth = Math.max(maxDepth, depth);

        if (left < right) {
            int pivotIndex = medianOfThree(objs, left, right);
            swap(objs, pivotIndex, right);
            int newPivotIndex = partition(objs, left, right);
            // 递归较小的部分
            if (newPivotIndex - left < right - newPivotIndex) {
                sort(objs, left, newPivotIndex - 1, depth + 1); // 递归左边
                sort(objs, newPivotIndex + 1, right, depth + 1); // 递归右边
            } else {
                sort(objs, newPivotIndex + 1, right, depth + 1); // 递归右边
                sort(objs, left, newPivotIndex - 1, depth + 1); // 递归左边
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

    private void swap(Comparable[] objs, int i, int j) {
        Comparable temp = objs[i];
        objs[i] = objs[j];
        objs[j] = temp;
    }



}
