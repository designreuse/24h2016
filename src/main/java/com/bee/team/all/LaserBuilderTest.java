package com.bee.team.all;

public class LaserBuilderTest {

	public static void main(String[] args) {
		
		Board board = BoardFactory.create("DEBUG");
		Laser laser = board.getLaser();
		new LaserBuilder().compute(laser, board);
		
		System.out.println(laser);
	}

}
