package Linked;

public class JosephusApp {
	CircularLinkedList cs;
	int no_of_people;
	int count;

	public static void main(String[] args) {
		JosephusApp j=new JosephusApp(10,2,1);
		j.execute();
	}
	
	public JosephusApp(int nPeople, int nCount, int startPoint){
		this.no_of_people=nPeople;
		this.count = nCount;
	    cs= new CircularLinkedList();
	    
	   for(int i=1;i<=nPeople;i++){          
		  cs.insert(i);
	   }
	   cs.step_for_display();	  //moving to old pointer position (at first insertion point), check the display at last node	
	   
	   for(int i=1;i<startPoint;i++){      //moving to starting position
		   cs.step_for_display();
	   }
	  
	   
	}
	public void execute(){
		for(int j=0;j<no_of_people-1;j++){              //same number times as (no_of_people -1) times
		
		for(int i=0; i<count-1; i++){                    //rotating till count-1 as delete operation works at next of current pointer, so stop before 1 count less 
			cs.step_for_display();
		}
		System.out.println("--- Killed----  "+cs.delete());
		   cs.step_for_display();                          //After deleting , moves to next node (left of dead person)
		
	}
		
		System.out.println("*****************************************");
		System.out.println("lucky Man is found at --->");
		cs.step_for_display();
		System.out.println("done");
	}
	
	
}
