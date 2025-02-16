public class Main {
    public static int sumOfArray(int[][] array) {
        // Инициализируем переменную для хранения суммы
        int sum = 0;

        // Проходим по каждой строке массива
        for (int i = 0; i < array.length; i++) {
            // Проходим по каждому элементу в строке
            for (int j = 0; j < array[i].length; j++) {
                // Добавляем текущий элемент к сумме
                sum += array[i][j];
            }
        }

        // Возвращаем сумму
        return sum;
    }

    public static void main(String[] args) {
        // Пример двумерного массива
        int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 10}
        };

        // Вычисляем сумму элементов массива
        int result = sumOfArray(array);

        // Выводим результат
        System.out.println("Сумма всех элементов массива: " + result);
    }
}
