import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        HashMap<String, gem> map = new HashMap<>();
        //qu에 넣고 peek과 동일할 경우 꺼내면서 범위를 줄이는 방식으로?
        //가지고 있는 다양함의 갯수 cnt로 갖고 있기
        //근데,, peek이 바꼈을때 또 꺼내야될 수도 있잖음?
        //아 어쩌란거임?
        // 갯수를 cnt로 체크해둘까?
        //있는지 없는지는 어떻게 체크하지?
        //contains는 너무 오래 걸리지 않음?
        
        
        
        Queue<String> qu = new ArrayDeque<>();
        ArrayList<range> arr = new ArrayList<>();
        
        int start = 0;
        int end = 0;
        
        for(int i=0;i<gems.length;i++){
           //System.out.println(start);
            
            if(map.containsKey(gems[i])){
                map.get(gems[i]).cnt++;

            }else{
                map.put(gems[i], new gem(gems[i], 1));
                arr.add(new range(start, i, map.size()));
                
            }
            
            int start_save = start;
            
            while(!qu.isEmpty() && (qu.peek().equals(gems[i])) ){
                start = start+1;
                map.get(qu.poll()).cnt--;
                
                while(map.containsKey(qu.peek()) && map.get(qu.peek()).cnt > 1){
                start = start+1;
                map.get(qu.poll()).cnt--;
                }
            
        
            }
            qu.offer(gems[i]);
            
            if(start_save != start){
               
                //System.out.println("start들 "+start_save+" "+start);
                arr.add(new range(start, i, map.size()));
            }
            
            //System.out.println(qu.toString());
            
        }
        Collections.sort(arr);
        int[] answer = new int[2];
        answer[0] = arr.get(arr.size()-1).start+1;
        answer[1] = arr.get(arr.size()-1).end+1;
        
        //System.out.println(arr.toString());
        //System.out.println(qu.toString());
        return answer;
    }
    
    public class range implements Comparable<range>{
        int start;
        int end;
        int cnt;
        
        range(int start, int end, int cnt){
            this.start = start;
            this.end = end;
            this.cnt = cnt;
        }
        
        public int compareTo(range r){
            if(this.cnt > r.cnt)return 1;
            if(this.cnt < r.cnt)return -1;
            if(this.start - this.end > r.start - r.end)return 1;
            if(this.start - this.end < r.start - r.end)return -1;
            if(this.start > r.start)return -1;
            if(this.start < r.start)return 1;
            return 0;
        }
        
        public String toString(){
            return this.start+" "+this.end+" = "+this.cnt;
        }
    }
    
    public class gem{
        String name;
        int cnt;
        
        gem(String name, int cnt){
            this.name = name;
            this.cnt = cnt;
        }
    }
}