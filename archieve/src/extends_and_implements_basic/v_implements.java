package extends_and_implements_basic;

import java.util.Arrays;

interface를 super로 받아올수 있는지 확인할것, 용어txt정리할것.
class implemented_obj implements v_implements_interface{

	public implemented_obj(){
		super();
	}
	@Override
	public void f_abstract() {
		System.out.println("f_abstract_implemented");
		//v_implements_interface.f_static();
	}

	@Override
	public void f_basic() {
		System.out.println("f_basic_implemented");		
	}

	@Override
	public void f_default() {
		//super.		//implements는 아예 super이 안되는듯
		System.out.println("f_default_implemented");		
	}

	/*@Override	//얘는 수정 불가능
	public void f_static() {
		System.out.println("f_default_implemented");		
	}*/

}
public class v_implements{
	public static void main(String[] args) {
		implemented_obj o1 =new implemented_obj();
		o1.f_basic();
		o1.f_abstract();
		o1.f_default();
		o1.f_default();
		
	}//end main	
}
