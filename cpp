1、linux下动态链接库找不到库的问题
解决方案：
    在/etc/ld.so.conf.d中添加一个conf文件，将动态库的地址配置进去。之后运行ldconfig命令，刷新配置路径（如果不刷新还是找不到文件）。

2、在eclipse中添加库文件
    eclipse中的文件并不是直接添加在makefile中，makefile会自动生成，如果修改了makefile会被新生成的makefile覆盖，不起作用。
解决方案：
    在objects.mk的LIBS:=后添加
