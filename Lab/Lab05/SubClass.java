class SuperClass {
	public static final int x = 1;
}

interface Nice{
	public static final int x = 1;
}

public class SubClass extends SuperClass implements Nice{
	public static void main(String[] args){
		System.out.println(SubClass.x);
		System.out.println(SuperClass.x);
	}
}