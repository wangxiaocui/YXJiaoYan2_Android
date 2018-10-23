package com.test.yanxiu.common_base.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Cai Lei on 2018/10/12.
 */

/**
 * 通用的树结构
 */
public class TreeNode extends Object {
    @SerializedName(value = "uid", alternate = {"number", "id"})
    public String uid;
    @SerializedName(value = "name")
    public Object payload;
    @SerializedName(value = "children", alternate = {"data", "subArea", "stages", "subjects", "items"})
    public ArrayList<TreeNode> children = null;     // 双向，子节点s
    public TreeNode parent = null;                  // 双向，父节点，树根为null

    // 根节点level为0，往叶子发展level每层增加1
    public int getLevel() {
        int level = 0;
        TreeNode node = parent;
        while (node != null) {
            level++;
            node = node.parent;
        }
        return level;
    }

    // 返回所有叶子节点到跟节点的距离里最大的距离值，只有根节点的话depth = 0
    public int getDepth() {
        if (children == null) {
            return 0;
        }

        int maxDepth = 0;
        for (TreeNode node : children) {
            maxDepth = Math.max(maxDepth, node.getDepth());
        }
        return maxDepth + 1;
    }

    // 格式为: [level][最少1个字符，最多5个字符的随机名称]
    private String randomName() {
        int len = new Random().nextInt(5) + 1;
        StringBuilder str = new StringBuilder();
        str.append(getLevel()+":");
        for (int i = 0; i < len; i++) {
            char c = (char) (new Random().nextInt(0x9fa5 - 0x4e00 + 1) + 0x4e00);
            str.append(c);
        }
        return str.toString();
    }

    // 从gson等转换过来的TreeNode没有设置parent，调用此函数补全parent
    static public void setParentRecurse(TreeNode root) {
        for (TreeNode node : root.children) {
            node.parent = root;
            setParentRecurse(node);
        }
    }

    /**
     * Utils方法，可以随机生成一颗树
     * @param limitDepth 最大可能的树深，实际生成的树depth <= limitDepth
     * @param limitChildrenCount 每个节点子节点数目 <= limitChildrenCount
     * @return
     */
    static public TreeNode randomTree(int limitDepth, int limitChildrenCount) {
        if (limitDepth < 0) {
            return null;
        }

        TreeNode root = new TreeNode();
        root.payload = root.randomName();
        int childrenCount = new Random().nextInt(limitChildrenCount + 1);
        if ((childrenCount > 0) && (limitDepth > 0)) {
            root.children = new ArrayList<>();
        }
        for (int i = 0; i < childrenCount; i++) {
            TreeNode node = randomTree(limitDepth - 1, limitChildrenCount);
            if (node != null) {
                root.children.add(node);
                node.parent = root;
            }
        }
        return root;
    }

    @Override
    public String toString() {
        return payload.toString();
    }
}
