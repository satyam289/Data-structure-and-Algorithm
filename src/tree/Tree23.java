package tree;

class Node23{
	int size;
	int maxSize=3;
	Node23 parent;
	Node23 [] childArray=new Node23[maxSize];;
	Data [] dataArray =new Data[maxSize-1];
	
	public int insert(int value){
		size++;
		for(int i=maxSize-2;i>=0;i--){
			if(dataArray[i]==null)
				continue;
			else{
		        if(value > dataArray[i].item ){
		             dataArray[i+1]=new Data(value);	
		              return i+1;
		        }
		        else
		        	dataArray[i+1]=dataArray[i];
		        }
	       }
		dataArray[0]=new Data(value);
		return 0;	
    }
	
	public int find(int value){
		for(int i=0;i<size;i++){
			if(dataArray[i]==null)
				break;
			else if(dataArray[i].item == value){
			  return 1;
			}
	     }
		return -1;
	}
	
	public Node23 disconnectChild(int childNum){
		Node23 temp=childArray[childNum];
		childArray[childNum]=null;
		return temp;
	}
	
	public void connectChild(Node23 node, int childNum){
		childArray[childNum]=node;
		if(node!=null){
			childArray[childNum].parent=this;
		}
	}
	
	public boolean isLeaf(){
		return childArray[0]==null?true:false;
	}
	
	public boolean isFull(){
		return size==maxSize-1?true:false;
	}
	
	public int remove(){
		int temp= dataArray[size-1].item;
		dataArray[size-1]=null;
		size--;
		return temp;
				
	}
	public void display(){
		for(int i=0;i<size;i++){
			System.out.print(" / "+dataArray[i].item);
		}
	}
	
	
}

public class Tree23 {
    
	Node23 root=new Node23();
	public int find(int value){
		Node23 current=root;
		int childNum;
		while(true){
		   if((childNum=current.find(value))!=-1)
		     return childNum;
		   else if(current.isLeaf())
			   return -1;
		  else
			current=getNextChild(current,value);
		}
	}
	
	public Node23 getNextChild(Node23 node, int value){
		for(int i=0;i<node.size;i++){
		      if(value < node.dataArray[i].item){
		    	return node.childArray[i]; 
		      }
		}
		return node.childArray[node.size];
	}
	
	public void display(){
		recdisplay(root,0,0);
	}
	public void recdisplay(Node23 node, int level, int childNum){
		System.out.print("Level : "+level+"  ChildNumber : "+childNum+"             ");
		node.display();
		for(int i=0;i<node.size+1;i++){
			if(node.childArray[i]!=null){
				System.out.println("");
				recdisplay(node.childArray[i],level+1,i);
			}
			else
				return;
		}
		
	}
	
	public void insert(int value){
		Node23 current=root;
		while(true){
		  if(current.isLeaf())
			  	break;
		   else
			   current=getNextChild(current,value);
	   }
		if(current.isFull())
			split(current,value);
		else
		    current.insert(value);
	}
	
	public Node23 split(Node23 node,int value){
		Data newData=new Data(value);
		Node23 parent=node.parent;
		Data SortdataArray[] =new Data[3];
		 if(value<node.dataArray[0].item){
			 SortdataArray[0]=newData;
			 SortdataArray[1]=node.dataArray[0];
			 SortdataArray[2]=node.dataArray[1];
		 }
		 else if(value<node.dataArray[1].item){
			 SortdataArray[0]=node.dataArray[0];
			 SortdataArray[1]=newData;
			 SortdataArray[2]=node.dataArray[1];
		 }
		 else{
			 SortdataArray[0]=node.dataArray[0];
			 SortdataArray[1]=node.dataArray[1];
			 SortdataArray[2]=newData;
		 }
		 
		 node.remove();
		 node.remove();
		 node.insert(SortdataArray[0].item);
		 
		 Node23 NewRightNode =new Node23();
		 NewRightNode.insert(SortdataArray[2].item);
		 
		 if(node==root){
			 Node23 newNode=new Node23();
			 newNode.insert(SortdataArray[1].item);
			 newNode.connectChild(node,0);
			 newNode.connectChild(NewRightNode, 1);
			 root=newNode;
			 return NewRightNode;
		 }
		 else if(parent.isFull()){
			Node23 sibling= split(parent,SortdataArray[1].item);
			if(parent.childArray[0]==node){
				Node23 child2=parent.disconnectChild(1);
				Node23 child3=parent.disconnectChild(2);
				parent.connectChild(NewRightNode, 1);
				sibling.connectChild(child2, 0);
				sibling.connectChild(child3, 1);
			}
			else if(parent.childArray[1]==node){
				 Node23 child2=parent.disconnectChild(2);
				 sibling.connectChild(NewRightNode, 0);
				 sibling.connectChild(child2, 1);
			}
			else{
				Node23 child2=parent.disconnectChild(2);
				 sibling.connectChild(NewRightNode, 1);
				 sibling.connectChild(child2, 0);
			}
			return NewRightNode;
		 }
		 else{
			 parent.insert(SortdataArray[1].item);
			 if(parent.childArray[0]==node){
				 parent.connectChild( parent.disconnectChild(1),2);
				 parent.connectChild(NewRightNode,1);
			 }
			 else
				 parent.connectChild(NewRightNode, 2);
			 
		 }
			 
		return NewRightNode;	 
		    
			
	}
	
	
	
	
	public static void main(String[] args) {
       Tree23 t=new Tree23();
       	t.insert(2);
      	t.insert(5);
      	t.insert(4);
     	t.insert(8);
     	t.insert(9);
     	t.insert(10);
     	t.display();
       System.out.println("  ");
     	 if(t.find(8)!=-1)
	    	 System.out.println("found at "+ t.find(8));
	     else System.out.println("Not Found");
	}	

}
