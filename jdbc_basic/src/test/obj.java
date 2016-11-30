package test;

public class obj extends Thread{

	//static int n;
	int n;
	
	public obj(int _n){
		n = _n;
	}

	public void run(){
		while(true){
			System.out.println("thread"+ n);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}

