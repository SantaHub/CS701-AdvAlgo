public class BTTraversal {
//    Binary tree traversal from leetcode.
//    https://leetcode.com/problems/binary-tree-inorder-traversal/submissions/

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> inOrderTraversal = new ArrayList();
            if (root != null) {
                if (root.left != null) {
                    inOrderTraversal.addAll(inorderTraversal(root.left));
                }
                inOrderTraversal.add(root.val);
                if (root.right != null) {
                    inOrderTraversal.addAll(inorderTraversal(root.right));
                }
            }
            return inOrderTraversal;
        }
    }
}