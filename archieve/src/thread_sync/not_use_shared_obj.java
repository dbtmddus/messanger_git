package thread_sync;

import java.util.concurrent.locks.ReentrantLock;

class thread_extends extends Thread{
	static private ReentrantLock locker = new ReentrantLock();

	private int iden_n=0;
	private static int n=0;

	public thread_extends(int _iden_n){
		iden_n= _iden_n;
	}	

	@Override
	public void run(){
		locker.lock();
		f();
		locker.unlock();
	}

	public void f(){	//�� ���, class ��ü�� ȥ�� �����ϹǷ� �̷� �ù���� ����õ �Ǵµ�(locker�� ����)
		//locker.lock();
		for(int i=0; i<100; i++){
			System.out.print(iden_n+"aaaaaaaaaaaaaaaaaaaaaaaaaa");			
			System.out.println(iden_n+"b");
			System.out.println(i);	
		}
		//locker.unlock();
	}
}


public class not_use_shared_obj {
	static private ReentrantLock locker = new ReentrantLock();

	public static void main(String[] args) throws InterruptedException {
		thread_extends t_extends1 = new thread_extends(1);	//�� ��ü�� �̹� thread ������.
		thread_extends t_extends2 = new thread_extends(2);
				
		//locker.lock();	//�̰� �ȵ�. thread�� �ִ� class���� ����ϴµ�
		t_extends1.start();
		//locker.unlock();
		t_extends2.start();		
	}
}
