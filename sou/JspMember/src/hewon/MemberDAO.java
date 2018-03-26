package hewon;

//웹상에서 필요로하는 기능을 가진 메서드를 선언,구현된 클래스
//메서드 호출->DB접속->SQL구문실행->DB접속 해제
//두 클래스의 연결->has a 관계 설정
import java.sql.*;//DB
import java.util.*;//Vector,ArrayList

public class MemberDAO {   //DB연결=>DBConnectionMgr->3개의 메서드필요

	//1.연결하고자하는 클래스객체를 선언
	private DBConnectionMgr pool=null;
	
	//2.생성자=>연결하고자하는 클래스객체를 생성하는 구문을 사용=>자연스럽다.
	public MemberDAO() {
		try {
			pool=DBConnectionMgr.getInstance();
			System.out.println("pool=>"+pool);
		}catch(Exception e) {
			System.out.println("DB연결실패!->"+e);//e.toString()
		}
	}//생성자
	
	//3.업무에 따른 어떤 기능을 가진 메서드를 작성?
	 //select * from member where id='nup' and passwd='1234';
	//1)회원로그인을 인증?->매개변수 갯수,자료형,반환형
	
	public boolean loginCheck(String id,String passwd) {
		//1.DB연결
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean check=false;//회원인증 유무
		String sql="";//sql구문을 저장
		
		//2.메서드 목적에 맞는 SQL구문
		try {
			con=pool.getConnection();
			System.out.println("con=>"+con);
			sql="select id,passwd from member where id=? and passwd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);//웹상에서 입력한 id값
			pstmt.setString(2, passwd);
			rs=pstmt.executeQuery();
			check=rs.next();// 찾았으면 check=true or false
		}catch(Exception e) {
			System.out.println("loginCheck() 메서드 실행오류=>"+e);
		}finally {//메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return check;
	}
	
	//2)중복id값을 체크해주는 메서드?->checkId 메서드
	//select * or id from member where id='kkk';
	public boolean checkId(String id) { //IdCheck.jsp에서 호출
		Connection con=null;
		PreparedStatement pstmt=null;//?때문에 사용
		ResultSet rs=null;
		boolean check=false;
		String sql="";
		
		try {
			con=pool.getConnection();
			sql="select  id from member where id=?";
			pstmt=con.prepareStatement(sql);  //->NullPointerException발생주의
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			check=rs.next(); //찾았다면 check=true or 못 찾았으면 check=false
		}catch(Exception  e) {
			System.out.println("checkId() 실행 중 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return check;
	}
	//3)우편번호를 실시간으로 검색?
	//select area1 from zipcode where area3 like '%수유3동%';=>String(자료형)
	//select zipcode,area1 from zipcode where area3 like '%수유3동%';
	//필드명이 한개이상 반환을 받고싶다.->DTO자료형을 반환값으로 설정
	//select * from zipcode where area3 like '%수유3동%'; //where조건식
	//화면에 출력된 레코드가 한개이상=>Vector,ArrayList을 사용해야 한다.
	public Vector<ZipcodeDTO>  zipcodeRead(String area3) {
		
		Connection con=null;
		PreparedStatement pstmt=null;//
		ResultSet rs=null;//select 
		//추가
		Vector<ZipcodeDTO> vecList=new Vector();
		//ArrayList<ZipcodeDTO> vecList=new ArrayList();
		String sql="";
		
		try {
			con=pool.getConnection();
			     //select * from zipcode where area3 like '수유3동%'
			sql="select * from zipcode where area3 like '"+area3+"%'";
			pstmt=con.prepareStatement(sql);  //->NullPointerException발생주의
			rs=pstmt.executeQuery();
			//검색된 레코드들을 필드별로 담아서 vecList에 담는 구문
			//레코드 한개를 담는다면 if(rs.next()){~}
			//레코드 한개 이상을 담는다면 while(rs.next()){~)
			while(rs.next()) {
				//MemberDTO mem=new MemberDTO();
				ZipcodeDTO tempZipcode=new ZipcodeDTO();
				tempZipcode.setZipcode(rs.getString("zipcode"));//tempZipcode.setZipcode("142-890");
				tempZipcode.setArea1(rs.getString("area1"));//area1="서울" =>
				tempZipcode.setArea2(rs.getString("area2"));
				tempZipcode.setArea3(rs.getString("area3"));
				tempZipcode.setArea4(rs.getString("area4"));
				//vector or ArrayList에 담는 구문
				vecList.add(tempZipcode);//대략 13개의 레코드가 저장이 된다.
			}
		}catch(Exception  e) {
			System.out.println("zipcdoeRead() 실행 중 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return vecList;
	}
	
	//4)회원가입 시켜주는 메서드?
	//insert into member values(?,?,?,?,?,?,?,?)->executeUpdate
	//DTO->1.테이블의 필드별로 저장,출력 2.메서드의 매개변수와 반환형으로 사용
	public  boolean   memberInsert(MemberDTO mem) {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		//ResultSet rs=null ->insert이기때문
		boolean check=false;//회원가입 성공유무
		String sql="";//sql구문 저장
		
		try {
			con=pool.getConnection();
			//트랜잭션처리 시작구문
			con.setAutoCommit(false);
			//-----입력을 받을때는 DTO->각각의 필드에 값을 저장->Getter Method
			sql="insert into member values(?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mem.getMem_id());
			pstmt.setString(2, mem.getMem_passwd());
			pstmt.setString(3, mem.getMem_name());
			pstmt.setString(4, mem.getMem_email());
			pstmt.setString(5, mem.getMem_phone());
			pstmt.setString(6, mem.getMem_zipcode());
			pstmt.setString(7, mem.getMem_address());
			pstmt.setString(8, mem.getMem_job());
			int insert=pstmt.executeUpdate();//1->성공, 0->실패->메모리상 저장
			//메모리상에 저장된 값이=>실제 테이블에 저장(동기화)
			con.commit();
			System.out.println("회원가입 성공유무(insert)=>"+insert);
			if(insert > 0) {
				check=true;
			}
		}catch(Exception e) {
			System.out.println("memberInsert() 실행 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return check;
	}
	
	//5)수정하기원하는 개인정보를 화면에 출력
	//select * from member where id=?; =>MemberDTO
	//select * from member ->레코드 한개이상->Vector(X)->ArrayList
	public MemberDTO getMember(String mem_id) {
		
		Connection con=null;
		PreparedStatement pstmt=null;//
		ResultSet rs=null;//select 
		//추가
		MemberDTO mem=null;//mem_id값에 해당하는 레코드를 담을 객체 필요
		//ArrayList<MemberDTO> vecList=new ArrayList();
		String sql="";
		
		try {
			con=pool.getConnection();
			sql="select * from member where id=?";
			pstmt=con.prepareStatement(sql);  //->NullPointerException발생주의
			pstmt.setString(1, mem_id);
			rs=pstmt.executeQuery();
			//레코드 한개를 담는다면 if(rs.next()){~}
			//레코드 한개 이상을 담는다면 while(rs.next()){~)
			if(rs.next()) { //화면에 보여줄 레코드가 한개 존재한다면
				//MemberDTO mem=new MemberDTO();
				mem=new MemberDTO();
				mem.setMem_id(rs.getString("id"));// nup~
				mem.setMem_passwd(rs.getString("passwd"));
				mem.setMem_name(rs.getString("name"));
				mem.setMem_phone(rs.getString("phone"));
				mem.setMem_zipcode(rs.getString("zipcode"));
				mem.setMem_address(rs.getString("address"));
				mem.setMem_email(rs.getString("e_mail"));
				mem.setMem_job(rs.getString("job"));
			}
		}catch(Exception  e) {
			System.out.println("getMember() 실행 중 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return mem;
	}
	
	//6)회원수정을 시켜주는 메서드=>회원가입과 소스코드 동일
	public boolean memberUpdate(MemberDTO mem) {
		
		System.out.println("mem.getMem_id()=>"+mem.getMem_id());
		//------------------------------------------------------------------------
		Connection con=null;
		PreparedStatement pstmt=null;
		//ResultSet rs=null ->insert이기때문
		boolean check=false;//회원수정 성공유무
		String sql="";//sql구문 저장
		
		try {
			con=pool.getConnection();
			//트랜잭션처리 시작구문
			con.setAutoCommit(false);//수정
			
			sql="update member set passwd=?, name=?, e_mail=?, phone=?,"
					+ " zipcode=?, address=? ,job=?  where id=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, mem.getMem_passwd());
			pstmt.setString(2, mem.getMem_name());
			pstmt.setString(3, mem.getMem_email());
			pstmt.setString(4, mem.getMem_phone());
			pstmt.setString(5, mem.getMem_zipcode());
			pstmt.setString(6, mem.getMem_address());
			pstmt.setString(7, mem.getMem_job());
			pstmt.setString(8, mem.getMem_id());
			
			int update=pstmt.executeUpdate();//1->성공, 0->실패->메모리상 저장
			//메모리상에 저장된 값이=>실제 테이블에 저장(동기화)
			con.commit();
			System.out.println("회원수정 성공유무(update)=>"+update);
			if(update == 1) {
				check=true; //수정성공
			}
		}catch(Exception e) {
			System.out.println("memberUpdate() 실행 에러유발=>"+e);
		}finally {
			pool.freeConnection(con, pstmt);
		}
		return check; //true or false
	}
	
	//7)회원탈퇴를 시켜주는 메서드=>delete
	//1.id->삭제시킬 id 필요  2.passwd->웹상에서 입력한 암호==DB상의 암호
	public  int deleteMember(String id,String passwd) {
		
		       //1.DB연결
				Connection con=null;
				PreparedStatement pstmt=null;
				ResultSet rs=null;//select passwd from member where id='nup'
				int x=-1;//암호를 체크하기위한 변수(성공->1, 실패->0)
				String dbpasswd="";//id값에 해당하는 DB의 암호값저장
				String sql="";//sql구문을 저장
				
				//2.메서드 목적에 맞는 SQL구문
				try {
					con=pool.getConnection();
					con.setAutoCommit(false);//트랜잭션 처리 시작
					sql="select passwd from member where id=?";
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, id);//웹상에서 입력한 id값
					rs=pstmt.executeQuery();
					//id값에 대한 DB에 저장된 암호를 찾았다면
					if(rs.next()) {
						dbpasswd=rs.getString("passwd");
						System.out.println("dbpasswd=>"+dbpasswd);
						//db상의 암호==웹상에 입력한 암호
						if(dbpasswd.equals(passwd)) {
						   	pstmt=con.prepareStatement("delete from member where id=?");
						   	pstmt.setString(1, id);
						   	int delete=pstmt.executeUpdate();
						   	con.commit();
						   	System.out.println("delete 회원탈퇴 성공유무=>"+delete);
						   	x=1;
						}else {
							x=0;//암호가 실패해서 삭제가 안되는 경우
						}
					}//else { x=-1 } 데이터를 못 찾은 경우
				}catch(Exception e) {
					System.out.println("deleteMember() 메서드 실행오류=>"+e);
				}finally {//메모리해제
					pool.freeConnection(con, pstmt, rs);
				}
				return x;
	}
	//8)회원리스트를 보여주는 메서드->관리자=>select * from member 
	//public int getMemberCount(){}->select count(*) from member
	//public ArrayList<MemberDTO> getMemberList(){} =>페이징 처리
}







