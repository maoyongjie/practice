# Flink

## 一、Flink部署与应用

### 1.1 Flink集群架构

- **JobManager**: 管理节点，每个集群至少一个，管理整个集群计算资源，Job管理与调度执行，以及 Checkpoint协调。
- **TaskManager**: 每个集群有多个TM，负责计算资源提供。
- **Client**: 本地执行应用main（）方法解析 Job Graph对象，并最终将 Job Graph提交到JobManager运行，同时监控Job执行的状态

<img src="C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210901161719230.png" alt="image-20210901161719230" style="zoom:150%;" />

#### 1.1.1 JobManager

1. Checkpoint Coordinator
2. JobGraph→ Execution Graph
3. Task部署与调度
4. RPC通信（ Actor System）
5. Job接收（ Job Dispatch）
6. 集群资源管理（ ResourceManager）
7. TaskManager注册与管理

![image-20210901162643381](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210901162643381.png)

#### 1.1.2 TaskManager

1. Task Execution
2. Network Manager
3. Shuffle environment管理
4. RpC通信（ Actor systen）
5. Heartbeat with JobManager And Rm
6. Data Exchange
7. Memory Management
8. Register TO RM
9. Offer Slots To JobManager

![image-20210901163036806](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210901163036806.png)

1.1.3 Client

1. Application‘ s main() Method 执行
2. Job Graph Generate
3. Execution environment管理
4. Job提交与运行
5. Dependeny Jar Ship
6. RPC With JobManager
7. 集群部署（ Cluster Deploy）

![image-20210901163448686](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210901163448686.png)

### 1.2 Flink运行时架构

![image-20210923194855945](E:\笔记\Flink\image-20210923194855945.png)

一个特定算子的子任务(subtask)的个数被称之为其并行度(parrallelism)

一般情况下，一个stream的并行度，可以认为   就是其所有算子中最大的并行度

![image-20210923194919187](E:\笔记\Flink\image-20210923194919187.png)

Flink中每一个TaskManager都是一个JVM进程，它可能会在独立的线程上执行一个或多个子任务

![image-20210923195044891](E:\笔记\Flink\image-20210923195044891.png)

默认情况下，FLINK允许子任务共享slot，即使他们是不同任务的子任务，但是这些子任务之间要是顺序继承关系。这样，一个slot可以保存作业的整个管道。

Task slot是静态的概念，是指Task具有的并发执行能力。

## 二、Flink DataStream API

### 2.2 DaraStream API 实践原理

Flink DataStream 程序结构

![image-20210901165058797](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210901165058797.png)

DataStream主要转化操作

![image-20210901172229161](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210901172229161.png)

![image-20210901172342438](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210901172342438.png)

![image-20210901172804173](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210901172804173.png)

### 2.3 Flink时间概念

![image-20210901183553335](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210901183553335.png)

### 2.4 Watermark统计计算实践原理

- Watermark用于标记 Event-Time的前进过程;
- Watermark跟随 Data stream event-Time变动，并自身携带 Timestamp;
- **Watermark用于表明所有较早的事件已经（可能）到达**
- Watermark本身也属于特殊的事件

![image-20210901184622358](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210901184622358.png)

### 2.5 Windows窗口计算

![image-20210903181902463](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210903181902463.png)

#### Window Assigner

必须要有的两个操作

1.使用窗囗分配器（ WindowAssigner）将数据流中的元素分配到对应的窗囗。

2.当满足窗口触发条件后，对窗口内的数据使用窗囗处理函数（ Window Function）进行处理，常用的 Window function有 reduce、 aggregate、 process。

**Flink支持的窗口类型**

![image-20210903183138274](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210903183138274.png)

**一种基于key分组的输入时间流** 使用方法

```java
stream.
    .keyBy(<key selector>)
    .window(<window assigner>)
    .reduce|aggregate|process(<window function>)
```

通过一些示例来展示关于这些窗口如何使用，或者如何区分它们：

- 滚动时间窗口
  - *每分钟页面浏览量*
  - `TumblingEventTimeWindows.of(Time.minutes(1))`
- 滑动时间窗口
  - *每10秒钟计算前1分钟的页面浏览量*
  - `SlidingEventTimeWindows.of(Time.minutes(1), Time.seconds(10))`
- 会话窗口
  - *每个会话的网页浏览量，其中会话之间的间隔至少为30分钟*
  - `EventTimeSessionWindows.withGap(Time.minutes(30))`

#### 滑动窗口是通过复制来实现的 

滑动窗口分配器可以创建许多窗口对象，并将每个事件复制到每个相关的窗口中。例如，如果您每隔 15 分钟就有 24 小时的滑动窗口，则每个事件将被复制到 4 * 24 = 96 个窗口中。

#### Window Trigger 窗口触发器

![image-20210903190025248](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210903190025248.png)

### 2.6 Asynchronous I/O异步操作

![image-20210926194520409](E:\笔记\Flink\image-20210926194520409.png)



# 附：FLINK八股文

## 第一部分：Flink 中的核心概念和基础

### 一、 简单介绍一下 Flink

Flink 是一个框架和分布式处理引擎，用于对无界和有界数据流进行有状态计算。并且 Flink 提供了数据分布、容错机制以及资源管理等核心功能。

Flink提供了诸多高抽象层的API以便用户编写分布式任务：

- DataSet API， 对静态数据进行批处理操作，将静态数据抽象成分布式的数据集，用户可以方便地使用Flink提供的各种操作符对分布式数据集进行处理，支持Java、Scala和Python。
- DataStream API，对数据流进行流处理操作，将流式的数据抽象成分布式的数据流，用户可以方便地对分布式数据流进行各种操作，支持Java和Scala。
- Table API，对结构化数据进行查询操作，将结构化数据抽象成关系表，并通过类SQL的DSL对关系表进行各种查询操作，支持Java和Scala。

### 二、 Flink 相比传统的 Spark Streaming 有什么区别?

Flink 是**标准的实时处理引擎**，**基于事件驱动**。而 Spark Streaming 是**微批（Micro-Batch）的模型**。

#### 1.架构模型

Spark Streaming 在运行时的主要角色包括：Master、Worker、Driver、Executor，Flink 在运行时主要包含：Jobmanager、Taskmanager和Slot。

#### 2.任务调度

Spark Streaming 连续不断的生成微小的数据批次，构建有向无环图DAG，Spark Streaming 会依次创建 DStreamGraph、JobGenerator、JobScheduler。

**Flink 根据用户提交的代码生成 StreamGraph，经过优化生成 JobGraph**，然后提交给 JobManager进行处理，JobManager 会根据 JobGraph 生成 ExecutionGraph，ExecutionGraph 是 Flink 调度最核心的数据结构，JobManager 根据 ExecutionGraph 对 Job 进行调度。

#### 3.时间机制

Spark Streaming 支持的时间机制有限，只支持处理时间。 Flink 支持了流处理程序在时间上的三个定义：处理时间、事件时间、注入时间。同时也支持 watermark 机制来处理滞后数据。

#### 4.容错机制

对于 Spark Streaming 任务，我们可以设置 checkpoint，然后假如发生故障并重启，我们可以从上次 checkpoint 之处恢复，但是这个行为只能使得数据不丢失，可能会重复处理，不能做到恰好一次处理语义。

Flink 则使用两阶段提交协议来解决这个问题。

### 三、 Flink 的组件栈有哪些？

![img](E:\笔记\Flink\format,png)

自下而上，每一层分别代表：Deploy 层：该层主要涉及了Flink的部署模式，在上图中我们可以看出，Flink 支持包括local、Standalone、Cluster、Cloud等多种部署模式。

Runtime 层：Runtime层提供了支持 Flink 计算的核心实现，比如：支持分布式 Stream 处理、JobGraph到ExecutionGraph的映射、调度等等，为上层API层提供基础服务。

API层：API 层主要实现了面向流（Stream）处理和批（Batch）处理API，其中面向流处理对应DataStream API，面向批处理对应DataSet API，后续版本，Flink有计划将DataStream和DataSet API进行统一。

Libraries层：该层称为Flink应用框架层，根据API层的划分，在API层之上构建的满足特定应用的实现计算框架，也分别对应于面向流处理和面向批处理两类。面向流处理支持：CEP（复杂事件处理）、基于SQL-like的操作（基于Table的关系操作）；面向批处理支持：FlinkML（机器学习库）、Gelly（图处理）。

### 四、Flink 的运行必须依赖 Hadoop组件吗？

Flink可以完全独立于Hadoop，在不依赖Hadoop组件下运行。但是做为大数据的基础设施，Hadoop体系是任何大数据框架都绕不过去的。Flink可以集成众多Hadooop 组件，例如Yarn、Hbase、HDFS等等。例如，Flink可以和Yarn集成做资源调度，也可以读写HDFS，或者利用HDFS做检查点。

### 五、Flink的基础编程模型

![img](E:\笔记\Flink\1.png)

![img](E:\笔记\Flink\2.png)

### 六、Flink集群有哪些角色？各自有什么作用？

![img](E:\笔记\Flink\3.png)

Flink 程序在运行时主要有 TaskManager，JobManager，Client三种角色。其中JobManager扮演着集群中的管理者Master的角色，它是整个集群的协调者，负责接收Flink Job，协调检查点，Failover 故障恢复等，同时管理Flink集群中从节点TaskManager。

TaskManager是实际负责执行计算的Worker，在其上执行Flink Job的一组Task，每个TaskManager负责管理其所在节点上的资源信息，如内存、磁盘、网络，在启动的时候将资源的状态向JobManager汇报。

Client是Flink程序提交的客户端，当用户提交一个Flink程序时，会首先创建一个Client，该Client首先会对用户提交的Flink程序进行预处理，并提交到Flink集群中处理，所以Client需要从用户提交的Flink程序配置中获取**JobManage**r的地址，并建立到JobManager的连接，将Flink Job提交给JobManager。

### 七、Flink 资源管理中 Task Slot 的概念

![img](E:\笔记\Flink\4.png)

在Flink架构角色中我们提到，TaskManager是实际负责执行计算的Worker，TaskManager 是一个 JVM 进程，并会以独立的线程来执行一个task或多个subtask。为了控制一个 TaskManager 能接受多少个 task，Flink 提出了 Task Slot 的概念。

简单的说，TaskManager会将自己节点上管理的资源分为不同的Slot：固定大小的资源子集。这样就避免了不同Job的Task互相竞争内存资源，但是需要主要的是，Slot只会做内存的隔离。没有做CPU的隔离。

### 八、Flink 的常用算子？

Flink 最常用的常用算子包括：Map：DataStream → DataStream，输入一个参数产生一个参数，map的功能是对输入的参数进行转换操作。Filter：过滤掉指定条件的数据。KeyBy：按照指定的key进行分组。Reduce：用来进行结果汇总合并。Window：窗口函数，根据某些特性将每个key的数据进行分组（例如：在5s内到达的数据）

### 九：Flink分区策略？

GlobalPartitioner 数据会被分发到下游算子的第一个实例中进行处理。

ShufflePartitioner 数据会被随机分发到下游算子的每一个实例中进行处理。

RebalancePartitioner 数据会被循环发送到下游的每一个实例中进行处理。

RescalePartitioner 这种分区器会根据上下游算子的并行度，循环的方式输出到下游算子的每个实例。这里有点难以理解，假设上游并行度为2，编号为A和B。下游并行度为4，编号为1，2，3，4。那么A则把数据循环发送给1和2，B则把数据循环发送给3和4。假设上游并行度为4，编号为A，B，C，D。下游并行度为2，编号为1，2。那么A和B则把数据发送给1，C和D则把数据发送给2。

BroadcastPartitioner 广播分区会将上游数据输出到下游算子的每个实例中。适合于大数据集和小数据集做Jion的场景。

ForwardPartitioner ForwardPartitioner 用于将记录输出到下游本地的算子实例。它要求上下游算子并行度一样。简单的说，ForwardPartitioner用来做数据的控制台打印。

KeyGroupStreamPartitioner Hash分区器。会将数据按 Key 的 Hash 值输出到下游算子实例中。

CustomPartitionerWrapper 用户自定义分区器。需要用户自己实现Partitioner接口，来定义自己的分区逻辑。例如：

```java
static classCustomPartitioner implements Partitioner<String> {
      @Override
      publicintpartition(String key, int numPartitions) {
          switch (key){
              case "1":
                  return 1;
              case "2":
                  return 2;
              case "3":
                  return 3;
              default:
                  return 4;
          }
      }
  }
```

