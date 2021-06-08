package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class 디스크컨트롤러_권영찬 {

    // 1. 모든 작업이 끝날 때 까지 loop 를 돈다.
    // 2. 각각의 작업을 진행 하는 동안에 들어오는 작업들을 계산해서 우선순위 큐에 넣는다.
    // 3. 작업이 진행되는 동안 우선순위 큐의 순서는 변경 될 수 있다.
    //    3-1. 중간에 작업이 추가 될 때마다 작업의 요청부터 종료까지 걸린 시간의 평균이 가장 작은 경우를 계산해 큐에 넣는다
    // 4. 하나의 작업이 종료되면 해당 작업에 들어있는 요청 시간과 종료 시간을 계산해 평균 계산 변수에 더한다.
    // 5. 모든 작업이 종료되면 더한 평균 시간을 작업의 갯수로 나누어 리턴한다.

    public static void main(String[] args) {
        int[][] arr = new int[][]{{0,3}, {1,9}, {2,6}};
        final int solution = new 디스크컨트롤러_권영찬().solution(arr);
        System.out.println(solution);
    }

    public int solution(int[][] jobs) {

        quickSort(jobs);

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        int completedJobs = 0;
        int msTime = 0;
        int startJobIdx = 0;
        int sumElapsedTime = 0;

        while(completedJobs < jobs.length) {
            while(startJobIdx < jobs.length && jobs[startJobIdx][0] <= msTime) {
                queue.add(jobs[startJobIdx++]);
            }

            if(!queue.isEmpty()) {
                final int[] poll = queue.poll();
                msTime += poll[1];
                sumElapsedTime += msTime - poll[0];
                completedJobs++;
            } else {
                msTime = jobs[startJobIdx][0];
            }
        }

        return sumElapsedTime / completedJobs;
    }


    public static void quickSort(int[][] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[][] arr, int low, int high) {
        if(low >= high) return;

        int mid = partition(arr, low, high);
        sort(arr, low, mid - 1);
        sort(arr, mid, high);
    }

    public static int partition(int[][] arr, int low, int high) {
        int pivot = arr[(low + high) / 2][0];
        while(low <= high) {
            while(arr[low][0] < pivot) low++;
            while(arr[high][0] > pivot) high--;
            if(low <= high) {
                swap(arr, low, high);
                low++;
                high--;
            }
        }
        return low;
    }

    public static void quickSort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public static void swap(int[][] arr, int i, int j) {
        int tmp1 = arr[i][0];
        int tmp2 = arr[i][1];
        arr[i][0] = arr[j][0];
        arr[i][1] = arr[j][1];
        arr[j][0] = tmp1;
        arr[j][1] = tmp2;
    }

    public static void sort(int[] arr, int low, int high) {
        if(low >= high) return;

        int mid = partition(arr, low, high);
        sort(arr, low, mid - 1);
        sort(arr, mid, high);
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[(low + high) / 2];
        while(low <= high) {
            while(arr[low] < pivot) low++;
            while(arr[high] > pivot) high--;
            if(low <= high) {
                swap(arr, low, high);
                low++;
                high--;
            }
        }
        return low;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
