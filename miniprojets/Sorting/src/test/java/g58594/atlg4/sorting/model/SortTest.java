package g58594.atlg4.sorting.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @Test
    void bubbleSort() {
        int[] array = {3,5,2,1,4,9,7,8,6};
        int[] sortedArray = {1,2,3,4,5,6,7,8,9};

        Sort.bubbleSort(array);
        assertArrayEquals(sortedArray,array);
    }

    @Test
    void mergeSort() {
        int[] array = {3,5,2,1,4,9,7,8,6};
        int[] sortedArray = {1,2,3,4,5,6,7,8,9};

        Sort.mergeSort(array);
        assertArrayEquals(sortedArray,array);
    }
}