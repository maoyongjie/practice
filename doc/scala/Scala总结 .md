# Scala总结

# Scala的简介

![img](https://img-blog.csdn.net/20160330204241216)

官方网址：http://www.scala-lang.org/

Scala 是 Scalable Language 的简写，是一门多范式的编程语言，联邦理工学院洛桑（EPFL）的Martin Odersky于2001年基于Funnel的工作开始设计Scala。

Funnel是把函数式编程思想和Petri网相结合的一种编程语言，Odersky先前的工作是Generic Java和javac（Sun Java编译器）。他先带领创建了Java 5编译器，而后觉得Java有太多羁绊而发明了Scala。Scala编译器和类型系统非常强大，它的目标是尽量把软件错误消灭在编写过程中。Scala类型系统是图灵完备的，甚至可以在编译期间解决问题。

## 特点

1. Scala是一门多范式的编程语言，一种类似java的编程语言，设计初衷是实现可伸缩的语言 、并集成面向对象编程和函数式编程的各种特性。Scala 发音为(/ˈskɑːlə, ˈskeɪlə/)。

- Scala编程语言为很多开发者所喜爱。如果你粗略浏览Scala的网站，你发现Scala是一种纯粹的面向对象编程语言，而又无缝地结合了命令式编程和函数式编程风格。

2. Scala有几项关键特性表明了它的面向对象的本质。例如，Scala中的每个值都是一个对象，包括基本数据类型（即布尔值、数字等）在内，连函数也是对象。另外，类可以被子类化，而且Scala还提供了基于mixin的组合（mixin-based composition）。
3. 与只支持单继承的语言相比，Scala具有更广泛意义上的类重用。Scala允许定义新类的时候重用“一个类中新增的成员定义（即相较于其父类的差异之处）”。Scala称之为[mixin类组合](https://blog.csdn.net/anxiaoyuthss/article/details/71169971)。
4. Scala还包含了若干函数式语言的关键概念，包括高阶函数（Higher-Order Function）、局部套用（Currying）、嵌套函数（Nested Function）、序列解读（Sequence Comprehensions）等等。
5. Scala具备类型系统，通过编译时检查，保证代码的安全性和一致性。类型系统具体支持以下特性：
  - 泛型类
  - 协变和逆变
  - 标注
  - 类型参数的上下限约束
  - 把类别和抽象类型作为对象成员
  - 复合类型
  - 引用自己时显式指定类型
  - 视图
  - 多态方法
6. Scala可以与Java互操作。它用scalac这个编译器把源文件编译成Java的class文件（即在JVM上运行的字节码）。你可以从Scala中调用所有的Java类库，也同样可以从Java应用程序中调用Scala的代码。它可以访问现存的数之不尽的Java类库，这让大多数Java开发者迁移到Scala更加容易。

# 与Java的对比

- Java实现wordcount

~~~java
import java.io.IOException;
import java.util.*;

/**
 * @ClassName WordCount
 * @Description TODO
 * @Author HuYuXiao
 * @Date 2021/4/28 9:04
 * @Version 1.0
 **/
public class WordCount {
    public static void main(String[] args) throws IOException {
        ArrayList<String> wordList = new ArrayList<>();
        wordList.add("hello world");
        wordList.add("new world");
        wordList.add("hello spark");
        wordList.add("hello scala");
        wordList.add("new scala");
        Map<String, Integer> map = getWord(wordList);  //取出单词和单词出现的次数存入map中
        List<Map.Entry<String, Integer>> list = sortValue(map); //根据value对map进行排序
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    /**
     * 根据map的value对map进行排序
     *
     * @param map key：单词；value：出现的次数
     * @return 按倒叙方式排好序的list
     */
    private static List<Map.Entry<String, Integer>> sortValue(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        /**
         * Comparator（接口）是匿名内部类，compare是创建匿名内部类要实现的抽象方法
         * Comparator可看作一个排序器
         */
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            /**
             * 对list进行排序；o1和o2谁在compareTo之前，谁就从list第一位开始取，在compateTo之后的从第二位开始取
             * 当o2小于o1时（也就是返回值为-1时），交换o2和o1的位置
             * @param o1    list从第二位开始取
             * @param o2    list从第一位开始取
             * @return 返回0和1时位置不变，返回-1时交换当前o1和o2的位置
             */
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());  //compareTo是Comparable接口的的方法，返回值为1，0，-1
            }
        });
        return list;
    }

    /**
     * 将StringBuffer中的单词单个取出存入map中，单词作为key，出现的次数作为value
     * @param list 文本中取出来的内容
     * @return 将内容中的单词作为key，出现次数作为value存好的map
     */
    private static Map<String, Integer> getWord(ArrayList<String> list) {
        Map<String, Integer> map = new HashMap<>();//用TreeMap,存入后key就是有序的
//        String word = String.valueOf(new StringBuffer());
//        String[] str = word.split(" ");     //使用split将字符串分隔，" "分隔条件为空格*/
        for (int i = 0; i < list.size(); i++) {
            for (String str : list.get(i).split(" ")) {
                if (map.containsKey(str)) {
                    Integer value = map.get(str);
                    map.put(str, ++value);
                } else {
                    Integer value = 1;
                    map.put(str, value);
                }
            }
        }
        return map;
    }
}
~~~

- Scala实现wordcount

~~~scala
import scala.collection.immutable

object WordCount1 extends App {
  val wordList: immutable.Seq[String] = List("hello world","new world","hello spark","hello scala","new scala")
  //1，切分 压平 List(hello, world, new, world, hello, spark, hello, scala, new, scala)
  val resultList1 = wordList.flatMap(i => i.split(" "))
    //2，将每个单词进行tuple List((hello,1), (world,1), (new,1), (world,1), (hello,1), (spark,1), (hello,1), (scala,1), (new,1), (scala,1))
    .map(i => (i, 1))
    //3,以key进行分组 Map(world -> List((world,1), (world,1)), spark -> List((spark,1)), new -> List((new,1), (new,1)),
    // scala -> List((scala,1), (scala,1)), hello -> List((hello,1), (hello,1), (hello,1)))
    .groupBy(i => i._1)
    //4,统计value的长度 Map(world -> 2, spark -> 1, new -> 2, scala -> 2, hello -> 3)
    .mapValues(i => i.size)
    //5,排序 List((spark,1), (world,2), (new,2), (scala,2), (hello,3))
    .toList.sortBy(i => i._2)
    //  6,降序反转  List((hello,3), (scala,2), (new,2), (world,2), (spark,1))
    .reverse
  println(resultList1)
  val wordList2: immutable.Seq[(String, Int)] = wordList.flatMap(_.split(" ")).map((_, 1)).groupBy(_._1).mapValues(_.size).toList.sortBy(_._2).reverse
  println(wordList2)
}
~~~

# 一、变量声明

**1、val/var 变量标识: 变量类型 = 初始值**

- val 定义的是不可重新赋值的变量
- var 定义的是可重新赋值的变量
- **Scala可以自动根据变量的值来自动推断变量的类型，这样编写代码更加简洁。**：val name = "tom"

**2、惰性赋值**

- 当有一些变量保存的数据较大时，但是不需要马上加载到JVM内存。可以使用惰性赋值来提高效率。

```scala
lazy val/var 变量名 = 表达式 
```

# 二、字符串

- 使用双引号

  ```scala
  val/var 变量名 = “字符串”
  ```

  

- 使用插值表达式

  ```scala
  val/var 变量名 = s"${变量/表达式}字符串" 
  ```

  

- 使用三引号

  ```scala
  val/var 变量名 = """字符串1
  字符串2"""
  ```

# 三、数据类型与操作符

## 1、数据类型

- 在Scala语言中 不存在基本数据类型，**所有类型都是Objects** 。对象（类）

![Scala的数据类型](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\Scala的数据类型.png)

## 2、操作符

- Scala中没有，++、--运算符

- 在Scala中，可以直接使用 == 、 != 进行比较，它们与 equals 方法表示一致，而比较两个对象的引用值，使用 eq

## 3、Scala 类型层次结构

- 在Scala语言中所有类型的【祖宗类】为【Any】，又分为【值类型AnyVal】和【引用类型AnyReg】。

![类型层次结构](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\类型层次结构.png)

# 四、条件表达式

## 1、有返回值的if

- 在Scala中，没有三元表达式，可以使用if表达式替代三元表达式

```scala
scala> val sex = "male"
sex: String = male
scala> val result = if(sex == "male") 1 else 0
result: Int = 1
```

## 2、IF…ELSE 表达式

- 于Java 的使用方法一致。

## 3、块表达式

- Scala中，使用{}表示一个块表达式。
- 和if表达式一样，块表达式也是有值的。
- 值就是这个{}最后一个表达式的值。

# 五、循环

## 1、Range 表达式

![range表达式](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\range表达式.png)

## 2、for 表达式

```scala
for(i <- 表达式/数组/集合) {
  // 表达式
}
```

**嵌套循环**

```scala
for(i <- 1 to 3; j <- 1 to 5) {print("*");if(j == 5) println("")} 
```

**IF 守卫**

- for表达式中，可以添加if判断语句，这个if判断就称之为守卫 ，可以使用守卫让for表达式更简洁。

使用for表达式打印1-10之间能够整除3的数字

```scala
for(i <- 1 to 10 if i % 3 == 0) println(i)
```

**FOR 推导式**

- 在for循环体中，可以使用yield表达式构建出一个集合，我们把使用yield的for表达式称之为推导式。

```scala
// for推导式：for表达式中以yield开始，该for表达式会构建出一个集合
val v = for(i <- 1 to 10) yield i * 10
```

## 3、while循环

Scala中while循环和Java中是一致的

## 4、实现 break

- 如果一定要使用break/continue，就需要 使用 scala.util.control 包的Break类的breable和break方法 。

用法：

- 导入Breaks包 import scala.util.control.Breaks._
- 使用 breakable 将for表达式包起来。
- for表达式中 需要退出循环的地方，添加 break() 方法调用

```scala
//使用for表达式打印1-100的数字，如果数字到达50，退出for表达式
import scala.util.control.Breaks._
breakable{
  for(i <- 1 to 100) {
    if(i >= 50) break()
    else println(i)
 }
}
```

# 六、方法

## 一、定义方法

```scala
def methodName (参数名:参数类型, 参数名:参数类型) : [return type] = {
  // 方法体：一系列的代码
}
```

- 参数列表的参数类型不能省略
- 返回值类型可以省略，由Scala编译器自动推断
- 返回值可以不写return，默认就是{}块表达式的值

## 二、方法参数

1. 默认参数：在定义方法时可以给参数定义一个默认值。
2. 带名参数：在调用方法时，可以指定参数的名称来进行调用。
3. 变长参数：如果方法的参数是不固定的，可以定义一个方法的参数是变长参数。

```scala
//调用方法，传入以下数据：1,2,3,4,5
scala> def add(num: Int*) = num.sum
add: (num: Int*)Int
scala> add(1, 2, 3, 4, 5)
res1: Int = 15
```

- 在参数类型后面加一个 * 号，表示参数可以是0个或者多个

## 三、方法调用方式

- 在Scala中，有以下几种方法调用方式，在后续编写Spark、Flink程序时，会使用到这些方法调用方式。
  - 后缀调用法：对象名.方法名(参数)  [Math.abs(-1)]
  - 中缀调用法：对象名 方法名 参数 [Math abs -1]

# 七、函数

## 一、定义函数

```scala
val 函数变量名 = (参数名:参数类型, 参数名:参数类型....) => 函数体 
```

- 函数是一个对象（变量）
- 类似于方法，函数也有输入参数和返回值
- 函数定义不需要使用 def 定义
- 无需指定返回值类型

## 二、匿名函数

![匿名函数](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\匿名函数.png)

## 三、方法和函数的区别

- 方法是隶属于类或者对象的，在运行时，它是加载到JVM的方法区中。
-  可以将函数对象赋值给一个变量，在运行时，它是加载到JVM的堆内存中。
- 方法无法赋值给变量，而函数则可以。

## 四、方法转换为函数

- 有时候需要将方法转换为函数，作为变量传递，就需要将方法转换为函数
- 使用 _ 即可将方法转换为函数

```scala
scala> def add(x:Int,y:Int) = x+y
add: (x: Int, y: Int)Int
scala> val a = add _
a: (Int, Int) => Int = <function2>
```

# 八、数组

## 一、定长数组

- 定长数组指的是数组的长度是不允许改变的

- 数组的元素是可以改变的

  ```scala
  // 通过指定长度定义数组
  val/var 变量名 = new Array[元素类型](数组长度)
  // 用元素直接初始化数组
  val/var 变量名 = Array(元素1, 元素2, 元素3...)
  ```

  

## 二、变长数组

- 变长数组指的是数组的长度是可变的，可以往数组中添加、删除元素

```scala
import scala.collection.mutable.ArrayBuffer
//创建空的 ArrayBuffer 变长数组，语法结构：
val a = ArrayBuffer[元素类型]() 
//创建带有初始元素的 ArrayBuffer
val a = ArrayBuffer(元素1，元素2，元素3....) 
```

## 三、添加/修改/删除元素

- 使用 += 添加元素
- 使用 -= 删除元素
- 使用 ++= 追加一个数组到变长数组

## 四、遍历数组

```scala
val a = Array(1,2,3,4,5)
//使用for表达式直接遍历，并打印数组的元素
for(i<-a) println(i)
//使用for表达式基于索引下标遍历，并打印数组的元素
for(i <- 0 to a.length - 1) println(a(i))
for(i <- 0 until a.length) println(a(i))
```

## 五、数组常用算法

```scala
package cn.itcast.scala.arr

import scala.collection.mutable.ArrayBuffer

object ArrayDemo03 {
	
	def main(args: Array[String]): Unit = {
		
		// 定义一个数组，不可变的数组（定长数组）
		val array: Array[Int] = Array(12, -98, 89, 34, 0, 9, 45)
		
		// 方法：sum、max、min、sorted
		// TODO: sum 方法-> 求和
		println(s"sum = ${array.sum}")
		
		// TODO: max方法-> 最大值
		println(s"max = ${array.max}")
		
		// TODO: min-> 最小值
		println(s"min = ${array.min}")
		
		// TODO： sorted-> 对数组中数据进行排序
		println(array.mkString(", "))
		// 默认按照升序排序
		println(s"sorted : ${array.sorted.mkString(", ")}")
		// 使用reverse方法进行反转
		println(s"reverse sorted : ${array.sorted.reverse.mkString(", ")}")
	}
	
}
```

# 九、元组

- 元组可以用来包含 一组不同类型的值。
- 元组的元素是不可变的。

## 一、定义元组

```scala
//使用括号来定义元组
val/var 元组 = (元素1, 元素2, 元素3....) 
//使用箭头来定义元组（元组只有两个元素）
val/var 元组 = 元素1->元素2
```

## 二、访问元组

```scala
val a = "zhangsan" -> "male"
// 获取第一个元素
scala> a._1
res41: String = zhangsan
// 获取第二个元素
scala> a._2
res42: String = male
```

# 十、集合类库

![集合类](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\集合类.png)

- Iterable[T] 是所有可遍历的集合，它提供了迭代的方法(foreach)。 Seq[T] 是有序集合， Set[T] 是数学上的集合(无序且不重复)， Map[T] 是关联数组，也是无序的。

## 一、列表

### 1、不可变列表

```scala
//使用 List(元素1, 元素2, 元素3, ...) 来创建一个不可变列表，语法格式：
val/var 变量名 = List(元素1, 元素2, 元素3...) 
//使用 Nil 创建一个不可变的空列表
val/var 变量名 = Nil 
//使用 :: 方法创建一个不可变列表，必须在最后添加一个 Nil
val/var 变量名 = 元素1 :: 元素2 :: Nil 
```

### 2、可变列表

- 可变集合都在 mutable 包中
- 不可变集合都在 immutable 包中（默认导入）

```scala
import Scala.collection.mutable.ListBuffer
//使用ListBuffer[元素类型]()创建空的可变列表，语法结构：
val/var 变量名 = ListBuffer[Int]() 
//使用ListBuffer(元素1, 元素2, 元素3...)创建可变列表，语法结构：
val/var 变量名 = ListBuffer(元素1，元素2，元素3...)
```

### 3、可变列表操作

```scala
// 导入不可变列表
import Scala.collection.mutable.ListBuffer
//创建可变列表
val a = ListBuffer(1,2,3)
//获取元素（使用括号访问 (索引值) ）
a(0)
//添加元素（ += ）
a += 4
//追加一个列表（ ++= ）
a ++= List(5,6,7)
//删除元素（ -= ）
a -= 7
//转换为不可变列表
a.toList
//转换为Array（ toArray ）
a.toArray
//判断列表是否为空
a.isEmpty
//拼接两个列表
val b = List(4,5,6)
a ++ b
//获取列表的首个元素和剩余部分
a.head     a.tail
//反转列表
a.reverse
//获取列表前缀和后缀
a.take(3) [获取前面列表的前三个数据] 
a.drop(3) [获取列表除去前三个数据剩下的数据]
//扁平化(压平)
val a = List(List(1,2), List(3), List(4,5))
a.flatten --->List(1, 2, 3, 4, 5)
//拉链与拉开
val a = List("zhangsan", "lisi", "wangwu")
val b = List(19, 20, 21)
a.zip(b) --->res1=List((zhangsan,19), (lisi,20), (wangwu,21))
res1.unzip --->(List(zhangsan, lisi, wangwu),List(19, 20,21))
//转换字符串
println(a.toString)[列表内的数据类型变成了字符串，但是还是列表]
//生成字符串
a.mkString[列表变成了字符串类型]
a.mkString(":") --->String = 1:2:3:4
//合并（并集）
val a1 = List(1,2,3,4)
val a2 = List(3,4,5,6)
a1.union(a2) --->List(1, 2, 3, 4, 3, 4, 5, 6)
//可以调用distinct去重
a1.union(a2).distinct -->List(1, 2, 3, 4, 5, 6)
//交集
a1.intersect(a2) [表示这两个列表由共同的数据]
//差集
a1.diff(a2)[表示a1中的数据除去a2中有相同的，剩下的数据]
```

## 二、set 

- 元素不重复
- 不保证插入顺序

### 1、不可变集

```scala
//创建一个空的不可变集，语法格式：
val/var 变量名 = Set[类型]() 
//给定元素来创建一个不可变集，语法格式：
val/var 变量名 = Set(元素1, 元素2, 元素3...) 
```

### 2、基本操作

- 获取集的大小（ size ）
- 遍历集（ 和遍历数组一致 ）

针对不可变的集合Set来说，如果删除其中某个元素，重新生成一个新的集合Set，原来集合Set是不变的。

- 添加一个元素，生成一个Set（ + ）
- 拼接两个集，生成一个Set（ ++ ）
- 拼接集和列表，生成一个Set（ ++ ）

### 3、可变集

```scala
import scala.collection._
val bufferSet =  mutable.Set(1, 2, 2, 3, 3, 3, 4, 5)
```

# 十一、映射[Map]

## 一、不可变Map

```scala
val map = Map(键->值, 键->值, 键->值...) // 推荐，可读性更好
val map = Map((键, 值), (键, 值), (键, 值), (键, 值)...)
```

## 二、可变Map

```scala
import Scala.collection._
val map = mutable.Map("zhangsan"->30, "lisi"->40)
```

## 三、Map基本操作

```scala
package cn.itcast.scala.collection

object MapDemo01 {
	
	def main(args: Array[String]): Unit = {
		
		// 定义不可变Map映射
		val map: Map[Int, String] = Map(1 -> "a", 2 -> "b", 3 -> "c")
		
		// 依据key获取value值
		map(1)
		
		// 此种方式使用较多
		println(map.getOrElse(4, "null"))
		
		// TODO: 获取所有key
		println(map.keys)
		
		// TODO：获取所有value
		println(map.values)
		
		// 遍历Map集合元素
		for(item <- map) println(item)
		
		for((key, value) <- map) println(s"key = $key, value = $value")
	}
}
```

# 十二、iterator迭代器

- Scala中所有集合类（List、Set和Map）都可以转换为迭代器Iterator对象，使用hashNext和next判断与遍历元素，具体演示代码下：

```scala
package cn.itcast.scala.collection

object IteratorDemo01 {
	
	def main(args: Array[String]): Unit = {
		
		// 定义一个列表List
		val list: List[Int] = List(1, 2, 3, 4, 5)
		
		// 从列表List中获取迭代器
		val iter: Iterator[Int] = list.iterator
		
		// 使用While循环遍历迭代器中元素
		while (iter.hasNext){
			println(iter.next())
		}
	}
}
```

# 十三、函数式编程

## 一、扁平化映射 | flatMap

- flatMap方法参数类型也是函数类型，同样对集合中每个元素进行操作，但是要求每个元素操作以后，返回类型是集合，最终进行扁平化操作。
- 可以理解：flatMap方法就是 map + flatten

```scala
package cn.itcast.scala.operator

/**
 * 讲解列表List中常见高阶函数的使用：flatMap函数
 */
object OperatorDemo02 {
	
	def main(args: Array[String]): Unit = {
	
		/*
			词频统计WordCount：
				hadoop spark spark flink flink
				spark flink spark
		 */
		val lineList: List[String] = List("hadoop spark spark flink flink", "spark flink spark")
	
		// a. 对每行数据进行单词分割
		/*
		lineList.map(
			(line: String) => {
				line.split("\\s+")
			}
		)*/
		val list: List[Array[String]] = lineList.map(line => line.split("\\s+"))
		
		// b. 进行扁平化操作：
		val wordsList: List[String] = list.flatten
		
		// TODO: flatMap
		lineList.flatMap(line => line.split("\\s+"))
		
	}
}
```

## 二、映射 | map

- 因为进行数据计算的时候，就是一个将一种数据类型转换为另外一种数据类型的过程。

- map方法接收一个函数，将这个函数应用到每一个元素，返回一个新的列表

```scala
scala> val a = List(1,2,3,4)
a: List[Int] = List(1, 2, 3, 4)
scala> a.map(_ + 1)
res4: List[Int] = List(2, 3, 4, 5)
```

## 三、遍历 | foreach

```scala
// 定义一个列表
scala> val a = List(1,2,3,4)
a: List[Int] = List(1, 2, 3, 4)
// 迭代打印
scala> a.foreach((x:Int)=>println(x))
```

## 四、过滤 | filter

```scala
scala> List(1,2,3,4,5,6,7,8,9).filter(_ % 2 == 0)
res8: List[Int] = List(2, 4, 6, 8)
```

- 过滤出filter中的条件为true的数据。

## 五、排序

### 1、sorted默认排序

```scala
scala> List(3,1,2,9,7).sorted
res16: List[Int] = List(1, 2, 3, 7, 9)
```

- 默认为升序排序

### 2、指定字段排序 | sortBy

```scala
scala> val a = List("01 hadoop", "02 flume", "03 hive", "04 spark")
a: List[String] = List(01 hadoop, 02 flume, 03 hive, 04 spark)
// 获取单词字段
scala> a.sortBy(_.split(" ")(1))
res8: List[String] = List(02 flume, 01 hadoop, 03 hive, 04 spark)
```

### 3、自定义排序 | sortWith

```scala
//降序排序
list.sortWith((x1, x2) => x1 > x2)
//升序排序
list.sortWith((x1, x2) => x1 < x2)
```

## 六、分组 | groupBy

- groupBy方法，对集合中每个元素进行操作，按照指定规则划分到每个组中，用Map的格式进行存储。

```scala
scala> val a = List("张三"->"男", "李四"->"女", "王五"->"男")
a: List[(String, String)] = List((张三,男), (李四,女), (王五,男))
// 按照性别分组
scala> a.groupBy(_._2)
res0: Scala.collection.immutable.Map[String,List[(String, String)]] =
Map(男 -> List((张三,男), (王五,男)),女 -> List((李四,女)))
```

## 七、聚合操作

- 对集合中数据进行聚合时，往往需要**中间临时变量temp**，具有多少临时变量和变量的类型需要依据业务而定。
- reduce聚合函数与fold聚合函数最大区别在于，fold可以让用户设置聚合时中间临时变量的初始值。

### 1、聚合 | reduce

![reduce](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\reduce.png)

### 2、reduce Right

![reduceRight](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\reduceRight.png)

### 3、折叠 | fold

![fold](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\fold.png)

# 十四、OOP 面向对象编程

## 一、类和对象

- 使用 class 来定义一个类
  - 如果类是空的，没有任何成员，可以省略 {}
- 使用 new 来创建对象
  - 如果构造器的参数为空，可以省略 ()

## 二、定义和访问成员变量

- 在类中使用 var/val 来定义成员变量
  - 在定义 var 类型的成员变量时，可以使用 _ 来初始化成员变量
- 对象直接使用成员变量名称来访问成员变量

## 三、定义成员方法

- 在Scala的类中，也是使用 def 来定义成员方法

## 四、访问修饰符

- Java中的访问控制，同样适用于Scala，可以在成员前面添加private/protected关键字来控制成员的可见性。

- 在Scala中， 没有 public关键字 

- 任何没有被标为private或protected的成员都是公共的

## 五、类的构造器

### 1、主构造器

- 每个类只有一个主构造方法，紧跟在类名称之后

```scala
package cn.itcast.scala.oop.demo06

/**
 * 在Scala语言中，构造方法（构造器）分为2种：
 * - 1. 主构造方法：1个
 *         紧跟类名称之后，使用一对圆括号括起来，如果主构造方法中没有变量，省略圆括号
 *         在主构造方法定义变量
 *              - 当变量时用val定义时，创建对象以后，此变量不能被修改
 *              - 当变量使用var定义时，创建对象以后，变量值能被修改
 * - 2. 附属构造方法：多个
 *          在类中定义的方法，方法名称必须为this
 *          在附属构造方法中第一行必须调用主构造方法或者其他附属构造方法
 */
class Person(val name: String, var age: Int = 0){
	override def toString: String = s"name = $name, age = $age"
}

object ClassDemo06 {
	
	def main(args: Array[String]): Unit = {
		// 创建对象, 调用的是主构造方法
		val person = new Person("itheima", 14)
		println(person)
		
		//person.name = "itcast"
		person.age = 15
		println(person)
		
		val p = new Person("itcast")
		println(p)
		p.age = 14
		println(p)
	}
}
```

### 2、辅助构造器

- 定义辅助构造器与定义方法一样，也使用 def 关键字来定义
- 这个方法的名字为`this `
- 辅助构造器的第一行代码，必须要调用主构造器或者其他辅助构造器

```scala
package cn.itcast.scala.oop.demo06

/**
 * 在Scala语言中，构造方法（构造器）分为2种：
 * - 1. 主构造方法：1个
 *         紧跟类名称之后，使用一对圆括号括起来，如果主构造方法中没有变量，省略圆括号
 *         在主构造方法定义变量
 *              - 当变量时用val定义时，创建对象以后，此变量不能被修改
 *              - 当变量使用var定义时，创建对象以后，变量值能被修改
 * - 2. 附属构造方法：多个
 *          在类中定义的方法，方法名称必须为this
 *          在附属构造方法中第一行必须调用主构造方法或者其他附属构造方法
 */
class Person(val name: String, var age: Int = 0){
	
	// 定义一个成员变量
	var gender: String = _
	
	// 定义附属构造器
	def this(name: String, age: Int, gender: String){
		// 调用主构造方法
		this(name, age)
		this.gender = gender
	}
	
	// 定义附属构造器
	def this(name: String, gender: String){
		// 调用主构造方法
		this(name)
		this.gender = gender
	}	
	
	override def toString: String = s"name = $name, age = $age"
}

object ClassDemo06 {
	
	def main(args: Array[String]): Unit = {
		// 创建对象, 调用的是主构造方法
		val person = new Person("itheima", 14)
		println(person)
		
		//person.name = "itcast"
		person.age = 15
		println(person)
		
		println("=========================================")
		
		val p = new Person("itcast")
		println(p)
		p.age = 14
		println(p)
		
		println("=========================================")
		// 调用附属构造方法
		val p1 = new Person("xuanxuan", 3, "female")
		println(p1)
		
		// 调用附属构造方法
		val p2: Person = new Person("zixuan", "female")
		println(p2)
	}
}
```

# 十五、单例对象（Object）

- 要定义类似于Java的static变量、static方法，就要使用到Scala中的单例对象： object 。

## 一、定义单例对象

- 定义单例对象和定义类很像，就是把class换成object
- 在object中定义的成员变量类似于Java的静态变量
- 可以使用object直接引用成员变量 

```scala
ackage cn.itcast.scala.oop.demo09
// 定义一个单例对象
object Dog {
  // 定义腿的数量
  val LEG_NUM = 4
}
object ObjectDemo01 {
 def main(args: Array[String]): Unit = {
  println(Dog.LEG_NUM)
}
}
```

## 二、定义成员方法

- 在object中定义的成员方法类似于Java的静态方法。

- 可以使用object直接引用成员方法 

# 十六、main方法

- 在Scala中，这个main方法必须放在一个单例对象中 

## 一、定义main方法

```scala
def main(args:Array[String]):Unit = {
  // 方法体
}
```

## 二、实现App Trait来定义入口

- 其效果与main方法是一致的。

```scala
object 单例对象名 extends App {
  // 方法体
}
```

# 十七、伴生对象

- 一个class和object具有同样的名字。这个object称为伴生对象，这个class称为伴生类。
  - 伴生对象必须要和伴生类一样的名字
  - 伴生对象和伴生类在同一个scala源文件中
  - 伴生对象和伴生类可以互相访问private属性 

```scala
class CustomerService {
  def save(): Unit = {
    println(s"${CustomerService.SERVICE_NAME}: 保存客户")
 }
}
// CustomerService的伴生对象
object CustomerService {
  private val SERVICE_NAME = "CustomerService"
}
object ObjectDemo04 {
 def main(args: Array[String]): Unit = {
  val customerService = new CustomerService()
  customerService.save()
}
}
```

## 一、apply方法

### 1、定义apply方法

```scala
object 伴生对象名 {
def apply(参数名:参数类型, 参数名:参数类型...) = new 类(...)
}
```

```scala
class Person(val name: String, val age: Int)
object Person {
  // 定义apply方法，接收两个参数
  def apply(name:String, age:Int) = new Person(name, age)
}
object ApplyDemo01 {
 def main(args: Array[String]): Unit = {
  // 使用伴生对象名称来创建对象
  val zhangsan = Person("张三", 20)//这种写法非常简便，不需要写一个new，然后敲一个空格，再写类名。
  println(zhangsan.name)
  println(zhangsan.age)
}
}
```

# 十八、继承

## 一、定义语法

- scala和Java一样， 使用extends关键字来实现继承
- 可以在子类中定义父类中没有的字段和方法，或者重写父类的方法
- **类和单例对象都可以从某个父类继承**

```scala
class/object 子类 extends 父类 {
 ..
}
```

- 类似于Java语言，在 子类中使用override需要来重写父类的成员，可以使用super来引用父类
- **在Scala语言中，子类中不可以使用super关键字调用父类变量**

# 十九、类型判断

## 一、isInstanceOf/asInstanceOf

```scala
// 判断对象是否为指定类型
val trueOrFalse: Boolean = 对象.isInstanceOf[类型]
// 将对象转换为指定类型
val 变量 = 对象.asInstanceOf[类型]
```

## 二、getClass和classOf

- isInstanceOf 只能判断对象是否为指定类以及其子类的对象，而不能精确的判断出

- 方式一、针对对象获取，调用`getClass`方法
- 方式二、针对类获取，使用`classOf`方法

# 二十、抽象类

- 定义抽象类和Java一样，在类前面加上abstract关键字

- 方法没有方法体（抽象方法）
- 变量没有初始化（抽象字段）

```scala
// 定义抽象类
abstract class 抽象类名 {
 // 定义抽象字段
 val 抽象字段名:类型
 // 定义抽象方法
 def 方法名(参数:参数类型,参数:参数类型...): 返回类型
}
```

# 二十一、匿名内部类

- 匿名内部类是没有名称的子类，直接用来创建实例对象。

```scala
val/var 变量名 = new 类/抽象类 {
  // 重写方法
}
```

# 二十二、特质(trait)

- Scala中没有Java中的接口（interface），替代的概念是—— 特质Trait .

## 一、定义

```scala
trait 名称 {
  // 抽象字段
  // 抽象方法
}
```

- 使用 extends 来继承trait（Scala不论是类还是特质，都是使用extends关键字）
- 如果要继承多个trait，则使用 with 关键字
- 在Scala的特质Trait中，既可以存在`抽象字段`和`抽象方法`，又可以存放`具体字段`和`具体方法`。

补充：

- 在Java语言中，接口interface，其中只能包含：常量和抽象方法。

# 二十三、样例类（Case Class）

- 样例类（Case Class） 是一种特殊类，它可以 用来快速定义一个用于保存数据的类 （类似于Java POJO类）

## 一、定义样例类

```scala
case class 样例类名([var/val] 成员变量名1: 类型1, 成员变量名2: 类型2[, ...] ) 
```

- 如果要实现某个成员变量可以被修改，可以添加var
- 默认为val，可以省略

## 二、样例类的方法

当定义一个样例类，编译器自动帮助我们实现了以下几个有用的方法：

apply方法：建立对象的时候可以省略new。
toString方法
equals方法
hashCode方法：
copy方法：样例类实现了copy方法，可以快速创建一个相同的实例对象，可以使用带名参数指定给成员进行重新赋值

# 二十四、模式匹配

![模式匹配](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\模式匹配.png)

## 一、简单模式匹配

```scala
变量 match {
  case "常量1" => 表达式1
  case "常量2" => 表达式2
  case "常量3" => 表达式3
  case _ => 表达式4 // 默认配
}
```

## 二、匹配类型

```scala
变量 match {
  case 类型1变量名: 类型1 => 表达式1
  case 类型2变量名: 类型2 => 表达式2
  case 类型3变量名: 类型3 => 表达式3
 ...
  case _ => 表达式4
}
```

## 三、守卫

```scala
readVal match {
case _ if readVal >= 0 && readVal <= 3 => println("[0-3]")
case _ if readVal >= 4 && readVal <= 8 => println("[3-8]")
case _ => println("未匹配")
}
```

## 四、匹配样例类

```scala
zhangsan match {
case Person(name, age) => println(s"姓名：${name}, 年龄：${age}")
case Order(id1) => println(s"ID为：${id1}")
case _ => println("未匹配")
}
```

## 五、匹配元组

```scala
tuple match {
case (1, x, y) => println(s"三个元素，1开头的元组：1, ${x}, ${y}")
case (x, y, 5) => println(s"三个元素，5结尾的元组：${x}, ${y}, 5")
case _ => println("未匹配")
}
```

## 六、变量声明中的模式匹配

```Scala
//生成包含0-10数字的数组，使用模式匹配分别获取第二个、第三个、第四个元素
val array = (1 to 10).toArray
val Array(_, x, y, z, _*) = array
println(x, y, z)
//生成包含0-10数字的列表，使用模式匹配分别获取第一个、第二个元素
val list = (1 to 10).toList
val x :: y :: tail = list
println(x, y)
println(tail)
```

# 二十五、Option类型

- 使用Option类型，可以用来有效避免空引用(null)异常。也就是说，将来返回某些数据时，可以返回一个Option类型来替代。

![option](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\option.png)

```scala
package cn.itcast.scala.pattern.demo05

object OptionDemo01 {
	
	// 定义一个两个数相除的方法，使用Option类型来封装结果
	def div(a: Int, b: Int): Option[Double] = {
		if(b != 0){
			val result: Double = a / b.toDouble
			// 有值：返回Some
			Some(result)
		}else{
			None
		}
	}
	
	
	def main(args: Array[String]): Unit = {
	
		val result: Option[Double] = div(10, 2)
		//println(s"result = ${result.get}")
		
		result match {
			case Some(x) => println(s"result = $x")
			case None => println("none")
		}
		
		// 映射Map集合
		val map = Map("a" -> 1, "b" -> 2, "c" -> 3)
		
		/*
		def getOrElse[B1 >: B](key: A, default: => B1): B1 = get(key) match {
		    case Some(v) => v
		    case None => default
		  }
		 */
		map.getOrElse("a", "none")
	}
}
```

# 二十六、偏函数

- 偏函数被包在花括号内没有match的一组case语句是一个偏函数
- 偏函数是PartialFunction[A, B]的一个实例
  - A代表输入参数类型
  - B代表返回结果类型

```scala
object MatchDemo09 {
def main(args: Array[String]): Unit = {
//定义一个列表，包含1-10的数字
val list = (1 to 10).toList
val list2 = list.map{
//请将1-3的数字都转换为[1-3]
case x if x >= 1 && x <= 3 => "[1-3]"
//请将4-8的数字都转换为[4-8]
case x if x >= 4 && x <= 8 => "[4-8]"
//将其他的数字转换为(8-*]
case x if x > 8 => "(8-*]"
}
println(list2)
}
```

# 二十七、正则表达式

![正则表达式](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\正则表达式.png)

```scala
package cn.itcast.scala.regex

import scala.util.matching.Regex

object RegexDemo01 {
	
	def main(args: Array[String]): Unit = {
		
		// step 1. 定义正则表达式, 邮箱: xxxx  @  126  .  com
		val regex: Regex = """.+@.+\..+""".r
		
		val email: String = "123534@qq.com"
		
		// step 2. 使用正则匹配字符串
		val matchOption: Option[Regex.Match] = regex.findFirstMatchIn(email)
		
		matchOption match {
			case Some(v) => println(v)
			case None => println("匹配不成功")
		}
	}
}
```

# 二十八、异常处理

## 一、捕获异常

```scala
try {
  // 代码
}catch {
  case ex: 异常类型1 => // 代码
  case ex: 异常类型2 => // 代码
}finally {
  // 代码
}
```

## 二、抛出异常

- 也可以在一个方法中，抛出异常。语法格式和Java类似，使用 throw new Exception

# 二十九、提取器(Extractor)

- 那 是不是所有的类都可以进行这样的模式匹配呢 ？答案是： 不可以 的，要支持模式匹配，必须要实现一个提取器。
- 与apply相反，unapply是将该类的对象，拆解为一个个的元素。
- 要实现一个类的提取器，只需要在该类的伴生对象中实现一个unapply方法即可。

![提取器](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\提取器.png)

```scala
def unapply(stu: Student): Option[(类型1, 类型2, 类型3...)] = {
  if(stu != null) {
    Some((变量1, 变量2, 变量3...))
 }
  else {
    None
 }
}
```

# 三十、泛型

- 使用方括号[]来定义类型参数 。

## 一、泛型方法

```scala
def 方法名[T](..) = {
  //...
}
```

## 二、泛型类

```Scala
class 类[T](val 变量名: T) 
```

- 定义一个泛型类， 直接在类名后面加上方括号，指定要使用的泛型参数
- 指定类对应的泛型参数后，就使用这些类型参数来定义变量了

## 三、上下界

- 在定义方法/类的泛型时，限定必须从哪个类继承、或者必须是哪个类的父类。此时，就需要使用到 上下界 。

### 1、上界定义

- 使用 <: 类型名 表示给类型添加一个上界，表示泛型参数必须要从该类（或本身）继承，即必须是这个类的子类或本身。

```
[T <: 类型] 
```

### 2、下界定义

- 而下界是必须是某个类的父类（或本身）

```
[T >: 类型] 
```

- **如果类既有上界、又有下界。 下界写在前面，上界写在后面**

## 四、协变、逆变、非变

![泛型](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\泛型.png)

- 默认泛型类是非变的
  - 类型B是A的子类型，Pair[A]和Pair[B]没有任何从属关系

- 类型B是A的子类型，Pair[B]可以认为是Pair[A]的子类型

- 类型B是A的子类型，Pair[A]反过来可以认为是Pair[B]的子类型

# 三十一、柯里化

- 柯里化（Currying）是指将原先接受多个参数的方法转换为多个 只有一个参数 的参数列表的过程。

```scala
// TODO: 通常采用柯里化方式，函数为高阶函数：有多个参数，其中一个参数类型为函数
def operation(x: Int, y: Int)(func: (Int, Int) => Int): Int = {
// 将参数x和y，传递到参数func中进行处理
func(x, y)
}

// 调用柯里化
println(operation(1, 99)((a, b) => a + b))
println(operation(1, 99)((a, b) => a - b))
println(operation(1, 99)((a, b) => a * b))
```

# 三十二、闭包

- 闭包其实就是一个函数，只不过这个函数的返回值依赖于声明在函数外部的变量。可以简单认为，就是 可以访问不在当前作用域范围的一个函数 。

```Scala
package cn.itcast.scala.func
object FuncDemo02 {
def main(args: Array[String]): Unit = {
// 定义一个变量
val y: Int = 1
// 定义一个变量：匿名函数赋值
val add = (x: Int) => x + y
// 此时add就是闭包
println(add(99))
}
}
```

# 三十三、隐式转换

- 所谓隐式转换，是指 以 implicit 关键字声明的带有单个参数的方法 。它是自动被调用的，自动将某种类型转换为另外一种类型。

隐式转换（ IMPLICIT CONVERSIONS） 主要有三种方式，如下截图所示：

![隐式转换](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\隐式转换.png)

## 一、隐式转换的时机

1、当使用对象调用类中不存在的方法或成员时，编译器会自动将对象进行隐式转换（超人变身案例）
2、方法中所定义的接收参数类型与调用方法时传递的参数类型不匹配时（特殊人群买票案例）

```scala
package cn.itcast.scala.advance.demo02

// 定义一个类：普通人
class Man(val name: String)

object Man{
	// 定义一个函数：隐式转换函数
	/*
		implicit def man2SuperMan(man: Man): SuperMan = {
			new SuperMan(man.name)
		}
	 */
}

// 定义一个类：奥特曼，超人
class SuperMan(val name: String){
	
	def emitLaser(): Unit = {
		println("emit Laser..........................")
	}
	
}


object Utils{
	// 定义一个函数：隐式转换函数
	implicit def man2SuperMan(man: Man): SuperMan = {
		new SuperMan(man.name)
	}
}

/**
 * 实现功能：奥特曼打怪兽
 *      TODO: 让普通人Man具有超人SuperMan能力，可以发射激光
 */
object ImplicitDemo02 {
	
	// 定义一个函数：隐式转换函数
	/*
		implicit def man2SuperMan(man: Man): SuperMan = {
			new SuperMan(man.name)
		}
	*/
	
	def main(args: Array[String]): Unit = {
	
		// 怪兽未出现
		val man: Man = new Man("大古")
		
		// 怪兽出现: Man -> SuperMan
		// 如果将隐式转换函数没有定义在当前作用域和源类型伴生对象中，而是其他地方，就需要手动导入函数
		import cn.itcast.scala.advance.demo02.Utils._
		man.emitLaser()
	}
}
```

# 三十四、Actor

# 一、Java中的多线程

- 在Java并发编程中， 每个对象都有一个逻辑监视器（monitor），可以用来控制对象的多线程访问 。
- 添加sychronized关键字来标记，需要进行同步加锁访问。
- 这样，通过加锁的机制来确保同一时间只有一个线程访问共享数据。
- 但这种方式存在资源争夺、以及死锁问题，程序越大问题越麻烦。

## 二、概念

-  Actor并发编程模型是一种不共享数据，依赖消息传递的一种并发编程模式 ，有效避免资源争夺、死锁等情况。

![Actor](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\Actor.png)

- 从图中可以看到，Actor与Actor之前只能用消息进行通信，当某一个Actor给另外一个Actor发消息，消息是有顺序的，只需要将消息投寄到每个Actor相应的邮箱，至于对方Actor怎么处理你的消息你并不知道，当然你也可等待它的回复。

# 三十五、Akka

## 一、概念

- Akka是一个用于构建高并发、分布式和可扩展的基于事件驱动的应用的工具包。Akka是使用Scala开发的库，同时可以使用Scala和Java语言来开发基于Akka的应用程序。

## 二、应用场景

- spark 1.6之前，底层进程之间交互使用Akka框架实现，后续时Netty
- Flink 框架来说，底层任然使用Akka框架

## 三、通信过程

![Akka通信过程](C:\Users\kedacom\Desktop\学习总结\Scala总结\新建文件夹\Akka通信过程.png)

1. 学生创建一个 ActorSystem
2. 通过ActorSystem来创建一个 ActorRef （老师的引用），并将消息发送给ActorRef
3. ActorRef将消息发送给Message Dispatcher（消息分发器）
4. Message Dispatcher将消息按照顺序保存到目标Actor的MailBox中
5. Message Dispatcher将MailBox放到一个线程中
6. MailBox按照顺序取出消息，最终将它递给TeacherActor接受的方法中