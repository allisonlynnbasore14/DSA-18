import java.util.*;

public class Permutations {

    // space = 0(n)
    // time = !n

    private static void backtrack(LinkedList<Integer> curr, Set<Integer> unused, List<List<Integer>> subsets) {
        if (unused.isEmpty())
            subsets.add(new LinkedList<>(curr));
        for (int u : new LinkedList<>(unused)) {
            curr.addLast(u);
            unused.remove(u);
            backtrack(curr, unused, subsets);
            unused.add(u);
            curr.removeLast();
        }

    }

    public static List<List<Integer>> permutations(List<Integer> A) {
        List<List<Integer>> permutations = new LinkedList<>();
        LinkedList<Integer> holder = new LinkedList<>();
        Set<Integer> options = new HashSet<>(A);
        backtrack(holder, options ,permutations);
        return permutations;
    }

}
