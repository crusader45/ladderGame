package com.naver.ladder;

/**
 * @author crusader
 *
 */
public class LadderGameController {

	private LadderGameView view;
	private LadderGameBO ladderGameBO;
	private int[][] userLadderInfo;																			//player가 사다리 정보를 입력해서 플레이 하는 경우 
	
	/**
	 * 사다리 게임 시작 전, 초기화.. 
	 * @param player
	 * @param totalRow
	 */
	public void init(int player, int totalRow) {
		view = new LadderGameView();
		ladderGameBO = new LadderGameBO();
		
		if (ladderGameBO.isInvalidPlayer(player) || ladderGameBO.isInvalidTotalRow(totalRow)) {
			throw new RuntimeException();
		}
		
		ladderGameBO.setPlayer(player);
		ladderGameBO.setTotalRow(totalRow);
		ladderGameBO.setLadderInfo(userLadderInfo);
	}

	/**
	 * 사다리 게임 시작
	 * @param playerNo
	 * @return
	 */
	public int run(int playerNo) {
		view.printLadderGameInfo(playerNo);
		
		if (ladderGameBO.getPlayer() == 1) {
			view.printLadderGameResult(playerNo);
			return playerNo;
		}

		int result = ladderGameBO.getLadderGameResult(playerNo, view);
		view.printLadderGameResult(result);
		return result;
	}

	/**
	 * player가 사다리 정보를 입력한다.
	 * @param userLadderInfo
	 */
	public void setUserLadderInfo(int[][] userLadderInfo) {
		this.userLadderInfo = userLadderInfo;
	}
}
