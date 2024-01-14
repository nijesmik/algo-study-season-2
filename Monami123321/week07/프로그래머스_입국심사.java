class 프로그래머스_입국심사 {
    public long solution(int n, int[] times) {
        long max = 0;
        for(int i = 0; i< times.length; i++) {
            max = Math.max(max,times[i]);
        }
        long end = ((n/times.length)+1) * max;
        long start = 1;

        while(start<=end) {
            long mid = (end+start)/2;
            long sum = 0;
            for(int i = 0; i<times.length;i++) {
                sum += mid/times[i];
            }
            if(sum>=n) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return start;
    }
}