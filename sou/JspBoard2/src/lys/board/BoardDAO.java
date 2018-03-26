package lys.board;

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

	//(1)게시판의 레코드수를 검색어에 따른 메서드 작성(검색분야,검색어)
	public int getArticleSearchCount(String search,String searchtext) {
		// 1.DB연결
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int x = 0;
                 //추가
				String sql="";//저장시킬 SQL구문
				// 2.메서드 목적에 맞는 SQL구문
				try {
					con = pool.getConnection();
					System.out.println("con=>" + con);
					//---검색어를 입력하지 않은 경우(검색분야 선택)X------
			    if(search==null || search=="") {
					sql = "select count(*) from board";
			    }else { //검색분야(제목,작성자,제목+본문)->검색했다면
			    	if(search.equals("subject_content")) {
			    		sql="select count(*) from board  where subject like '%"
			    	            +searchtext+"%' or content like  '%"+searchtext+"%' ";
			    				
			    	}else { //제목 or 작성자->매개변수를 이용해서 하나의 sql통합
			    		sql="select count(*) from board  where "+search+" like '%"+searchtext+"%' ";
			    	   //sql="select count(*) from board  where writer like '%"+searchtext+"%' ";
			    	  // sql="select count(*) from board  where subject like '%"+searchtext+"%' ";
			    	}
			    }
			        System.out.println("getArticleSearchCount 검색어 sql=>"+sql);
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					// 추가
					if (rs.next()) { // 레코드갯수가 한개이상이라면
						x = rs.getInt(1);// 필드명이 없다->select ~from사이의 필드순서로 지정
						System.out.println("총레코드수(x)=>" + x);
					}
				} catch (Exception e) {
					System.out.println("getArticleSearchCount() 메서드 실행오류=>" + e);
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
	//(2)검색어에 따른 레코드의 범위 지정에 대한 메서드 추가
	public List<BoardDTO> getBoardArticles(int start, int end,
			                                          String search,String searchtext) { // 1,10,11,10,20,10

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;// select
		List<BoardDTO> articleList = null;// ArrayList articleList=null; //10개를 담을 객체선언
        //추가
		String sql="";
		// 2.메서드 목적에 맞는 SQL구문
		try {
			con = pool.getConnection();
			//--------------------------------------------------------------------
			if(search==null || search=="") {
			  sql = "select * from board order by ref desc,re_step asc limit ?,?";
			}else {//제목,작성자->검색범위에 있는것만 가장 최신의 글로 정렬~
				if(search.equals("subject_content")) {
		    		sql="select * from board  where subject like '%"
		    	            +searchtext+"%' or content like  '%"+searchtext
		    	            +"%'  order by ref desc,re_step asc limit ?,?";
		    				
		    	}else { //제목 or 작성자->매개변수를 이용해서 하나의 sql통합
		    		sql="select * from board  where "+search+" like '%"+searchtext
		    				+"%'  order by ref desc,re_step asc limit ?,?";
		    	}
			}
			System.out.println("getBoardArticles()의 sql=>"+sql);
			//----------------------------------------------------------------------
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql이 내부적으로 레코드순번을 0부터 시작
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			// 추가->do~while->기존의 데이터는 그대로 유지=>신규글이 추가=>같이출력
			if (rs.next()) { // 레코드갯수가 한개라도 존재한다면
				articleList = new ArrayList(end);//end갯수 만큼 데이터를 담기위해서
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
			System.out.println("getBoardArticles() 메서드 실행오류=>" + e);
		} finally {// 메모리해제
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}
 
	//(3)페이징 처리를 재조정해주는 메서드 작성(ListAction클래스 너무 복잡)
	//1.화면에 보여주는 페이지번호   2.화면에 출력할 레코드갯수
	public Hashtable pageList(String pageNum,int count) {
		
		//페이징 처리결과를 저장할 Hashtable 또는 HashMap객체를 선언
		Hashtable<String,Integer> pgList=new Hashtable<String,Integer>();
		//-----------ListAction부분의 페이징처리 계산부분을 복사(hashtable에 저장)---------------
		int pageSize=10;//int numPerPage->페이지당 보여주는 게시물수
	    int blockSize=10;//int pagePerBlock->블럭당 보여주는 페이지수
	    
	   //게시판을 맨 처음 실행시킬 경우 초기화 설정부분->1페이지(pageNum)
	   if(pageNum==null){//맨 처음에는 선택한 적이 없기때문에 null
		   pageNum="1";//1
	   }
	   //1.nowPage=>currentPage
	   int currentPage=Integer.parseInt(pageNum);//"1"->1	   
	   int startRow=(currentPage-1)*pageSize+1;
	   int endRow=currentPage*pageSize;
	   int number=0;//4.beginPerPage->페이지별로 맨 처음에 나오는 시작 게시물번호
	   number=count-(currentPage-1)*pageSize;
	   
	   System.out.println("현재 레코드수(count)=>"+count+",number=>"+number);
	   
	   //2.총페이지수,시작,종료페이지 계산
	     int pageCount=count/pageSize+(count%pageSize==0?0:1);
	      //2.시작페이지,끝페이지
	      int startPage=0;
	      //10의 배수인지 아닌지(경계선)
	      if(currentPage%blockSize!=0){//1~9,11~19,21~22
	           startPage=currentPage/blockSize*blockSize+1;
	      }else{ //10,20,30,40
	                            //((10/10)-1)*10+1=1
	           startPage=((currentPage/blockSize)-1)*blockSize+1;
	      }
	       int endPage=startPage+blockSize-1;//1+10-1=10
	       System.out.println("pgList메서드의 startPage="+startPage+",endPage="+endPage);
	       //블럭별로 구분해서 링크걸어서 출력
	       if(endPage > pageCount) 
	    	   endPage=pageCount;  //마지막페이지=총페이지수
	      //ListAction(pageNum,count)=>pgList()처리결과 ->ListAction->list.jsp
	       pgList.put("pageSize", pageSize);//<->pgList.get("pageSize")
	       pgList.put("blockSize", blockSize);
	       pgList.put("currentPage", currentPage);
	       pgList.put("startRow", startRow);
	       pgList.put("endRow", endRow);
	       pgList.put("count", count);
	       pgList.put("number", number);
	       pgList.put("startPage", startPage);
	       pgList.put("endPage", endPage);
	       pgList.put("pageCount", pageCount);
	       
		//------------------------
		return pgList; //ListAction-->list.jsp
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
}











