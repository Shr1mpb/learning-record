package homework.oop.h20;

/**定义合适的类、接口，使得下面的代码编译并能正确运行*/
public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	/*
		推导：
		 	A、D、E是类 因为被new出来
		 	剩余的一步一步推导
		 	D首先要继承A 并实现C 前三行 通过1
		 	B和C有关系，让C继承B 通过2
		 	D和A有关系 让D继承A(已做过) 通过3
		 	E和C有关系 让E实现C 通过4
	 */
	public static void main(String[] args) {
		A a = new D();
		C c = new D();
		D d = new D();
		 
		System.out.println("pass 1");
		
		B b = c;
		System.out.println("pass 2");
		
		a = d;
		System.out.println("pass 3");
		
		c=new E();
		System.out.println("pass 4");
		
		a=new A();
		if (!(a instanceof B)) {
			System.out.println("pass 5");
		}
		
		if (!(c instanceof A)) {
			System.out.println("pass 6");
		}
		if (!(c instanceof D)) {
			System.out.println("pass 7");
		}

	}
}
