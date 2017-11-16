import java.util.*;

public class Main {

    public static ArrayList<Long> generateNumbers(int N) {
        Random rnd = new Random();
        ArrayList<Long> result = new ArrayList<>();
        for (int i = 0; i < N; i++){
            long ins = 0;
            do {
                ins = rnd.nextLong();
            } while (result.contains(ins));
            result.add(ins);
        }
        return result;
    }

    public static long maxValue(long[] array){
        long result = Long.MIN_VALUE;
        for (long a: array){
            if (a > result){
                result = a;
            }
        }
        return result;
    }

    public static<T extends AbstractCollection<Long>> long maxValue(T array){
        long result = Long.MIN_VALUE;
        for (long a: array){
            if (a > result){
                result = a;
            }
        }
        return result;
    }

    public static long[] getArray(ArrayList<Long> etalon){
        long[] result = new long[etalon.size()];
        int index = 0;
        for (long a: etalon){
            result[index] = a;
            index++;
        }
        return result;
    }

    public static LinkedList<Long> getLinkedList(ArrayList<Long> etalon){
        LinkedList<Long> result = new LinkedList<>();
        result.addAll(etalon);
        return result;
    }

    public static Vector<Long> getVector(ArrayList<Long> etalon){
        Vector<Long> result = new Vector<>();
        result.addAll(etalon);
        return result;
    }

    public static HashSet<Long> getHashSet(ArrayList<Long> etalon){
        HashSet<Long> result = new HashSet<>();
        result.addAll(etalon);
        return result;
    }

    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        int N = Integer.parseInt(args[1]);
        long arrayTime = 0;
        long arrayListTime = 0;
        long listTime = 0;
        long vectorTime = 0;
        long hashSetTime = 0;
        long currentTime;

        for (int i = 0; i < 1000; i++){
            ArrayList<Long> etalon = generateNumbers(N);
            maxValue(etalon);
        }

        for (int i = 0; i < M; i++) {
            ArrayList<Long> etalon = generateNumbers(N);
            long[] array = getArray(etalon);
            currentTime = System.nanoTime();
            maxValue(array);
            arrayTime += System.nanoTime() - currentTime;
        }
        System.out.println("Array: " + arrayTime / M);

        for (int i = 0; i < M; i++) {
            ArrayList<Long> etalon = generateNumbers(N);
            currentTime = System.nanoTime();
            maxValue(etalon);
            arrayListTime += System.nanoTime() - currentTime;
        }
        System.out.println("ArrayList: " + arrayListTime / M);

        for (int i = 0; i < M; i++) {
            ArrayList<Long> etalon = generateNumbers(N);
            LinkedList<Long> list = getLinkedList(etalon);
            currentTime = System.nanoTime();
            maxValue(list);
            listTime += System.nanoTime() - currentTime;
        }
        System.out.println("LinkedList: " + listTime / M);

        for (int i = 0; i < M; i++) {
            ArrayList<Long> etalon = generateNumbers(N);
            Vector<Long> vector = getVector(etalon);
            currentTime = System.nanoTime();
            maxValue(vector);
            vectorTime += System.nanoTime() - currentTime;
        }
        System.out.println("Vector: " + vectorTime / M);

        for (int i = 0; i < M; i++) {
            ArrayList<Long> etalon = generateNumbers(N);
            HashSet<Long> hashSet = getHashSet(etalon);
            currentTime = System.nanoTime();
            maxValue(hashSet);
            hashSetTime += System.nanoTime() - currentTime;
        }
        System.out.println("HashSet: " + hashSetTime / M);
    }
}
