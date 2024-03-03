package tree;

public class TreeAlgo {

    public int diameter = 0;

    /**
     * get the longest path of a tree, not necessary passing through root
     * maximum taken summing path got from left and right child
     * @return diameter
     */
    public int getTreeDiameter(TreeNode<Integer> root) {
        if (root == null)
            return 0;

        int dl = getTreeDiameter(root.left);
        int dr = getTreeDiameter(root.right);
        diameter = Math.max(diameter, dl + dr);
        return Math.max(dl, dr) + 1;
    }
}
