1、sbt引用本地jar包
  sbt 会搜索你的本地 Maven 仓库如果你将它添加为一个仓库：

  resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository" 
  如maven库位置为/homw/scc/则resolvers += "Local Maven Repository" at "file:///home/scc/.m2/repository"
  为了方便期间，可以写作:
  resolvers += Resolver.mavenLocal

2、对于不需要打包进jar的依赖包，可以使用provided，如：
  "org.apache.spark" %% "spark-core" % "0.8.0-incubating" % "provided"