package this_and_super_and_protected;

import this_and_super_and_protected.test_obj;

public class test2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test_obj o1 = new test_obj();
		o1.static_int++;	//	protected 확인용. 상속받은 class or 같은 패키지 안에서만 접근 가능하므로, 이 코드는 상속이 아니므로 다른 패키지에서는 오류남(접근 불가), 
	}

}
