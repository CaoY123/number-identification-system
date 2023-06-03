# 集装箱编号识别系统

## 说明：
这是一个用SpringBoot搭建的集装箱编号识别系统，提供了基础的用户功能，因为不太会写前端，直接抄的这里的代码：https://github.com/CaoY123456/SsmDemo2.0.git  
自己编写了一个调用集装箱编号识别内核的模块，地址为：https://github.com/CaoY123456/container-number-identification.git  
效果我登录系统后可以看到一个上传集装箱编号图片的界面，上传后会调用识别内核识别出编号，最后展示出来，并在后台的image_file中存储一条识别记录。其中这个外围的系统还提供了对于识别出的编号结果内容的检验和纠正以及编号最后一位校验码的计算。  
坦白来说，我的这个外围系统并不好，主要我不太会前端的东西。所以这个系统看看就好，不过我觉得我上面的识别内核还是有一点参考价值的，希望能帮到各位。  

## 要点说明：
1. 输入一张带有集装箱编号的图片，给出识别的结果字符串
2. 使用SpringBoot 2.6.13作为整体的开发框架
3. 使用Log4j2作为日志框架，并在log4j2.yml中引入了日志的相关配置，大致策略为，按天记录日志并将日志压缩为gz格式，同时会当日志文件保存时间超过30天后删除，log/app.log记录当前的日志信息
4. 使用 Jasypt 对数据库密码等敏感信息进行加密，加密算法采用的是 PBEWithMD5AndDES 需要运行 JasyptEncryptionTest 测试类，往其中填入
你要加密的密码 和 使用的主密码（密钥），同时要在application.yml文件中配置上相关信息，具体步骤上网查找即可
5. 在将该代码拉取到本地计算机后，你还需要拉一个python语言构建的代码，在项目文件夹下创建一个名为“python”的文件夹，请到 https://github.com/CaoY123456/container-number-identification.git 仓库下拉取集装箱编号识别内核代码  
6. 创建数据库，执行db.sql脚本创建用户表和识别记录表，并在application.yml文件中完成数据库配置。其中，对于数据库的密码，需要先运行JasyptEncryptionTest类按照提示信息对密码进行加密，并将加密后的结果填入ENC()括号之间，并将下面的全局变量配置进去。
7. 运行启动类NumberIdentificationSystemApplication即可跑通，启动后默认访问的url：http://localhost:9091/api/


## 配置的全局变量（开发时需要配置在IDEA的Environment variables中，部署时需要配置在服务器的全局变量中）
1) MY_SECRET_PASSWORD // 主密码（Jasypt加密配置信息时的密钥，自己定义的字符串，用于加密），注意在运行单元测试的时候也要加上相应的全局配置。  
2) CONDA_ENVIRONMENT_NAME // conda 虚拟环境的名称  
3) CONDA_EXECUTABLE_PATH // conda 命令的执行文件的绝对路径，一般为 Anaconda安装路径下\app\condabin\conda.bat  
4) WORKING_DIRECTORY_PATH // 用python编写的识别项目所在的绝对路径
