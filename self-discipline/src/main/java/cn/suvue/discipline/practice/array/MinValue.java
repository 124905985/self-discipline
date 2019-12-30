package cn.suvue.discipline.practice.array;

/**
 * 设计一个泛型函数，用于获取数组的最小值
 *
 * @author suvue
 * @date 2019/12/30
 */
public class MinValue {
    private static <T extends Number & Comparable<? super T>> T minValue(T[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        T min = values[0];
        for (int i = 0; i < values.length; i++) {
            if (min.compareTo(values[i]) > 0) {
                min = values[i];
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Integer[] ints = new Integer[]{1, 2, 3, 8, -1, -34};
        System.out.println(minValue(ints));//-34
    }
}
