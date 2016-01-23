package com.bee.team.all;

import com.bee.team.app.board.entity.Board;

public class LaserBuilderTest {

	public static void main(String[] args) {
		
		Board board = BoardFactory.create("DEBUG");
		Laser laser = board.getLaser();
		
		System.out.println("start point: "+laser.getStart());
		System.out.println("start direction: "+laser.getStartDirection());
		
		new LaserBuilder().compute(board);
		
		System.out.println("path-length: "+laser.getPath().size());
		System.out.println(laser);
	}

}
