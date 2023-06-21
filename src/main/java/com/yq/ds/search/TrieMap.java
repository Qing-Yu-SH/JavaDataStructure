package com.yq.ds.search;

import java.util.LinkedList;
import java.util.List;

/**
 * @program: JavaDataStructure
 * @description: 前缀树
 * @author: Yuqing
 * @create: 2023-06-21 16:58
 **/
public class TrieMap<V> {

    // ASCII 码个数
    private static final int R = 256;
    // 当前存在 Map 中的键值对个数
    private int size = 0;

    // 树节点
    private static class TrieNode<V> {
        V val = null;
        TrieNode<V>[] children = new TrieNode[R];
    }

    // Trie 树的根节点
    private TrieNode<V> root = null;

    /***** 增/改 *****/

    /**
     * 在 Map 中添加 key
     * @param key 键
     * @param val 值
     */
    public void put(String key, V val){
        if(!containsKey(key)){
            size++;
        }
        root = put(root,key,val,0);
    }

    private TrieNode<V> put(TrieNode<V> node,String key,V val,int i){
        if(node == null){
            node = new TrieNode<>();
        }
        if(i == key.length()){
            node.val = val;
            return node;
        }
        char ch = key.charAt(i);
        node.children[ch] = put(node.children[ch],key,val,i+1);
        return node;
    }

    /***** 删 *****/

    /**
     * 删除键 key 以及对应的值
     * @param key 键
     */
    public void remove(String key){
        if(!containsKey(key)){
            return;
        }
        root = remove(root,key,0);
        size--;
    }

    private TrieNode<V> remove(TrieNode<V> node,String key,int i){
        if(node == null){
            return null;
        }
        // 删除
        if(i == key.length()){
            node.val = null;
        }else{
            char ch = key.charAt(i);
            node.children[ch] = remove(node.children[ch],key,i+1);
        }

        if(node.val != null){
            // 如果该 TireNode 存储着 val，不需要被清理
            return node;
        }
        // 检查该 TrieNode 是否还有后缀
        for(int j=0;j<R;j++){
            if(node.children[j] != null){
                // 只要存在一个子节点（后缀树枝），就不需要被清理
                return node;
            }
        }
        return null;
    }

    /***** 查 *****/

    /**
     * 键是否存在
     * @param key 键
     * @return true or false
     */
    public boolean containsKey(String key){
        return get(key) != null;
    }

    /**
     * 搜索 key 对应的值，不存在则返回 null
     * @param key 键
     * @return 值
     */
    public V get(String key){
        TrieNode<V> x = getNode(root,key);
        if(x==null || x.val==null){
            return null;
        }
        return x.val;
    }

    /**
     * 根据 key 的每个字符获取最后的节点，即值所在节点
     * @param node 当前节点
     * @param key 键
     * @return 节点
     */
    private TrieNode<V> getNode(TrieNode<V> node, String key) {
        TrieNode<V> p = node;
        for(int i=0;i<key.length();i++){
            if(p == null){
                return null;
            }
            char ch = key.charAt(i);
            p = p.children[ch];
        }
        return p;
    }

    /**
     * 在 Map 的所有键中搜索 query 的最短前缀
     * @param query 字符串；shortestPrefixOf("themxyz") -> "the"
     * @return 最短前缀
     */
    public String shortestPrefixOf(String query){
        TrieNode<V> p = root;
        for(int i=0;i<query.length();i++){
            if(p == null){
                return "";
            }
            if(p.val != null){
                return query.substring(0,i);
            }
            char ch = query.charAt(i);
            p = p.children[ch];
        }
        if(p!=null && p.val!=null){
            return query;
        }
        return "";
    }

    /**
     * 在 Map 的所有键中搜索 query 的最长前缀
     * @param query 字符串；longestPrefixOf("themxyz") -> "them"
     * @return
     */
    public String longestPrefixOf(String query){
        TrieNode<V> p = root;
        int maxLen = 0;
        for(int i=0;i<query.length();i++){
            if(p == null){
                break;
            }
            if(p.val != null){
                maxLen = i;
            }
            char ch = query.charAt(i);
            p = p.children[ch];
        }
        if(p!=null && p.val!=null){
            return query;
        }
        return query.substring(0,maxLen);
    }

    /**
     * 搜索所有前缀为 prefix 的键；keysWithPrefix("th") -> ["that", "the", "them"]
     * @param prefix 前缀
     * @return 键
     */
    public List<String> keysWithPrefix(String prefix){
        List<String> ans = new LinkedList<>();
        TrieNode<V> x = getNode(root,prefix);
        if(x == null){
            return ans;
        }
        traverse(x,new StringBuilder(prefix),ans);
        return ans;
    }

    // 通过回溯遍历多叉树
    private void traverse(TrieNode<V> node,StringBuilder path,List<String> ans){
        if(node == null){
            return;
        }
        if(node.val != null){
            ans.add(path.toString());
        }
        for(int i=0;i<R;i++){
            path.append((char) i);
            traverse(node.children[i],path,ans);
            path.deleteCharAt(path.length()-1);
        }
    }

    /**
     * 判断是和否存在前缀为 prefix 的键
     * @param prefix 前缀
     * @return true or false
     */
    public boolean hasKeyWithPrefix(String prefix){
        return getNode(root,prefix) != null;
    }

    /**
     * 通配符 . 匹配任意字符，搜索所有匹配的键；keysWithPattern("t.a.") -> ["team", "that"]
     * @param pattern 通配符表达式
     * @return 键集合
     */
    public List<String> keysWithPattern(String pattern){
        List<String> ans = new LinkedList<>();
        traverse(root,new StringBuilder(),pattern,0,ans);
        return ans;
    }

    private void traverse(TrieNode<V> node,StringBuilder path,String pattern,int i,List<String> ans){
        if(node == null){
            return;
        }
        if(i == pattern.length()){
            if(node.val != null){
                ans.add(path.toString());
            }
            return;
        }
        char ch = pattern.charAt(i);
        if(ch == '.'){
            for(int j=0;j<R;j++){
                path.append((char) j);
                traverse(node.children[j],path,pattern,i+1,ans);
                path.deleteCharAt(path.length()-1);
            }
        }else{
            path.append(ch);
            traverse(node.children[ch],path,pattern,i+1,ans);
            path.deleteCharAt(path.length()-1);
        }
    }

    /**
     * 通配符 . 匹配任意字符，判断是否存在匹配的键
     * @param pattern 通配符表达式
     * @return true or false
     */
    public boolean hasKeyWithPattern(String pattern){
        return hasKeyWithPattern(root,pattern,0);
    }

    private boolean hasKeyWithPattern(TrieNode<V> node,String pattern,int i){
        if(node == null){
            return false;
        }
        if(i == pattern.length()){
            return node.val != null;
        }
        char ch = pattern.charAt(i);
        if(ch != '.'){
            return hasKeyWithPattern(node.children[ch],pattern,i+1);
        }
        for(int j=0;j<R;j++){
            if(hasKeyWithPattern(node.children[j],pattern,i+1)){
                return true;
            }
        }
        return false;
    }

    /**
     * 返回 Map 中键值对的数量
     * @return 键值对的数量
     */
    public int size(){
        return size;
    }


}
