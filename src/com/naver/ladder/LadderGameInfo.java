package com.naver.ladder;

/**
 * 사다리 정보 
 * @author crusader
 *
 */
public class LadderGameInfo {

	private int[][] ladderInfo; // 사다리 정보
	private int totalRow; // 마지막 행
	private int player; // 게임을 하는 player수
	
	public int[][] getLadderInfo() {
		return ladderInfo;
	}
	public void setLadderInfo(int[][] ladderInfo) {
		this.ladderInfo = ladderInfo;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	public int getPlayer() {
		return player;
	}
	public void setPlayer(int player) {
		this.player = player;
	}
}
