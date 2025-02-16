import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        int n = i;
        int j;
        int heightMinRoad = 1000;
        int heightMaxMin = 0;
        int height;
        int Road = 0;

        while (i > 0) {
            j = scanner.nextInt();
            while (j > 0) {
                height = scanner.nextInt();
                if (height <  heightMinRoad) {
                    heightMinRoad = height;
                }
                j--;
            }
            if (heightMaxMin < heightMinRoad) {
                heightMaxMin = heightMinRoad;
                Road = n+1-i;
            }
            heightMinRoad = 1000;
            i--;
            scanner.nextLine();
        }
        scanner.close();
        System.out.print(Road);
        System.out.print(" ");
        System.out.print(heightMaxMin);
    }
}
