<%@ page contentType="text/html;charset=EUC-KR"%>
<html>
<head>
<title>ȸ������</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="script.js"></script>
</head>
<body bgcolor="#996600" onLoad="regForm.mem_id.focus()">
<br><br>
<table align="center" border="0" cellspacing="0" cellpadding="5" >
  <tr> 
    <td align="center" valign="middle" bgcolor="#FFFFCC"> 
      <table border="1" cellspacing="0" cellpadding="2"  align="center">
        <form name="regForm" method="post" action="RegisterProc.jsp">
          <tr align="center" bgcolor="#996600"> 
            <td colspan="3"><font color="#FFFFFF"><b>ȸ�� ����</b></font></td>
          </tr>
          <tr> 
            <td width="16%">���̵�</td>
            <td width="57%"> <input type="text" name="mem_id" size="15">
		      <input type="button" value="ID�ߺ�Ȯ��" 
       onClick="idCheck(this.form.mem_id.value)"></td>
            <td width="27%">���̵� ���� �ּ���.</td>
          </tr>
          <tr> 
            <td>�н�����</td>
            <td> <input type="password" name="mem_passwd" size="15"> </td>
            <td>�н����带 �����ּ���.</td>
          </tr>
          <tr> 
            <td>�н����� Ȯ��</td>
            <td> <input type="password" name="mem_repasswd" size="15"> </td>
            <td>�н����带 Ȯ���մϴ�.</td>
          </tr>
          <tr> 
            <td>�̸�</td>
            <td> <input type="text" name="mem_name" size="15"> </td>
            <td>���Ǹ��� �����ּ���.</td>
          </tr>
		  <tr> 
            <td>�ֹε�Ϲ�ȣ</td>
            <td> <input type="text" name="mem_num1" size="6" maxlength="6"
			onKeyUp="if(this.value.length==6)regForm.mem_num2.focus()" >-
			     <input type="text" name="mem_num2" size="7" maxlength="7"
				  onKeyUp="if(this.value.length==7)regForm.mem_email.focus();"></td>
            <td>�ֹε�Ϲ�ȣ�� �����ּ���.</td>
          </tr>
          <tr> 
            <td>�̸���</td>
            <td> <input type="text" name="mem_email" size="27"> </td>
            <td>�̸����� �����ּ���.</td>
          </tr>
          <tr>  
            <td>��ȭ��ȣ</td>
            <td> <input type="text" name="mem_phone" size="20"> </td>
            <td>����ó�� ���� �ּ���.</td>
          </tr>
		  <tr>  
            <td>�����ȣ</td>
            <td> <input type="text" name="mem_zipcode" size="7">
                 <input type="button" value="�����ȣã��" onClick="zipCheck()"></td>
            <td>�����ȣ�� �˻� �ϼ���.</td>
          </tr>
		  <tr>  
            <td>�ּ�</td>
            <td><input type="text" name="mem_address" size="70"></td>
            <td>�ּҸ� ���� �ּ���.</td>
          </tr>
		  <tr>  
            <td>����</td>
            <td><select name=mem_job>
 					<option value="0">�����ϼ���.
 					<option value="ȸ���">ȸ���
 					<option value="����������">����������
 					<option value="�����л�">�����л�
 					<option value="�Ϲ��ڿ���">�Ϲ��ڿ���
 					<option value="������">������
 					<option value="�Ƿ���">�Ƿ���
 					<option value="������">������
 					<option value="����,���,������">����.���/������
 					<option value="��,��,����,������">��/��/����/������
 					<option value="�ֺ�">�ֺ�
 					<option value="����">����
 					<option value="��Ÿ">��Ÿ
				  </select></td>
            <td>������ ���� �ϼ���.</td>
          </tr>
          <tr> 
            <td colspan="3" align="center"> 
             <input type="button" value="ȸ������"   onclick="inputCheck()"> 
              &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
             <input type="reset" value="�ٽþ���"> 
            </td>
          </tr>
        </form>
      </table>
    </td>
  </tr>
</table>
</body>
</html>
