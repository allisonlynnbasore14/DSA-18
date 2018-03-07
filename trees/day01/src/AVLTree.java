public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    TreeNode<T> delete(TreeNode<T> n, T key) {
        n = super.delete(n, key);
        if (n != null) {
            // update the height of the tree using the height of the left and right child
            if(n.rightChild.height > n.leftChild.height){
                n.height = n.rightChild.height + 1;
            }else{
                n.height = n.leftChild.height + 1;
            }

            // return balance(n)
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
        n.height = height(n);
        if (n != null) {
            // update the height of the tree using the height of the left and right child
            if(n.rightChild != null ){
                if(n.leftChild != null){
                    if(n.rightChild.height < n.leftChild.height){
                        n.height = n.leftChild.height + 1;
                        return balance(n);
                    }
                }
                n.height = n.rightChild.height + 1;
            }

            // return balance(n)
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

        TreeNode<T>  rC = n.rightChild;
        TreeNode<T>  lC = n.leftChild;
        if(rC == null && lC== null){
            return 1;
        }

        int left = 0;
        int right = 0;

        if(rC != null){
            right  = height(rC);
        }

        if(lC != null){
            left  = height(lC);
        }

        if(right > left){
            return right + 1;
        }else{
            return left + 1;
        }

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
/*        if(rC == null && lC== null){
            return root;
        }
        if(rC == null){
            return balance(lC);
        }
        if(lC == null){
            return balance(rC);
        }

        if (balance(n) == root){*/
        int left = 0;
        int right = 0;

        // check cases
        if (balanceFactor(n) >= 2) {
            if (right >= 0) {
                rotateLeft(n);
            } else if (right < 0) {
                rotateRight(rC);
                rotateLeft(n);
            }
        }

        if (balanceFactor(n) <= -2) {
            if (right <= 0) {
                rotateRight(n);
            } else if (right > 0) {
                rotateLeft(rC);
                rotateRight(n);
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
            hL = height(n.leftChild);
        }
        int hR = -1;
        if(n.rightChild != null){
            hR = height(n.rightChild);
        }

        return hR - hL;
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        TreeNode<T> b = null;
        TreeNode<T> x = null;
        if(n!= null && n.hasRightChild()){
            x = n.rightChild;
        }
        if(x != null && x.hasLeftChild()){
            b = x.leftChild;
        }

        if(x!= null){
            x.leftChild = n;
        }

        if(n!= null){
            n.rightChild = b;
        }
        return n;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {

        TreeNode<T> b = null;
        TreeNode<T> x = null;
        if(n!= null && n.hasLeftChild()){
            x = n.leftChild;
        }
        if(x != null && x.hasRightChild()){
            b = x.rightChild;
        }

        if(x!= null){
            x.rightChild = n;
        }

        if(n!= null){
            n.leftChild = b;
        }
        return n;
    }
}
