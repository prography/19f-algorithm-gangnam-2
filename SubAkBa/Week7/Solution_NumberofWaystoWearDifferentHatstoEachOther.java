import java.util.*;

public class Solution_NumberofWaystoWearDifferentHatstoEachOther {
	static int hatstype = 40;

	public static int numberWays(List<List<Integer>> hats) {
		int person = hats.size();
		int[][] dp = new int[hatstype + 1][(int) Math.pow(2, 10)];

		List<Integer>[] adj = new ArrayList[hatstype + 1];

		for (int i = 1; i <= hatstype; ++i) {
			adj[i] = new ArrayList<>();
			Arrays.fill(dp[i], -1);
		}

		for (int i = 0; i < person; ++i) {
			for (int hat : hats.get(i))
				adj[hat].add(i);
		}

		return DFS(adj, dp, (1 << person) - 1, 1, 0);
	}

	public static int DFS(List<Integer>[] adj, int[][] dp, int all_hats, int current_hats, int people) {
		if (people == all_hats)
			return 1;

		if (current_hats > hatstype)
			return 0;

		if (dp[current_hats][people] != -1)
			return dp[current_hats][people];

		int count = DFS(adj, dp, all_hats, current_hats + 1, people);

		for (int hat : adj[current_hats]) {
			if (((people >> hat) & 1) == 1)
				continue;

			count += DFS(adj, dp, all_hats, current_hats + 1, (people | (1 << hat)));
			count %= (1e9 + 7);
		}

		return dp[current_hats][people] = count;
	}

	public static int numberWays(List<List<Integer>> hats) {
		int hlen = hats.size();
		int m = 1 << hlen;
		int[] dp = new int[m];
		List<Integer>[] hatToPerson = new ArrayList[hatstype + 1];

		for (int i = 1; i <= hatstype; i++)
			hatToPerson[i] = new ArrayList<>();

		for (int i = 0; i < hlen; i++) {
			for (int h : hats.get(i))
				hatToPerson[h].add(i);
		}

		dp[0] = 1;
		for (int h = 1; h <= hatstype; h++) {
			if (hatToPerson[h].isEmpty())
				continue;

			int[] tmp = dp.clone();

			for (int p : hatToPerson[h]) {
				int used = 1 << p;

				for (int i = m - 1; i >= used; i--) {
					if ((i & used) != 0)
						tmp[i] = (tmp[i] + dp[i ^ used]) % 1000000007;
				}
			}
			dp = tmp;
		}

		return dp[m - 1];
	}

	public static void main(String[] args) {
		List<List<Integer>> t1 = new ArrayList<>();
		t1.add(new ArrayList<>());
		t1.get(0).add(3);
		t1.get(0).add(4);

		t1.add(new ArrayList<>());
		t1.get(1).add(4);
		t1.get(1).add(5);

		t1.add(new ArrayList<>());
		t1.get(2).add(5);
		System.out.println(numberWays(t1));

		List<List<Integer>> t2 = new ArrayList<>();
		t2.add(new ArrayList<>());
		t2.get(0).add(3);
		t2.get(0).add(5);
		t2.get(0).add(1);

		t2.add(new ArrayList<>());
		t2.get(1).add(3);
		t2.get(1).add(5);

		System.out.println(numberWays(t2));

		List<List<Integer>> t3 = new ArrayList<>();
		t3.add(new ArrayList<>());
		t3.get(0).add(1);
		t3.get(0).add(2);
		t3.get(0).add(3);
		t3.get(0).add(4);

		t3.add(new ArrayList<>());
		t3.get(1).add(1);
		t3.get(1).add(2);
		t3.get(1).add(3);
		t3.get(1).add(4);

		t3.add(new ArrayList<>());
		t3.get(2).add(1);
		t3.get(2).add(2);
		t3.get(2).add(3);
		t3.get(2).add(4);

		t3.add(new ArrayList<>());
		t3.get(3).add(1);
		t3.get(3).add(2);
		t3.get(3).add(3);
		t3.get(3).add(4);

		System.out.println(numberWays(t3));

		List<List<Integer>> t4 = new ArrayList<>();
		t4.add(new ArrayList<>());
		t4.get(0).add(1);
		t4.get(0).add(2);
		t4.get(0).add(3);

		t4.add(new ArrayList<>());
		t4.get(1).add(2);
		t4.get(1).add(3);
		t4.get(1).add(5);
		t4.get(1).add(6);

		t4.add(new ArrayList<>());
		t4.get(2).add(1);
		t4.get(2).add(3);
		t4.get(2).add(7);
		t4.get(2).add(9);

		t4.add(new ArrayList<>());
		t4.get(3).add(1);
		t4.get(3).add(8);
		t4.get(3).add(9);

		t4.add(new ArrayList<>());
		t4.get(4).add(2);
		t4.get(4).add(5);
		t4.get(4).add(7);

		System.out.println(numberWays(t4));
	}
}
