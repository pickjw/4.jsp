//Mybatis형태로 변경
package lee;

import java.util.*;//List->글목록보기
//스프링 전용 예외처리 클래스를 사용하기위해서 import->SQLException
import org.springframework.dao.DataAccessException;

//게시판에서 자주 사용이 되는 공통의 추상 메서드 선언->작업
public interface BoardDAO{
	
	//1.글목록보기
	public List list() throws DataAccessException;
	
	//2.최대값 구하기
	public int getNewNum() throws DataAccessException;
	
	//3.글쓰기
	public void write(BoardCommand data) throws DataAccessException;
	
	//4-1)글조회수 증가하기->update
	public void updateReadcnt(String num) throws DataAccessException;
	
	//4-2)글상세보기
	public BoardCommand retrieve(String num) throws DataAccessException;
	
	//5.글수정하기->update
	public void update(BoardCommand data) throws DataAccessException;
	
	//6.글삭제하기
	public void delete(String num)  throws DataAccessException;
	
	//7.글검색하기(검색분야,검색어)
	//(String searchName,String searchValue)->1.HashMap를 이용 2.DTO처럼
	public List search(BoardCommand data) throws DataAccessException;
	
}




/*  before
package lee;

import java.sql.*;//Connection,PrepardStatement~
import java.util.*;//ArrayList,List~(레코드 여러개 저장)

//----추가(JNDI 방식)-----------------------------------------
import javax.sql.*;//DataSource객체->getConnection()
import javax.naming.*;//Context(인터페이스),InitialContext객체
                                    //lookup(java:comp/env/찾고자하는 이름(JNDI명))
                                    //ex)~lookup("java:comp/env/jdbc/orcl")
//---------------------------------------------------------------
public class BoardDAO{
       DataSource ds;     //DBConnectionMgr pool=null;
       
   //생성자->DataSource ds 얻어오는 구문->InitialContext와 JNDI명 부여
   public BoardDAO(){
		try {
			//pool=DBConnectionMgr.getInstance();
			//InitialContext ctx=new InitialContext();
			Context ctx=new InitialContext();
			//lookup(java:comp/env/찾고자하는 이름(JNDI명))
			ds=(DataSource)ctx.lookup("java:comp/env/jdbc/orcl");
			System.out.println("ds=>"+ds);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	 //public List list(){}
	public ArrayList  list(){  // 글목록보기(레코드 한개이상)
		
		ArrayList list = new ArrayList();//여러개의 레코드를 담을 객체선언
		
		try{
			String sql = "SELECT * FROM springboard ORDER BY num desc";
			Connection con = ds.getConnection();
			//Connection con = pool.getConnection();
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				BoardDTO data = new BoardDTO();
				data.setNum( rs.getInt( "num" ) );
				data.setAuthor(rs.getString( "author" ));
				data.setTitle(rs.getString( "title"));
				data.setContent(rs.getString( "content" ));
				data.setDate(rs.getString( "writeday" ));//작성날짜->String
				data.setReadcnt(rs.getInt( "readcnt" ));
				list.add( data );
			}//end while
			rs.close();	stmt.close(); con.close();//약어
		}catch(Exception e){ e.printStackTrace(); }
		return  list;
	}//end list
	
	//select max(num) from springboard->1+1=2+1=3
	public int getNewNum(){ //글쓰기 번호 얻기->
	    int newNum=1;//저장할 게시물번호
	    try {
	    	String sql="select max(num) from springboard";
	    	Connection con=ds.getConnection();
	    	PreparedStatement stmt=con.prepareStatement(sql);
	    	ResultSet rs=stmt.executeQuery(sql);
	    	if(rs.next()) {
	    		newNum=rs.getInt(1)+1;
	    	}
	    }catch(Exception e) {e.printStackTrace();}
		return newNum;
	}//end getNewNum();
	  
	public void write(String author, String title , String content){
		try{ //보안때문에 ?로 입력을 받아야 된다.
			int newNum = getNewNum();
			String sql ="insert into springboard(num,author,title,content) values(";
			sql +=  newNum + ",'" + author + "','" + title + "','" + content + "')";
			System.out.println(sql);
			
	  	  	Connection con = ds.getConnection();
	  	  	PreparedStatement stmt = con.prepareStatement(sql);
	  	  	stmt.execute(sql);//stmt.executeUpdate(sql)
	  	  	stmt.close(); con.close();
	  	}catch(Exception e ) {e.printStackTrace();}
	}//end write
	
	//select * from springboard where num=2;
	public BoardDTO retrieve(String num){ // 글상세보기 ->조회수 증가
		BoardDTO data=new BoardDTO();
		try {
			//1.조회수증가
		  String sql="update springboard set readcnt=readcnt+1 where num="+num;
		  Connection con=ds.getConnection();
		  PreparedStatement stmt=con.prepareStatement(sql);
		  int update=stmt.executeUpdate(sql);
		  System.out.println("조회수 증가유무(update)=>"+update);
		  stmt=null;
		  sql="select * from springboard where num="+num;
		  stmt=con.prepareStatement(sql);
		  ResultSet rs=stmt.executeQuery(sql);
		  
		  if(rs.next()) {
			  data.setNum(rs.getInt("num"));
			  data.setAuthor(rs.getString("author"));
			  data.setTitle(rs.getString("title"));
			  data.setContent(rs.getString("Content"));
		  }
		  rs.close(); stmt.close(); con.close();
		}catch(Exception e) {e.printStackTrace();}
		return data;
	}//end retrieve
	
    
	public void update( String num , String author, 
			            String title , String content){ // 글수정하기
	     try{
		  String sql ="update springboard set title='" + title + "',";
		  sql += " content='" + content+"',";
		  sql += " author ='" + author+"'";
		  sql += " where num=" + num;
		  System.out.println(sql);

		  Connection con = ds.getConnection();
		  PreparedStatement stmt = con.prepareStatement(sql);  
		  int update=stmt.executeUpdate(sql);
		  System.out.println("게시판의 수정성공유무(update)=>"+update);
		  stmt.close();  con.close();
	     }catch(Exception e){e.printStackTrace();}
	  }//end update
      
	  //delete from springboard whre num=2
	  public void delete( String num){ //글삭제하기
			try {
			 String sql="delete from springboard where num="+num;
			 Connection con=ds.getConnection();
			 PreparedStatement pstmt=con.prepareStatement(sql);
			 int delete=pstmt.executeUpdate(sql);
			 System.out.println("delete 삭제유무(delete)=>"+delete);
			 pstmt.close();  con.close();
			}catch(Exception e) {
				System.out.println("delete()에러발생=>"+e);
			}
		}//end delete
       
	  //name(검색분야) ,value(검색어)
	  // public ArrayList<BoardDTO> search( String name , String value ){ 
	  public ArrayList search( String name , String value ){ 
		    ArrayList list = new ArrayList();
		    // List list = new ArrayList();
		    try{
		  	  String sql = "SELECT * FROM springboard";
			  sql += " WHERE  " + name + " LIKE  '%" + value + "%' "; 
			  System.out.println( sql );
		  
			      Connection con = ds.getConnection();
		    	  PreparedStatement stmt = con.prepareStatement(sql);
		    	  ResultSet rs = stmt.executeQuery( sql );
		    	  while( rs.next()){//찾은 레코드가 한개이상이라도
		    		BoardDTO data = new BoardDTO();
		    		data.setNum(rs.getInt( "num" ));
		    		data.setAuthor(rs.getString( "author" ));
		    		data.setTitle(rs.getString( "title"));
		    		data.setContent(rs.getString( "content" ));
		    		data.setDate(rs.getString( "writeday" ));
		    		data.setReadcnt(rs.getInt( "readcnt" ));
		    		list.add( data );
		    	  }
		    	  rs.close();	stmt.close(); con.close();
		    	}catch( Exception e){ e.printStackTrace();}
		    	return list;
    }
}
*/









