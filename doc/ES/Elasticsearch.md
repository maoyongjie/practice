# Elasticsearch

## 一、Elasticsearch简介

### 1.1 Elasticsearch是什么

Elasticsearch是一个基于Apache Lucene(TM)的开源搜索引擎，Elasticsearch也是使用Java编写并使用Lucene来**建立索引并实现搜索功能**，但是它的目的是通过**简单连贯的RESTful API**让全文搜索变得简单并隐藏Lucene的复杂性。 此外还提供：

- 分布式的实时文件储存，每个字段都被索引并可被搜索
- 实时分析的分布式搜索引擎
- 可以扩展到上百台服务器，处理PB级结构化或非结构化数据

### 1.2 Elasticsearch中涉及到的重要概念

**(1)  接近实时 （NRT）**

Elasticsearch是一个接近实时的搜索平台。这意味着，从索引一个文档直到这个文档能够被搜索到有一个轻微的延迟（通常是1秒）。

**(2) 集群（cluster）**

一个集群就是由一个或多个节点组织在一起，它们共同持有你整个的数据，并一起提供索引和搜索功能。**一个集群由一个唯一的名字标识，这个名字默认就是“elasticsearch”**。这个名字是重要的，因为**一个节点只能通过指定某个集群的名字，来加入这个集群**。在产品环境中显式地设定这个名字是一个好习惯，但是使用默认值来进行测试/开发也是不错的。

**(3) 节点 （node）**

**一个节点是你集群中的一个服务器**，作为集群的一部分，它**存储数据**，参与集群的**索引和搜索**功能。和集群类似，一个节点也是由一个名字来标识的，默认情况下，这个名字是一个随机的漫威漫画角色的名字，这个名字会在启动的时候赋予节点。这个名字对于管理工作来说挺重要的，因为在这个管理过程中，你会去确定网络中的哪些服务器对应于Elasticsearch集群中的哪些节点。

一个节点可以通过配置集群名称的方式来加入一个指定的集群。默认情况下，每个节点都会被安排加入到一个叫做“elasticsearch”的集群中，这意味着，如果你在你的网络中启动了若干个节点，并假定它们能够相互发现彼此，它们将会自动地形成并加入到一个叫做“elasticsearch”的集群中。

在一个集群里，只要你想，可以拥有任意多个节点。而且，如果当前你的网络中没有运行任何Elasticsearch节点，这时启动一个节点，会默认创建并加入一个叫做“elasticsearch”的集群。

**(4) 索引（index）**

**一个索引就是一个拥有几分相似特征的文档的集合**。比如说，你可以有一个客户数据的索引，另一个产品目录的索引，还有一个订单数据的索引**。一个索引由一个名字来标识（必须全部是小写字母的）**，并且当我们要对对应于这个索引中的文档进行索引、搜索、更新和删除的时候，都要使用到这个名字。**索引类似于关系型数据库中Database**的概念。在一个集群中，如果你想，可以定义任意多的索引。

**(5) 类型 （type）**

在一个索引中，你可以定义一种或多种类型。**一个类型是你的索引的一个逻辑上的分类/分区**，其语义完全由你来定。通常，会为具有一组共同字段的文档定义一个类型。比如说，我们假设你运营一个博客平台并且将你所有的数据存储到一个索引中。在这个索引中，你可以为用户数据定义一个类型，为博客数据定义另一个类型，当然，也可以为评论数据定义另一个类型。**类型类似于关系型数据库中Table的概念**。

**(6) 文档（document）**

**一个文档是一个可被索引的基础信息单元**。比如，你可以拥有某一个客户的文档，某一个产品的一个文档，当然，也可以拥有某个订单的一个文档。**文档以JSON（Javascript Object Notation）格式来表示**，而JSON是一个到处存在的互联网数据交互格式。 
在一个index/type里面，只要你想，你可以存储任意多的文档。注意，尽管一个文档，物理上存在于一个索引之中，文档必须被索引/赋予一个索引的type。**文档类似于关系型数据库中Record的概念**。实际上一个文档除了用户定义的数据外，还包括`_index`、`_type`和`_id`字段。

**(7) 分片和复制（shards & replicas）**

一个索引可以存储超出单个结点硬件限制的大量数据。比如，一个具有10亿文档的索引占据1TB的磁盘空间，而任一节点都没有这样大的磁盘空间；或者单个节点处理搜索请求，响应太慢。

为了解决这个问题，Elasticsearch提供了**将索引划分成多份的能力，这些份就叫做分片**。当你创建一个索引的时候，你可以指定你想要的分片的数量。每个分片本身也是一个功能完善并且独立的“索引”，这个“索引”可以被放置到集群中的任何节点上。 
分片之所以重要，主要有两方面的原因：

- 允许你水平分割/扩展你的内容容量
- 允许你在分片（潜在地，位于多个节点上）之上进行分布式的、并行的操作，进而提高性能/吞吐量

在一个网络/云的环境里，失败随时都可能发生，在某个分片/节点不知怎么的就处于离线状态，或者由于任何原因消失了。这种情况下，有一个故障转移机制是非常有用并且是强烈推荐的。为此目的，Elasticsearch允许你创建分片的一份或多份拷贝，这些拷贝叫做复制分片，或者直接叫复制。复制之所以重要，主要有两方面的原因：

- 在分片/节点失败的情况下，提供了高可用性。因为这个原因，注意到**复制分片从不与原/主要（original/primary）分片置于同一节点上**是非常重要的。
- 扩展你的搜索量/吞吐量，因为搜索可以**在所有的复制上并行运行**

Elasticsearch底层使用**倒排索引**



### 1.3 安装与配置

![](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210820143948840.png)

启动：![image-20210820144651752](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210820144651752.png)

默认地址：127.0.0.1:9200

可视化工具：http://localhost:9100

ES配置解决跨域问题：

http.cors.enabled: true
http.cors.allow-origin: "*"

## 二、ES Restful API基本使用

### 2.1 基础知识

**Rest系统的约束：**

1. 使用客户/服务器模型。客户和服务器之间通过一个统一的接口来互相通讯。
2. 层次化的系统。在一个REST系统中，客户端并不会固定地与一个服务器打交道。
3. 无状态。在一个REST系统中，服务端并不会保存有关客户的任何状态。也就是说，客户端自身负责用户状态的维持，并在每次发送请求时都需要提供足够的信息。
4. 可缓存。REST系统需要能够恰当地缓存请求，以尽量减少服务端和客户端之间的信息传输，以提高性能。
5. 统一的接口。一个REST系统需要使用一个统一的接口来完成子系统之间以及服务与用户之间的交互。这使得REST系统中的各个子系统可以独自完成演化。

和REST密切相关的两个名词：**资源和状态**。可以说，资源是REST系统的核心概念。所有的设计都会以资源为中心，包括如何对资源进行添加，更新，查找以及修改等。而资源本身则拥有一系列状态。在每次对资源进行添加 ，删除或修改的时候，资源就将从一个状态转移到另外一个状态。

#### **2.1.1 Mapping详解**

Mapping是ES中的一个很重要的内容，它类似于传统关系型数据中table的schema，用于定义一个索引（index）的某个类型（type）的数据的结构。

mapping中主要包括**字段名**、**字段数据类型**和**字段索引类型**这3个方面的定义。

**字段数据类型**：定义该字段保存的数据的类型，不符合数据类型定义的数据不能保存到ES中。

简单类型

- Text/Keyword
- Date
- Integer/Floating
- Boolean
- IPv6&IPv4

复杂类型--对象和嵌套对象

- 对象类型/嵌套类型

特殊类型

- geo_point&geo_shape/percolator

##### 1. **Dynamic Mapping** 

即使没有指定类型，也会动态地为字段指定类型

![image-20210826184803683](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210826184803683.png)

**两种情况**

​		**1.新增加字段**

​	Dynamic设为**true**时，一旦有新增字段的文档写入， Mapping也同时被更新

​	Dynamic设为 **false**， Mapping不会被更新，新增字段的数据无法被索引l，但是信息会出现在 source中

​	Dynamic设置成 **Strict**，文档写入失败

​		 **2.对已有字段，一旦已经有数据写入，就不再支持修改字段定义**

​	Lucene实现的倒排索引，一旦生成后，就不允许修改

​	如果希望改变字段类型，必须 Reindex AP，重建索引

**原因**：

​	如果修改了字段的数据类型，会导致已被索引的属于无法被搜索

​	但是如果是增加新的字段，就不会有这样的影响

##### 2. **index**

index可以控制当前字段是否被索引。默认为true，若改为false，该字段不可被搜索。

##### 3. copy_to![image-20210826193348818](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210826193348818.png)

##### 4. null_value

![image-20210827134241114](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210827134241114.png)

设置null_value的值，相当于给予null一个重定义

#### 2.1.2 数组类型

**Elasticsearch中不提供专门的数组类型。但是任何字段，都可以包含多个相同类类型的数值**

#### 2.1.3 字段

**字段索引类型**：索引是ES中的核心，ES之所以能够实现实时搜索，完全归功于Lucene这个优秀的Java开源索引。在传统数据库中，如果字段上建立索引，我们仍然能够以它作为查询条件进行查询，只不过查询速度慢点。而在ES中，**字段如果不建立索引，则就不能以这个字段作为查询条件来搜索**。也就是说，不建立索引的字段仅仅能起到数据载体的作用。

string类型的数据是日常使用得最多的数据类型，下面为mapping中string类型字段可以配置的索引类型。

|   索引类型   |                             解释                             |
| :----------: | :----------------------------------------------------------: |
|   analyzed   | 首先分析这个字符串，然后再建立索引。换言之，以全文形式索引此字段。 |
| not_analyzed | 索引这个字段，使之可以被搜索，但是索引内容和指定值一样。不分析此字段。 |
|      no      |            不索引这个字段。这个字段不能被搜索到。            |



### 2.2 常用的Rest API

![image-20210820165003334](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210820165003334.png)

1.创建一个索引

```
PUT /索引名/类型名/文档id
{请求体}
```

![image-20210820181601612](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210820181601612.png)

![image-20210820182034687](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210820182034687.png)

2.通过GET获取详情

![image-20210820182619918](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210820182619918.png)

### 2.3 分词器

standard Analyzer 标准分词器：按词切分，小写处理

simple Analyzer : 按照非字母切分，非字母的都被除去；小写处理

whitesapce Analyzer : 按空格切分

stop Analyzer: 相比simple Analyzer,会去掉stop word

keyword Analyzer : 将输入作为输出

Pattern Analyzer : 通过正则表达式切分 ，默认是\w+，非字符的符号进行分隔



### 2.4 复杂查询

#### 2.4.1 URI查询

**指定字段查询**: 在“q=”+指定字段+“:”+字段值

字段值加上引号表示and, 不加表示or

Beautiful Mind ====> Beautiful OR Mind

"Beautiful Mind"====>指定字符串查询

```json
GET /movies/_search?q=title:2012
```

**布尔查询**  必须大写

![image-20210825194625418](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210825194625418.png)

(Beautiful AND Mind)====>同时有Beautiful 和Mind，顺序无所谓

(Beautiful NOT Mind)====>有Beautiful 无Mind

**范围查询**

[]闭区间，{}开区间

#### 2.4.2 Request Body 查询

```json
GET test3/keda/_search
{
  "query":{
    "match": {    //匹配
      "name": "kedacom company"
      "operator":"and"  //操作符
    }
  },
  "sort":[     //排序
    {
     "age":{
       "order":"desc"
      }
    }
    ],
  "_source": ["name","age"],  //select
  "from":0, //分页查询
  "size":2
}
```

![image-20210825200124933](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210825200124933.png)

**脚本字段**

![image-20210825195555689](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210825195555689.png)

### 2.5 聚合分析

**集合的分类**

- Bucket Aggregation--些列满足特定条件的文档的集合

![image-20210827141744744](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210827141744744.png)

- Metric Aggregation-一些数学运算，可以对文档字段进行统计分析
- ![image-20210827142007337](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210827142007337.png)
- Pipeline Aggregation-对其他的聚合结果边行上次聚合
- Matrⅸκ Aggregration-支持对多个字段的操作并提供一个结果矩阵

## 三、集成SpringBoot

1.原生依赖

```xml
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
    <version>7.14.0</version>
</dependency>
```

2.初始化

![image-20210820193732512](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210820193732512.png)

## 四、深入搜索 

### 4.1  基于词项term和基于全文的搜索

**基于term查询**  

term查询属于包含关系，而非精确匹配

![image-20210830192045773](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210830192045773.png)

**基于全文本的查找**

Match Query /Match Phrase Query/ Query String Query

**特点**

●索引和搜索时都会进行分词，查询字符串先传递到一个合适的分词器，然后生成一个供查询的词项列表

●查询时候，先会对输入的查询进行分词，然后每个词项逐个进行底层的查询，最终将结果进行合并。并为每个文档生成一个算分。一例如查Matiⅸ reloaded”，会查到包括Matr或者 reload的所有结果

### 4.2  结构化搜索

● 布尔，时间，日期和数字这类结构化数据:有精确的格式，我们可以对这些格式进行逻辑操作。包括比较数字或时间的范围，或判定两个值的大小。

● 结构化的文本可以做精确匹配或者部分匹配

​		o Tern查询/ Prefix前缀查询

● 结构化结果只有“是或“否两个值

​		o 根据场景需要，可以决定结构化搜索是否需要打分

```json
DELETE products

POST /products/_bulk
{"index":{"_id":1}}
{"price":10, "avaliable":true,"date":"2018-01-01","productID":"XHDK-A-1293-#FJ3"}
{"index":{"_id":2}}
{"price":20, "avaliable":true,"date":"2019-01-01","productID":"XHDK-A-1293-#kL5"}
{"index":{"_id":3}}
{"price":30, "avaliable":true,"productID":"XHDK-A-1293-#pV7"}
{"index":{"_id":4}}
{"price":30, "avaliable":false,"productID":"XHDK-A-1293-#aD8"}

GET products/_mapping

//hits = 3
POST products/_search
{
  "profile": "true",
  "explain": true,
  "query": {
    "term": {
      "avaliable": true
    }
  }
}

//范围查询、跳过算分
GET products/_search
{
  "query": {
    "constant_score": {
      "filter": {
        "range": {
          "price": {
            "gte": 20,
            "lte": 30
          }
        },
        "range": {
          "date": {
            "gte": "now-1y"  //现在到一年前的数据
          }
        }
      },
      "boost": 1.2
    }
  }
}

//查询某字段存在的记录
POST products/_search
{
  "query": {
    "constant_score": {
      "filter": {
        "exists": {
          "field": "date"
        }
      },
      "boost": 1.2
    }
  }
}
```

## 附：遇见的搜索方法

### 1.只返回某些字段

```java
//fetchSource 选择你需要返回的字段、接收boolean 字符串以及字符串数组
sourceBuilder.query(queryBuilder).fetchSource(false)
sourceBuilder.query(queryBuilder).fetchSource(String[])
```

### 2.Redis管道插入

```java
Pipeline pipelined = jedis.pipelined();
for (SearchHit hit : hits) {
	pipelined.set(hit.getId(), Constants.REDIS_DEFAULT_VALUE);
}
pipelined.sync();
jedis.close();
```

