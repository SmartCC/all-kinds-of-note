1、java加载jar包中的文件
  类.class.getClassLoader().getResource("文件")即可获得所需要的类
  类.class.getClassLoader().getResource("")获得jar包的根目录
  