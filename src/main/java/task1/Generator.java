package task1;

import java.util.*;

public class Generator {

    private int size;
    private int min;
    private int max;

    public Generator(int size, int min, int max) {
        this.size = size;
        this.min = min;
        this.max = max;
    }

    public List<Integer> genList(){
        List<Integer> list = new LinkedList<Integer>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(max - min + 1) + min);
        }
        return list;
    }

    public Set<Integer> genSet(){
        Set<Integer> set = new HashSet<Integer>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            set.add(random.nextInt(max - min + 1) + min);
        }
        return set;
    }
}
