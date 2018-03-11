class A {
	int var1;
	void f1() {
		int a = 0;
		this.var1 = a;
	}
}
public class KnobsTest {
    public static void main(String[] args) {
        System.out.println();
    }

    public void test1() {
    	int a = 1;
    	a = 2;
    }
    public void test2() {
    	Boolean a;
    	a = false;
    }
    public void test3() {
    	String a;
    	a = "";
    }
    public void test4() {
    	A a;
    	a = new A();
    }
    public void test5() {
    	final String b = "";
    }
    public void test6() {
    	A a = null;
    	a = new A();
    }
    public void test7() {
    	int a;
    	int b = 1;
    	a = b;
    }

    public void test8() {
    	String a = "";
    	int _tmp = 1;
    	for(int i=0;i<10;i++) {
    		for(int j=0;j<100;j++) {
    			int b = 0;
    			a = "test";
    			int c = _tmp;
    		}
    	}
    }
}
