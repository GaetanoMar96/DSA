package tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {

    /**
     * Build tree based on preorder and inorder
     * @param preorder first element is the root node
     * @param inorder first left subtree, then root and then right subtree
     * @return tree
     */
    public TreeNode<Integer> buildTree(int[] preorder, int[] inorder) {
        List<Integer> preorderList = new ArrayList<>();
        for (int value : preorder) {
            preorderList.add(value);
        }
        List<Integer> inorderList = new ArrayList<>();
        for (int value : inorder) {
            inorderList.add(value);
        }
        return dfs(preorderList, inorderList);
    }

    public TreeNode<Integer> dfs(List<Integer> preorder, List<Integer> inorder) {
        if (preorder == null || inorder == null)
            return null;
        int rootVal = preorder.remove(0); //remove root value
        int index = inorder.indexOf(rootVal);
        TreeNode<Integer> node = new TreeNode<>(rootVal);
        List<Integer> leftPortion = inorder.subList(0, index);
        List<Integer> rightPortion = inorder.subList(index + 1, inorder.size());
        node.left = dfs(preorder, leftPortion);
        node.right = dfs(preorder, rightPortion);
        return node;
    }

    public void insertNodeBST() {
        //TODO
    }

    public void removeNodeBST() {
        //TODO
    }
}
