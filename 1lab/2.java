import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sum = 0;
        int i;
        while (n > 0) {
            i = scanner.nextInt();
            if (n % 2 == 0) {
                sum -= i;
            }
            else {
                sum += i;
            }
            n--;
        }
        scanner.close();
        System.out.println(sum);
    }
}
