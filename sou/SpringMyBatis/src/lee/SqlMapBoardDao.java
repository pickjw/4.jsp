package lee;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.dao.DataAccessException;

public class SqlMapBoardDao extends SqlSessionDaoSupport 
                                            implements BoardDAO {
     //@Inject or @Autowired
	//private SqlSession sqlSession;->객체를 넣어주는 멤버변수선언->getSqlSession()
	
	@Override
	public List list() throws DataAccessException {
		// TODO Auto-generated method stub
		//형식)sqlSession객체명.selectOne(실행시킬 sql구문의 id값,매개변수)->레코드한개
		//형식)sqlSession객체명.selectList(실행시킬 sql구문의 id값,매개변수)->레코드여러개
		/*
		List list=getSqlSession().selectList("list");//전달할 매개변수가 없으면 생략
		return list;*/
		return getSqlSession().selectList("list");
	}
	/*
	 * 
	 * <select id="getNewNum" resultType="Integer">
           select max(num) from springboard 
       </select>
	 */
	@Override
	public int getNewNum() throws DataAccessException {
		// TODO Auto-generated method stub
		/* 객체하나 얻어오는것(레코드한개 포함) selectOne
		int max=(Integer)getSqlSession().selectOne("getNewNum");
		return max;*/
		return (Integer)getSqlSession().selectOne("getNewNum");
	}
	
	@Override
	public void write(BoardCommand data) throws DataAccessException {
		// TODO Auto-generated method stub
		//형식)sqlSession객체명.insert("실행시킬 id명",매개변수명)
		getSqlSession().insert("write",data);
	}
	//조회수 증가
    @Override
    public void updateReadcnt(String num) throws DataAccessException {
    	// TODO Auto-generated method stub
    	//형식)sqlSession객체명.update("실행시킬 sql의 id값",매개변수명)
    	getSqlSession().update("updateReadcnt",num);
    }
    //글상세보기
    @Override
    public BoardCommand retrieve(String num) throws DataAccessException {
    	// TODO Auto-generated method stub
    	//BoardCommand board=(BoardCommand)getSqlSession().selectOne("retrieve",num);
    	return (BoardCommand)getSqlSession().selectOne("retrieve",num);
    }
    
    //글수정하기
    @Override
    public void update(BoardCommand data) throws DataAccessException {
    	// TODO Auto-generated method stub
    	getSqlSession().update("update",data);
    }
    //글삭제하기
    @Override
    public void delete(String num) throws DataAccessException {
    	// TODO Auto-generated method stub
    	getSqlSession().delete("delete",num);
    }
    //글검색하기
    @Override
    public List search(BoardCommand data) throws DataAccessException {
    	// TODO Auto-generated method stub
    	//data->searchName,searchValue->Setter Method가 이미 호출이 된상태
    	return getSqlSession().selectList("search",data);
    }
}




