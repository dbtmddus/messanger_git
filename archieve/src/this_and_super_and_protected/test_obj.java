package this_and_super_and_protected;

//public class test_obj extends Object{
public class test_obj{
	
	protected static int static_int = 0;	//protected이므로, 다른 패키지에서는 상속받지 않는 이상 접근 불가하다.
	
	public test_obj(){
		static_int = 10;
	}
	
	public void show(){
		System.out.println("class : "+this.toString());
		System.out.println("super at this method: "+super.toString());
		System.out.println("this at this method: "+this.toString());
		System.out.println("value : "+static_int);
	}
	
	public void show2(){
		//System.out.println("class : "+this.getClass());
		
		System.out.println("this at P_class: "+this.toString());
		System.out.println("super at P_class: "+super.toString());
		System.out.println();
	}
	
	@Override
	public String toString() {
		return "test_obj [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

	public void show_test(){
		System.out.println("ttttttt : "+static_int);
	}
}
