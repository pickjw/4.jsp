CREATE TABLE zipcode (
	zipcode             char(7)              NOT NULL  ,
	area1               char(10)             NULL      ,
	area2               char(20)             NULL      ,
	area3               char(40)             NULL      ,
	area4               char(20)             NULL      
);
c:\mysql5.1\bin¿¡ º¹»ç(zipcode.txt)

mysql -u root -p  mydb < zipcode.txt