1、java加载jar包中的文件
  类.class.getClassLoader().getResource("文件").getPath()即可获得所需要的jar包中文件的路径
  类.class.getClassLoader().getResource("").getPath()获得jar包的根目录
  如果是读取文件，使用stream方式读取：
	InputStream is = 类.class.getClass().getResourceAsStream("/文件");
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
  文件路径前使用相对jar包的绝对路径，即以/开头

2、查看jar包的目录结构
    如果该jar包以及存在，使用命令jar tf jar包  

3、maven的java编译版本
   maven默认的java编译版本为1.5，如果想使用其他版本可以使用插件来实现，如使用java1.8配置为：
	<plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF8</encoding>
		</configuration>
	<plugins>
