package homework.h18;

public class Apple {

	private String color;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	private int weight;

	@Override
	public String toString() {
		return "Apple{" +
				"color='" + color + '\'' +
				", weight=" + weight +
				'}';
	}
}
