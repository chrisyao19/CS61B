class X {
	protected String str;
}
class Y extends X{

}

public class P1{
	public static void main(String[] args){
		X[] xa = new Y[1];
		Y[] ya = new Y[1];
		ya = (Y[])xa;
		xa = ya;
		System.out.println("axiba");
	}
}