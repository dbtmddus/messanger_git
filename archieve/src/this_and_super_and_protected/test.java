package this_and_super_and_protected;
public class test {

	public static void main(String[] args) {

		test_obj o1 = new test_obj();
		test_obj2 sub_o1 = new test_obj2();
		
		o1.show2();
		sub_o1.show2();
		
		System.out.println();
		System.out.println(o1);
		System.out.println(sub_o1);
		
	}
}
