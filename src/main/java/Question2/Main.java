package Question2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    /**
     * Case 1.
     * 4
     * {1,2,3}
     * Answer = 4
     */

    /**
     * Case 2.
     * 10
     * {2,5,3,6}
     * Answer = 5
     */

    /**
     * Case 3.
     * 4
     * {1,2}
     * Answer = 3
     */

    /**
     * Case 4.
     * 5
     * {1,2,5}
     * Answer = 4
     */

    /**
     * Case 5. 0 {1, 2, 3} Answer = 1
     */

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) {
        int sum = read();
        int[] coins = readWithSplit(",");

        int[] dp = new int[sum + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= sum; i++) {
                dp[i] += dp[i - coin];
            }
        }
        write(dp[sum]);
        close();
    }


    public static int read() {
        try {
            return Integer.parseInt(String.valueOf(br.readLine()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int[] readWithSplit(String split) {
        try {
            String input = br.readLine().replaceAll("[{}]", "");
            return Arrays.stream(input.split(split))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void write(int result) {
        try {
            bw.write(String.valueOf(result));
            bw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close() {
        try {
            br.close();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
