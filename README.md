# LogTyper
 如何用MC服务器日志炸掉QQ群

## 如何构建

Windows:

`gradlew build`

Linux/Unix:

`./gradlew build`

## 数据文件格式
在文本中插入以`%%%%DELAY`开头的行可以设置之后所有行输入的间隔时间，例如

`%%%%DELAY500`意为每行间隔500ms

具体用法见示例data.txt
