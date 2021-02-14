package CrackingTheCode.Ch_15_Thread_and_Locks.IntroductionSynchronization;

public class MyClass extends Thread  {
	private String name;
	
	public MyClass(MyObject obj, String n) {
		name = n;
	}
	
	public void run() {
		if (name.equals("1")) {
			MyObject.foo(name);
		} else if (name.equals("2")) {
			MyObject.bar(name);
		}
	}
}
