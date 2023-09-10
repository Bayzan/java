package XOX;

import java.util.Random;
import java.util.Scanner;

public class Game {

	String[][] map;
	String player1, player2;
	int size, row, col, skor1 = 0, skor2 = 0, hamle = 1,sıra=1;

	Scanner scan = new Scanner(System.in);
	Random rand = new Random();

	public Game(int row, int col) {
		this.map = new String[row][col];
		this.row = row;
		this.col = col;
		this.size = row * col;
	}

	public void login() {
		System.out.println("XOX oyununa hoş geldiniz\n ");
		System.out.println("Oyuna başlamadan önce oyundaki adlarınızı giriniz");
		System.out.print("1. Oyuncunun Adı : ");
		this.player1 = scan.nextLine();
		System.out.println();
		System.out.print("2. Oyuncunun Adı : ");
		this.player2 = scan.nextLine();

		System.out.println("\nSon olarak oyuna kimin başlayacağını seçicez\n");
		System.out.println(
				"Benim tuttuğum sayıya en çok yaklaşan oyuna ilk önce başlar , 0 ile 11 arasında bir tam sayı tuttum");

		int r = rand.nextInt(10) + 1;
		// System.out.println("Doğru sayı : " + r);
		int ctrl = 0;

		while (ctrl != 1) {
			System.out.print(player1 + " tahmin et : ");
			int t1 = scan.nextInt();
			scan.nextLine();
			System.out.print(player2 + " tahmin et : ");
			int t2 = scan.nextInt();
			scan.nextLine();
			if (Math.abs(t1 - r) > Math.abs(t2 - r)) {
				String tmp = player1;
				setPlayer1(player2);
				setPlayer2(tmp);
				ctrl = 1;
			} else if (Math.abs(t1 - r) == Math.abs(t2 - r)) {
				System.out.println("Eşitlik , tekrar sayı tahmin ediniz ");
				r = rand.nextInt(10) + 1;
				System.out.println("Doğru sayı : " + r);
			} else {
				break;
			}

		}

		System.out.println("\nOyuna " + player1 + " başlayacak");
		System.out.println("Doğru sayı " + r + " idi\n");

		prepareGame(map);
		playGame();

	}

	public void playGame() {
		int sat = -1, süt = -1;
		boolean deger = false;
		String secim = "a";
		print(map);
		System.out.println();

		while (this.hamle <= this.size) {
			// hamle++;
			System.out.println(this.hamle + ". hamle ");

			if (sıra % 2 != 0) {
				System.out.println("Hamle sırası : " + getPlayer1());
				System.out.print("X mi , O mu : ");
				secim = scan.nextLine();
				scan.nextLine();
				secim = secim.toUpperCase();
				while (!(secim.equals("X") || secim.equals("O"))) {
					System.out.println("Geçersiz bir harf girdiniz.");
					System.out.print("X mi , O mu : ");
					secim = scan.nextLine();
					scan.nextLine();
					secim = secim.toUpperCase();
				}
				System.out.print("Satır : ");
				sat = scan.nextInt();
				System.out.print("Sütun : ");
				süt = scan.nextInt();
				scan.nextLine();
				while (sat < 0 || sat >= this.row || süt < 0 || süt >= this.col || map[sat][süt].equals("X")
						|| map[sat][süt].equals("O")) {
					System.out.println("\nGeçersiz kordinatlar");
					System.out.print("Satır : ");
					sat = scan.nextInt();
					System.out.print("Sütun : ");
					süt = scan.nextInt();
					scan.nextLine();
				}
				if (secim.equals("X")) {
					map[sat][süt] = "X";
					deger = skorX(sat, süt,skor1);
				} else {
					map[sat][süt] = "O";
					deger = skorO(sat, süt,skor1);
				}
				System.out.println();
				print(map);

				while (deger == true && ++this.hamle <= this.size) {
					// this.hamle++;
					System.out.println(hamle + ". hamle ");
					System.out.println("Hamle sırası : " + getPlayer1());
					System.out.print("X mi , O mu : ");
					secim = scan.nextLine();
					scan.nextLine();
					secim = secim.toUpperCase();
					System.out.print("Satır : ");
					sat = scan.nextInt();
					System.out.print("Sütun : ");
					süt = scan.nextInt();
					scan.nextLine();
					while (sat < 0 || sat >= this.row || süt < 0 || süt >= this.col || map[sat][süt].equals("X")
							|| map[sat][süt].equals("O")) {
						System.out.println("\nGeçersiz kordinatlar");
						System.out.print("Satır : ");
						sat = scan.nextInt();
						System.out.print("Sütun : ");
						süt = scan.nextInt();
						scan.nextLine();
					}
					if (secim.equals("X")) {
						map[sat][süt] = "X";
						deger = skorX(sat, süt,skor1);
					} else {
						map[sat][süt] = "O";
						deger = skorO(sat, süt,skor1);
					}
					print(map);
				}

			} else {
				if (hamle <= size) {
					System.out.println("Hamle sırası : " + getPlayer2());
					System.out.print("X mi , O mu : ");
					secim = scan.nextLine();
					scan.nextLine();
					secim = secim.toUpperCase();
					System.out.print("Satır : ");
					sat = scan.nextInt();
					System.out.print("Sütun : ");
					süt = scan.nextInt();
					scan.nextLine();
					while (sat < 0 || sat >= this.row || süt < 0 || süt >= this.col || map[sat][süt].equals("X")
							|| map[sat][süt].equals("O")) {
						System.out.println("\nGeçersiz kordinatlar\n");
						System.out.print("Satır : ");
						sat = scan.nextInt();
						System.out.print("Sütun : ");
						süt = scan.nextInt();
						scan.nextLine();
					}
					if (secim.equals("X")) {
						map[sat][süt] = "X";
						deger = skorX(sat, süt,skor2);
					} else {
						map[sat][süt] = "O";
						deger = skorO(sat, süt,skor2);
					}
					print(map);

					while (deger && ++this.hamle <= this.size) {
						// hamle++;
						System.out.println(hamle + ". hamle");
						System.out.println("Hamle sırası : " + getPlayer2());
						System.out.print("X mi , O mu : ");
						secim = scan.nextLine();
						scan.nextLine();
						secim = secim.toUpperCase();
						System.out.print("Satır : ");
						sat = scan.nextInt();
						System.out.print("Sütun : ");
						süt = scan.nextInt();
						scan.nextLine();
						while (sat < 0 || sat >= this.row || süt < 0 || süt >= this.col || map[sat][süt].equals("X")
								|| map[sat][süt].equals("O")) {
							System.out.println("\nGeçersiz kordinatlar");
							System.out.print("Satır : ");
							sat = scan.nextInt();
							System.out.print("Sütun : ");
							süt = scan.nextInt();
							scan.nextLine();
						}
						if (secim.equals("X")) {
							map[sat][süt] = "X";
							deger = skorX(sat, süt,skor2);
						} else {
							map[sat][süt] = "O";
							deger = skorO(sat, süt,skor2);
						}
						print(map);
						System.out.println(player1 + " Skor : " + this.skor1);
						System.out.println(player2 + " Skor : " + this.skor2);
					}

				} else {
					break;
				}
			}
			this.hamle++;
			this.sıra++;
			// print(map);
			System.out.println(player1 + " Skor : " + this.skor1);
			System.out.println(player2 + " Skor : " + this.skor2);
			//System.out.println(deger);
			System.out.println();
		}
		System.out.println(player1 + " Skor : " + this.skor1);
		System.out.println(player2 + " Skor : " + this.skor2 + "\n");
		if (skor1 > skor2) {
			System.out.println("Oyunu kazanan : " + player1);
		} else if (skor2 > skor1) {
			System.out.println("Oyunu kazanan : " + player2);
		} else {
			System.out.println("Oyun berabere");
		}

	}

	public boolean skorO(int r, int c,int skor) {
		int ctrl = 0;
		if (r >= 1 && r <= (row - 2) && map[r - 1][c].equals("X") && map[r + 1][c].equals("X")) {
			skor++;
			ctrl++;
		}
		if (c >= 1 && c <= (col - 2) && map[r][c - 1].equals("X") && map[r][c + 1].equals("X")) {
			skor++;
			ctrl++;
		}
		if (r <= (row - 2) && c >= 1 && r >= 1 && c <= (col - 2) && map[r - 1][c + 1].equals("X")
				&& map[r + 1][c - 1].equals("X")) {
			skor++;
			ctrl++;
		}
		if (r <= (row - 2) && c >= 1 && r >= 1 && c <= (col - 2) && map[r - 1][c - 1].equals("X")
				&& map[r + 1][c + 1].equals("X")) {
			skor++;
			ctrl++;
		}
		if(sıra % 2 != 0) {
			this.skor1 = skor;
		}
		else {
			this.skor2 = skor;
		}
		
		if (ctrl != 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean skorX(int r, int c,int skor) {
		int ctrl = 0;
		if (r >= 2 && map[r - 1][c].equals("O") && map[r - 2][c].equals("X")) {
			skor++;
			ctrl++;
		}
		if (c <= (col - 3) && map[r][c + 1].equals("O") && map[r][c + 2].equals("X")) {
			skor++;
			ctrl++;
		}
		if (r <= (row - 3) && map[r + 1][c].equals("O") && map[r + 2][c].equals("X")) {
			skor++;
			ctrl++;
		}
		if (c >= 2 && map[r][c - 1].equals("O") && map[r][c - 2].equals("X")) {
			skor++;
			ctrl++;
		}
		// 2.köşegen tamam
		if (r >= 2 && c <= (col - 3) && map[r - 1][c + 1].equals("O") && map[r - 2][c + 2].equals("X")) {
			skor++;
			ctrl++;
		}
		// 1.köşegen tamam
		if (r <= (row - 3) && c <= (col - 3) && map[r + 1][c + 1].equals("O") && map[r + 2][c + 2].equals("X")) {
			skor++;
			ctrl++;
		} // tamam
		if (r >= 2 && c >= 2 && map[r - 1][c - 1].equals("O") && map[r - 2][c - 2].equals("X")) {
			skor++;
			ctrl++;
		} // tamam
		if (r <= (row - 3) && c >= 2 && map[r + 1][c - 1].equals("O") && map[r + 2][c - 2].equals("X")) {
			skor++;
			ctrl++;
		}
		
		if(sıra % 2 != 0) {
			this.skor1 = skor;
		}
		else {
			this.skor2 = skor;
		}
		
		if (ctrl != 0) {
			return true;
		} else {
			return false;
		}

	}

	public void print(String[][] arr) {
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void prepareGame(String arr[][]) {
		for (int i = 0; i < this.row; i++) {
			for (int j = 0; j < this.col; j++) {
				arr[i][j] = "*";
			}
		}
	}

	public String[][] getMap() {
		return map;
	}

	public void setMap(String[][] map) {
		this.map = map;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

}
