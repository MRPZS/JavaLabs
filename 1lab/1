import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        int count = 0;
        do {
            if(n % 2 == 0) {
                n = n/2;
            }
            else {
                n = 3 * n + 1;
            }
            count++;
        } while(n != 1);
        System.out.println(count);
    }
}
