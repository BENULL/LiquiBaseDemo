mybatis分批插入

500*100    1min39s      innodb		UUID主键
			
500*2000   3min28s      innodb  	自增主键

10000*100  7hour        innodb		UUID主键
			
JDBC分批插入

500*100     1min41s       innodb    UUID主键 

500*2000    6min0s98ms    innodb    自增主键

		
mybatis分批插入	
										
500*2000   2min15s      myisam

1000*1000  1min51s      myisam

10000*100  1min25s      myisam