public class Main {
    public static int[][] rotate90CounterClockwise(int[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }

        int n = matrix.length;
        int m = matrix[0].length;
        int[][] rotatedMatrix = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                rotatedMatrix[m - 1 - j][i] = matrix[i][j];
            }
        }

        return rotatedMatrix;
    }

    public static void printMatrix(int[][] matrix) {
        if (matrix == null) {
            System.out.println("Матрица пустая.");
            return;
        }

        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("Исходная матрица:");
        printMatrix(matrix);

        int[][] rotatedMatrix = rotate90CounterClockwise(matrix);

        System.out.println("Матрица после поворота на 90 градусов по часовой стрелке:");
        printMatrix(rotatedMatrix);
    }
}
