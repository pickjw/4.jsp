//package LYS.common;

import java.sql.*;//DB����
import java.util.*;//�ؽ����̺�

public class ConnectionPoolTest {

			private Hashtable connections;//ConnectionPool��ü
			private int increment;//����ġ
			private String dbURL, user, password;
			/******************************************
			* ������
			*******************************************/
			public ConnectionPoolTest(String dbURL,//������ġ
		                        String user,//����
		                        String password,//��ȣ
		                        String driverClassName,//����̺��
		                        int initialConnections,//�ʱ� ����
		                        int increment) throws SQLException, 
		                        ClassNotFoundException
			{
				try
				{   //Driver Loading ��ü����
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

                //Ŀ�ؼ�Ǯ�� ����
				connections = new Hashtable();
			
				for(int i = 0; i < initialConnections; i++)
				{
					//������ ���ᰴü,�뿩����
				connections.put(DriverManager.getConnection(dbURL, user, password), 
		Boolean.FALSE);
					System.out.println(i + "��° Ŀ�ؼ��� ������");
				}
			}// ������ End..............................................................

			/******************************************
			* �ؽ����̺�κ��� Ŀ�ؼ� ��� �޼ҵ�
			*******************************************/
			public Connection getConnection() throws SQLException
			{
				Connection con = null;
				int index = 0;
				//�˻��ϱ�����
				Enumeration cons = connections.keys();
				//�ؽ����̺��� ����ȭ
				synchronized (connections)
				{
					//�ϳ��� �޸𸮻󿡼� �����;�
					while(cons.hasMoreElements())
					{
						con = (Connection)cons.nextElement();
						Boolean b = (Boolean)connections.get(con);
						
						if (b == Boolean.FALSE)// ������� �ʴ� ���..
						{
							try
							{
								 //������ �� �ִ� ó��
								con.setAutoCommit(true);
							}
							catch(SQLException e)
							{
					con = DriverManager.getConnection(dbURL, user, password);
							}
					//������ ���ᰴü�� �뿩��
					connections.put(con, Boolean.TRUE);
							return con;
						}
					}
				}
           //�����ϰ� �Ǹ� 
				for(int i = 0; i < increment; i++)
				{
					System.out.println("Ŀ�ؼ� �߰� ���� : " + i);

			connections.put(DriverManager.getConnection(dbURL, user, password), 
		Boolean.FALSE);
				}
				
				return getConnection();
			}

			/******************************************
			* Ŀ�ؼ� �ݳ� �޼ҵ�
			*******************************************/
			public void returnConnection(Connection returned)
			{
				Connection con;
				//�ݳ��ϱ����ؼ� �˻�
				Enumeration cons = connections.keys();

				while (cons.hasMoreElements())
				{
					con = (Connection)cons.nextElement();
					//�޸𸮻� ���� ���ᰴü==�ݳ����ᰴü
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
			* ��� Ŀ�ؼ� �ݱ� �޼ҵ�
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
			* Ŀ�ؼ� Ǯ �׽�Ʈ ���θ޼ҵ�..
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
		         	 
				//Ŀ�ؼ� ���� �ʱⰪ
           
				ConnectionPoolTest pool = new ConnectionPoolTest(JDBC_URL, USER, PASS, 
		    JDBC_DRIVER,2 , 2);

				System.out.println("Ŀ�ؼ�Ǯ ������ : " + pool.toString());

				// ������ ������ Ŀ�ؼ� 10���� ����...
				for(int i=0; i<10; i++)
				{
					Connection con = pool.getConnection();
					System.out.println(i + "��° Ŀ�ؼ� : " + con.toString());
				}//for
			}   
	}


