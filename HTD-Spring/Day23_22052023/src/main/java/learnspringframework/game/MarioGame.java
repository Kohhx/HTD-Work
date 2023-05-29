package learnspringframework.game;

public class MarioGame implements GamingConsole {

	@Override
	public void up() {
		System.out.println("Jump");
	}

	@Override
	public void down() {
		System.out.println("Going down the hole");
	}

	@Override
	public void left() {
		System.out.println("Going back");
	}

	@Override
	public void right() {
		System.out.println("Accelerate forward");
	}

}
