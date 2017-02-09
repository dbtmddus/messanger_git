package thread_sync;

class thread_obj {
    public synchronized void decrement(){
        try {
            for (int i = 1000; i > 0; i--) {
                System.out.println("Thread " + i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class TestThreadClass extends Thread {
    private String threadName;
    thread_obj obj1;

    public TestThreadClass(String threadName, thread_obj Obj1) {
        this.threadName = threadName;
        obj1 = Obj1;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);
        obj1.decrement();
        System.out.println("End of " + threadName);
    }    
}

public class use_shared_obj {

    public static void main(String[] args) {
        thread_obj obj1 = new thread_obj();		//이런식으로 공유객체를 써야 정상적으로 동기화됨

        TestThreadClass t1 = new TestThreadClass("Thread 1", obj1);
        TestThreadClass t2 = new TestThreadClass("Thread 2", obj1);

        t1.start();
        t2.start();
    }
}