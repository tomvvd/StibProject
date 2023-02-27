package g58594.atlg4.sorting.model;

import java.util.Arrays;

public class Sort {
    
    public static SortRecord bubbleSort(int[] arr){
        long startTime = System.currentTimeMillis();
        int nbOp = 0;
        for (int i = arr.length; i>0; i--) {
            for (int j = 0; j < i-1; j++) {
                if(arr[j]>arr[j+1]){
                    int tmp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = tmp;
                    nbOp+=4;
                }
                nbOp+=1;
            }
        }
        long endTime = System.currentTimeMillis();
        return new SortRecord(nbOp,endTime-startTime);
    }

    public static SortRecord mergeSort(int[] arr) {
        long startTime = System.currentTimeMillis();
        if (arr == null || arr.length < 2) {
            return new SortRecord(0, System.currentTimeMillis() - startTime);
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        SortRecord leftResult = mergeSort(left);
        SortRecord rightResult = mergeSort(right);
        int nbOp = mergeRec(left, right, arr);
        long endTime = System.currentTimeMillis();
        return new SortRecord(leftResult.getNbOp() + rightResult.getNbOp() + nbOp,endTime-startTime);
    }

    private static int mergeRec(int[] left, int[] right, int[] arr) {
        int i = 0;
        int j = 0;
        int k = 0;
        int nbOp = 3;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
            nbOp += 2;
        }
        while (i < left.length) {
            arr[k++] = left[i++];
            nbOp++;
        }
        while (j < right.length) {
            arr[k++] = right[j++];
            nbOp++;
        }
        return nbOp;
    }





    public static void main(String[] args) {
        int[] array = {3,5,2,1,4,0,9,7,8,6};
        int[] array2 = {3,5,2,1,4,0,9,7,8,6};
        int[] sortedArray = {0,1,2,3,4,5,6,7,8,9};

        SortRecord sr1 = Sort.bubbleSort(array);
        SortRecord sr2 = Sort.mergeSort(array2);

        for (int val:array) {
            System.out.print(val+" ");
        }
        System.out.println();

        System.out.println(sr1.getNbOp());
        System.out.println(sr1.getDuration());


        for (int val:array2) {
            System.out.print(val+" ");
        }
        System.out.println();

        System.out.println(sr2.getNbOp());
        System.out.println(sr2.getDuration());
    }
}
