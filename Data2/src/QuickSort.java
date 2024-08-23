public class QuickSort {
    int partition(double[] arr, int low, int high , int[] arr1, String[] array2, int[] arr3, int[] arr4){
        double pivot=arr[high];
        int i=(low-1);
        for (int j=low; j<high; j++){
            if (arr[j]>pivot){
                i++;
                swap(arr, i, j);
                swap1(arr1, i, j);
                swap2(array2, i, j);
                swap1(arr3, i, j);
                swap1(arr4, i, j);
            }
        }

        swap(arr,i+1, high);
        swap1(arr1, i+1, high);
        swap2(array2, i+1, high);
        swap1(arr3, i+1, high);
        swap1(arr4, i+1, high);
        return i+1;
    }


    void sort(double[] arr, int low, int high , int[] arr1, String[] array2, int[] arr3, int[] arr4){
        if (low<high+1) {
            int pi = partition(arr, low, high, arr1,  array2, arr3,  arr4 );
            sort(arr, low, pi - 1,  arr1,  array2, arr3,  arr4);
            sort(arr, pi + 1, high, arr1, array2, arr3, arr4 );
        }
    }

    void swap(double [] arr, int i, int j){
        double temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    void swap1(int[] arr, int i, int j){
        int temp=arr[j];
        arr[j]=arr[i];
        arr[i]=temp;
    }

    void swap2(String[] arr, int i, int j){
        String temp=arr[j];
        arr[j]=arr[i];
        arr[i]=temp;
    }

    double calculateDensity(int pop /*population*/, int covc /*covid cases*/ ){
        double temp=(double)(50000*covc)/pop;
        double d = (double)Math.round(temp * 100.0) / 100.0;
        return d ;
    }

    int StrCompare(String s1, String s2){
        int l1=s1.length(); //first string length
        int l2=s2.length(); //second string length
        int result =0;
        int length=0;
        if (l1>l2)
            length=l1-l2;
        else
            length=l2-l1;
        for (int i = 0; i < length; i++) {
            int a = (int) s1.charAt(i);
            int b = (int) s2.charAt(i);
            if (a!=b){
                result=a-b;
                break;
            }
        }
        return result;
    }
}
