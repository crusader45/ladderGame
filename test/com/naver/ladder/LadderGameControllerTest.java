package com.naver.ladder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class LadderGameControllerTest {

	private static final int TEST_ROW_SMALL_SIZE = 5;
	private static final int TEST_ROW_BIG_SIZE = 10;
	
	private LadderGameController game;
	
	@Before
	public void setUp() {
		game = new LadderGameController();
	}

	/**
	 * player가 1보다 작은 경우 테스트 
	 */
	@Test(expected = RuntimeException.class)
	public void testInvalidPlayer() {
		int player = 0;
		game.init(player, TEST_ROW_SMALL_SIZE);
	}
	
	/**
	 * endRow를 셋팅하지 않은 경우 
	 */
	@Test(expected = RuntimeException.class)
	public void testInvalidEndRow() {
		int player = 3;
		game.init(player, 0);
	}

	/**
	 * 1명이 게임을 하는 경우 테스트
	 */
	@Test
	public void testRunWithOnePlayer() {
		int player = 1;
		game.setUserLadderInfo(getSamepleLadderInfoByOnePlayer());
		game.init(player, TEST_ROW_SMALL_SIZE);
		
		assertThat(game.run(player), is(1));
	}

	/**
	 * 2명이 게임을 하는 경우 테스트
	 */
	@Test
	public void testRunWithTwoPlayer() {
		int player = 2;
		game.setUserLadderInfo(getSampleLadderInfoByTwoPlayer());
		game.init(player, TEST_ROW_SMALL_SIZE);

		int playerNo = 1;
		assertThat(game.run(playerNo), is(2));

		playerNo = 2;
		assertThat(game.run(playerNo), is(1));
	}

	/**
	 * 3명이 게임을 하는 경우 테스트
	 */
	@Test
	public void testRunWithThreePlayer() {
		int player = 3;
		game.setUserLadderInfo(getSampleLadderInfoByThreePlayer());
		game.init(player, TEST_ROW_SMALL_SIZE);

		int playerNo = 1;
		assertThat(game.run(playerNo), is(2));

		playerNo = 2;
		assertThat(game.run(playerNo), is(1));

		playerNo = 3;
		assertThat(game.run(playerNo), is(3));
	}

	/**
	 * 요구사항에서 제시한 사다리 정보로 테스트 player는 6명, row는 10
	 */
	@Test
	public void testRunWithLadderGameInRequesteInfo() {
		int player = 6;
		game.setUserLadderInfo(getSampleLadderInfoByRequest());
		game.init(player, TEST_ROW_BIG_SIZE);

		int playerNo = 1;
		assertThat(game.run(playerNo), is(6));

		playerNo = 2;
		assertThat(game.run(playerNo), is(4));

		playerNo = 3;
		assertThat(game.run(playerNo), is(5));

		playerNo = 4;
		assertThat(game.run(playerNo), is(1));

		playerNo = 5;
		assertThat(game.run(playerNo), is(2));

		playerNo = 6;
		assertThat(game.run(playerNo), is(3));
	}
	
	/**
	 * 랜덤하게 사다리를 그리고, 게임을 실행하는 경우 테스트 
	 */
	@Test
	public void testRandomLadderGame() {
		int player = 6;
		game.init(player, TEST_ROW_BIG_SIZE);
		
		int[] actualResult = new int[player];
		
		for (int i = 1; i < player + 1; i++) {
			int resultNo = game.run(i);
			actualResult[resultNo - 1] = 1;
		}
		
		for (int num : actualResult) {
			assertThat(num, is(1));
		}
	}

	/**
	 * player 1인 경우 반환<br>
	 * [0]<br>
	 * [0]<br>
	 * [0]<br>
	 * [0]<br>
	 * [0]<br>
	 */
	private int[][] getSamepleLadderInfoByOnePlayer() {
		int[][] sampleLadder = { { 0 }, { 0 }, { 0 }, { 0 }, { 0 }};
		return sampleLadder;
	}

	/**
	 * player 2인 경우 반환<br>
	 * [0 0]<br>
	 * [1 -1]<br>
	 * [0 0]<br>
	 * [1 -1]<br>
	 * [1 -1]<br>
	 * 
	 * @return
	 */
	private int[][] getSampleLadderInfoByTwoPlayer() {
		int[][] sampleLadder = { { 0, 0 }, { 1, -1 }, { 0, 0 }, { 1, -1 }, { 1, -1 } };
		return sampleLadder;
	}

	/**
	 * player 3인 경우 반환<br>
	 * [0 1 -1]<br>
	 * [1 -1 0]<br>
	 * [1 -1 0]<br>
	 * [0 1 -1]<br>
	 * [1 -1 0]<br>
	 * 
	 * @return
	 */
	private int[][] getSampleLadderInfoByThreePlayer() {
		int[][] sampleLadder = { { 0, 1, -1 }, 
													{ 1, -1, 0 },
													{ 1, -1, 0 },
													{ 0, 1, -1 },
													{ 1, -1, 0 } };
		return sampleLadder;
	}

	/**
	 * 요구사항에 정의된 사다리 정보<br>
	 * [1 1 0 0 0 0]<br>
	 * [0 0 0 1 1 0]<br>
	 * [0 1 1 0 1 1]<br>
	 * [0 0 1 1 0 0]<br>
	 * [0 1 1 0 1 1]<br>
	 * [1 1 0 1 1 0]<br>
	 * [0 0 1 1 1 1]<br>
	 * [0 1 1 1 1 0]<br>
	 * [1 1 0 0 0 0]<br>
	 * [0 0 1 1 0 0]<br>
	 * 
	 * @return
	 */
	private int[][] getSampleLadderInfoByRequest() {
		int[][] sampleLadder = { { 1, -1, 0, 0, 0, 0 },
													{ 0, 0, 0, 1, -1, 0 },
													{ 0, 1, -1, 0, 1, -1 },
													{ 0, 0, 1, -1, 0, 0 },
													{ 0, 1, -1, 0, 1, -1 },
													{ 1, -1, 0, 1, -1, 0 },
													{ 0, 0, 1, -1, 1, -1 },
													{ 0, 1, -1, 1, -1, 0 },
													{ 1, -1, 0, 0, 0, 0 }, 
													{ 0, 0, 1, -1, 0, 0 } };
		return sampleLadder;
	}
}
