/**
 * 
 */
package homework.oop.h04;

/**
 * 
 * 根据本类的要求，构建类并设置合适的方法和属性
 * 
 * @author Administrator
 *
 */
public class Test {

	/**
	 * 
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String color = "yellow";
		int size = 10;
		Apple app1 = new Apple();

		if (app1.getColor().equals("red")) {
			// 正确
		}

		Apple app2 = new Apple(size, color);
		if (app2.getColor().equals(color) && app2.getSize() == size) {
			// 正确
		}


		// 定义三角形类 
		Triangle tr = new Triangle();
		// 分别设置三角形a,b,c边长
		tr.setA(3);
		tr.setB(4);
		tr.setC(5);
		// 计算面积
		double area = tr.getArea();
		if (Math.abs(area - 6) < 0.001) {
			// 正确
		}
		

		// 请实现栈(stack)这种数据结构用来存放int
		// 并实现如下操作
		// Stack(int size) 构造函数 size为这个栈的可以容纳元素个数
		// int pop()
		// void push(int e)
		// boolean isEmpty()
		// boolean isFull()
		// 构造函数中的正整数为这个stack最大的元素个数，如果stack中元素个数达到这个则stack.isFull()==true
		MyStack stack = new MyStack(3);
		// 正确
		if (stack.isEmpty()) {
			// 正确
		}
		stack.push(1);
		stack.push(4);
		stack.push(5);
		// push了3个元素，对应 stack=new MyStack(3)
		if (stack.isFull()) {
			// 正确
		}
		if (stack.pop() == 5) {
			// 正确
		}
		if (!stack.isFull()) {
			// 正确
		}
	}


}
class Apple{
	private String color;
	private int size;

	public Apple(int size, String color) {
		this.size = size;
		this.color = color;
	}
	public Apple() {
		this.color = "red";
	}


	public String getColor() {
		return this.color;
	}

	public int getSize() {
		return this.size;
	}
}

class Triangle{
	private int a;
	private int b;
	private int c;

	public void setA(int a) {
		this.a = a;
	}

	public void setB(int b) {
		this.b = b;
	}

	public void setC(int c) {
		this.c = c;
	}

	public double getArea() {
		double s = (double) (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
	}
}

class MyStack {
	private int size;
	private int[] elements;
	private int curIndex;

	public MyStack(int size) {
		this.size = size;
		this.elements = new int[size];
		this.curIndex = 0;
	}

	public int pop() {
		int popNum = this.elements[--this.curIndex];
		this.elements[this.curIndex] = 0;
		return popNum;
	}

	public void push(int e) {
		this.elements[this.curIndex++] = e;
	}

	public boolean isEmpty() {
		return this.curIndex == 0;
	}

	public boolean isFull() {
		return this.curIndex == this.size;
	}
}
