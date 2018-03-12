import java.util.NoSuchElementException;


public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private boolean isRed(TreeNode x) {
        return x != null && x.color == RED;
    }

    private boolean isBlack(TreeNode x) {
        return x != null && x.color == BLACK;
    }

    // ====================================
    //            Insertion Code
    // ====================================


    public boolean add(T key) {
        super.add(key);
        root.color = BLACK;
        return true;
    }


    // make a left-leaning link lean to the right
    TreeNode<T> rotateRight(TreeNode<T> h) {
        TreeNode<T> x = h.leftChild;
        TreeNode<T> b = x.rightChild;
        boolean c = h.color;
        x.rightChild = h;
        h.leftChild = b;
        h.color = x.color;
        x.color = c;
        return x;
    }

    // make a right-leaning link lean to the left
    TreeNode<T> rotateLeft(TreeNode<T> h) {
        TreeNode<T> x = h.rightChild;
        TreeNode<T> b = x.leftChild;
        boolean c = h.color;
        x.leftChild = h;
        h.rightChild = b;
        h.color = x.color;
        x.color = c;
        return x;
    }

    // flip the colors of a TreeNode and its two children
    TreeNode<T> flipColors(TreeNode<T> h) {
        boolean c = h.color;
        TreeNode<T> x1 = h.rightChild;
        TreeNode<T> x2 = h.leftChild;
        h.color = x1.color;
        if(x1 != null){
            x1.color = c;
        }
        if(x2 != null){
            x2.color = c;
        }
        return h;
    }


    /**
     * fix three cases:
     *   1. h.right is red
     *   2. h.left is red, and h.left.left is red
     *   3. h.left and h.right are red
     * return balanced node
     */
    private TreeNode<T> balance(TreeNode<T> h) {

        if(h.rightChild != null && h.rightChild.color){
            h = rotateLeft(h);
        }


        if( h.leftChild != null && h.leftChild.color && h.leftChild.leftChild != null && h.leftChild.leftChild.color){
            h = rotateRight(h);
        }

        if(h.rightChild != null && h.rightChild.color){
            if(h.leftChild != null && h.leftChild.color){
                h = flipColors(h);
            }
        }
        return h;
    }


    /**
     * Recursively insert a new node into the Bbalance(ST
     * Runtime: TODO
     */
    @Override
    TreeNode<T> insert(TreeNode<T> h, T key) {

//        if(root != null){
//            System.out.println(root.key.toString());
//        }
//
//        if(h == null){
//            //h = new TreeNode<T>(key, true);
//                    //h = super.insert(h, key);
//            //return super.insert(h, key);
//            return new TreeNode<T>(key, true);
//        }
//
//        if(h.key.compareTo(key) < 0){
//            h = insert(h.rightChild, key);
//            //super.insert(h, key);
//        }else if (h.key.compareTo(key) >= 0){
//            h = insert(h.leftChild, key);
//            //super.insert(h, key);
//        }

        h = super.insert(h, key);
        h = balance(h);
        return h;
    }


    // ====================================
    //            Deletion Code
    // ====================================


    /**
     * Removes the specified key from the tree
     * (if the key is in this tree).
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean delete(T key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return false;

        // if both children of root are black, set root to red
        if (!isRed(root.leftChild) && !isRed(root.rightChild))
            root.color = RED;

        root = delete(root, key);
        size--;
        if (!isEmpty()) root.color = BLACK;
        return true;
    }


    // the smallest key in subtree rooted at x; null if no such key
    private TreeNode<T> min(TreeNode<T> x) {
        if (x.leftChild == null) return x;
        else return min(x.leftChild);
    }

    // delete the key-value pair with the minimum key rooted at h
    TreeNode<T> deleteMin(TreeNode<T> h) {
        // OPTIONAL TODO: write this function and use it in delete(h, key)
        return h;
    }
    // delete the key-value pair with the given key rooted at h
    TreeNode<T> delete(TreeNode<T> h, T key) {
        // OPTIONAL TODO
        return h;
    }

    // ====================================
    //          LLRB Verification
    // ====================================


    // TODO: understand how the following functions can be used to verify a valid LLRB

    public boolean is23() {
        return is23(root);
    }

    // return true if this LLRB is a valid 2-3 tree
    private boolean is23(TreeNode<T> n) {
        if (n == null) return true;
        if (isRed(n.rightChild)) return false;
        if (isRed(n.leftChild) && isRed(n.leftChild.leftChild)) return false;
        return is23(n.rightChild) && is23(n.leftChild);
    }

    public boolean isBalanced() {
        return isBalanced(root) != -1;
    }

    // return -1 if the tree is not balanced. Otherwise, return the black-height of the tree
    private int isBalanced(TreeNode<T> n) {
        if (n == null) return 0;
        int lBalanced = isBalanced(n.leftChild);
        int rBalanced = isBalanced(n.rightChild);
        if (lBalanced == -1 || rBalanced == -1) return -1;
        if (isBlack(n.leftChild)) lBalanced++;
        if (isBlack(n.rightChild)) rBalanced++;
        if (lBalanced != rBalanced) return -1;
        return lBalanced;
    }

}