public class RecursionDemo {
    public static void main(String[] args){
        System.out.print(fact(5));
        print("Jeff");
        System.out.println(gcd(16, 100));
        System.out.println(bin(137));
        rev(5);
    }


    /* Recursive Method: calls itself */
    public static int fact(int n){
        //* Base Case */
        if (n == 1){
            return 1;
        }

        //* Recursive Case */
        return n * fact(n-1);
    }

    public static int sum(int n){
        if (n == 1){
            return 1;
        }

        return n + sum(n - 1);
    }

    // Show stack
    public static void rev(int n){
        if (n==0){
            return;
        }
        System.out.println("Before recursive call, n = " + n);
        rev(n-1);
        System.out.println("After recursive call, n = "+n);
    }

    //! Every recursive solution has a iterative solution

    public static String print(String str){
        if (str.length() == 0){
            return null;
        }

        System.out.print(str.charAt(0));

        return print(str.substring(1));
    }

    public static int gcd(int x, int y){
        //* Base Case */
        if (y==0){
            return x;
        }

        // "%" returns the remainder
        //* Recursive Case */
        return gcd(y , x % y);
    }

    public static String bin(int n){
        if (n==1){
            return "1";
        }
        //* Integer division | "+" concatinates, not adds. */
        return bin(n / 2) + (n % 2);
    }

}
