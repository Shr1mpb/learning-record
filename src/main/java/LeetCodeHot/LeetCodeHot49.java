package LeetCodeHot;

import homework.tree.TreeNode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LeetCodeHot49 {
    /**
     * 二叉树的最近公共祖先
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //思路：深度优先搜索 如果其中一个是另一个的子节点，则返回其中的父节点
        //如果都不是，那么返回其中层高的节点的父节点
        lowestHelper(root, p, q, new TreeNode(Integer.MIN_VALUE));
        return ret;

    }

    Map<TreeNode, Integer> map = new HashMap<>();//回溯时清除的map，存储节点和它的层数，层数从1开始
    Map<TreeNode, Integer> map2 = new HashMap<>();//回溯时不清除的map
    Map<TreeNode,TreeNode> map3 = new HashMap<>();//存储父节点
    TreeNode ret;
    boolean acontinue = true;
    int finish = 0;//标记搜寻到了几个p和q，如果搜到了，那就+1，当为2时开始分析
    //用于存储节点
    private void lowestHelper(TreeNode root, TreeNode p, TreeNode q,TreeNode father) {
        while(acontinue){
            if (root == null) return;
            map.put(root, map.getOrDefault(father, 0) + 1);
            map2.put(root, map2.getOrDefault(father, 0) + 1);
            map3.putIfAbsent(root, father);

            lowestHelper(root.left, p, q, root);
            lowestHelper(root.right, p, q, root);
            if (root.val == p.val || root.val == q.val) {
                finish++;
            }
            if (finish == 2) {//结算
                if (map.containsKey(p) && map.containsKey(q)) {//父子关系
                    if (map.get(p) < map.get(q)) {//p是父
                        ret = p;
                    }else{
                        ret = q;
                    }
                }else{
                    //非父子关系
                    if(map2.get(p) < map2.get(q)){//p在上
                        if (root == p){
                            ret = father;
                        }else{
                            ret = map3.get(q);
                        }
                    }else{
                        if (root == q){
                            ret = father;
                        }else{
                            ret = map3.get(p);
                        }
                    }
                }
                acontinue = false;
                return;
            }
            //回溯时清除
            map.remove(root);
        }
    }



    //上面自己写的方法超时了：下面是官方解法(递归)

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }
    private TreeNode ans;
    private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return false;
        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        //   分别是root的左右   或      root是其中之一，另一个是子
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {//结算
            ans = root;
        }
        //    如果有子返回true 无子返回false
        return lson || rson || (root.val == p.val || root.val == q.val);
    }



    //精简：
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q)//根节点是之一 直接返回
            return root;
        /*
        递归左右子树：
            左子树返回值为 l，右子树返回值为 r。
            根据左右子树的返回值判断：
            如果左子树返回 null，说明 p 和 q 都在右子树中，返回右子树的结果。
            如果右子树返回 null，说明 p 和 q 都在左子树中，返回左子树的结果。
            如果左右子树都不为 null，说明当前节点就是最近公共祖先，返回当前节点。
         */
        TreeNode r = lowestCommonAncestor(root.right , p , q);
        TreeNode l = lowestCommonAncestor(root.left , p , q);
        return l == null ? r : (r == null ? l : root);
    }





}
