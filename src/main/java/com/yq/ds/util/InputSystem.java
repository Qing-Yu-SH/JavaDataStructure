package com.yq.ds.util;

/**
 * @program: JavaDataStructure
 * @description: 输入
 * @author: Yuqing
 * @create: 2023-05-29 22:32
 **/
public class InputSystem {

    private int t0,t1,t2,t3,t4;
    private int[] b0,b1,b2,b3,b4;

    public InputSystem(){
        t0 = 0;
        t1 = 0;
        t2 = 0;
        t3 = 0;
        t4 = 0;
        b0 = new int[]{2,15,26};
        b1 = new int[]{20,52,80};
        b2 = new int[]{6,82,96};
        b3 = new int[]{20,20,52,66,92};
        b4 = new int[]{6,8};
    }

    public int input(int index){
        int key = Integer.MAX_VALUE;
        switch (index){
            case 0:
                if(t0 < b0.length){
                    key = b0[t0++];
                }
                break;
            case 1:
                if(t1 < b1.length){
                    key = b1[t1++];
                }
                break;
            case 2:
                if(t2 < b2.length){
                    key = b2[t2++];
                }
                break;
            case 3:
                if(t3 < b3.length){
                    key = b3[t3++];
                }
                break;
            case 4:
                if(t4 < b4.length){
                    key = b4[t4++];
                }
                break;
        }
        return key;
    }

}
