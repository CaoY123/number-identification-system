# 集装箱编号识别系统

## 要点说明：
1. 输入一张带有集装箱编号的图片，给出识别的结果字符串
2. 使用SpringBoot 2.6.13作为整体的开发框架
3. 使用Log4j2作为日志框架，并在log4j2.yml中引入了日志的相关配置，大致策略为，按天记录日志并将日志压缩为gz格式，同时会当日志文件保存时间超过30天后删除，log/app.log记录当前的日志信息
4. 使用 Jasypt 对数据库密码等敏感信息进行加密，加密算法采用的是 PBEWithMD5AndDES 需要运行 JasyptEncryptionTest 测试类，往其中填入
你要加密的密码 和 使用的主密码（密钥），同时要在application.yml文件中配置上相关信息，具体步骤上网查找即可


## 配置的全局变量（开发时需要配置在IDEA的Environment variables中，部署时需要配置在服务器的全局变量中）
MY_SECRET_PASSWORD // 主密码（Jasypt加密配置信息时的密钥，自己定义的字符串，用于加密）