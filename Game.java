
public class Game {

	Player player[];
	Base board[][];
	Equipment equipment;
	static int turn;

	public Game() {
		player = new Player[2];
		player[0] = new Player("박성준");
		player[1] = new Player("멍청이");
		board = new Base[8][8];
		turn = 1; // 1일시 플레이어[0]의 턴, -1일시 플레이어[1]의 턴
	}

	public static void main(String args[]) {
		Game g = new Game();

		g.board[4][0] = new Rook("룩", "기계형", g.player[0].name, 4 , 0);
		g.board[3][1] = new Pawn("폰", "인간형", g.player[0].name, 3 , 1);
		g.board[5][1] = new Pawn("폰", "인간형", g.player[0].name, 5 , 1);
		g.board[3][7] = new Rook("룩", "기계형", g.player[1].name, 3 , 7);
		g.board[2][6] = new Pawn("폰", "인간형", g.player[1].name, 2 , 6);
		g.board[4][6] = new Pawn("폰", "인간형", g.player[1].name, 4 , 6);

		Game.turn = 1;
	
		g.move(4, 0, 4, 3);
		g.move(3, 7, 3, 4);
		g.attack(4, 3, 2, 6);
		g.attack(4, 3, 2, 7);
		g.attack(4, 3, 3, 4);
		g.attack(3, 4, 4, 3);
		g.equipment = new Weapon();
		g.equip(4, 3, g.equipment);
		
	}

	public void win() {
		if (turn == 1)
			System.out.println(player[0].name + "이(가) 승리했습니다.");
		if (turn == -1)
			System.out.println(player[1].name + "이(가) 승리했습니다.");
	}

	public void move(int x, int y, int toX, int toY) {
		if (board[x][y] == null) {
			System.out.println("말을 선택 안하셨습니다.");
		} else {
			if (turn == 1 && board[x][y].master.equals(player[0].name) && board[toX][toY] == null) {
				player[0].move(board[x][y], toX, toY, board);
			} else if (turn == -1 && board[x][y].master.equals(player[1].name) && board[toX][toY] == null) {
				player[1].move(board[x][y], toX, toY, board);
			} else {
				System.out.println("말을 잘못 선택하셨습니다.");
			}
		}
	}

	public void attack(int x, int y, int toX, int toY) {
		if (board[x][y] == null || board[toX][toY] == null) {
			System.out.println("말을 잘못 선택하셨습니다.");
		} else {
			if (turn == 1 && board[x][y].master.equals(player[0].name)
					&& board[toX][toY].master.equals(player[1].name)) {
				player[0].attack(board[x][y], board[toX][toY]);
			} else if (turn == -1 && board[x][y].master.equals(player[1].name)
					&& board[toX][toY].master.equals(player[0].name)) {
				player[1].attack(board[x][y], board[toX][toY]);
			} else {
				System.out.println("말을 잘못 선택하셨습니다.");
			}
		}
	}

	public void skill(int x, int y, int toX, int toY) {
		if (board[x][y] == null || board[toX][toY] == null) {
			System.out.println("말을 잘못 선택하셨습니다.");
		} else {
			if (turn == 1 && board[x][y].master.equals(player[0].name)
					&& board[toX][toY].master.equals(player[1].name)) {
				player[0].skill(board[x][y], board[toX][toY]);
			} else if (turn == -1 && board[x][y].master.equals(player[1].name)
					&& board[toX][toY].master.equals(player[0].name)) {
				player[1].skill(board[x][y], board[toX][toY]);
			} else {
				System.out.println("말을 잘못 선택하셨습니다.");
			}
		}
	}

	public void equip(int x, int y, Equipment e) { // e는 0 헤드 1 무기 2 다리
		if (board[x][y] == null) {
			System.out.println("말을 잘못 선택하셨습니다.");
		} else {
			if (turn == 1 && board[x][y].master.equals(player[0].name)) {
				player[0].equip(board[x][y], e);
			} else if (turn == -1 && board[x][y].master.equals(player[1].name)) {
				player[1].equip(board[x][y], e);
			} else {
				System.out.println("말을 잘못 선택하셨습니다.");
			}
		}
	}
}
