package com.bee.team.all;

import com.bee.team.app.board.entity.Board;

public class LaserBuilderTest {

	public static void main(String[] args) {
		
		Board board = BoardFactory.create("DEBUG");
		Laser laser = board.getLaser();
		new LaserBuilder().compute(laser, board);
		
		System.out.println("path-length: "+laser.getPath().size());
		System.out.println(laser);
	}

}
