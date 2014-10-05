package com.naver.ladder;

import java.util.Random;

/**
 * @author crusader
 *
 */
public class LadderGameBO {
	private static final int RIGHT_CONNECTION_VALUE = 1; // 해당 위치에서 오른쪽으로 연결되었을  때
	private static final int LEFT_CONNECTION_VALUE = -1; // 해당 위치에서 왼쪽으로 연결되었을 때
	private static final int RANDOM_BOUND_VALUE = 2; // 랜덤값이 0, 1만 생성하기 위한

	private LadderGameInfo ladderGameInfo;

	public LadderGameBO() {
		if (this.ladderGameInfo == null) {
			ladderGameInfo = new LadderGameInfo();
		}
	}

	/**
	 * player 설정 
	 * @param player
	 */
	public void setPlayer(int player) {
		ladderGameInfo.setPlayer(player);
	}

	/**
	 * 사다리 총 행 설정 
	 * @param totalRow
	 */
	public void setTotalRow(int totalRow) {
		ladderGameInfo.setTotalRow(totalRow);
	}

	/**
	 * player가 사다리 정보를 입력한 경우, 입력한 사다리 정보 설정 
	 * @param userLadderInfo
	 */
	public void setLadderInfo(int[][] userLadderInfo) {
		if (userLadderInfo == null) {
			ladderGameInfo.setLadderInfo(getRandomLadderInfo());
			return;
		}
		ladderGameInfo.setLadderInfo(userLadderInfo);
	}

	/**
	 * player의 수가 0이하인 경우 false를 리턴한다.
	 * 
	 * @return
	 */
	public boolean isInvalidPlayer(int player) {
		if (player < 1) {
			return true;
		}
		return false;
	}

	/**
	 * totalRow(사다리의 총 행)이 10보다 크거나 0이하인 경우 false를 리턴한다.
	 * 
	 * @param totalRow
	 * @return
	 */
	public boolean isInvalidTotalRow(int totalRow) {
		if (totalRow > 10 || totalRow < 1) {
			return true;
		}
		return false;
	}

	/**
	 * 랜덤하게 사다리정보를 생성한다.
	 */
	private int[][] getRandomLadderInfo() {
		int totalRow = getTotalRow();
		int player = getPlayer();

		int[][] randomLadder = new int[totalRow][player];
		Random random = new Random();

		int colSize = player - 1;
		for (int i = 0; i < colSize; i++) {
			iterateRow(totalRow, randomLadder, random, i);
		}
		return randomLadder;
	}

	/**
	 * 랜덤 사다리 정보 생성 시, 행을 순회한다.
	 * 
	 * @param totalRow
	 * @param randomLadder
	 * @param random
	 * @param col
	 */
	private void iterateRow(int totalRow, int[][] randomLadder, Random random, int col) {
		for (int i = 0; i < totalRow; i++) {
			insertRandomValue(randomLadder, random, i, col);
		}
	}

	/**
	 * 랜덤값을 생성하여 사다리에 추가한다.
	 * 
	 * @param randomLadder
	 * @param random
	 * @param row
	 * @param col
	 */
	private void insertRandomValue(int[][] randomLadder, Random random, int row, int col) {
		int randomValue = random.nextInt(RANDOM_BOUND_VALUE);
		if ((randomLadder[row][col] == 0)
				&& (randomValue == RIGHT_CONNECTION_VALUE)) {
			randomLadder[row][col] = RIGHT_CONNECTION_VALUE;
			randomLadder[row][col + 1] = LEFT_CONNECTION_VALUE;
		}
	}

	/**
	 * 사다리 게임의 총 player 수를 반환한다.
	 * 
	 * @return
	 */
	public int getPlayer() {
		return ladderGameInfo.getPlayer();
	}

	/**
	 * 사다리 게임의 총 row를 반환한다.
	 * 
	 * @return
	 */
	public int getTotalRow() {
		return ladderGameInfo.getTotalRow();
	}

	/**
	 * 사다리 게임의 결과를 반환한다.
	 * 
	 * @return
	 */
	public int getLadderGameResult(int playerNo, LadderGameView view) {
		int currentCol = playerNo - 1;
		int currentRow = 0;
		int totalRow = getTotalRow();
		int player = getPlayer();
		while (currentRow < totalRow) {
			view.printLadderInfo(totalRow, player, currentCol, currentRow, ladderGameInfo.getLadderInfo());
			currentCol = moveDirection(currentRow, currentCol, view);
			currentRow++;
		}
		return currentCol + 1;
	}

	/**
	 * 좌, 우로 열을 이동하고, 이동하는 경우 위치를 출력한다.
	 * 
	 * @param currentRow
	 * @param currentCol
	 * @return
	 */
	private int moveDirection(int currentRow, int currentCol, LadderGameView view) {
		int[][] ladderInfo = ladderGameInfo.getLadderInfo();
		if (ladderInfo[currentRow][currentCol] == 1 || ladderInfo[currentRow][currentCol] == -1) {
			view.printLadderInfo(getTotalRow(), getPlayer(), currentCol + ladderInfo[currentRow][currentCol], currentRow, ladderInfo);
			return currentCol + ladderInfo[currentRow][currentCol];
		}
		return currentCol;
	}
}
