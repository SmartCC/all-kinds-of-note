1、java加载jar包中的文件
  类.class.getClassLoader().getResource("文件").getPath()即可获得所需要的jar包中文件的路径
  类.class.getClassLoader().getResource("").getPath()获得jar包的根目录
  
