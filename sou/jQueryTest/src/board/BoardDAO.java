package board;

//DB에 접속해서 웹상에서 호출할 메서드를 선언->DAO
import java.sql.*;//DB
import java.util.*;//ArrayList,List

public class BoardDAO {

	private DBConnectionMgr pool = null;// 1.연결할 클래스 객체선언
	// 2.생성자를 통해서 자연스럽게 연결=>의존성때문

	public BoardDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
			System.out.println("pool=>" + pool);
		} catch (Exception e) {
			System.out.println("Error접속 오류=>" + e);
		}
	}
	// 3.페이징 처리(페이지별로 화면에 보여주는 레코드수를 조절)
	// 1)전체 레코드수를 구해와야 한다.=>select count(*) from board;

	public int getArticleCount() {

		// 1.DB연결
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			System.out.println("con=>" + con);
			String sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 레코드갯수가 한개이상이라면
				x = rs.getInt(1);// 필드명이 없다->select ~from사이의 필드순서로 지정
				System.out.println("총레코드수(x)=>" + x);
			}
		} catch (Exception e) {
			System.out.println("getArticleCount() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}

	// 2)글목록보기에 대한 메서드구현(범위)
	// select * from board order by ref desc,re_step asc limit ?,?
	// 1.레코드의 시작번호 2.웹상에 보여줄 레코드갯수
	public List<BoardDTO> getArticles(int start, int end) { // 1,10,11,10,20,10

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select
		List<BoardDTO> articleList = null;// ArrayList articleList=null; //10개를 담을 객체선언

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			// 가장 최신의글을 내림차순으로 정렬하되 re_step값은 오름차순으로 정렬
			// (몇번째 레코드를 기준으로 몇개의 레코드 범위)
			String sql = "select * from board order by ref desc,re_step asc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql이 내부적으로 레코드순번을 0부터 시작
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// 추가->do~while->기존의 데이터는 그대로 유지=>신규글이 추가=>같이출력
			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				// articleList = new List();
				// articleList=new 자식클래명();
				articleList = new ArrayList(end);// 초기생성값을 미리 부여end(크기 지정)
				do { // 무조건 기존의 데이터 추가+새로추가
					BoardDTO article = new BoardDTO();// MemberDTO mem=new MemberDTO
					article.setNum(rs.getInt("num"));// 번호
					article.setWriter(rs.getString("writer"));// 작성자
					article.setEmail(rs.getString("email"));// 메일
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));// 오늘날짜->코딩->now()
					// 추가
					article.setReadcount(rs.getInt("readcount"));// 조회수
					article.setRef(rs.getInt("ref"));// 그룹번호->답변다는 위치지정
					article.setRe_step(rs.getInt("re_step"));// 답변글 순서
					article.setRe_level(rs.getInt("re_level"));// 답변의 깊이(depth)
					article.setContent(rs.getString("content"));// 글내용
					article.setIp(rs.getString("ip"));// 글쓴이의 ip
					// 추가
					articleList.add(article);// 최종 웹상에서 필드별로 출력(Getter)
				} while (rs.next());
			} // if(rs.next()){}
		} catch (Exception e) {
			System.out.println("getArticles() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	// 3.insertArticle()->글쓰기 및 답변글 쓰기
	// insert into~ ->내부에서 성공유무 체크,->list.jsp로 자동으로 이동
	public void insertArticle(BoardDTO article) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select ->select max(num) from board->+1(num구함)

		// -------------신규글인지 답변글인지 구분----------------------
		int num = article.getNum();// 0->신규글 0아닌 경우->답변글
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();
		// 추가
		int number = 0;// 테이블에 입력할 게시물번호를 저장할 변수
		System.out.println("insertArticle메서드 내부의 num=>" + num);
		System.out.println("ref=" + ref + ",re_step=>" + re_step + ",re_level=>" + re_level);
		String sql = "";// sql구문 저장

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			sql = "select max(num) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 레코드갯수가 한개이상이라면
				number = rs.getInt(1) + 1;
			} else {
				number = 1;// 게시물 번호를 1로 설정
			}
			System.out.println("insertArticle의 number=>" + number);
			// 만약에 답변글이라면 ->num=0 신규글이기 때문에
			if (num != 0) {
				// 같은 그룹번호를 가지고 있으면서 내가 들어가 위치에 있는 게시물의
				// re_step값을 하나 증가->빈 step->답변글
				sql = "update board set re_step=re_step+1 where ref=? and re_step > ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				int update = pstmt.executeUpdate();
				System.out.println("댓글 수정 유무(update)=>" + update);//1
				re_step = re_step + 1;
				re_level = re_level + 1;
			} else { // 신규글이라면
				ref = number;// 1,2,3,4,5,6,
				re_step = 0;
				re_level = 0;
			}
			// 12개의 필드->num,reg_date,readcount(생략)->sysdate,now()<-?
			// num(자동입력),readcount(디폴트 기능때문에)->0이 들어가게 설정
			sql = "insert into board(writer,email,subject,passwd,reg_date,";
			sql += " ref,re_step,re_level,content,ip) values(?,?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPasswd());// 암호
			pstmt.setTimestamp(5, article.getReg_date());// 작성 날짜
			// ---------------------------------------
			pstmt.setInt(6, ref);// ref ->article.getRe_ref() X
			pstmt.setInt(7, re_step);// re_step
			pstmt.setInt(8, re_level);// re_level
			// ------------------------------------------
			pstmt.setString(9, article.getContent());// 글내용
			pstmt.setString(10, article.getIp());// 접속자의 ip주소
            //실행
			int insert=pstmt.executeUpdate();
			System.out.println("게시판의 글쓰기 성공유무(insert)=>"+insert);
		} catch (Exception e) {
			System.out.println("insertArticle() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
	}

	// 4.글 상세보기->조회수도 증가
	//update board set readcount=readcount+1 where num=?
	//select * from board where num=?
	public BoardDTO getArticle(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select * from board where num=?
		BoardDTO article = null;// 게시물번호에 해당하는 레코드 한개
		
		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			
			String sql = "update board set readcount=readcount+1 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int update=pstmt.executeUpdate();
			System.out.println("조회수 증가유무(update)=>"+update);//1
			//pstmt.close();
			sql="select * from board where  num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				   article=makeArticleFromResult(rs);
				    /*article = new BoardDTO();// MemberDTO mem=new MemberDTO
					article.setNum(rs.getInt("num"));// 번호
					article.setWriter(rs.getString("writer"));// 작성자
					article.setEmail(rs.getString("email"));// 메일
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));
					article.setReg_date(rs.getTimestamp("reg_date"));// 오늘날짜->코딩->now()
					article.setReadcount(rs.getInt("readcount"));// 조회수
					article.setRef(rs.getInt("ref"));// 그룹번호->답변다는 위치지정
					article.setRe_step(rs.getInt("re_step"));// 답변글 순서
					article.setRe_level(rs.getInt("re_level"));// 답변의 깊이(depth)
					article.setContent(rs.getString("content"));// 글내용
					article.setIp(rs.getString("ip"));// 글쓴이의 ip
*/			} // if(rs.next()){}
		} catch (Exception e) {
			System.out.println("getArticle() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}
    //----중복된 레코드한개를 담을 수 있는 메서드를 따로 처리해주는 메서드작성-------------------------------------------------------------
	//이 메서드는 웹상에서 호출되는 메서드X=>이 클래스내부에서만 사용(private)
	//private MemberDTO makeMemberFromResult(ResultSet rs){}
	private BoardDTO  makeArticleFromResult(ResultSet rs) throws Exception {
		BoardDTO article = new BoardDTO();// MemberDTO mem=new MemberDTO
		article.setNum(rs.getInt("num"));// 번호
		article.setWriter(rs.getString("writer"));// 작성자
		article.setEmail(rs.getString("email"));// 메일
		article.setSubject(rs.getString("subject"));
		article.setPasswd(rs.getString("passwd"));
		article.setReg_date(rs.getTimestamp("reg_date"));// 오늘날짜->코딩->now()
		article.setReadcount(rs.getInt("readcount"));// 조회수
		article.setRef(rs.getInt("ref"));// 그룹번호->답변다는 위치지정
		article.setRe_step(rs.getInt("re_step"));// 답변글 순서
		article.setRe_level(rs.getInt("re_level"));// 답변의 깊이(depth)
		article.setContent(rs.getString("content"));// 글내용
		article.setIp(rs.getString("ip"));// 글쓴이의 ip
		return article;
	}
	
	//-----------------------------------------------------------------------
	
	// 5.글수정을 하기위한 게시물번호에 해당하는 레코드를 찾는 메서드
	// ->updateGetArticle(int num)->select * from board where num=?
	public BoardDTO updateGetArticle(int num) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select * from board where num=?
		BoardDTO article = null;// 게시물번호에 해당하는 레코드 한개
		
		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			String sql="select * from board where  num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				article=makeArticleFromResult(rs);
			} // if(rs.next()){}
		} catch (Exception e) {
			System.out.println("getArticle() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}

	// 6.글수정시켜주는 메서드->updateArticle(BoardDTO article){}
	//   =>글쓰기(insertArticle()와 기능이 동일(데이터를 저장)->인증->수정
    public int updateArticle(BoardDTO article) { //(int passwd)
    	
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// passwd를 조회할 select필요
        String dbpasswd=null;//db에서 찾은 암호를 저장
		String sql = "";// sql구문 저장
		int x= -1;//게시물의 수정 성공 유무

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			sql = "select passwd from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,article.getNum()); //수정하고자하는 데이터에서 암호분리
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 게시물번호에 해당하는 암호를 찾았다면
				dbpasswd=rs.getString("passwd");
				System.out.println("dbpasswd=>"+dbpasswd);
				//db상의 암호=웹상에 입력한 암호가 맞다면
				if(dbpasswd.equals(article.getPasswd())) {
	
			sql = "update board set writer=?, email=?,subject=?,passwd=?,";
			sql += " content=?   where num=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPasswd());// 암호
			pstmt.setString(5, article.getContent());// 글내용
			pstmt.setInt(6, article.getNum());// 게시물 번호
            //실행
			int update=pstmt.executeUpdate();
			System.out.println("게시판의 글수정 성공유무(update)=>"+update);
			x=1;
			}else {
				x=0;//수정실패->암호가 틀린경우
			}
		  }//if(rs.next())=>암호가 존재한다면
		} catch (Exception e) {
			System.out.println("updateArticle() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;//1->수정성공, 0->수정실패
    }
    //글삭제 시켜주는 메서드=>회원탈퇴(삭제)=>암호를 물어본다.(deletePro.jsp)
    //select passwd from board where num=? ->직접
    //delete from board where num=?
    public int deleteArticle(int num,String passwd) { //String pageNum,HttpRequestResponse res
    	//response.sendRedirect(list.jsp?pageNum=pageNum)
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// passwd를 조회할 select필요
        String dbpasswd=null;//db에서 찾은 암호를 저장
		String sql = "";// sql구문 저장
		int x= -1;//게시물의 삭제 성공 유무

		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			sql = "select passwd from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,num); //삭제하고자하는 데이터에서 암호분리
			rs = pstmt.executeQuery();
			// 추가
			if (rs.next()) { // 게시물번호에 해당하는 암호를 찾았다면
				dbpasswd=rs.getString("passwd");
				System.out.println("dbpasswd=>"+dbpasswd);
				//db상의 암호=웹상에 입력한 암호가 맞다면
				if(dbpasswd.equals(passwd)) {
	
			sql = "delete from board where num=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);// 게시물 번호
            //실행
			int delete=pstmt.executeUpdate();
			System.out.println("게시판의 글삭제 성공유무(delete)=>"+delete);
			    x=1; //삭제성공
			}else {
				x=0;//삭제실패->암호가 틀린경우
			}
		  }//if(rs.next())=>암호가 존재한다면
		} catch (Exception e) {
			System.out.println("deleteArticle() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return x;//1->삭제성공, 0->삭제실패
    }
    //실시간으로 웹상에서 요청할 게시판의 작성자를 검색
    public ArrayList<String> getArticleWriter(String name){
    	
    	Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select * from board where num=?
		ArrayList<String> nameList=new ArrayList();
		
		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			String sql="select writer from board where  writer like '%"+name+"%'";
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				String writer=rs.getString("writer");
				nameList.add(writer);
			} 
		} catch (Exception e) {
			System.out.println("getArticleWriter() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return nameList;
    }
}











