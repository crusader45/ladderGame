package com.naver.ladder;

/**
 * @author crusader
 *
 */
public class LadderGameView {

	/**
	 * 사다리 게임에서 이동 위치 로그 
	 * @param totalRow
	 * @param player
	 * @param playerNo
	 * @param currentRow
	 * @param ladderInfo
	 */
	public void printLadderInfo(int totalRow,int player,  int playerNo, int currentRow, int[][] ladderInfo) {
		System.out.println("**** 사다리 위치 로그 ****");
		for (int i = 0; i < totalRow; i++) {
			for (int j = 0; j < player; j++) {
				System.out.printf("%3d",ladderInfo[i][j]);
				printCurrentLocation(playerNo, currentRow, i, j);
			}
			System.out.println("");
		}
	}
	
	/**
	 * 사다리 게임에서 현재 위치 출력 
	 * @param playerNo
	 * @param currentRow
	 * @param i
	 * @param j
	 */
	public void printCurrentLocation(int playerNo, int currentRow, int i, int j) {
		if ((playerNo == j) && (currentRow == i)) {
			System.out.print("*");
			return;
		}
		System.out.print("  ");
	}

	/**
	 * 사다리 게임 시작할 때, 정보 출력 
	 * @param playerNo
	 */
	public void printLadderGameInfo(int playerNo) {
		System.out.println("**** 사다리 게임을 시작합니다. ****");
		System.out.println("현재 playerNo : " + playerNo + "\n");
	}

	/**
	 * 사다리 게임 결과 출력 
	 * @param playerNo
	 */
	public void printLadderGameResult(int playerNo) {
		System.out.println("***** 사다리 게임 결과입니다. ****");
		System.out.println("도착 지점 : " + playerNo + "\n");
	}
}
