
##Spring Boot Web archetype
-------------------
* Spring Boot
* jfaster mango (http://mango.jfaster.org/)
* weixin-java-tools(https://github.com/wechat-group/weixin-java-tools)
* guava&apache common
* mongodb
* redis
* mysql

###存储选型
* 一般小型项目推荐以mongodb做主数据存储，搭配redis缓存

### demo
* 演示mysql基本操作
* 演示redis基本操作
* 演示mongodb基本操作
* 演示微信基本授权
* 演示session解决方案

### 使用规则
* mvn install
* mvn archetype:generate   -DarchetypeGroupId=com.github.uninxc  -DarchetypeArtifactId=springboot-archetype -DarchetypeVersion=0.1    -DgroupId=com.me      -DartifactId=test  -Dversion=2.0

### JVM 一般性配置
* -server -Xms1400M -Xmx1400M -Xss512k -XX:+AggressiveOpts -XX:+UseBiasedLocking -XX:PermSize=128M -XX:MaxPermSize=256M -XX:+DisableExplicitGC -XX:MaxTenuringThreshold=31 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC  -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m  -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -Djava.awt.headless=true

### maven仓库
* 建立github的lib库

### 架构规则
nginx(总代理+静态文件处理)+tomcat(docker)

###监控架构

###相关参考文档
http://docs.spring.io/spring-data/mongodb/docs/1.3.1.RELEASE/reference/html/mongo.repositories.html
http://blog.csdn.net/laigood/article/details/7056093
http://www.cnblogs.com/lori/p/4418584.html


