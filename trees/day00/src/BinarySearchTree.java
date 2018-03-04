import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    /**
     * Add a node to the BST. Internally calls insert to recursively find the new node's place
     */
    public boolean add(T key) {
        if (find(root, key) != null) return false;
        root = insert(root, key);
        size++;
        return true;
    }

    public void addAll(T[] keys) {
        for (T k : keys)
            add(k);
    }

    public List<T> inOrderTraversal() {
        return inOrdert(root);
    }


    public List<T> inOrdert(TreeNode<T> n){
        // go to left then root then right
        List<T> list = new ArrayList<T>();

        //Base Case

        if(n == null){
            return list;
        }

        list.addAll(inOrdert(n.leftChild));
        list.add(n.key);
        list.addAll(inOrdert(n.rightChild));

        return list;

    }

    /**
     * Deletes a node from the BST using the following logic:
     * 1. If the node has a left child, replace it with its predecessor
     * 2. Else if it has a right child, replace it with its successor
     * 3. If it has no children, simply its parent's pointer to it
     */
    public boolean delete(T key) {
        TreeNode<T> toDelete = find(root, key);
        if (toDelete == null) {
            System.out.println("Key does not exist");
            return false;
        }
        TreeNode<T> deleted = delete(toDelete);
        if (toDelete == root) {
            root = deleted;
        }
        size--;
        return true;
    }

    private TreeNode<T> delete(TreeNode<T> n) {
        // Recursive base case
        if (n == null) return null;

        TreeNode<T> replacement;

        if (n.isLeaf()){
            // Case 1: no children
            replacement = null;
            n.replaceWith(replacement);
/*            if(n.parent != null){
                replacement.parent = n.parent;
            }*/

            return replacement;
        }else if (n.hasRightChild() != n.hasLeftChild()) {
            // Case 2: one child
            replacement = (n.hasRightChild()) ? n.rightChild : n.leftChild; // replacement is the non-null child
            n.replaceWith(replacement);

            return replacement;
        }else {
            // Case 3: two children
            // replace n with succ or pres
            // recrusively deltet the succ or pres
            TreeNode<T> p = findSuccessor(n);
            replacement = p;
            n.replaceWith(replacement);

            delete(p);
            return replacement;
        }

        // Put the replacement in its correct place, and set the parent.
    }

    public T findPredecessor(T key) {
        TreeNode<T> n = find(root, key);
        if (n != null) {
            TreeNode<T> predecessor = findPredecessor(n);
            if (predecessor != null)
                return predecessor.key;
        }
        return null;
    }

    public T findSuccessor(T key) {
        TreeNode<T> n = find(root, key);
        if (n != null) {
            TreeNode<T> successor = findSuccessor(n);
            if (successor != null)
                return successor.key;
        }
        return null;
    }

/*    private TreeNode<T> findPredecessorIter(TreeNode<T> n, T key) {
        TreeNode<T> target = n;
        if(target == null){
            return target;
        }
        return target;
    }*/

    private TreeNode<T> findPredecessor(TreeNode<T> n) {
        // Needs to be a recursive thing that
        // Base case of null
        // 1) The right most node on your left sideS
        // 2) if key is < root, sucesss = root and , search recrusivle into left


        TreeNode<T> target = n;

        if (target == null) {
            return target;
        }


        if (n.leftChild != null) {
            target = n.leftChild;
            while (target != null) {


                System.out.println(target);
                if (target.rightChild == null) {
                    //System.out.println(target);
                    return target;
                }
                target = target.rightChild;
            }
            return target;
        }

/*        if (root.key.compareTo(target.key) >= 0) {
            return findPredecessor(root.leftChild);
        }*/

        target = n;
        while(target.parent != null){
            if(target.parent.key.compareTo(n.key) <=0){
                // if the parent is smaller
                return target.parent;
            }else{
                target = target.parent;
            }
        }
        return null;

    }

/*        if(target.parent != null){
            if(target.parent.key.compareTo(target.key) <= 0){
                return target.parent;
            }else if(target.leftChild == null){
                if(target.parent.parent != null) {
                    if(target.parent.parent.key.compareTo(target.parent.key) <=0){
                        System.out.println(target.parent);
                        System.out.println("ddd");
                        return target.parent.parent;
                    }
                }
            }
        }
        if(n.leftChild != null) {
            target = n.leftChild;

        }else{



            if( n.parent.key.equals(7)){
                System.out.println("lll");
            }
            return null;
        }



       // System.out.println(target);
        return target;
    }*/

    private TreeNode<T> findSuccessor(TreeNode<T> n) {

        TreeNode<T> target = n;

        if (target == null) {
            return target;
        }


        if (n.rightChild != null) {
            target = n.rightChild;
            while (target != null) {
                if (target.leftChild == null) {
                    //System.out.println(target);
                    return target;
                }
                target = target.leftChild;
            }
            return target;
        }

/*        if (root.key.compareTo(target.key) >= 0) {
            return findPredecessor(root.leftChild);
        }*/

        target = n;
        while(target.parent != null){
            if(target.parent.key.compareTo(n.key) >=0){
                // if the parent is smaller
                return target.parent;
            }else{
                target = target.parent;
            }
        }
        return null;

    }

    /**
     * Returns a node with the given key in the BST, or null if it doesn't exist.
     */
    private TreeNode<T> find(TreeNode<T> currentNode, T key) {
        if (currentNode == null)
            return null;
        int cmp = key.compareTo(currentNode.key);
        if (cmp < 0)
            return find(currentNode.leftChild, key);
        else if (cmp > 0)
            return find(currentNode.rightChild, key);
        return currentNode;
    }

    /**
     * Recursively insert a new node into the BST
     */
    private TreeNode<T> insert(TreeNode<T> node, T key) {
        if (node == null) return new TreeNode<>(key);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.leftChild = insert(node.leftChild, key);
            node.leftChild.parent = node;
        } else {
            node.rightChild = insert(node.rightChild, key);
            node.rightChild.parent = node;
        }
        return node;
    }
}
