package com.yq.ds.search;

/**
 * @program: JavaDataStructure
 * @description: B-树
 * @author: Yuqing
 * @create: 2023-06-20 09:04
 **/
public class BTree<E extends Comparable> {

    // 默认3阶树
    private final Integer DEFAULT_ORDER = 3;

    // 树阶
    private int order = DEFAULT_ORDER;

    // 结点中关键字个数的最大值
    private int maxKeySize = order - 1;

    // 一个节点内的最少关键字数，少于该数目需要进行合并
    private int nonLeafMinKeys = (int) Math.ceil(order / 2.0) - 1;

    // 根节点
    private BNode<E> root;

    public BTree() {
        root = new BNode<>();
        root.setLeaf(true);
    }

    public BTree(int order) {
        this();
        this.order = order;
        this.maxKeySize = order - 1;
        this.nonLeafMinKeys = (int) Math.ceil(order / 2.0) - 1;
    }

    /**
     * 查询某个元素是否存在
     * @param key 要查询的元素
     * @return true or false
     */
    public boolean search(E key){
        SearchResult result = search(root, key);
        return result.isExist();
    }

    private SearchResult search(BNode<E> node,E key){
        SearchResult result = node.searchResult(key);
        if(result.isExist()){
            return result;
        }else{
            // 叶子结点
            if(node.isLeaf()){
                return result;
            }
            // 递归搜索子结点
            int index = result.getIndex();
            return search(node.getChildNodesList().get(index),key);
        }
    }

    /**
     * 插入新结点
     */
    public boolean insertKey(E key){
        // 查询 key 在 B-树 中的情况
        SearchResult result = search(root, key);
        if(result.isExist()){
            return false;
        }
        return insertKey(result.getNode(),result.getIndex(),key);
    }

    private boolean insertKey(BNode<E> node,int index,E key){
        node.getKeyList().add(index,key);
        if(node.getKeyList().size() > maxKeySize){
            // 分裂
            splitNode(node);
        }
        return true;
    }

    /**
     * 分裂结点
     */
    private void splitNode(BNode<E> node){
        // 取结点中间 key 下标
        int midIndex = node.getKeyList().size() / 2;
        // 该 key 需要往上移动
        E key = node.getKeyList().get(midIndex);

        // --------------划分关键字--------------
        BNode<E> newNode = new BNode<>();
        newNode.setLeaf(node.isLeaf());
        // 新结点取源结点的 (midIndex,node.keyList.size()-1] 的关键字
        for(int i=midIndex+1;i<node.getKeyList().size();i++){
            newNode.getKeyList().add(node.getKeyList().get(i));
        }
        // 源结点只留下标为 [0,midIndex) 的关键字
        if (node.getKeyList().size() > midIndex) {
            node.getKeyList().subList(midIndex, node.getKeyList().size()).clear();
        }
        // --------------划分指针--------------
        // 若不是叶子结点，需要将原来结点的孩子结点也进行分裂，
        // 新结点取 (midIndex,node.keyList.size()-1] 的孩子结点
        if (!node.isLeaf()) {
            for (int i = midIndex + 1; i < node.getChildNodesList().size(); i++) {
                newNode.getChildNodesList().add(node.getChildNodesList().get(i));
                // 修改孩子结点的双亲结点为新结点
                node.getChildNodesList().get(i).setParent(newNode);
            }
            // 源结点只留下标为 [0,midIndex] 的孩子结点
            if (node.getChildNodesList().size() > midIndex + 1) {
                node.getChildNodesList().subList(midIndex + 1,
                        node.getChildNodesList().size()).clear();
            }
        }

        // --------------分裂父结点--------------
        // 将 key 和 newNode 插入父结点，明显 node 在父结点中已存在，
        // 并且 node 代表的数字小于 newNode 代表的数字
        BNode<E> father = node.getParent();
        if (null == father) {
            // 若结点的双亲结点为 null,说明是根结点进行的分裂，需要新增结点作为根结点
            father = new BNode<>();
            father.getChildNodesList().add(node);
            father.setLeaf(false);
            father.getKeyList().add(key);
            father.getChildNodesList().add(newNode);
            node.setParent(father);
            newNode.setParent(father);
        } else {
            newNode.setParent(father);
            SearchResult re = father.searchResult(key);
            father.getKeyList().add(re.getIndex(), key);
            father.getChildNodesList().add(re.getIndex() + 1, newNode);
            // 若双亲的关键字个数超出最大允许值，则继续分裂
            if (father.getKeyList().size() > maxKeySize) {
                splitNode(father);
            }
        }

        // 如果 father 无双亲，则说明是根节点
        if (father.getParent() == null) {
            this.root = father;
        }
    }


    /**
     * 在 B-树 中删除元素
     * @param key 要删除的元素
     * @return true or false
     */
    public boolean deleteKey(E key){
        SearchResult result = search(root, key);
        if(!result.isExist()){
            return false;
        }

        BNode<E> keyInNode = result.getNode();
        // 是否是叶子结点
        if (!keyInNode.isLeaf()) {

            // 非叶子结点，取第i个孩子结点中的最大关键字；keyChildNode 中的节点存在最大的小于 key 的节点
            BNode<E> keyChildNode = keyInNode.getChildNodesList().get(result.getIndex());
            // 如果孩子结点不是叶子结点，去找这个子树到叶子结点中最大的关键字，与key互换位置
            if (!keyChildNode.isLeaf()) {
                keyChildNode = getMaxLeaf(keyChildNode);
            }
            // 获取 B-树 中小于 key 的最大元素
            E childMinKey = keyChildNode.getKeyList().get(keyChildNode.getKeyList().size() - 1);
            // 将两个元素交换位置
            keyInNode.getKeyList().add(result.getIndex(), childMinKey);
            keyInNode.getKeyList().remove(key);
            keyChildNode.getKeyList().remove(childMinKey);
            keyChildNode.getKeyList().add(key);
            keyInNode = keyChildNode;
        }
        return deleteKey(keyInNode,key);
    }

    private boolean deleteKey(BNode<E> node,E key){
        // 如果需要删除关键字的结点，原本的关键字个数超过 Math.ceil(order / 2.0) - 1，则直接删除
        if (node.getKeyList().size() > nonLeafMinKeys) {
            node.getKeyList().remove(key);
            return true;
        }
        // 如果需要删除关键字的结点，原本的关键字个数不超过Math.ceil(order / 2.0) - 1，则需要进行合并
        if (node.getKeyList().size() == nonLeafMinKeys) {
            doManageNode(node, key);
        }
        return true;
    }

    /**
     * 合并节点；合并节点时，node 是叶子节点；因为在 deleteKey 方法中不是叶子节点会进行交换
     * @param node 要删元素所在节点
     * @param key 要删除的元素
     */
    private void doManageNode(BNode<E> node,E key){
        // 根节点无需合并
        if(null == node.getParent()){
            return;
        }
        // 根据 node 在其父结点中的位置，得到左右兄弟
        int nodeIndex = node.getParent().getChildNodesList().indexOf(node);
        BNode<E> parent = node.getParent();
        BNode<E> leftNode = null;
        BNode<E> rightNode = null;
        if(0 < nodeIndex){
            leftNode = parent.getChildNodesList().get(nodeIndex-1);
        }
        if(0<=nodeIndex && nodeIndex<parent.getChildNodesList().size()-1){
            rightNode = parent.getChildNodesList().get(nodeIndex+1);
        }

        // 例如 P0 1 P1 2 P2 6 P3 7 P4
        // P0   P1   P2   P3   P4
        //    1    2    6    7
        if(null!=leftNode && leftNode.getKeyList().size()>nonLeafMinKeys){
            // 获取父结点中 nodeIndex-1 处的关键字，该关键字小于 node 中所有的关键字
            E nodeParentKey = parent.getKeyList().get(nodeIndex-1);
            // 将左兄弟的最大关键字插入父结点 nodeIndex-1 位置
            parent.getKeyList().add(
                    nodeIndex-1,leftNode.getKeyList().get(leftNode.keySize()-1));
            // 删除这两个节点
            parent.getKeyList().remove(nodeParentKey);
            leftNode.getKeyList().remove(leftNode.keySize()-1);
            // 将 nodeParentKey 插入 node 的第一个位置
            node.getKeyList().add(0,nodeParentKey);
            node.getKeyList().remove(key);
            return;
        }

        if(null!=rightNode && rightNode.getKeyList().size()>nonLeafMinKeys){
            // 获取父结点中 nodeIndex 处的关键字，该关键字大于 node 中所有的关键字
            E nodeParentKey = parent.getKeyList().get(nodeIndex);
            // 将右兄弟的最小关键字插入父结点 nodeIndex 位置
            parent.getKeyList().add(nodeIndex,rightNode.getKeyList().get(0));
            parent.getKeyList().remove(nodeParentKey);
            rightNode.getKeyList().remove(0);
            // 将 nodeParentKey 插入 node 的最后一个位置
            node.getKeyList().add(nodeParentKey);
            node.getKeyList().remove(key);
            return;
        }

        // 左右兄弟结点的关键字个数都不足的话，合并兄弟结点，下放一个双亲结点的关键字
        if (leftNode != null) {
            //合并结点
            node = merge(leftNode, node);
            node.getKeyList().remove(key);
            // 父结点是非终端结点，其关键字个数少于 nonLeafMinKeys，则对父结点进行合并
            // 如果父结点是根结点，则无需处理
            if (node.getParent().getKeyList().size() < nonLeafMinKeys
                    && null != node.getParent().getParent()) {
                // 寻找结点合并
                doManageNode(node.getParent(), null);
            }

            // node 的父节点是根节点，并且父节点为空，则更新根节点
            if (null == node.getParent().getParent()
                    && node.getParent().getKeyList().isEmpty()) {
                root = node;
                root.setParent(null);
            }
            return;
        }

        if (rightNode != null) {
            //合并结点
            node = merge(node, rightNode);
            node.getKeyList().remove(key);

            if (node.getParent().getKeyList().size() < nonLeafMinKeys
                    && null != node.getParent().getParent()) {
                // 寻找结点合并
                doManageNode(node.getParent(), null);
            }
            if (null == node.getParent().getParent()
                    && node.getParent().getKeyList().isEmpty()) {
                root = node;
                root.setParent(null);
            }
        }
    }

    /**
     * 合并两个结点
     * 将父结点中的第一个大于所有左结点关键字的关键字并入左结点中，将右结点的关键字也并入左结点中；如果右结点不是叶子结点，将所有指针并入左结点
     * 删除父结点中的关键字以及右指针
     */
    private BNode<E> merge(BNode<E> leftNode, BNode<E> rightNode) {
        // leftNode 在父节点中的位置
        int index = leftNode.getParent().getChildNodesList().indexOf(leftNode);
        // 将在 leftNode 和 rightNode 之间的关键字并入 leftNode
        leftNode.getKeyList().add(leftNode.getParent().getKeyList().get(index));
        leftNode.getParent().getKeyList().remove(index);
        // 将 rightNode 中的关键字并入 leftNode
        leftNode.getKeyList().addAll(rightNode.getKeyList());
        if (!rightNode.isLeaf()) {
            // rightNode 不是叶子节点，则将指针也并入 leftNode
            leftNode.getChildNodesList().addAll(rightNode.getChildNodesList());
        }
        // 在父节点中删除 rightNode 的指针
        leftNode.getParent().getChildNodesList().remove(rightNode);
        return leftNode;
    }

    /**
     * 获取 node 结点到最右侧子结点的叶子结点
     * @param node
     * @return
     */
    private BNode<E> getMaxLeaf(BNode<E> node) {
        BNode<E> keyChildNode = node.getChildNodesList().get(node.getChildNodesList().size() - 1);
        if (!keyChildNode.isLeaf()) {
            getMaxLeaf(keyChildNode);
        }
        return keyChildNode;
    }

}
