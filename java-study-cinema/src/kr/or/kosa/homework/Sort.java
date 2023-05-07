package kr.or.kosa.homework;

public class Sort {

	public static void main(String[] args) {
//		int[] arr = {5,4,3,2,1};
		int[] arr = {3,4,6,2,1};
		
		//sortByBubbleSort(arr);//버블정렬
		sortBySelectionSort(arr);//선택정렬
		sortByInsertionSort(arr);//삽입정렬
		
	}
	
	//swqp 함수
	public static void swap(int[] arr, int idx1, int idx2) {
		int tmp = arr[idx1];
	    arr[idx1] = arr[idx2];
	    arr[idx2] = tmp;
	}
	
	//버블정렬
	public static void sortByBubbleSort(int[] arr) {
	    for (int i = 0; i < arr.length - 1; i++) {
	        for (int j = 0; j < arr.length - 1 - i ; j++) {
	            if (arr[j] > arr[j + 1]) {
	                swap(arr, j, j + 1);
	            }
	        }
	    }
	}
	
	//선택정렬
//	5[0] 4[1] 3[2] 2[3] 1[4]
//	
//	minIdx 
	
	public static void sortBySelectionSort(int[] arr) {
	    for (int i = 0; i < arr.length - 1; i++) {
	        int minIdx = i;
	        for (int j = i + 1; j < arr.length; j++) {
	            if (arr[j] < arr[minIdx]) {
	                minIdx = j;
	            }
	        }
	        swap(arr, i, minIdx);
	    }
	}
	
	//0 1
	
	//삽입정렬
	public static void sortByInsertionSort(int[] arr) {
	    for (int i = 1; i < arr.length; i++) {
	        int tmp = arr[i];
	        int j = i - 1;
	        while (j >= 0 && tmp < arr[j]) {
	            arr[j + 1] = arr[j];
	            j--;
	        }
	        arr[j + 1] = tmp;
	    }
	}

}