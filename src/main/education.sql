DROP DATABASE IF EXISTS education;
CREATE DATABASE education DEFAULT CHARACTER SET utf8;
USE education;

drop table if exists edu_admin;
drop table if exists edu_role;
drop table if exists edu_menu;
drop table if exists edu_adminrole;
drop table if exists edu_rolemenu;
drop table if exists edu_topic;
drop table if exists edu_course;
drop table if exists edu_users;
drop table if exists edu_tag;
drop table if exists edu_block;
drop table if exists edu_route;
drop table if exists edu_care;
drop table if exists edu_teachers;
drop table if exists edu_routecourse;


# Host: 39.97.189.46  (Version: 5.1.73)
# Date: 2019-04-16 20:02:41
# Generator: MySQL-Front 5.3  (Build 4.269)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "edu_admin"
#

DROP TABLE IF EXISTS `edu_admin`;
CREATE TABLE `edu_admin` (
  `admin_id` int(5) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(30) DEFAULT NULL,
  `admin_pwd` varchar(30) DEFAULT NULL,
  `adminphone` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11126 DEFAULT CHARSET=utf8;

#
# Data for table "edu_admin"
#

INSERT INTO `edu_admin` VALUES (11111,'田雅静4','123456','13265786540'),(11112,'乔冰兰','123456','15678907658'),(11113,'上官凌青','123456','18567985673'),(11114,'文初瑶','123456','16278436787'),(11115,'陌上花开','123456','17689054632');

#
# Structure for table "edu_block"
#

DROP TABLE IF EXISTS `edu_block`;
CREATE TABLE `edu_block` (
  `block_id` int(5) NOT NULL AUTO_INCREMENT,
  `block_name` varchar(20) DEFAULT NULL,
  `block_desc` varchar(500) DEFAULT NULL,
  `block_order` int(5) NOT NULL DEFAULT '1',
  `block_visibility` int(5) DEFAULT '1',
  PRIMARY KEY (`block_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11118 DEFAULT CHARSET=utf8;

#
# Data for table "edu_block"
#

INSERT INTO `edu_block` VALUES (11111,'课程问答','对于课程的相关问答信息',1,1),(11112,'交流讨论','对于课程以及其他相关信息的交流讨论',1,1),(11113,'技术分享','对于以下任何与本系统相关的技术交流分享',1,1),(11114,'站内公告','本站发布的所有公告信息',1,1),(11115,'实验报告','对于实验的相关实验报告',1,1),(11117,'111','<p>111<br/></p>',1,1);

#
# Structure for table "edu_course"
#

DROP TABLE IF EXISTS `edu_course`;
CREATE TABLE `edu_course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) NOT NULL,
  `tag_id` int(11) DEFAULT NULL,
  `online_date` date NOT NULL,
  `course_desc` varchar(255) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `course_price` int(11) DEFAULT NULL,
  `course_hour` int(11) DEFAULT NULL,
  `course_easy` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11112 DEFAULT CHARSET=utf8;

#
# Data for table "edu_course"
#

INSERT INTO `edu_course` VALUES (11111,'java',11114,'2019-04-16','java_Object',11111,11111,1,10,1);

#
# Structure for table "edu_menu"
#

DROP TABLE IF EXISTS `edu_menu`;
CREATE TABLE `edu_menu` (
  `menu_id` int(5) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(20) DEFAULT NULL,
  `menu_code` varchar(20) DEFAULT NULL,
  `last_menu` varchar(50) DEFAULT NULL,
  `menu_line` varchar(20) DEFAULT NULL,
  `menu_url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `menu_code` (`menu_code`)
) ENGINE=InnoDB AUTO_INCREMENT=11131 DEFAULT CHARSET=utf8;

#
# Data for table "edu_menu"
#

INSERT INTO `edu_menu` VALUES (11111,'课程总览','0938428u823','11110','在线','/admin/index.jsp'),(11112,'热帖应答','09qi894er65r','11110','不在线','/admin/index.jsp'),(11113,'信息核察','09qi89y34e33','11110','在线','/admin/index.jsp'),(11114,'系统授权','09qiup4xc833','11110','不在线','/admin/index.jsp'),(11115,'用户管理','09qi894xopw3','11113','在线','/admin/users.jsp'),(11116,'课程管理','09qi89o4yi33','11111','不在线','/admin/course.jsp'),(11117,'路径管理','09qi894xup73','11111','在线','/admin/maruiping/route.jsp'),(11118,'标签管理','09qi894xupu8','11111','不在线','/admin/tag.jsp'),(11119,'帖子管理','09qi894xioer','11112','在线','/admin/topic.jsp'),(11121,'板块信息','09qi894xop0y','11112',' 在线','/admin/block.jsp'),(11122,'老师管理','09qi894xpo67','11113','不在线','/admin/maruiping/teachers.jsp'),(11123,'课程评论','09qi894x6poq','11111','在线','/admin/cdiscuss.jsp'),(11124,'关注管理','09qi894wiop4','11111','在线','/admin/care.jsp'),(11126,'管理员','09qi894x:-O5','11113',' 在线','/admin/admin.jsp'),(11129,'菜单管理','09qi894x654','11114',' 在线','/admin/menu.jsp'),(11130,'权限控制','09qi894x21we','11114',' 在线','/admin/role.jsp');

#
# Structure for table "edu_role"
#

DROP TABLE IF EXISTS `edu_role`;
CREATE TABLE `edu_role` (
  `role_id` int(5) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(10) DEFAULT NULL,
  `role_code` varchar(30) DEFAULT NULL,
  `role_line` varchar(20) DEFAULT NULL,
  `role_level` int(5) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_code` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=11120 DEFAULT CHARSET=utf8;

#
# Data for table "edu_role"
#

INSERT INTO `edu_role` VALUES (11111,'普通管理','ty5678ir43oww','在线',1),(11112,'课程管理','ty5678iwe65w','不在线',2),(11113,'超级管理','ty56tr6543oww','在线',3);

#
# Structure for table "edu_adminrole"
#

DROP TABLE IF EXISTS `edu_adminrole`;
CREATE TABLE `edu_adminrole` (
  `usro_id` int(5) NOT NULL AUTO_INCREMENT,
  `role_id` int(5) DEFAULT NULL,
  `admin_id` int(5) DEFAULT NULL,
  PRIMARY KEY (`usro_id`),
  KEY `fk_admin_role` (`role_id`),
  KEY `fk_admin_admin` (`admin_id`),
  CONSTRAINT `fk_admin_admin` FOREIGN KEY (`admin_id`) REFERENCES `edu_admin` (`admin_id`),
  CONSTRAINT `fk_admin_role` FOREIGN KEY (`role_id`) REFERENCES `edu_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11129 DEFAULT CHARSET=utf8;

#
# Data for table "edu_adminrole"
#

INSERT INTO `edu_adminrole` VALUES (11111,11111,11111),(11112,11111,11112),(11113,11111,11113),(11114,11112,11114),(11115,11113,11115);

#
# Structure for table "edu_rolemenu"
#

DROP TABLE IF EXISTS `edu_rolemenu`;
CREATE TABLE `edu_rolemenu` (
  `rome_id` int(5) NOT NULL AUTO_INCREMENT,
  `role_id` int(5) DEFAULT NULL,
  `menu_id` int(5) DEFAULT NULL,
  PRIMARY KEY (`rome_id`),
  KEY `fk_role_role` (`role_id`),
  KEY `fk_role_menu` (`menu_id`),
  CONSTRAINT `fk_role_menu` FOREIGN KEY (`menu_id`) REFERENCES `edu_menu` (`menu_id`),
  CONSTRAINT `fk_role_role` FOREIGN KEY (`role_id`) REFERENCES `edu_role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11149 DEFAULT CHARSET=utf8;

#
# Data for table "edu_rolemenu"
#

INSERT INTO `edu_rolemenu` VALUES (11111,11111,11111),(11112,11111,11116),(11113,11111,11118),(11114,11111,11123),(11115,11111,11124),(11116,11111,11112),(11117,11111,11119),(11121,11112,11117),(11122,11112,11111),(11123,11112,11112),(11124,11112,11121),(11126,11112,11113),(11127,11112,11115),(11128,11112,11122),(11129,11113,11111),(11130,11113,11112),(11131,11113,11113),(11132,11113,11114),(11133,11113,11115),(11134,11113,11116),(11135,11113,11117),(11136,11113,11118),(11137,11113,11119),(11139,11113,11121),(11140,11113,11122),(11141,11113,11123),(11142,11113,11124),(11144,11113,11126),(11147,11113,11129),(11148,11113,11130);

#
# Structure for table "edu_route"
#

DROP TABLE IF EXISTS `edu_route`;
CREATE TABLE `edu_route` (
  `route_id` int(11) NOT NULL AUTO_INCREMENT,
  `route_name` varchar(20) DEFAULT NULL,
  `route_desc` varchar(255) DEFAULT NULL,
  `route_count` int(11) DEFAULT NULL,
  `course_count` int(11) DEFAULT NULL,
  `route_detail` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`route_id`)
) ENGINE=MyISAM AUTO_INCREMENT=20018 DEFAULT CHARSET=utf8;

#
# Data for table "edu_route"
#

/*!40000 ALTER TABLE `edu_route` DISABLE KEYS */;
INSERT INTO `edu_route` VALUES (20001,'新手入门','新手入门路径帮助对 IT 技术感兴趣的新手0基础入门计算机编程。本路径通过新手入门、Linux 及 Vim课程熟悉实验楼的实践学习环境，再以 C 语言和一个简单的项目引导你一步步进入计算机技术的殿堂。',10,6,'新手入门detail','新手入门评论'),(20002,'java工程师','Java 学习路径中将首先完成 Java基础、JDK、JDBC、正则表达式等基础实验，然后进阶到 J2SE 和 SSH 框架学习。通过使用 Java 语言实现日记本，个人相册、编辑器、通讯录等，学习并实践 Java 编程基础，SSH 项目、Spring Boot 实战开发。',10,10,'java工程师detail','java工程师评论'),(20003,'Python研发工程师','Python技术路径中包含入门知识、Python基础、Web框架、基础项目、网络编程、数据与计算及综合项目七个模块。模块中的课程将带着你逐步深入，学会如何使用 Python 实现一个博客，桌面词典，微信机器人或网络安全软件等。完成本路径的实验课，将具备独立开发 Python 软件和 Web 应用的能力。',10,10,'afdsa','afdsa'),(20004,'C++ 研发工程师','C++ 学习路径中将通过使用 C++ 语言实现 Web 服务器，Markdown 解析器，内存池以及 Docker 容器管理工具等，学习并实践 C++ 编程基础，C++ 11/14 标准，C++ 图像处理及增强现实技术。完成本路径的所有实验课，将能够使用 C++ 独立实现复杂的应用程序。',11,11,NULL,NULL),(20005,'大数据工程师','大数据学习路径中会学习并实践 Java、Scala、Hadoop、HBase、Mahout、Sqoop及Spark等大数据技术，本路径通过大量的动手实验，在实验数据集上实践各种大数据工具，帮助你成长为具备动手能力的大数据工程师。',12,12,NULL,NULL),(20006,'Linux运维工程师','Linux运维技术路径中首先学习 Linux 相关的基本操作和系统管理，然后依次学习并实践服务部署、数据库管理、脚本编程、系统监控和安全防护、以及Web服务运维技术。最后学习 Docker 容器服务和 Windows Server的运维知识。本路径的实验将帮助你成为一名合格的 Linux 运维工程师。',13,13,NULL,NULL),(20007,'PHP研发工程师','PHP 技术路径中包含入门知识、PHP 编程基础、PHP Web 框架、项目实战和项目进阶五个模块。模块中的课程将带着你逐步深入，学会如何使用 PHP 实现项目一个博客，聊天室，MVC框架及搜索系统等。完成本路径所有实验课，将具备使用 PHP 独立开发 Web 应用的能力。',14,14,NULL,NULL),(20008,'机器学习工程师','机器学习路径将带你学会使用 Numpy，Scipy，Pandas，Scikit-learn，Matplotlib 等开源工具，完成从数据清理、数据分析、模型构建，到最终的预测评估及可视化呈现。本路径既包含了机器学习算法的基础知识，又融合了大量的实战操作，还覆盖了深度学习的有趣样例，将为你了解并掌握一定的机器学习技能提供动力。',15,15,NULL,NULL),(20009,'SDN网络工程师','SDN网络工程师\r\nSDN 学习路径将从简单的使用 wireshark 捕获数据包开始，一步步深入介绍 SDN 环境下 floodlight 控制器及 openvswitch 交换机的安装和使用，最后具备基于 floodlight 控制器实现对 SDN 网络的设计和管理的能力。',16,16,NULL,NULL),(20010,'R数据分析师','R语言学习路径将首先学习入门知识和R语言基础知识，然后通过数据统计分析、计量经济建模分析、量化金融投资分析、市场研究分析、数据挖掘五个模块的动手操作实践，深入强化R语言知识。完成本路径的实验课，将具备独立运用R语言进行各类数据分析的能力。',17,17,NULL,NULL),(20011,'Web前端工程师','Web 前端学习路径中首先实践 HTML、CSS、JavaScript、CSS3、HTML5等基础知识，然后完成实现页面特效组件、网页游戏、Web应用等一系列项目，帮助你通过动手实践成长为合格的 Web 前端工程师',18,18,NULL,NULL),(20012,'NodeJS研发工程师','NodeJS 学习路径中将通过使用 NodeJS 实现博客、在线笔记、爬虫、即时搜索、漂流瓶、端口扫描器等应用，学习并实践 NodeJS 编程基础和Web 应用开发等技术。完成本路径的所有实验课，将能够使用 NodeJS 独立实现 Web 应用程序。',19,19,NULL,NULL),(20013,'MongoDB工程师','MongoDB 技术路径中首先学习 Linux、Bash、MySQL基础知识，然后依次学习并实践 Memcached、Redis 和 MongoDB技术。本路径的实验将帮助你逐步入门 MongoDB 这类 NoSQL 数据库。',20,20,NULL,NULL),(20017,'qwer','rewqre',1,1,'qrewqr','rewqre');
/*!40000 ALTER TABLE `edu_route` ENABLE KEYS */;

#
# Structure for table "edu_routecourse"
#

DROP TABLE IF EXISTS `edu_routecourse`;
CREATE TABLE `edu_routecourse` (
  `rc_id` int(11) NOT NULL AUTO_INCREMENT,
  `route_id` int(11) DEFAULT NULL,
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`rc_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

#
# Data for table "edu_routecourse"
#

/*!40000 ALTER TABLE `edu_routecourse` DISABLE KEYS */;
/*!40000 ALTER TABLE `edu_routecourse` ENABLE KEYS */;

#
# Structure for table "edu_tag"
#

DROP TABLE IF EXISTS `edu_tag`;
CREATE TABLE `edu_tag` (
  `tag_id` int(5) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(20) DEFAULT NULL,
  `tag_desc` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11151 DEFAULT CHARSET=utf8;

#
# Data for table "edu_tag"
#

/*!40000 ALTER TABLE `edu_tag` DISABLE KEYS */;
INSERT INTO `edu_tag` VALUES (11111,'Python','<p>哥斯达黎加地枯地枯&nbsp;模压&nbsp;棋&nbsp;枯哥斯达黎加地枯地枯&nbsp;模压&nbsp;棋&nbsp;枯哥斯达黎加地枯地枯&nbsp;模压&nbsp;棋&nbsp;枯哥斯达黎加地枯地枯&nbsp;模压&nbsp;棋&nbsp;枯哥斯达黎加地枯地枯&nbsp;模压&nbsp;棋&nbsp;枯哥斯达黎加地枯地枯&nbsp;模压&nbsp;棋&nbsp;枯哥斯达黎加地枯地枯&nbsp;模压&nbsp;棋&nbsp;枯哥斯达黎加地枯地枯&nbsp;模压&nbsp;棋&nbsp;枯哥斯达黎加地枯地枯&nbsp;模压&nbsp;棋&nbsp;枯哥斯达黎加地枯地枯&nbsp;模压&nbsp;棋&nbsp;枯哥斯达黎加地枯地枯&nbsp;模压&nbsp;棋&nbsp;枯<img src=\"http://img.baidu.com/hi/jx2/j_0013.gif\"/><img src=\"http://img.baidu.com/hi/jx2/j_0025.gif\"/></p>'),(11112,'C/C++','<p>&nbsp; &nbsp; &nbsp; &nbsp;C语言是在70年代初问世的。一九七八年由美国电话电报公司(AT&amp;T)贝尔实验室正式发表了C语言。同时由B.W.Kernighan和D.M.Ritchit合著了著名的“THE C PROGRAMMING LANGUAGE”一书。通常简称为《K&amp;R》，也有人称之为《K&amp;R》标准。但是，在《K&amp;R》中并没有定义一个完整的标准C语言，后来由美国国家标准学会在此基础上制定了一个C 语言标准，于一九八三年发表。通常称之为ANSI C。&nbsp; &nbsp; &nbsp; &nbsp;1</p>'),(11113,'Linux','Linux是一套免费使用和自由传播的类Unix操作系统，是一个基于POSIX和UNIX的多用户、多任务、支持多线程和多CPU的操作系统。它能运行主要的UNIX工具软件、应用程序和网络协议。它支持32位和64位硬件。Linux继承了Unix以网络为核心的设计思想，是一个性能稳定的多用户网络操作系统。\r\nLinux操作系统诞生于1991 年10 月5 日（这是第一次正式向外公布时间）。Linux存在着许多不同的Linux版本，但它们都使用了Linux内核。Linux可安装在各种计算机硬件设备中，比如手机、平板电脑、路由器、视频游戏控制台、台式计算机、大型机和超级计算机。\r\n严格来讲，Linux这个词本身只表示Linux内核，但实际上人们已经习惯了用Linux来形容整个基于Linux内核，并且使用GNU 工程各种工具和数据库的操作系统。'),(11114,'Web','web（World Wide Web）即全球广域网，也称为万维网，它是一种基于超文本和HTTP的、全球性的、动态交互的、跨平台的分布式图形信息系统。是建立在Internet上的一种网络服务，为浏览者在Internet上查找和浏览信息提供了图形化的、易于访问的直观界面，其中的文档及超级链接将Internet上的信息节点组织成一个互为关联的网状结构。'),(11115,'信息安全','信息安全主要包括以下五方面的内容，即需保证信息的保密性、真实性、完整性、未授权拷贝和所寄生系统的安全性。信息安全本身包括的范围很大，其中包括如何防范商业企业机密泄露、防范青少年对不良信息的浏览、个人信息的泄露等。网络环境下的信息安全体系是保证信息安全的关键，包括计算机安全操作系统、各种安全协议、安全机制（数字签名、消息认证、数据加密等），直至安全系统，如UniNAC、DLP等，只要存在安全漏洞便可以威胁全局安全。信息安全是指信息系统（包括硬件、软件、数据、人、物理环境及其基础设施）受到保护，不受偶然的或者恶意的原因而遭到破坏、更改、泄露，系统连续可靠正常地运行，信息服务不中断，最终实现业务连续性。'),(11116,'PHP','PHP（外文名:PHP: Hypertext Preprocessor，中文名：“超文本预处理器”）是一种通用开源脚本语言。语法吸收了C语言、Java和Perl的特点，利于学习，使用广泛，主要适用于Web开发领域。PHP 独特的语法混合了C、Java、Perl以及PHP自创的语法。它可以比CGI或者Perl更快速地执行动态网页。用PHP做出的动态页面与其他的编程语言相比，PHP是将程序嵌入到HTML（标准通用标记语言下的一个应用）文档中去执行，执行效率比完全生成HTML标记的CGI要高许多；PHP还可以执行编译后代码，编译可以达到加密和优化代码运行，使代码运行更快。'),(11117,'Java','Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程'),(11118,'NodeJS','Node 是一个让 JavaScript 运行在服务端的开发平台，它让 JavaScript 成为与PHP、Python、Perl、Ruby 等服务端语言平起平坐的脚本语言。 发布于2009年5月，由Ryan Dahl开发，实质是对Chrome V8引擎进行了封装。'),(11119,'Android','Android是一种基于Linux的自由及开放源代码的操作系统。主要使用于移动设备，如智能手机和平板电脑，由Google（谷歌）公司和开放手机联盟领导及开发。尚未有统一中文名称，中国大陆地区较多人使用“安卓”或“安致”。Android操作系统最初由Andy Rubin开发，主要支持手机。2005年8月由Google收购注资。2007年11月，Google与84家硬件制造商、软件开发商及电信营运商组建开放手机联盟共同研发改良Android系统。随后Google以Apache开源许可证的授权方式，发布了Android的源代码。第一部Android智能手机发布于2008年10月。Android逐渐扩展到平板电脑及其他领域上，如电视、数码相机、游戏机、智能手表等。2011年第一季度，Android在全球的市场份额首次超过塞班系统，跃居全球第一。 2013年的第四季度，Android平台手机的全球市场份额已经达到78.1%。   2013年09月24日谷歌开发的操作系统Android在迎来了5岁生日，全世界采用这款系统的设备数量已经达到10亿台。'),(11120,'GO','Go（又称Golang）是Google开发的一种静态强类型、编译型、并发型，并具有垃圾回收功能的编程语言。'),(11121,'Spark','Apache Spark 是专为大规模数据处理而设计的快速通用的计算引擎。Spark是UC Berkeley AMP lab (加州大学伯克利分校的AMP实验室)所开源的类Hadoop MapReduce的通用并行框架，Spark，拥有Hadoop MapReduce所具有的优点；但不同于MapReduce的是——Job中间输出结果可以保存在内存中，从而不再需要读写HDFS，因此Spark能更好地适用于数据挖掘与机器学习等需要迭代的MapReduce的算法。'),(11122,'计算机专业课','本专业是计算机硬件与软件相结合、面向系统、侧重应用的宽口径专业。通过基础教学与专业训练，培养基础知识扎实、知识面宽、工程实践能力强，具有开拓创新意识，在计算机科学与技术领域从事科学研究、教育、开发和应用的高级人才。\r\n计算机学科的特色主要体现在：理论性强，实践性强，发展迅速按一级学科培养基础扎实的宽口径人才，体现在重视数学、逻辑、数据结构、算法、电子设计、计算机体系结构和系统软件等方面的理论基础和专业技术基础，前两年半注重自然科学基础课程和专业基础课程，拓宽面向。后一年半主要是专业课程的设置，增加可选性、多样性、灵活性和方向性，突出学科方向特色，体现最新技术发展动向。'),(11123,'Hadoop','Hadoop是一个由Apache基金会所开发的分布式系统基础架构。\r\n用户可以在不了解分布式底层细节的情况下，开发分布式程序。充分利用集群的威力进行高速运算和存储。\r\n Hadoop实现了一个分布式文件系统（Hadoop Distributed File System），简称HDFS。HDFS有高容错性的特点，并且设计用来部署在低廉的（low-cost）硬件上；而且它提供高吞吐量（high throughput）来访问应用程序的数据，适合那些有着超大数据集（large data set）的应用程序。HDFS放宽了（relax）POSIX的要求，可以以流的形式访问（streaming access）文件系统中的数据。\r\nHadoop的框架最核心的设计就是：HDFS和MapReduce。HDFS为海量的数据提供了存储，而MapReduce则为海量的数据提供了计算。'),(11124,'HTML5','万维网的核心语言、标准通用标记语言下的一个应用超文本标记语言（HTML）的第五次重大修改'),(11125,'Scala','Scala是一门多范式的编程语言，一种类似java的编程语言  ，设计初衷是实现可伸缩的语言   、并集成面向对象编程和函数式编程的各种特性。'),(11126,'Ruby','Ruby，一种简单快捷的面向对象（面向对象程序设计）脚本语言，在20世纪90年代由日本人松本行弘(Yukihiro Matsumoto)开发，遵守GPL协议和Ruby License。它的灵感与特性来自于 Perl、Smalltalk、Eiffel、Ada以及 Lisp 语言。由 Ruby 语言本身还发展出了JRuby（Java平台）、IronRuby（.NET平台）等其他平台的 Ruby 语言替代品。Ruby的作者于1993年2月24日开始编写Ruby，直至1995年12月才正式公开发布于fj（新闻组）。因为Perl发音与6月诞生石pearl（珍珠）相同，因此Ruby以7月诞生石ruby（红宝石）命名。'),(11127,'R','R是用于统计分析、绘图的语言和操作环境。R是属于GNU系统的一个自由、免费、源代码开放的软件，它是一个用于统计计算和统计制图的优秀工具。'),(11128,'网络','网络是由节点和连线构成，表示诸多对象及其相互联系。在数学上，网络是一种图，一般认为专指加权图。网络除了数学定义外，还有具体的物理含义，即网络是从某种相同类型的实际问题中抽象出来的模型。在计算机领域中，网络是信息传输、接收、共享的虚拟平台，通过它把各个点、面、体的信息联系到一起，从而实现这些资源的共享。网络是人类发展史来最重要的发明，提高了科技和人类社会的发展。'),(11129,'Git','Git(读音为/gɪt/。)是一个开源的分布式版本控制系统，可以有效、高速地处理从很小到非常大的项目版本管理。 [1]  Git 是 Linus Torvalds 为了帮助管理 Linux 内核开发而开发的一个开放源码的版本控制软件。\r\nTorvalds 开始着手开发 Git 是为了作为一种过渡方案来替代 BitKe'),(11130,'SQL','结构化查询语言(Structured Query Language)简称SQL(发音：/ˈes kjuː ˈel/ \"S-Q-L\")，是一种特殊目的的编程语言，是一种数据库查询和程序设计语言，用于存取数据以及查询、更新和管理关系数据库系统；同时也是数据库脚本文件的扩展名。\r\n结构化查询语言是高级的非过程化编程语言，允许用户在高层数据结构上工作。它不要求用户指定对数据的存放方法，也不需要用户了解具体的数据存放方式，所以具有完全不同底层结构的不同数据库系统, 可以使用相同的结构化查询语言作为数据输入与管理的接口。结构化查询语言语句可以嵌套，这使它具有极大的灵活性和强大的功能。\r\n1986年10月，美国国家标准协会对SQL进行规范后，以此作为关系式数据库管理系统的标准语言（ANSI X3. 135-1986），1987年得到国际标准组织的支持下成为国际标准。不过各种通行的数据库系统在其实践过程中都对SQL规范作了某些编改和扩充。所以，实际上不同数据库系统之间的SQL不能完全相互通用。'),(11131,'NoSQL','NoSQL，泛指非关系型的数据库。随着互联网web2.0网站的兴起，传统的关系数据库在应付web2.0网站，特别是超大规模和高并发的SNS类型的web2.0纯动态网站已经显得力不从心，暴露了很多难以克服的问题，而非关系型的数据库则由于其本身的特点得到了非常迅速的发展。NoSQL数据库的产生就是为了解决大规模数据集合多重数据种类带来的挑战，尤其是大数据应用难题。'),(11132,'算法','算法（Algorithm）是指解题方案的准确而完整的描述，是一系列解决问题的清晰指令，算法代表着用系统的方法描述解决问题的策略机制。也就是说，能够对一定规范的输入，在有限时间内获得所要求的输出。如果一个算法有缺陷，或不适合于某个问题，执行这个算法将不会解决这个问题。不同的算法可能用不同的时间、空间或效率来完成同样的任务。一个算法的优劣可以用空间复杂度与时间复杂度来衡量。'),(11133,'Docker','Docker 是一个开源的应用容器引擎，让开发者可以打包他们的应用以及依赖包到一个可移植的容器中，然后发布到任何流行的 Linux 机器上，也可以实现虚拟化。容器是完全使用沙箱机制，相互之间不会有任何接口。'),(11134,'Swift','Swift，苹果于2014年WWDC（苹果开发者大会）发布的新开发语言，可与Objective-C*共同运行于Mac OS和iOS平台，用于搭建基于苹果平台的应用程序。'),(11135,'汇编','汇编大多是指汇编语言，汇编程序。把汇编语言翻译成机器语言的过程称为汇编。在汇编语言中，用助记符(Memoni)代替操作码，用地址符号(Symbol)或标号(Label)代替地址码。这样用符号代替机器语言的二进制码，就把机器语言变成了汇编语言。于是汇编语言亦称为符号语言。用汇编语言编写的程序，机器不能直接识别，要由一种程序将汇编语言翻译成机器语言，这种起翻译作用的程序叫汇编程序，汇编程序是系统软件中语言处理的系统软件。'),(11136,'Windows','Microsoft Windows操作系统是美国微软公司研发的一套操作系统，它问世于1985年，起初仅仅是Microsoft-DOS模拟环境，后续的系统版本由于微软不断的更新升级，不但易用，也慢慢的成为家家户户人们最喜爱的操作系统。'),(11146,'123123','<p>12312312312</p>'),(11148,'111','<p>111</p>'),(11149,'111','<p>111</p>'),(11150,'111','<p>111</p>');
/*!40000 ALTER TABLE `edu_tag` ENABLE KEYS */;

#
# Structure for table "edu_teachers"
#

DROP TABLE IF EXISTS `edu_teachers`;
CREATE TABLE `edu_teachers` (
  `teacher_id` int(5) NOT NULL DEFAULT '11111' COMMENT '老师编号',
  `teacher_name` varchar(255) DEFAULT '' COMMENT '老师姓名',
  `teacher_pwd` varchar(255) DEFAULT NULL COMMENT '老师密码',
  `teacher_img` varchar(255) DEFAULT NULL COMMENT '老师头像',
  `teacher_phone` varchar(255) DEFAULT NULL COMMENT '老师电话',
  `teacher_email` varchar(255) DEFAULT NULL COMMENT '老师邮箱',
  `teacher_desc` varchar(255) DEFAULT NULL COMMENT '个人描述',
  PRIMARY KEY (`teacher_id`),
  KEY `teacher_id` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "edu_teachers"
#

INSERT INTO `edu_teachers` VALUES (11111,'马云','123456',NULL,'18945678934','mayun@albb.com','专业吹牛逼'),(11112,'马化腾','123456',NULL,'15678345645','mahuateng@tx.com','游戏才是生活的基本'),(11113,'马明哲','123456',NULL,'15678654298','maminzhe@bx.com','保险业是小康生活的保障'),(11114,'李嘉诚','123456',NULL,'17865785643','lijiacheng@lc.com','生活就是房地产'),(11115,'王健林','123456',NULL,'15643675648','wangjianlin@wj.com','万达广场就是生活'),(11116,'刘德华','123456',NULL,'17865438976','liudehua@liu.com','低调的外表高调的才华'),(11117,'周润发','123456',NULL,'18967458324','zhouyunfa@zy.com','曾经是大哥现在还是大哥'),(11118,'周星驰','123456',NULL,'17865907856','zhouyinchi@zxx.com','搞笑我们是认真的'),(11119,'成龙','123456',NULL,'17865985632','chenglong@cl.com','演员是要有真功夫的'),(11120,'宋小宝','123456',NULL,'18967853987','songxiaobao@ss.com','东北人演二人转我演小品');

#
# Structure for table "edu_users"
#

DROP TABLE IF EXISTS `edu_users`;
CREATE TABLE `edu_users` (
  `user_id` int(5) NOT NULL AUTO_INCREMENT,
  `user_nickname` varchar(20) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_img` varchar(200) DEFAULT NULL,
  `user_state` int(1) NOT NULL,
  `user_phone` varchar(11) NOT NULL,
  `user_email` varchar(100) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `vip` int(1) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10080 DEFAULT CHARSET=utf8;

#
# Data for table "edu_users"
#

INSERT INTO `edu_users` VALUES (10002,' 四哥','李四','admin/userimg/1555056356590_userimg.png',1,'13212345678','lisi@qq.com','123456',1),(10003,' 五哥',' 王五','admin/userimg/1555056923175_userimg.png',0,'13312345678','wangwu@qq.com','123456',0),(10004,' 二哥',' 王二',NULL,1,'13412345678','wanger@qq.com','123456',1),(10005,' 小三','唐三',NULL,0,'13512345678','tangsan@qq.com','123456',0),(10006,' 柔骨兔',' 小舞',NULL,1,'13612345678','xiaowu@qq.com','123456',1),(10007,' 邪眸白虎','戴沐白',NULL,0,'13712345678','daimubai@qq.com','123456',0),(10008,' 幽冥灵猫',' 朱竹青',NULL,1,'13812345678','zhuzhuqing@qq.com','123456',1),(10009,' 香肠专卖',' 奥斯卡',NULL,0,'13912345678','aosika@qq.com','123456',0),(10010,' 七宝琉璃',' 宁荣荣',NULL,1,'13112341324','ningrongrong@qq.com','123456',1),(10011,' 浴火凤凰',' 马红俊',NULL,0,'1321234134','mahongjun@qq.com','123456',0),(10012,'三哥','张三',NULL,0,'13112345678','zhangsan@qq.com','123456',0),(10037,' 二哥',' 王二','',1,'13412345678','wanger@qq.com','123456',1),(10038,' 小三','唐三','',0,'13512345678','tangsan@qq.com','123456',0),(10039,' 柔骨兔',' 小舞','',1,'13612345678','xiaowu@qq.com','123456',1),(10040,' 四哥','李四','',1,'13212345678','lisi@qq.com','123456',1),(10041,' 五哥',' 王五','',0,'13312345678','wangwu@qq.com','123456',0),(10042,' 二哥',' 王二','',1,'13412345678','wanger@qq.com','123456',1),(10043,' 小三','唐三','',0,'13512345678','tangsan@qq.com','123456',0),(10044,' 柔骨兔',' 小舞','',1,'13612345678','xiaowu@qq.com','123456',1),(10045,' 四哥','李四','',1,'13212345678','lisi@qq.com','123456',1),(10046,' 五哥',' 王五','',0,'13312345678','wangwu@qq.com','123456',0),(10047,' 二哥',' 王二','',1,'13412345678','wanger@qq.com','123456',1),(10048,' 小三','唐三','',0,'13512345678','tangsan@qq.com','123456',0),(10049,' 柔骨兔',' 小舞','',1,'13612345678','xiaowu@qq.com','123456',1),(10050,' 四哥','李四','',1,'13212345678','lisi@qq.com','123456',1),(10051,' 五哥',' 王五','',0,'13312345678','wangwu@qq.com','123456',0),(10052,' 二哥',' 王二','',1,'13412345678','wanger@qq.com','123456',1),(10053,' 小三','唐三','',0,'13512345678','tangsan@qq.com','123456',0),(10054,' 柔骨兔',' 小舞','',1,'13612345678','xiaowu@qq.com','123456',1),(10055,' 四哥','李四','',1,'13212345678','lisi@qq.com','123456',1),(10056,' 五哥',' 王五','',0,'13312345678','wangwu@qq.com','123456',0),(10057,' 二哥',' 王二','',1,'13412345678','wanger@qq.com','123456',1),(10058,' 小三','唐三','',0,'13512345678','tangsan@qq.com','123456',0),(10059,' 柔骨兔',' 小舞','',1,'13612345678','xiaowu@qq.com','123456',1),(10060,' 四哥','李四','',1,'13212345678','lisi@qq.com','123456',1),(10061,' 五哥',' 王五','',0,'13312345678','wangwu@qq.com','123456',0),(10062,' 二哥',' 王二','',1,'13412345678','wanger@qq.com','123456',1),(10063,' 小三','唐三','',0,'13512345678','tangsan@qq.com','123456',0),(10064,' 柔骨兔',' 小舞','',1,'13612345678','xiaowu@qq.com','123456',1),(10065,' 四哥','李四','',1,'13212345678','lisi@qq.com','123456',1),(10066,' 五哥',' 王五','',0,'13312345678','wangwu@qq.com','123456',0),(10071,' 小三','唐三','',0,'13512345678','tangsan@qq.com','123456',0),(10072,' 柔骨兔',' 小舞','',1,'13612345678','xiaowu@qq.com','123456',1),(10073,' 小三','唐三','',0,'13512345678','tangsan@qq.com','123456',0),(10074,' 柔骨兔',' 小舞','',1,'13612345678','xiaowu@qq.com','123456',1),(10076,' 柔骨兔',' 小舞','',1,'13612345678','xiaowu@qq.com','123456',1),(10078,'4321','脑子嗡嗡的1',NULL,0,'13012345678','zhangsan@qq.com','123456',0),(10079,'而我却若','奇热网群','admin/userimg/xidada.jpg',1,'13112345678','二七王若','123456',0);

#
# Structure for table "edu_topic"
#

DROP TABLE IF EXISTS `edu_topic`;
CREATE TABLE `edu_topic` (
  `topic_id` int(5) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `topic_time` date NOT NULL,
  `topic_name` varchar(500) NOT NULL,
  `topic_content` varchar(500) NOT NULL,
  `block_id` int(5) NOT NULL,
  `user_id` int(5) NOT NULL,
  `course_id` int(5) NOT NULL,
  `replys` int(5) NOT NULL,
  `looks` int(5) NOT NULL,
  PRIMARY KEY (`topic_id`),
  KEY `fkooor` (`block_id`),
  KEY `fkuser` (`user_id`),
  KEY `fkcourse` (`course_id`),
  CONSTRAINT `fkcourse` FOREIGN KEY (`course_id`) REFERENCES `edu_course` (`course_id`),
  CONSTRAINT `fkooor` FOREIGN KEY (`block_id`) REFERENCES `edu_block` (`block_id`),
  CONSTRAINT `fkuser` FOREIGN KEY (`user_id`) REFERENCES `edu_users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11127 DEFAULT CHARSET=utf8;

#
# Data for table "edu_topic"
#

INSERT INTO `edu_topic` VALUES (11111,'2019-04-15','实验楼百楼俱乐部成立，欢迎100楼的你加','1',11111,10002,11111,89,2409),(11112,'2019-04-15',' 实验楼会员服务FAQ','1',11111,10002,11111,112,6359),(11113,'2019-04-15','\"模型（一）\" 实验报告','1',11111,10002,11111,0,0),(11114,'2019-04-15','\"用户及文件权限管理\" 实验报告','1',11111,10002,11111,0,1),(11115,'2019-04-15','\"高级功能入门\" 实验报告','1',11111,10002,11111,0,0),(11116,'2019-04-15','fatal: could not cre','1',11111,10002,11111,0,3),(11117,'2019-04-15','无法创建目录权限不够','1',11111,10002,11111,2,45),(11118,'2019-04-15','模块后缀名错误','1',11111,10002,11111,2,13),(11119,'2019-04-15','\"选择程序设计\" 实验报告','1',11111,10002,11111,0,1),(11120,'2019-04-15','\"Hello，ShiYanLou!\" 实','1',11111,10002,11111,0,4),(11121,'2019-04-15','\"视图和URL配置\" 实验报告','1',11111,10002,11111,0,1),(11122,'2019-04-15','Xfce终端，怎样检查代码错误？','1',11111,10002,11111,1,8),(11123,'2019-04-15','\"进阶篇 - SQL 语句语法详解\" 实','1',11111,10002,11111,0,1),(11124,'2019-04-15','python数据库配置问题，求各路大神指','1',11111,10002,11111,1,6),(11125,'2019-04-15','什么时候会出Python自动化测试的课程','1',11111,10002,11111,2,132);

#
# Structure for table "edu_care"
#

DROP TABLE IF EXISTS `edu_care`;
CREATE TABLE `edu_care` (
  `care_id` int(5) NOT NULL AUTO_INCREMENT,
  `user_id` int(5) NOT NULL,
  `course_id` int(5) NOT NULL,
  PRIMARY KEY (`care_id`),
  KEY `FK_CARE_COURSEID` (`course_id`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `edu_users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10018 DEFAULT CHARSET=utf8;

#
# Data for table "edu_care"
#

INSERT INTO `edu_care` VALUES (10001,10003,10002),(10002,10002,10002),(10003,10003,10003),(10004,10004,10004),(10005,10005,10005),(10006,10006,10006),(10007,10007,10007),(10008,10008,10008),(10009,10009,10009),(10010,10010,10010),(10011,10011,10011);
