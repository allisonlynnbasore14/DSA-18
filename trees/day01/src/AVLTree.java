public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    // everything is logn
    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    TreeNode<T> delete(TreeNode<T> n, T key) {

        n = super.delete(n, key);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    TreeNode<T> insert(TreeNode<T> n, T key) {

        n = super.insert(n, key);
        if (n != null) {

            // update the height of the tree using the height of the left and right child
            // return balance(n)

            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    TreeNode<T> deleteMin(TreeNode<T> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(TreeNode<T> n) {
        // Recursively call n.child height until there are no children and return the heights each time adding one
        // At the end, keep which is higher
        // Base case is when the child is null
        if (n == null) {
            return -1;
        }
        return n.height;
    }


    public int height() {
        return Math.max(height(root), 0);
    }

    // Restores the AVL tree property of the subtree. Return the head of the new subtree
    TreeNode<T> balance(TreeNode<T> n) {

        // recursively check for the balance factor of every node
        // if it is a very unbalanced thing, check the four cases
        // once you are through them all return the root

        // Base Case: when children == null
        TreeNode<T>  rC = n.rightChild;
        TreeNode<T>  lC = n.leftChild;

        int left = 0;
        int right = 0;
        n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));

        // check cases

        if(n.hasRightChild()){
            right = balanceFactor(n.rightChild);
        }
        if(n.hasLeftChild()){
            left = balanceFactor(n.leftChild);
        }

        //int kk = balanceFactor(n);
        if (balanceFactor(n) >= 2) {
            if (right >= 0) {
                n = rotateLeft(n);
            } else if (right < 0) {
                n.rightChild = rotateRight(rC);
                n = rotateLeft(n);
            }
        }

        if (balanceFactor(n) <= -2) {
            if (left <= 0) {
                n = rotateRight(n);
            } else if (left > 0) {
                n.leftChild = rotateLeft(lC);
                n = rotateRight(n);
            }
        }

        return n;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(TreeNode<T> n) {
        int hL = -1;
        if(n.leftChild != null){
            hL = n.leftChild.height;
        }
        int hR = -1;
        if(n.rightChild != null){
            hR = n.rightChild.height;
        }

        return hR - hL;
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        TreeNode<T> x = n.leftChild;
        TreeNode<T> b = x.rightChild;
        //n.leftChild = x.rightChild;
        x.rightChild = n;
        n.leftChild = b;

        if (b != null) {
            b.height = 1 + Math.max(height(b.leftChild), height(b.rightChild));
        }
        n.height = Math.max(height(n.leftChild), height(n.rightChild)) + 1;
        x.height = Math.max(height(x.rightChild), n.height) + 1;
        return x;
    }
    /**
     *
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {
        TreeNode<T> x = n.rightChild;
        TreeNode<T> b = x.leftChild;
        n.rightChild = x.leftChild;
        x.leftChild = n;
        n.rightChild = b;

        if (b != null) {
            b.height = 1 + Math.max(height(b.leftChild), height(b.rightChild));
        }
        n.height = Math.max(height(n.leftChild), height(n.rightChild)) + 1;
        x.height = Math.max(height(x.rightChild), n.height) + 1;
        return x;
    }

}
