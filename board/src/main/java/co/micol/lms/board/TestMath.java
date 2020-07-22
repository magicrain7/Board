package co.micol.lms.board;

public class TestMath {
	private int n;
	private int m;
	
	public TestMath() { //default 생성자
						//construct overloading ??
		
	}
	
	public TestMath(int n , int m) { //class명과 같은것을 생성자고 한다.
		this.n = n;
		this.m = m;
	}
	
	//[범위 지시자] 리턴 type(void, ...) 메소드명() {}
	public int sum() {
		return n + m;
	}
	
	public int sub() {
		int sub;
		if( n > m ) {
			sub= n - m;
		} else {
			sub = m - n;
		}
		return sub;
	}
	
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public int getM() {
		return m;
	}
	public void setM(int m) {
		this.m = m;
	}
	
	
}
