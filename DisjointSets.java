/*
Class DisjointSet


*/

public class DisjointSets{
	private int[] s;

	public DisjointSets(int numElems){
		s = new int[numElems];
		for (int i=0; i<s.length;i++ ){
			s[i]=-1;
		}
	}
	public void union(int root1,int root2){
		assertIsRoot (root1);
		assertIsRoot (root2);
		if (root1 == root2){
			throw new IllegalArgumentException ();
		}
		if (s[root1] < s[root2]){
			s[root1] = root2;
		}
		else{
			if (s[root1]==s[root2]){
				s[root1]-- ;
			}
			s[root2]=root1;
		}
	}

	public int find(int x){
		assertIsItem(x);
		if (s[x] < 0 ){
			return x;
		}
		else{
			return s[x]= find(s[x]);
		}
	}

	private void assertIsRoot (int root){
		assertIsItem (root);
		if (s[root]>= 0){
			throw new IllegalArgumentException ();
		}
	}
	private void assertIsItem (int x) {
		if (x < 0 || x >= s.length){
			throw new IllegalArgumentException ();
		}
	}

}