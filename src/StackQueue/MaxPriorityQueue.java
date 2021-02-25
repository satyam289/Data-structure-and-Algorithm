package StackQueue;


//insert at O(1) but removing at highest priority(here max value).(good in when insertion is more than remove)

public class OppoPriorityQueue {
int a[];
int rear;
int size;
   OppoPriorityQueue(int n){
          a=new int[n];
          rear=0;
          size=n;
   }
  
   public void enque(int value){
          if(rear==size)
                 throw new RuntimeException("queue overflows");
          a[rear++]=value;    
   }
  
   public int deque(){
          if(rear==0)
                 throw new RuntimeException("stack underflows");
          int max=0;
          int k = 0;
          for(int i=rear; i>0; i--){
                 if(a[i]>max){
                       max=a[i];
                       k=i;
                 }
          }
          System.out.println("Removing the max value from queue "+max);
          for(int j=k+1;j<rear;j++)
                 a[j-1]=a[j];
          rear--;
          return max;
   }
  
   public String toString(){
            String output ="[";
        for(int i=0;i<rear;i++){
           output += a[i];
          if(i<rear-1)
             output += ",";
        }
        output +="]";
        return output;
   }
  
   public static void main(String[] args) {
          OppoPriorityQueue q=new OppoPriorityQueue(5);
          q.enque(3);
          q.enque(5);
          //q.deque();
          q.enque(9);
          //q.enque(1);
          q.enque(7);
          q.deque();
         
    System.out.println(q);
   }

}
