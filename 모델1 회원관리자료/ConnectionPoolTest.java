//package LYS.common;

import java.sql.*;//DB접속
import java.util.*;//해쉬테이블

public class ConnectionPoolTest {

			private Hashtable connections;//ConnectionPool객체
			private int increment;//증가치
			private String dbURL, user, password;
			/******************************************
			* 생성자
			*******************************************/
			public ConnectionPoolTest(String dbURL,//서버위치
		                        String user,//계정
		                        String password,//암호
		                        String driverClassName,//드라이브명
		                        int initialConnections,//초기몇개 지정
		                        int increment) throws SQLException, 
		                        ClassNotFoundException
			{
				try
				{   //Driver Loading 객체생성
					Class.forName(driverClassName).newInstance();
				}
				catch(Exception e)
				{
					Class.forName(driverClassName);
				}
				
				this.dbURL = dbURL;
				this.user = user;
				this.password = password;
				this.increment = increment;

                //커넥션풀을 생성
				connections = new Hashtable();
			
				for(int i = 0; i < initialConnections; i++)
				{
					//생성된 연결객체,대여유무
				connections.put(DriverManager.getConnection(dbURL, user, password), 
		Boolean.FALSE);
					System.out.println(i + "번째 커넥션이 생성됨");
				}
			}// 생성자 End..............................................................

			/******************************************
			* 해쉬테이블로부터 커넥션 얻기 메소드
			*******************************************/
			public Connection getConnection() throws SQLException
			{
				Connection con = null;
				int index = 0;
				//검색하기위해
				Enumeration cons = connections.keys();
				//해쉬테이블에서 동기화
				synchronized (connections)
				{
					//하나씩 메모리상에서 꺼내와야
					while(cons.hasMoreElements())
					{
						con = (Connection)cons.nextElement();
						Boolean b = (Boolean)connections.get(con);
						
						if (b == Boolean.FALSE)// 사용하지 않는 경우..
						{
							try
							{
								 //빌려줄 수 있는 처리
								con.setAutoCommit(true);
							}
							catch(SQLException e)
							{
					con = DriverManager.getConnection(dbURL, user, password);
							}
					//빌려준 연결객체는 대여중
					connections.put(con, Boolean.TRUE);
							return con;
						}
					}
				}
           //부족하게 되면 
				for(int i = 0; i < increment; i++)
				{
					System.out.println("커넥션 추가 생성 : " + i);

			connections.put(DriverManager.getConnection(dbURL, user, password), 
		Boolean.FALSE);
				}
				
				return getConnection();
			}

			/******************************************
			* 커넥션 반납 메소드
			*******************************************/
			public void returnConnection(Connection returned)
			{
				Connection con;
				//반납하기위해서 검색
				Enumeration cons = connections.keys();

				while (cons.hasMoreElements())
				{
					con = (Connection)cons.nextElement();
					//메모리상에 꺼낸 연결객체==반납연결객체
					if (con == returned)
					{
						connections.put(con, Boolean.FALSE);
						break;
					}
				}
			}
			
		    public void returnConnection(Connection c, 
				                         Statement s, 
				                         ResultSet r) {
		        try {
		            if (r != null) r.close();
		            if (s != null) s.close();
		            returnConnection(c);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    public void returnConnection(Connection c, PreparedStatement p) {
		        try {
		            if (p != null) p.close();
		            returnConnection(c);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }

		    public void returnConnection(Connection c, Statement s) {
		        try {
		            if (s != null) s.close();
		            returnConnection(c);
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		
			/******************************************
			* 모든 커넥션 닫기 메소드
			*******************************************/
			public void closeAll()
			{
				Enumeration cons = connections.keys();
				
				while(cons.hasMoreElements())
				{
					Connection con = (Connection)cons.nextElement();
					
					try
					{
						con.close();
					}
					catch(Exception e) { }
				}
			}
			
			/*****************************************************
			* 커넥션 풀 테스트 메인메소드..
			*****************************************************/
			
			public static void main(String[] args) throws Exception
			{   
			
				/*  Type 1  
				String     dbDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
				String        dbURL = "jdbc:odbc:java";  */
				
				//  Type 4   
				/*
				final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
				final String JDBC_URL = "jdbc:oracle:thin:@192.168.10.214:1521:orcl";
				final String USER  = "scott";
				final String PASS = "tiger";*/  
		         
		 	        final String JDBC_DRIVER = "org.gjt.mm.mysql.Driver";
		 	        final String JDBC_URL = "jdbc:mysql://localhost:3306/mydb";
		 	        final String USER = "root";
		 	        final String PASS = "1234"; 
		         	 
				//커넥션 생성 초기값
           
				ConnectionPoolTest pool = new ConnectionPoolTest(JDBC_URL, USER, PASS, 
		    JDBC_DRIVER,2 , 2);

				System.out.println("커넥션풀 생성됨 : " + pool.toString());

				// 루프를 돌려서 커넥션 10개를 얻어보자...
				for(int i=0; i<10; i++)
				{
					Connection con = pool.getConnection();
					System.out.println(i + "번째 커넥션 : " + con.toString());
				}//for
			}   
	}


