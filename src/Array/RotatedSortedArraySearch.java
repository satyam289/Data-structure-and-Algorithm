package Array;

public class RotatedSortedArraySearch {

	public static void main(String[] args) {
		RotatedSortedArraySearch r=new RotatedSortedArraySearch();
		int [] a={1,2,3,4,7};	
		int index=r.findbinaryPosition(a,0,a.length-1);
		if(index == a.length-1)
			System.out.println("All sorted");
		else
			System.out.println("Rotated at index "+index+" The min value is "+a[index]);
		
		if(index==a.length-1){
			System.out.println("Rotated at index 0  the min value "+a[0]);
			System.out.println("search item at "+r.binarySearch(a,0,a.length-1,4));
		}
		else{
			int index2=r.nonRecursiveBinarySearch(a, index,a.length-1,7);
		  
				if(index2==-1)
					System.out.println("seached at index "+r.nonRecursiveBinarySearch(a, 0, index, 7));
				else
					System.out.println("the index: "+index2 +" search item "+ a[index2] );
			
	   }
	}
	   
	
	
	public int findbinaryPosition(int[] arr, int low , int high){
		
		/*if(lower <= upper){
			if(lower == upper){
				return lower;
			}
			
			int mid= (upper+lower)/2;
			
			if(a[mid+1]>a[mid] && a[mid-1]>a[mid])
				return mid;
			
			if(a[mid] >= a[lower])
			  return findbinaryPosition(a, mid+1, upper);
			else 
			  return findbinaryPosition(a,lower, mid-1);
		}
		return -1;*/
		
		
		if(high==low)
			return low;
		int mid= (low+high)/2;
		if(mid>low && arr[mid] > arr[mid+1])
			return mid;
		if(mid<high && arr[mid] < arr[mid-1])
		    return mid-1;
		if(arr[low]>arr[mid])
			return findbinaryPosition(arr,low,mid-1);
		else
			return findbinaryPosition(arr,mid+1, high);
		
	}
	
	public int binarySearch(int [] a , int lower , int upper, int value){
		
		if(lower<=upper){
			int mid= (lower+upper)/2;
		
			if(a[mid]==value)
				return mid;
			else if(value<a[mid])
				return binarySearch(a,lower,mid-1,value);
			else 
				return binarySearch(a,mid+1, upper,value);
		
		}
		return -1;
	}
	
	public int nonRecursiveBinarySearch(int [] a, int lower, int upper, int value){
		 
		 while(lower<=upper){
			int mid= (upper+lower)/2;
			 if(a[mid] == value)
			     return mid;   
			 else if(value < a[mid])
				 upper=mid-1;
			 else 
				 lower=mid+1;	 
		 }
		
		return -1;
	}
}
