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
        int indexResult = 0;
        long[] result = new long[first.length + second.length];

        while (indexFirst < first.length && indexSecond < second.length){
            if (first[indexFirst] < second[indexSecond]){
                result[indexResult] = first[indexFirst];
                indexFirst++;
            }
            else {
                result[indexResult] = second[indexSecond];
                indexSecond++;
            }
            indexResult++;
        }

        while (indexFirst < first.length)
        {
            result[indexResult] = first[indexFirst];
            indexFirst++;
            indexResult++;
        }

        while (indexSecond < second.length)
        {
            result[indexResult] = second[indexSecond];
            indexSecond++;
            indexResult++;
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

        long[] warmArray1;
        long[] warmArray2;

        for (int i = 0; i < 100; i++) {
            warmArray1 = generateNumbers(100000);
            sort(warmArray1, 0, warmArray1.length - 1);
            warmArray2 = generateNumbers(100000);
            sort(warmArray2, 0, warmArray2.length - 1);
        }

        long currentTime = System.nanoTime();
        sort(first, 0, first.length - 1);
        System.out.println("First array sort: " + (System.nanoTime() - currentTime) + "ns");

        currentTime = System.nanoTime();
        sort(second, 0, second.length - 1);
        System.out.println("Second array sort: " + (System.nanoTime() - currentTime) + "ns");

        for (int i = 0; i < 100; i++) {
            long[] warmArray3 = merge(first, second);
        }

        currentTime = System.nanoTime();
        long[] result = merge(first, second);
        System.out.println("Arrays merge: " + (System.nanoTime() - currentTime) + "ns");

        for (int i = 0; i < first.length - 1 ; i++){
            if (first[i] > first[i+1])
            {
                System.out.println("error");
                break;
            }
        }
        for (int i = 0; i < second.length - 1 ; i++){
            if (second[i] > second[i+1])
            {
                System.out.println("error");
                break;
            }
        }
        for (int i = 0; i < result.length - 1 ; i++){
            if (result[i] > result[i+1])
            {
                System.out.println("error");
                break;
            }
        }
    }
}
