package com.yq.ds.str;

/**
 * @program: JavaDataStructure
 * @description: BF算法，即暴力(Brute Force)算法，是普通的模式匹配算法
 * @author: Yuqing
 * @create: 2023-05-29 16:55
 **/
public class BruteForce {

    /**
     * 如果 S 中存在匹配串 T，则返回在 S 中的索引，否则返回 -1
     * @param S 目标串
     * @param T 匹配串
     * @return
     */
    int index_BF(String S,String T){
        int lenS = S.length();
        int lenT = T.length();
        if(lenS < lenT) return -1;
        int i=0,j=0;
        while(i<lenS && j<lenT){
            if(S.charAt(i) == T.charAt(j)){
                // 相等时，指针往前进
                i++;
                j++;
            }else{
                // 不相等时，指针 i 需要回退
                i = i-j+1;
                j = 0;
            }
        }
        if(j >= lenT){
            return i - lenT;
        }
        return -1;
    }

}
