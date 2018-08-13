package graph;

public class GraphImpLinkedAdjList {
	vertex [] vertexArray;
	LinkedList[] listArray;
	Stack thestack;
	int nver;
	GraphImpLinkedAdjList(int size){
		vertexArray=new vertex[size];
		listArray=new LinkedList[size];
		nver=0;
		thestack=new Stack(size);
		for(int i=0;i<size;i++)
			listArray[i]=new LinkedList();
	}
	
	public void addVertex(char ch){
		vertexArray[nver]=new vertex(ch);
		nver++;
	}
	
	public void addEdge(int start,  int end){
		LinkedList list=listArray[start];
		list.insert(end);
		//System.out.print("List"+start+":    ");
	    //list.display();
	}
	
	public void dfs(){
		System.out.print(vertexArray[0].ch);
		vertexArray[0].wasvisited=true;
		thestack.push(0);
		while(!thestack.isEmpty()){
			int vertex=thestack.peek();
			LinkedList list2=listArray[vertex];
		
			int Nextvertex=list2.find();
			//System.out.println(Nextvertex);
			if(Nextvertex==-1)
				thestack.pop();
			else{
				System.out.print(vertexArray[Nextvertex].ch);
				vertexArray[Nextvertex].wasvisited=true;
				thestack.push(Nextvertex);
				
			}
				
		}
	}
	
	public static void main(String[] args) {
		GraphImpLinkedAdjList g =new GraphImpLinkedAdjList(5);
         g.addVertex('a');
         g.addVertex('b');
         g.addVertex('c');
         g.addVertex('e');
         g.addVertex('d');
         g.addEdge(0,1);
         g.addEdge(1,2);
         g.addEdge(0,3);
         g.addEdge(3,4);
         g.dfs();

	}

	class vertex{
		char ch;
		boolean wasvisited;
		vertex(char ch){
			this.ch=ch;
			wasvisited=false;
		}
	}
	
	class Link{
		int data;
		Link next;
		Link(int data){
			this.data=data;
		}
	}
	
	class LinkedList{
		Link head;
		LinkedList(){
			head=null;
		}
		public void insert(int end){
			if(end==0){
				if(head==null)
					head=new Link(1);
				else{
					Link temp=head.next;
					head= new Link(1);
					head.next=temp;
				}				
			}
			else{
				if(head==null)
					head=new Link(0);
				Link current=head;
				for(int i=0; i<end;i++){
					
					if(current.next == null)
						current.next=new Link(0);
			
						current=current.next;
					
				}
				
				//Link temp= current.next;
				current.data=1;
				//current.next=temp;
			}
			
			display();
			
			
		}
		
		public void display(){
			Link current=head;
			while(current!=null){
				System.out.print(current.data+"--->");
				current=current.next;
			}
			System.out.println("");
		}
		
	
		public int find(){
			
			
			Link current=head;
			for(int i=0; i<nver; i++){
				 if(current==null)
					 break;
				if(current.data==1 && vertexArray[i].wasvisited==false)
					return i;
				  current=current.next;
				}
			return -1;
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
	
}
