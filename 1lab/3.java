import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x_treasure = scanner.nextInt();
        int y_treasure = scanner.nextInt();
        int x_current = 0;
        int y_current = 0;

        int steps = 0;
        String direction = "";

        scanner.nextLine();
        direction = scanner.nextLine();

        while (!direction.equals("стоп")) {
            steps = scanner.nextInt();

            scanner.nextLine();
            direction = scanner.nextLine();

            if (!direction.equals("север")) {
                y_current += steps;
            }
            else if (!direction.equals("юг")) {
                y_current -= steps;
            }
            else if (!direction.equals("запад")) {
                x_current -= steps;
            }
            else {
                x_current += steps;
            }
        }
        scanner.close();

        if (x_treasure == 0 && y_treasure == 0) {
            System.out.println(0);
        }
        else if  (x_treasure == 0 ^ y_treasure == 0) {
            System.out.println(1);
        }
        else {
            System.out.println(2);
        }
    }
}
