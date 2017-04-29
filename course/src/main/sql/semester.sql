--getcourse :数据库名
create table semester
(
	sid  VARCHAR(20) NOT NULL  COMMENT '学期编号',
  	sname VARCHAR(100) NOT NULL COMMENT '学期名称', 	
  	PRIMARY KEY (sid)	
)ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='学期表';


create table teacher
(
	tid  bigint NOT NULL AUTO_INCREMENT COMMENT '教师id',
	tnum VARCHAR(20) NOT NULL COMMENT '教师编号',
  	tname VARCHAR(100) NOT NULL COMMENT '教师姓名', 	
  	PRIMARY KEY (tid)	
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='教师表';


create table coursetable
(
	cid  bigint NOT NULL AUTO_INCREMENT COMMENT '课程id',
	sid  VARCHAR(20) NOT NULL  COMMENT '学期编号',
	tnum VARCHAR(20) NOT NULL  COMMENT '教师编号',
	descr VARCHAR(1000)     COMMENT '课表描述',
	first   VARCHAR(1000) 		   COMMENT '周一课程',
	second  VARCHAR(1000) 		   COMMENT '周二课程',
	third   VARCHAR(1000) 		   COMMENT '周三课程',
	four VARCHAR(1000) 		   COMMENT '周四课程',
	five   VARCHAR(1000) 		   COMMENT '周五课程',
  	PRIMARY KEY (cid)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='课表表';
