package co.micol.lms.board;

import java.util.ArrayList;
import java.util.List;

import co.micol.lms.db.MemberDao;
import co.micol.lms.db.MemberVo;

public class App {

	public static void main(String[] args) {
		MemberVo vo = new MemberVo(); //vo객체 생성
		MemberDao dao = new MemberDao(); //dao 객체생성
		vo.setId("park");
		vo = dao.select(vo);
		vo.toString();
		
		System.out.println("=========================================");
		List<MemberVo> list = new ArrayList<MemberVo>();
		list =  dao.selectAllMember(); //형변환 필요.
		for(MemberVo a: list) {	//forEach 구문
			a.toString();
		}
		
//		TestMath testMath = new TestMath();
//		testMath.setN(10);
//		testMath.setM(20);
//		System.out.println("합 " + testMath.sum());
//		System.out.println("차 " + testMath.sub());
	}
	
}
