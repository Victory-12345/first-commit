import java.util.Arrays;

public class NutBoltMatcher {
    public void matchNutsAndBolts(String[] nuts, String[] bolts) {
        match(nuts, bolts, 0, nuts.length - 1);
    }

    private void match(String[] nuts, String[] bolts, int left, int right) {
        if (left > right) return;

        // 选择最后一个螺丝作为基准
        String pivot = bolts[right];
        int pivotIndex = partition(nuts, pivot, left, right);

        // 使用匹配的螺母索引来分割螺丝
        partition(bolts, nuts[pivotIndex], left, right);

        // 递归处理
        match(nuts, bolts, left, pivotIndex - 1);
        match(nuts, bolts, pivotIndex + 1, right);
    }

    private int partition(String[] array, String pivot, int left, int right) {
        int i = left;
        for (int j = left; j < right; j++) {
            if (match(array[j], pivot) == -1) {
                swap(array, i, j);
                i++;
            } else if (match(array[j], pivot) == 0) {
                swap(array, j, right); // 把等于 pivot 的放到最后
                j--; // 继续检查这个位置
            }
        }
        swap(array, i, right); // 将 pivot 放到正确的位置
        return i;
    }

    private int match(String nut, String bolt) {
        // 返回 0（匹配），1（nut > bolt），-1（nut < bolt）
        return nut.compareTo(bolt);
    }

    private void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        NutBoltMatcher matcher = new NutBoltMatcher();
        String[] nuts = {"1", "2", "3", "4","5", "6", "7", "8"};
        String[] bolts = {"1", "2", "3", "4","5", "6", "7", "8"};

        matcher.matchNutsAndBolts(nuts, bolts);

        System.out.println("Nuts: " + Arrays.toString(nuts));
        System.out.println("Bolts: " + Arrays.toString(bolts));
    }
}
