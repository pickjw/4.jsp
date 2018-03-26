package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//�߰�
import lys.board.*;
import java.util.*;

// /list.do=actionListAction(���ó��Ŭ����=������(�׼�)
public class ListAction2 implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		// list.jsp(�ڹټҽ� �ڵ�+ȭ����¼ҽ��ڵ�)
		
		      int pageSize=5;//numPerPage->�������� �����ִ� �Խù���
		      int blockSize=5;//pagePerBlock->���� �����ִ� ��������
		      // 1 2 3  [����] 4 5 6  7 8 9  10 11 12
		      
		   //�Խ����� �� ó�� �����Ű�� ������ 1������ ���� ���
		   String pageNum=request.getParameter("pageNum");
		   if(pageNum==null){
			   pageNum="1";//default
		   }
		   int currentPage=Integer.parseInt(pageNum);//����������(����)->���
		   //���۷��ڵ��ȣ->limit ?,?
		    //                  (1-1)*10+1=1,(2-1)*10+1=11,(3-1)*10+1=21
		   int startRow=(currentPage-1)*pageSize+1;
		   int endRow=currentPage*pageSize;//1*10,2*10=20,3*10=30(���ڵ尹�� X)
		   int count=0;//�ѷ��ڵ��
		   int number=0;//beginPerPage->�������� �����ϴ� �� ó���� ������ �Խù���ȣ
		   List articleList=null;//ȭ�鿡 ����� ���ڵ嵥����
		   
		   BoardDAO dbPro=new BoardDAO();
		   count=dbPro.getArticleCount();//select count(*) from board
		   System.out.println("���� ���ڵ��(count)=>"+count);
		   if(count > 0){
			   articleList=dbPro.getArticles(startRow, pageSize);//10���� (endRow X)
		   }else {
			   articleList=Collections.EMPTY_LIST;//�ƹ��͵� ���ٴ� ǥ��
		   }
		   //            122-(1-1)*10=122,121,120,119
		   //            122-(2-1)*10=110,109,108,,,
		   number=count-(currentPage-1)*pageSize;
		   System.out.println("�������� number=>"+number);
		
		   //2.ó�����->request��ü�� ���屸��
		   request.setAttribute("currentPage", currentPage);//int->Integer
		   request.setAttribute("startRow", startRow);
		   request.setAttribute("count", count);
		   request.setAttribute("pageSize", pageSize);
		   request.setAttribute("blockSize", blockSize);
		   request.setAttribute("number", number);
		   request.setAttribute("articleList", articleList);//${articleList}
		   
		   //3.�������� forward�� �̵�
		return "/list.jsp";  //  /board/list.jsp
	}
}



