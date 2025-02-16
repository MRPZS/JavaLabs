import java.util.HashSet;
import java.util.Set;

public class Main {
    public static int[] findPair(int[] nums, int target) {

        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {

            int complement = target - num;

            if (seen.contains(complement)) {
                return new int[]{complement, num};
            }
            seen.add(num);
        }

        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15, 3, 8};
        int target = 10;

        int[] result = findPair(nums, target);

        if (result != null) {
            System.out.println("Пара: (" + result[0] + ", " + result[1] + ")");
        } else {
            System.out.println("Пара не найдена.");
        }
    }
}
