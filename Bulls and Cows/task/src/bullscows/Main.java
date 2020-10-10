package bullscows;

import java.util.HashSet;
import java.util.Scanner;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    static int calculateBulls(String s, String t) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(i)) {
                res++;
            }
        }
        return res;
    }

    static int calculateCows(String s, String t) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 1; j < t.length(); j++) {
                if (i != j && s.charAt(i) == t.charAt(j))
                    res++;
            }

        }
        return res;
    }

    static char foo(int n) {
        if (n < 10)
            return (char) (n + '0');
        return (char) (n - 10 + 'a');
    }

    static String generator(int n, int baze) {

        StringBuffer res = new StringBuffer();

        int size = 0;
        Set<Character> hash_Set = new HashSet<Character>();
        while (size < n) {
            int x = (int) (Math.random() * baze);
            char digit = foo(x);
            if (size == 0 && digit == '0') {
                continue;
            }
            if (hash_Set.contains(digit)) {
                continue;
            }
            hash_Set.add(digit);
            size++;
            res.append(digit);

        }
        return res.toString();
        /*
        while (true) {
            long pseudoRandomNumber = Math.abs(System.nanoTime());
            long res = 0;
            int size = 0;
            Set<Integer> hash_Set = new HashSet<Integer>();
            while (!(pseudoRandomNumber <= 0) && size != n) {
                int digit = (int) (pseudoRandomNumber % 10);
                //System.out.println( digit + "  " + res + " " + pseudoRandomNumber );
                pseudoRandomNumber /= 10;
                if (hash_Set.contains(digit)) {
                    continue;
                }
                hash_Set.add(digit);
                res = res * 10 + digit;
                size++;
            }
            if (size == n) {
                return res;
            }
        }*/
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Input the length of the secret code:");
        int n = -1;
        try {
            n = scanner.nextInt();
        } catch (Exception e){
            System.out.println("Error: \"abc 0 -7\" isn't a valid number.");
            return;
        }
        scanner.nextLine();
        if (n > 36 || n < 1) {
            //System.out.println("Note that the "  + n +" character indicates the beginning of the input line.");
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }
        System.out.println("Input the number of possible symbols in the code:");
        int baze = -1;
        try {
            baze = scanner.nextInt();
        } catch (Exception e){
            System.out.println("Error: \"abc 0 -7\" isn't a valid number.");
            return;
        }
        scanner.nextLine();
        if (n > baze) {
            //System.out.println("Note that the "  + n +" character indicates the beginning of the input line.");
            System.out.println("Error: it's not possible to generate a code with a length of " + n +  " with " + baze  + " unique symbols.");
            return;
        }
        if (baze > 36 ) {
            //System.out.println("Note that the "  + n +" character indicates the beginning of the input line.");
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        }

        String code = String.valueOf(generator(n, baze));
        //System.out.println("Code = " + code);
        System.out.print("The secret is prepared: "); //"**** (0-9, a-f)."
        for (int i = 0; i < n; i++) {
            System.out.print("*");
        }
        System.out.print(" (0-" + Math.min(9, baze));
        if (baze > 9) {
            System.out.print(", a-" + (char) (baze - 11 + 'a') + ").");
        } else {
            System.out.print(").");
        }
        System.out.println("");


        System.out.println("Okay, let's start a game!");
        int step = 1;
        while (true) {
            System.out.println("Turn " + step++ + ":");
            String input = scanner.nextLine();
            int cows = calculateCows(code, input);
            int bulls = calculateBulls(code, input);
            System.out.print("Grade: ");
            if (bulls != 0) {
                System.out.print(bulls + " bull(s)");
            }
            if (bulls != 0 && cows != 0) {
                System.out.print(" and ");
            }
            if (cows != 0) {
                System.out.print(cows + " cow(s)");
            }
            if (bulls == 0 && cows == 0) {
                System.out.print("None");
            }
            System.out.println();
            if (bulls == n) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
        }


    }
}
