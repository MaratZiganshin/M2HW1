import java.util.ArrayList;
import java.util.Random;

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
    public static void main(String[] args) {
        ArrayList<Long> test = generateNumbers(50);
        for (long a : test)
            System.out.println(a);
    }
}
