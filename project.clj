    ;;================================================================================
    ;;这是一个包含注释的project.clj文件。
    ;;包含了所有选项。可以视为一个配置样本。
    ;;包含了比”lein帮助教程”更详细的注释
    ;;================================================================================
    ;; 这是一个项目名叫 "sameple"
    ;; 组名(或者公司网站名之类的，group-id)叫 "org.example"
    ;; 版本(version)为"1.0.0-SNAPSHOT"的项目（project）
    (defproject org.example/sample "1.0.0-SNAPSHOT"
    ;;除了这一点，你可能前面加上反引号（unquote），或~，对它求值（eval）。（这个注释不是我也不是很明白）
    ;; 这个描述文本有助于仓库搜索（比如clojars仓库）
    :description "A sample project"
    :url "http://example.org/sample-clojure-project"
    ;;邮件列表。没啥好说的
    :mailing-list {:name "sample mailing list"
    :archive "http://example.org/sample-mailing-list-archives"
    :other-archives ["http://example.org/sample-list-archive2"
    "http://example.org/sample-list-archive3"]
    :post "list@example.org"
    :subscribe "list-subscribe@example.org"
    :unsubscribe "list-unsubscribe@example.org"}
    ;;license这个也没啥好说的
    :license {:name "Eclipse Public License - v 1.0"
    :url "http://www.eclipse.org/legal/epl-v10.html"
    :distribution :repo
    :comments "same as Clojure"}
    ;;依赖的格式类似：[group-id/project-name version]
    ;; classifier ：它表示在相同版本下针对不同的环境或者jdk使用的jar,如果配置了这个元素，则会将这个元素名在加在最后来查找相应的jar
    ;; exclusions : 用来排除相应的重复依赖。比如log4j下包含了a.jar。spring.jar也包含了a.jar，但是两个版本不同，则需要排除掉一个，避免冲突。
    :dependencies [[org.clojure/clojure "1.1.0"]
                   [org.clojure/clojure-contrib "1.1.0"]
                   [org.jclouds/jclouds "1.0-RC6" :classifier "jdk15"]
                   [log4j "1.2.15" :exclusions [javax.mail/mail
                                                javax.jms/jms
                                                com.sun.jdmk/jmxtools
                                                com.sun.jmx/jmxri]]]
    ;; 只用于开发阶段的依赖。打包部署将不包含这些依赖。
    :dev-dependencies [[org.clojure/swank-clojure "1.2.1"]]
    ;; 全局的一个依赖排除。
    :exclusions [org.apache.poi/poi
                 org.apache.poi/poi-ooxml]
    ;;在project.clj改变或者库文件目录（:library-path directory）为空时，重新获取依赖。
    :checksum-deps true
    ;;如果版本低于这个就警告
    :min-lein-version "1.3.0"
    ;; 如果这个选项为false，那么获取依赖的时候，lib目录将被清空。
    ;; 如果要lib目录不被清空，请将它设为true
    :disable-deps-clean false
    ;; 禁用隐式的clean
    :disable-implicit-clean true
    ;; Delete .class files that do not have a corresponding package in
    ;; the src/ directory. Workaround for Clojure bug CLJ-322. Causes problems
    ;; with protocols in upstream libraries; false by default. Set to
    ;; true to delete all non-project classes or set to a seq of regexes
    ;; to only delete class files that match one of the regexes.
    （这个翻译我觉的可能有问题，故保留）
    ;; 不存在src/目录，删除所有.class文件
    ;; 为了解决Clojure bug CLJ-322, 可以将其设置为true，或者定义一个正则序列，仅删除不匹配的class文件。
    :clean-non-project-classes true
    ;; 如果 :clean-non-project-classes 设置为true
    ;; 你可以设置这个正则，用来保留匹配的class文件。
    :class-file-whitelist #"^(org/example|clojure)"
    ;; 在clean阶段其他文件将被删除（除了:compile-path 和 jars/uberjars）。
    ;; %s这个符号将被替换成当前项目的版本号
    :extra-files-to-clean ["tmp" "sample-%s.tar"]
    ;; 如果你不能精确匹配到你要删除的文件名
    ;; 你可以使用正则表达（从项目根目录对文件名进行匹配）
    ;; 默认为 #"^$NAME-.*\.jar$".
    :regex-to-clean #"hs_err_pid.*"
    ;; 项目的checkout路径
    :checkout-deps-shares [:source-path :test-path
    ~(fn [p] (str (:root p) "/lib/dev/*"))]
    ;; 在启动时加载hooks中的namespaces
    ;; Hooks一般来自插件，但也可能包含在你的项目source中
    :hooks [leiningen.hooks.difftest]
    ;; Predicates to determine whether to run a test or not. See tutorial.
    ;; 决定是否运行测试。请参考tutorial
    :test-selectors {:default (fn [t] (not (or (:integration v) (:regression v))))
    :integration :integration
    :regression :regression}
    ;; 如果设置这个为true。将加载所有和leiningen.hooks.*匹配的namespaces。
    ;; 警告！！！：很明显的将导致加载依赖过多，启动起来比蜗牛还慢
    :implicit-hooks false
    ;; （提示：ahead-of-time (AOT) compiler 是一个实现时间提前编译的编译器）
    ;; gen-class和java互操作所需的将被提前编译。:namespaces 在这里是个别名
    ;; 设置一个正则将编译所以匹配的
    :aot [org.example.sample]
    ;; 打包成jar文件的入口函数
    ;; 设置 :skip-aot 元数据用来做其他事情。例如运行shell或者task。
    :main org.example.sample
    ;; 在repl启动的时候自动加载这个namespace
    :repl-init sample.repl-helper
    ;;和:repl-init一样，不过已经是过时的东西了，所以请使用:repl-init。
    :repl-init-script "src/main/clojure/init.clj"
    ;; 这些将传递给 clojure.main/repl; 查看他们的细节
    :repl-options [:prompt (fn [] (print "your command, master? ") (flush))]
    ;; 自定义repl的监听端口
    :repl-port 4001
    :repl-host "0.0.0.0"
    ;; A form to prepend to every form that is evaluated inside your project.
    ;; Allows working around the Gilardi Scenario: http://technomancy.us/143
    :project-init (require 'clojure.pprint)
    ;; 超时重连。默认为100
    :repl-retry-limit 1000
    ;; 对所有的反射调用进行警告
    :warn-on-reflection true
    ;; 仓库配置。即使没配置，maven的中央仓库也依然会被查找。
    :omit-default-repositories true
    :repositories {"java.net" "http://download.java.net/maven/2"
    "sonatype"
    {:url "http://oss.sonatype.org/content/repositories/releases"
    ;; If a repository contains releases only; setting :snapshots
    ;; to false will speed up dependency checking.
    :snapshots false
    ;; You can also set the policies for how to handle :checksum
    ;; failures to :fail, :warn, or :ignore. In :releases, :daily,
    ;; :always, and :never are supported.
    :releases {:checksum :fail
    :update :always}}
    ;; Repositories named "snapshots" and "releases" automatically
    ;; have their :snapshots and :releases disabled as appropriate.
    "snapshots" {:url "http://blueant.com/archiva/snapshots"
    ;; Also supports :private-key and :passphrase.
    :username "milgrim" :password "locative.1"}
    "releases" {:url "http://blueant.com/archiva/internal"
    :username "milgrim" :password "locative.1"}}
    ;; 开发依赖的仓库
    :deploy-repositories {"releases" {:url "http://blueant.com/archiva/internal/releases"
    :username "milgrim" :password "locative.1"}
    "snapshots" "http://blueant.com/archiva/internal/snapshots"}
    ;; 源文件路径
    :source-path "src/main/clojure"
    ;; 编译后的文件路径
    :compile-path "target/classes" ; for .class files
    ;; 打包所需jar文件路径
    :library-path "target/dependency" ; for .jar files
    ;; 单元测试源文件路径
    :test-path "src/test/clojure"
    ;; 配置文件路径
    :resources-path "src/main/resource" ; non-code files included in classpath/jar
    ;; 测试配置文件路径
    :dev-resources-path "src/test/resource" ; added to dev classpath but not jar
    ;; 本地依赖查找路径
    :native-path "src/native" ; where to look for native dependencies
    ;; 目标路径
    :target-dir "target/ " ; where to place the project's jar file
    ;; 额外的类路径
    :extra-classpath-dirs ["script"] ; more classpath entries not included in jar
    ;; jar包名
    :jar-name "sample.jar" ; name of the jar produced by 'lein jar'
    ;; 一样的，jar包名。uberjar是可执行的jar包
    :uberjar-name "sample-standalone.jar" ; as above for uberjar
    ;; 从~/.m2自定义classpath，而不是拷贝到:library-path.
    :local-repo-classpath true
    ;; java文件的编译目录
    :javac-options {:destdir "classes/"}
    :java-source-path "src/main/java" ; location of Java source
    ;; Leave the contents of :source-path out of jars (for AOT projects)
    :omit-source true
    ;; 匹配的jar包将被排除
    :jar-exclusions [#"(?:^|/).svn/"]
    ;; 一样的东东，只针对于uberjar
    :uberjar-exclusions [#"META-INF/DUMMY.SF"]
    ;; 对jar's manifest设置任意的键值对。
    :manifest {"Project-awesome-level" "super-great"}
    ;; 设置jvm选项
    :jvm-opts ["-Xmx1g"]
    ;; 如果你的项目是一个Leiningen插件，设置这个跳过subprocess步骤
    :eval-in-leiningen false
    ;; 解决Clojure's agent的线程池问题。
    ;; If you see RejectedExecutionException using
    ;; futures or agents, you may be working with a plugin that doesn't
    ;; take this workaround into account yet--see the "Threads" section
    ;; of doc/PLUGINS.md. This key will disable Leiningen's workaround.
    ;; It may cause some other plugins to fail to exit when they finish.
    :skip-shutdown-agents true
    ;; 设置一个多模块的maven项目的父项目。
    :parent [org.example/parent "0.0.1" :relative-path "../parent/pom.xml"])
    ;; You can use Robert Hooke to modify behaviour of any task function,
    ;; but the prepend-tasks function is shorthand that is more convenient
    ;; on tasks that take a single project argument.
    ;; 你可以使用Robert Hooke去修改任何task函数的行为。
    (use '[leiningen.core :only [prepend-tasks]]
    '[leiningen.deps :only [deps]]
    '[leiningen.clean :only [clean]]
    '[leiningen.pom :only [pom]])
    ;; 缩略名字参数调用
    (prepend-tasks #'deps clean pom)
