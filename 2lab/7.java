public class Main {
    public static int[] findMaxInEachRow(int[][] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        int[] maxElements = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int max = array[i][0];

            for (int j = 1; j < array[i].length; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                }
            }

            maxElements[i] = max;
        }

        return maxElements;
    }

    public static void main(String[] args) {
        int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 18, 9}
        };

        int[] result = findMaxInEachRow(array);

        if (result != null) {
            System.out.print("Максимальные элементы в каждой строке: ");
            for (int num : result) {
                System.out.print(num + " ");
            }
        } else {
            System.out.println("Массив пустой.");
        }
    }
}
