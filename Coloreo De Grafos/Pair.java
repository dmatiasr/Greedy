/*
Clase Pair generic
*/

public class Pair<L,R> {
	private L left;
	private R right;
	
	public Pair(){

	}
	public Pair(L left, R right){
		this.left=left;
		this.right=right;
	}
	public void setPair(L left, R right){
		this.left=left;
		this.right=right;		
	}
	public void setLeft(L left){
		this.left=left;
	}
	public void setRight(R right){
		this.right=right;
	}
	public L getLeft (){
		return left;
	}
	public R getRight(){
		return right;
	}
	public String toString(){
		return "("+left+","+right+")";
	}
}