import java.util.HashMap;
import java.util.Map;

public class LongestUniqueSubstring {
    public static String findLongestUniqueSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        Map<Character, Integer> lastSeen = new HashMap<>();
      
        int start = 0;
        int maxStart = 0;
        int maxEnd = 0;

        for (int end = 0; end < s.length(); end++) {
            char currentChar = s.charAt(end);

            if (lastSeen.containsKey(currentChar) && lastSeen.get(currentChar) >= start) {
                start = lastSeen.get(currentChar) + 1;
            }

            lastSeen.put(currentChar, end);
          
            if (end - start > maxEnd - maxStart) {
                maxStart = start;
                maxEnd = end;
            }
        }

        return s.substring(maxStart, maxEnd + 1);
    }

    public static void main(String[] args) {
        String input = "abcabcbb";
        String result = findLongestUniqueSubstring(input);
        System.out.println("Наибольшая подстрока без повторяющихся символов: " + result);
    }
}
