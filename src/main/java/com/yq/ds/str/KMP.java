package com.yq.ds.str;

/**
 * @program: JavaDataStructure
 * @description: KMP 算法
 * @author: Yuqing
 * @create: 2023-05-29 16:56
 **/
public class KMP {

    int index_KMP(String S,String T){
        int lenS = S.length();
        int lenT = T.length();
        if(lenS<lenT || lenT==0) return -1;
        int[] next = getNext(T);
        int j = -1;
        for(int i=0;i<lenS;i++){
            while(j>=0 && S.charAt(i)!=T.charAt(j+1)){
                j = next[j];
            }
            if(S.charAt(i) == T.charAt(j+1)){
                j++;
            }
            if(j == lenT-1){
                return i - lenT + 1;
            }
        }
        return -1;
    }

    int[] getNext(String s){
        int n = s.length();
        int[] next = new int[n];
        int j = -1;
        next[0] = j;
        for(int i=1;i<n;i++){
            while(j>=0 && s.charAt(i)!=s.charAt(j+1)){
                j = next[j];
            }
            if(s.charAt(i) == s.charAt(j+1)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }

}
