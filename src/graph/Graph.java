package graph;
public class Graph {
	   private Vertex[] vertexarr;
	   private int[][] adjmax;
	   private Stack thestack,thestack2;
	   private int nver;
	   queue q;
	   Graph(int size){
	          vertexarr = new Vertex[size];
	          adjmax = new int[size][size];
	          thestack = new Stack(size);
	          nver=0;
	          for(int i=0;i<size; i++){
	                 for(int j=0;j<size;j++){
	                        adjmax[i][j]=0;
	                 }
	          }
	      
	          q =new queue(size);
	         
	   }
	  
	   public void addVertex(char ch){
	          vertexarr[nver++]=new Vertex(ch);
	   }
	  
	   public void addedge(int start,int end){
	          adjmax[start][end]=1;
	          adjmax[end][start]=1;
	   }
	      
	   public void dfs(){
	          vertexarr[0].wasvisited=true;
	          System.out.print(vertexarr[0].ch +"  ");
	          thestack.push(0);
	        
	         while(!thestack.isEmpty()){
	               
	                int vertex=thestack.peek();
	              int nextvertex = getadjUnvistedVertex(vertex);
	             // System.out.println(vertexarr[vertex].ch);
	              if(nextvertex == -1){
	                 thestack.pop();
	              }
	              else {
	                 System.out.print(vertexarr[nextvertex].ch +"  ");
	                 vertexarr[nextvertex].wasvisited=true;
	                 thestack.push(nextvertex);
	              }
	         }
	        
	         for(int i=0;i<nver;i++){
	                vertexarr[i].wasvisited=false;
	         }
	        
	   }  
	       public void bfs(){
	         vertexarr[0].wasvisited=true;
	         System.out.print(vertexarr[0].ch+"  ");
	          q.add(0);
	          while(!q.isEmpty()){
	                 int data=(int) q.remove();
	                
	                 int i;
	                 while((i=getadjUnvistedVertex(data))!= -1 ){
	                       
	                        vertexarr[i].wasvisited=true;
	                        System.out.print(vertexarr[i].ch+"  ");
	                        q.add(i);
	                       
	                 }
	                // System.out.println("queuue array is " +q);
	          }
	         
	          for(int i=0;i<nver;i++){
	                 vertexarr[i].wasvisited=false;
	          }
	          
	        
	       }
	       
	      
	  
	    public void mst(){    //minimum spanning tree same as dfs
	    	vertexarr[0].wasvisited=true;
	    	thestack.push(0);
	    	while(!thestack.isEmpty()){
	    		int vertex=thestack.peek();
	    		int nextvertex=getadjUnvistedVertex(vertex);
	    		if(nextvertex==-1)
	    			thestack.pop();
	    		else{
	    			vertexarr[nextvertex].wasvisited=true;
	    		System.out.print(vertexarr[vertex].ch+" ");
	    		System.out.print(vertexarr[nextvertex].ch+"      ");
	    		thestack.push(nextvertex);
	    		}
	    	
	    	}
	    	
	    }
	       
	   public int getadjUnvistedVertex(int vertex){
	          for(int i=0;i<nver;i++){
	              if(vertexarr[i].wasvisited==false && adjmax[vertex][i]==1)
	                 return i;
	          }
	                 return -1;
	   }
	  
	       public static void main(String[] args) {
	             
	              Graph g=new Graph(10);
	              g.addVertex('a');
	              g.addVertex('b');
	              g.addVertex('c');
	              g.addVertex('e');
	              g.addVertex('d');
	              g.addedge(0,1);
	              g.addedge(1,2);
	              g.addedge(0,3);
	              g.addedge(3,4);
	              g.dfs();
	              System.out.println(" ");
	              g.bfs();
	              System.out.println(" ");
	              g.mst();
	       }
	 
	}
	 
	class Stack{
	       int [] arr;
	       int top;
	       int size;
	       Stack(int size){
	              this.size=size;
	              arr=new int[size];
	              top=-1;
	       }
	      
	       public boolean isEmpty(){
	              return top == -1;
	       }
	      
	       public void push(int value){
	              if(top==size)
	                     System.out.print("stack Overflows");
	              else{
	                     arr[++top]=value;
	              }
	       }
	       public int pop(){
	              if(isEmpty()){
	                  System.out.print("stack UnderFlows");
	                  return 0;
	              }
	              else{
	              return arr[top--];
	              }
	       }
	      
	       public int peek(){
	              return arr[top];
	       }
	}
	 
	 
	class queue{
	       int [] arr;
	       int maxsize;
	       int size=0;
	       int front=0;
	       int rear=-1;
	       queue(int size){
	              this.maxsize=size;
	              arr=new int[size];
	       }
	      
	       public void add(int value){
	             if(size==maxsize)
	            	 System.out.println("stack overflows");
	             else{
	            	 rear = (rear+1) %  maxsize;
	            	 arr[rear] = value;
	            	 size++;
	         }
	       }
	      
	       public int remove(){
	              if(size==0){
	            	  System.out.println("stack underflows");
	                  return 0;
	              }
		             else{
		            	 size--;
		            	 int temp=arr[front];
	                     front=(front+1) % maxsize;
	                     return temp;
		             }
	       }
	             
	      
	       public boolean isEmpty(){
	              if(size==0)
	                 return true;
	              else
	                 return false;
	       }
	       
	       public String toString(){
	    	   String output="[";
	    	   for(int i=0; i<size;i++){
	    		   output+=arr[(front+i)%maxsize];
	    	       if(i<size-1)
	    	    	   output+=",";
	    	   }
	    	   output+="]";
	    	   return output;
	    	   
	       }
	      
	}
	class Vertex{
	       char ch;
	       boolean wasvisited;
	       Vertex(char ch){
	              this.ch=ch;
	              wasvisited=false;
	       }
	}