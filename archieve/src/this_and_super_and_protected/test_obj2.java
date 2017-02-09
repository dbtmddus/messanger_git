package this_and_super_and_protected;

public class test_obj2 extends test_obj{
	
	static int static_int =500;
		
	@Override
	public void show(){
		System.out.println();
		System.out.println("super : " + super.toString());
		System.out.println("super.static_int : " + super.static_int);
		System.out.println("this : "+ this.toString());
		System.out.println("this.static_int : " + this.static_int);
		
		System.out.println("static_int : " + static_int);
	}	

	@Override
	public void show2(){
		//System.out.println("class : "+this.getClass());
		System.out.println("this at C_class: "+this.toString());
		System.out.println("super at C_class: "+super.toString());
		//System.out.println("value : "+static_int);
		
	}
	/*
	@Override
	public String toString() {
		return "test_obj2 [toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}*/
	@Override
	public String toString() {
		return "test_obj2 [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
		+ "]";
	}

	@Override
	public void show_test(){
		super.show_test();
	}
}
