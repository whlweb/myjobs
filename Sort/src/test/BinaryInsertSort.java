package test;

public class BinaryInsertSort {
    public static int count = 0;  
    
    public static void main(String[] args) {  
  
        int[] data = new int[] { 5, 3, 6, 2, 1, 9, 4, 8, 7 };  
        print(data);  
        binaryInsertSort(data);  
        print(data);  
  
    }  
  
    public static void binaryInsertSort(int[] data) {  
        for (int i = 1; i < data.length; i++) {  
            if (data[i] < data[i - 1]) {  
                // ����i����Ԫ��ֵ  
                int tmp = data[i];  
                // ��¼������Χ����߽�  
                int low = 0;  
                // ��¼������Χ���ұ߽�  
                int high = i - 1;  
                while (low <= high) {  
                    // ��¼�м�λ��  
                    int mid = (low + high) / 2;  
                    // �Ƚ��м�λ�����ݺ�i�����ݴ�С������С������Χ  
                    if (data[mid] < tmp) {  
                        low = mid + 1;  
                    } else {  
                        high = mid - 1;  
                    }  
                }  
                //��low~i��������������ƶ�1λ  
                for (int j = i; j > low; j--) {  
                    data[j] = data[j - 1];  
                }  
                data[low] = tmp;  
                print(data);  
            }  
        }  
    }  
  
    public static void print(int[] data) {  
        for (int i = 0; i < data.length; i++) {  
            System.out.print(data[i] + "\t");  
        }  
        System.out.println();  
    } 
    
}
