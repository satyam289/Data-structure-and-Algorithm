package graph;

public class WeightedDirectedShortedPathGraph {
	  private final int infinity=100000;
      private vertex [] vertexArray;
      private int [][] adjmax;
      public static int nver;
	  private dis[] spath;
	  private int currentvertex;
	  private int startingvertex;
	  private int start2current;


      WeightedDirectedShortedPathGraph(int max){
    	  vertexArray=new vertex[max];
    	  adjmax=new int[max][max];
    	  nver=0;
    	  spath=new dis[max];
    	  for(int i=0;i<max;i++){
    		  for(int j=0;j<max;j++){
    			  adjmax[i][j]=infinity;
    		  }
    	  }

      }


      public static void main(String[] args) {
  		WeightedDirectedShortedPathGraph w =new WeightedDirectedShortedPathGraph(10);
  		w.addVertex('a');
  		w.addVertex('b');
  		w.addVertex('c');
  		w.addVertex('d');
  		w.addVertex('e');
  		w.addEdge(0, 1, 50);
  		w.addEdge(0, 3, 80);
  		w.addEdge(1, 2, 60);
  		w.addEdge(1, 3, 90);
  		w.addEdge(2, 4, 40);
  		w.addEdge(3, 2, 20);
  		w.addEdge(3, 4, 70);
  		w.addEdge(4, 1, 50);
  		System.out.println("shortest paths from B ");     //zero index vertex
  		w.path(2);
  		System.out.println();



  	}




      public void addVertex(char ch){
    	  vertexArray[nver]=new vertex(ch);
    	  nver++;
      }
      public void addEdge(int start,int end,int weight){
    	  adjmax[start][end]=weight;
      }


      public void path(int passvertex){
    	     int ntree=1;
    	     startingvertex=passvertex;                                                                    //choose ,at what vertex to look
          	 currentvertex=startingvertex;
          	 for(int i=0;i<nver;i++){
          		 spath[i]=new dis(adjmax[currentvertex][i],currentvertex);                        //filling the default value(direct path) to that selected vertex
          	 }
          	 vertexArray[currentvertex].wasvisited=true;

          	 while(ntree<nver){                                                                //till every vertex
          		 int minIndex =  getMin();
          		//System.out.println("**********"+minIndex);
          		 int distance=spath[minIndex].distannce;

          		 if(distance==infinity){                                        //there are some unreachable vertex  when all minimum node is visited, only infinity left in spath
          			 System.out.println("there are unreachable vertex");
          			 break;
          		 }
          		 else{
          		 vertexArray[minIndex].wasvisited=true;
          		 start2current=spath[minIndex].distannce;
          		 currentvertex=minIndex;                                    //next vertex for lookup which is minimum at spath
          		 adust_path() ;                                            //adjust according to currentvertex.
          		 ntree++;
          		 }
          	 }
          	 for(int i=0;i<nver;i++){
          		 vertexArray[i].wasvisited=false;
          	 }
          	 display();

      }


      public void adust_path(){                                //adjusting the spath according to the currentvertex(min_vertex), establish the link and replacing with lower value if exist!
    	      int col=0;
    	      while(col<nver){                                 //for that current vertex to all other vertex
    	    	  if(vertexArray[col].wasvisited){             //skip,if that node already visited
    	    		  col++;
    	    		  continue;
    	    	  }
    	    	  int current2fringe=adjmax[currentvertex][col];         //lookup for unvisited node(column wise)
    	    	  int start2fringe= start2current+current2fringe;         //adding old value (if infinity, then addition is always greater , so cant be replaced
    	    	  //System.out.println("total dis  "+start2fringe+ "  spath  "+spath[col].distannce);

    	    	  if(start2fringe<spath[col].distannce){                    //if older is greater, replace with new lesser value or new entry
    	    		  spath[col].distannce=start2fringe;
    	    		  spath[col].parentvertex=currentvertex;
    	    	  }
    	    	  col++;

    	      }
      }

      public int getMin(){
    	  int min=infinity;
    	  int index=0;
    	  for(int i=0;i<nver;i++){
    		  dis d=spath[i];
    		  if((!vertexArray[i].wasvisited) && d.distannce < min){     //minimum of unvisited vertex
    			  min=d.distannce;
    			  index=i;
    		  }

    	  }
    	  return index;

      }


      public void display(){
    	for(int i=0;i<nver;i++){
    		if(spath[i].distannce==infinity)
    			System.out.print("inf"+"    ");
    		else{
    			System.out.print(spath[i].distannce+" ");
    			System.out.print("("+vertexArray[spath[i].parentvertex].ch+")"+"    ");
    		}
    	}
      }






	class dis{
		int distannce;
		int parentvertex;
		dis(int distance, int parentvertex){
			this.distannce=distance;
			this.parentvertex=parentvertex;
		}
	}

	class vertex{
		char ch;
		boolean wasvisited;
		vertex(char ch){
			this.ch=ch;
		}
	}

}
