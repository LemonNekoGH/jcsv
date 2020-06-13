## JCSV - 一个用来读写CSV表格的简易工具库
这个工具库的功能非常简单，就是把Java对象转换成CSV表格并输出到文件，或者读取CSV表格并转换成Java对象。

这个工程包含两部分，第一部分是Java实现的工具库本体，第二部分是用Kotlin实现的扩展方法，如果你使用Kotlin，建议把这两部分都用上，这样的话能提高编程效率。

### 构建
这很简单，只要运行一个gradle task即可
~~~shell script
./gradlew jar
~~~