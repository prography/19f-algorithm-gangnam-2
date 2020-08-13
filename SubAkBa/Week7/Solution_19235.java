import java.util.*;
import java.io.*;

public class Solution_19235 {
	final static int size = 10;

	public static int GetBlockCount(int[][] map) {
		int count = 0;

		for (int x = 0; x < 4; ++x) {
			for (int y = size - 1; y >= 6; --y) {
				if (map[x][y] == 1)
					++count;
			}
		}

		for (int y = 0; y < 4; ++y) {
			for (int x = size - 1; x >= 6; --x) {
				if (map[x][y] == 1)
					++count;
			}
		}

		return count;
	}

	public static void GreenDownBlock(int[][] map, boolean exceed) {
		if (exceed) {
			for (int x = size - 1; x > 4; --x) {
				for (int y = 0; y < 4; ++y)
					map[x][y] = map[x - 1][y];
			}

			for (int y = 0; y < 4; ++y)
				map[4][y] = 0;
		} else {
			Queue<Integer> queue = new LinkedList<>();

			for (int y = 0; y < 4; ++y) {
				for (int x = size - 1; x >= 0; --x) {
					if (map[x][y] == 1)
						queue.offer(1);
				}

				int idx = size;
				while (!queue.isEmpty())
					map[--idx][y] = queue.poll();

				for (int x = idx - 1; x >= 4; --x)
					map[x][y] = 0;
			}
		}
	}

	public static void BlueDownBlock(int[][] map, boolean exceed) {
		if (exceed) {
			for (int y = size - 1; y > 4; --y) {
				for (int x = 0; x < 4; ++x)
					map[x][y] = map[x][y - 1];
			}

			for (int x = 0; x < 4; ++x)
				map[x][4] = 0;
		} else {
			Queue<Integer> queue = new LinkedList<>();

			for (int x = 0; x < 4; ++x) {
				for (int y = size - 1; y >= 0; --y) {
					if (map[x][y] == 1)
						queue.offer(1);
				}

				int idx = size;
				while (!queue.isEmpty())
					map[x][--idx] = queue.poll();

				for (int y = idx - 1; y >= 4; --y)
					map[x][y] = 0;
			}
		}
	}

	public static boolean isGreenExceed(int[][] map) {
		for (int x = 4; x < 6; ++x) {
			for (int y = 0; y < 4; ++y) {
				if (map[x][y] == 1)
					return true;
			}
		}

		return false;
	}

	public static boolean isBlueExceed(int[][] map) {
		for (int y = 4; y < 6; ++y) {
			for (int x = 0; x < 4; ++x) {
				if (map[x][y] == 1)
					return true;
			}
		}

		return false;
	}

	public static void MoveBlock1x1(int[][] map, int x, int y) {
		int tx = x;

		while (tx + 1 < size && map[tx + 1][y] == 0)
			++tx;

		map[tx][y] = 1;

		int ty = y;

		while (ty + 1 < size && map[x][ty + 1] == 0)
			++ty;

		map[x][ty] = 1;
	}

	public static void MoveBlock2x1(int[][] map, int x, int y) {
		int tx = x;

		while (tx + 1 < size && map[tx + 1][y] == 0)
			++tx;

		map[tx][y] = map[tx - 1][y] = 1;

		int ty = y;

		while (ty + 1 < size && map[x][ty + 1] == 0 && map[x + 1][ty + 1] == 0)
			++ty;

		map[x][ty] = map[x + 1][ty] = 1;
	}

	public static void MoveBlock1x2(int[][] map, int x, int y) {
		int tx = x;

		while (tx + 1 < size && map[tx + 1][y] == 0 && map[tx + 1][y + 1] == 0)
			++tx;

		map[tx][y] = map[tx][y + 1] = 1;

		int ty = y;

		while (ty + 1 < size && map[x][ty + 1] == 0)
			++ty;

		map[x][ty] = map[x][ty - 1] = 1;
	}

	public static int GreenCheck(int[][] map) {
		int score = 0;
		boolean isPossible = true;

		while (isPossible) {
			isPossible = false;

			for (int x = 6; x < size; ++x) {

				for (int y = 0; y < 4; ++y) {
					if (map[x][y] == 0)
						break;

					if (y == 3) {
						for (int ty = 0; ty < 4; ++ty)
							map[x][ty] = 0;

						isPossible = true;
						++score;
					}
				}
			}

			if (isPossible)
				GreenDownBlock(map, false);
		}

		while (isGreenExceed(map))
			GreenDownBlock(map, true);

		return score;
	}

	public static int BlueCheck(int[][] map) {
		int score = 0;
		boolean isPossible = true;

		while (isPossible) {
			isPossible = false;

			for (int y = 6; y < size; ++y) {

				for (int x = 0; x < 4; ++x) {
					if (map[x][y] == 0)
						break;

					if (x == 3) {
						for (int tx = 0; tx < 4; ++tx)
							map[tx][y] = 0;

						isPossible = true;
						++score;
					}
				}
			}

			if (isPossible)
				BlueDownBlock(map, false);
		}

		while (isBlueExceed(map))
			BlueDownBlock(map, true);

		return score;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[size][size];

		int score = 0;
		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			switch (t) {
			case 1:
				MoveBlock1x1(map, x, y);
				break;
			case 2:
				MoveBlock1x2(map, x, y);
				break;
			case 3:
				MoveBlock2x1(map, x, y);
				break;
			}

			score += GreenCheck(map);
			score += BlueCheck(map);
			for (int j = 0; j < size; ++j)
				System.out.println(Arrays.toString(map[j]));
			System.out.println();
		}

		bw.write(score + "\n" + GetBlockCount(map) + "");
		bw.flush();
		bw.close();
		br.close();
	}
}
