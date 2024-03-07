class 추석_트래픽 {
    public int solution(String[] lines) {
        int answer = 1;
        for(int l = 0; l < lines.length; l++) {
            String line = lines[l];
            String[] response = line.split(" ");
            String responseTime = response[1];
            String process = response[2];
            int time = timeToMs(responseTime); // 종료 시점
            
            int count = 1;
            for(int k = l+1; k < lines.length; k++) {
                String line2 = lines[k];
                String[] response2 = line2.split(" ");
                String responseTime2 = response2[1];
                String process2 = response2[2];
                int time2 = timeToMs(responseTime2);
                int idx = 3;
                int processTime = 0;
                for(int i = 0; i < process2.length(); i++) {
                    if(process2.charAt(i)=='.') continue;
                    if(process2.charAt(i)=='s') break;
                    processTime += Math.pow(10,idx--) * (process2.charAt(i)-'0');
                }
                // System.out.println(time+999);
                // System.out.println(time2);
                // System.out.println(time2-processTime+1);
                if(time+999 >= time2-processTime+1) {
                    count++;
                    answer = Math.max(answer, count);
                }
                // else break; 시작시간이랑 비교해야되니까 break뺴야돼
            }
            
        }
        
        return answer;
    }
    
    public int timeToMs(String time) {
        String[] t = time.split(":");
        int h = Integer.parseInt(t[0])*1000*60*60;
        int m = Integer.parseInt(t[1])*1000*60;
        int s = Integer.parseInt(t[2].split("\\.")[0])*1000;
        int ms = Integer.parseInt(t[2].split("\\.")[1]);
        return h+m+s+ms;
    }
}