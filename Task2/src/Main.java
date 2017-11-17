import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static long[] generateNumbers(int N) {
        Random rnd = new Random();
        long[] result = new long[N];
        for (int i = 0; i < N; i++){
            result[i] = rnd.nextLong();
        }
        return result;
    }

    public static void sort(long[] array, int start, int end){
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (array[i] <= array[cur])) {
                i++;
            }
            while (j > cur && (array[cur] <= array[j])) {
                j--;
            }
            if (i < j) {
                long temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        sort(array, start, cur);
        sort(array, cur+1, end);
    }

    public static long[] merge(long[] first, long[] second){
        int indexFirst = 0;
        int indexSecond = 0;
        long[] result = new long[first.length + second.length];
        for (int i = 0; i < result.length; i++){
            if (indexSecond == second.length || first[indexFirst] < second[indexSecond]){
                result[i] = first[indexFirst];
                indexFirst++;
            }
            else {
                result[i] = second[indexSecond];
                indexSecond++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Size of first array");
        int n1 = input.nextInt();
        System.out.println("Size of second array");
        int n2 = input.nextInt();
        long[] first = generateNumbers(n1);
        long[] second = generateNumbers(n2);
        sort(first, 0, first.length - 1);
        sort(second, 0, second.length - 1);
        long[] result = merge(first, second);
        for (int i = 0; i < result.length - 1; i++)
            if (result[i] > result[i + 1])
            {
                System.out.println("error");
                break;
            }
    }
}
