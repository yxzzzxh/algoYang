import java.util.*;


public class Main {
	static int N; 
	static int L;
	static int[][] map;
	static int[][] rotateMap;
	
	static int isWalkable(int h1, int h2){
	
		if(h2 - h1 > 1 || h2 - h1 < -1){
			return -2;
			}else{
			return h2 - h1;
			}
	}
	
	static Stack<Integer> builted = new Stack();
	
	static boolean eachRow(int[] aRow){
			
		
		for(int idx = 0; idx < aRow.length-1; idx++){
			
			int walkable = isWalkable(aRow[idx],aRow[idx+1]);
		    if(walkable==-2){
		    	return false;
		    }else if(walkable == 0){
		    	continue;
		    }else if(walkable == 1){ 
		    						//3 2 2 1 2 3
		    						//      . .
		    						if(idx-L+1 < 0){ //idx  = 3 (val :  1) >
		    										 //idx  = 4 (val :  2)
		    						return false;
		    						}else{
		    										for(int i = idx-L+1; i < idx; i++){
		    												
		    												if(aRow[i]!=aRow[idx] || builted.contains(i) ){
		    													builted.clear();
		    													return false;
		    												}else{
		    													builted.push(i);
		    												}
		    										}
		    						
		    										if(builted.contains(idx)){
		    											builted.clear();
		    											return false;
		    										}else{				
		    											builted.push(idx);
		    										}
		    						}
		    		
		    		
		    }else if(walkable == -1){	//idx / idx+1... idx+L
		    		
		    	if(idx+L > aRow.length-1 ){
		    		return false;
		    	}else{
		    									for(int i = idx+L; i > idx+1 ; i--){
		    							
		    										if(aRow[i]!=aRow[idx] || builted.contains(i)){
		    											builted.clear();
		    											return false;
		    										}else{
		    											builted.push(i);
		    										}
		    									}
		    									
		    			if(builted.contains(idx+1)){	
		    				builted.clear();
		    				return false;
		    			}else{
		    				builted.push(idx+1);
		    				}
		    			}
    		
		    	
		    	}
		    
		    
				}
		
				return true;
			} 
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
				
//	1.	int [] testRow = {2,2,2,2,2,2};
//	2.	int [] testRow = {2,2,3,3,3,3};
//	3. 	int [] testRow = {2,2,2,2,2,2,2,2,2,2};
// 	4. 	int [] testRow = {4,4,2,2,2,2,2,2,2};
	 	
//int [] testRow = {3, 2, 2, 2, 3, 3}; 
//겹치는 경우
		
		int ans = 0;
		map = new int[N][N];
		rotateMap = new int[N][N];
		
		for(int i = 0; i < N ; i++){
			for(int j = 0; j < N ; j ++){
				 map[i][j] = sc.nextInt();
				 rotateMap[N-j-1][i] = map[i][j];
			}
		}
		
		
		for(int i = 0; i < N; i++){
				if(eachRow(map[i]))
				{
					ans++;
				}
				
				if(eachRow(rotateMap[N-i-1]))
				{
					ans++;
				}
			
		}
		
		System.out.print(ans);
		
		sc.close();
	}

}
