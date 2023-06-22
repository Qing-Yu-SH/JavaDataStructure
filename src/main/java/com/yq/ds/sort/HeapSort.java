package com.yq.ds.sort;

/**
 * @program: JavaDataStructure
 * @description: 选择排序 - 堆排序
 * @author: Yuqing
 * @create: 2023-06-22 13:29
 **/
public class HeapSort {

    public static final int CODE_MIN_HEAP = 0;
    public static final int CODE_MAX_HEAP = 1;

    public static void heapSort(int[] nums,int code){
        if(nums==null || nums.length<=1) return;
        if(code == CODE_MAX_HEAP){
            heapSort_Max(nums);
        }else{
            heapSort_Min(nums);
        }
    }


    private static void heapSort_Max(int[] nums){
        // 建立大根堆，并不断把根节点与堆的最后一个结点交换，再缩小堆并调整
        createMaxHeap(nums);
        for(int i=nums.length;i>=1;i--){
            int x = nums[0];
            nums[0] = nums[i-1];
            nums[i-1] = x;
            heapAdjust_Max(nums,1,i-1);
        }
    }

    private static void createMaxHeap(int[] nums){
        // n 是最后一个叶子结点的索引
        int n = nums.length;
        // 从最后一个非终端结点开始调整
        for(int i=n/2;i>0;i--){
            heapAdjust_Max(nums,i,n);
        }
    }

    private static void heapAdjust_Max(int[] nums,int s,int m){
        // 假设 tree[s+1..m] 已经是堆，将 tree[s..m] 调整为以 tree[s] 为根的大根堆
        // nums 是从 0 开始的，而 s 和 m 是堆中节点的编号，从 1 开始，所以需要减 1
        int rc = nums[s-1];
        // 每次都挑选左右孩子中比它大的最大的那个，然后交换位置
        // 移动到该孩子结点的位置，接着往下比较，直到无法找到比之大的，或者无孩子结点可比较
        for(int j=s*2;j<=m;j*=2){
            // 挑选大的孩子比较
            if(j<m && nums[j-1]<nums[j]) j++;
            // 没有比之大的孩子，则说明已是大根堆
            if(rc >= nums[j-1]) break;
            nums[s-1] = nums[j-1];
            s = j;
        }
        nums[s-1] = rc;
    }


    private static void heapSort_Min(int[] nums){
        // 建立小根堆，并不断把根节点与堆的最后一个结点交换，再缩小堆并调整
        createMinHeap(nums);
        for(int i=nums.length;i>=1;i--){
            int x = nums[0];
            nums[0] = nums[i-1];
            nums[i-1] = x;
            heapAdjust_Min(nums,1,i-1);
        }
    }

    private static void createMinHeap(int[] nums){
        // n 是最后一个叶子结点的索引
        int n = nums.length;
        // 从最后一个非终端结点开始调整
        for(int i=n/2;i>0;i--){
            heapAdjust_Min(nums,i,n);
        }
    }

    private static void heapAdjust_Min(int[] nums,int s,int m){
        // 假设 tree[s+1..m] 已经是堆，将 tree[s..m] 调整为以 tree[s] 为根的大根堆
        // nums 是从 0 开始的，而 s 和 m 是堆中节点的编号，从 1 开始，所以需要减 1
        int rc = nums[s-1];
        // 每次都挑选左右孩子中比它小的最小的那个，然后交换位置
        // 移动到该孩子结点的位置，接着往下比较，直到无法找到比之小的，或者无孩子结点可比较
        for(int j=s*2;j<=m;j*=2){
            // 挑选小的孩子比较
            if(j<m && nums[j-1]>nums[j]) j++;
            // 没有比之小的孩子，则说明已是小根堆
            if(rc <= nums[j-1]) break;
            nums[s-1] = nums[j-1];
            s = j;
        }
        nums[s-1] = rc;
    }

}
