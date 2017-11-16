import java.util.*;

public class Main {

    public static ArrayList<Long> generateNumbers(int N){
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
        int N = 1000;
        ArrayList<Long> etalon = generateNumbers(N);
        System.out.println(maxValue(getArray(etalon)));
        System.out.println(maxValue(etalon));
        System.out.println(maxValue(getLinkedList(etalon)));
        System.out.println(maxValue(getVector(etalon)));
        System.out.println(maxValue(getHashSet(etalon)));
    }
}
