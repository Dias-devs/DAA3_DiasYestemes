package MST.Utils;

import java.util.HashMap;
import java.util.Map;

public class DisjointSet<T> {
    private final Map<T, T> parent = new HashMap<>();
    private final Map<T, Integer> rank = new HashMap<>();

    public void makeSet(Iterable<T> items) {
        for (T x : items) {
            parent.put(x, x);
            rank.put(x, 0);
        }
    }

    public T find(T x) {
        T p = parent.get(x);
        if (p == null) return null;
        if (!p.equals(x)) {
            T root = find(p);
            parent.put(x, root);
            return root;
        }
        return p;
    }

    public void union(T a, T b) {
        T ra = find(a);
        T rb = find(b);
        if (ra == null || rb == null) return;
        if (ra.equals(rb)) return;
        int rka = rank.getOrDefault(ra, 0);
        int rkb = rank.getOrDefault(rb, 0);
        if (rka < rkb) parent.put(ra, rb);
        else if (rka > rkb) parent.put(rb, ra);
        else {
            parent.put(rb, ra);
            rank.put(ra, rka + 1);
        }
    }
}