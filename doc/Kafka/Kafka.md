# **Kafka**

## 一、Kafka概述

### 1.1 消费队列的好处

1）解耦

2）可恢复性

3）缓冲

4）灵活性&峰值处理

5）异步通信

### 1.2 消息队列的两种模式

（1）点对点模式

（2）发布/订阅模式

![image-20210721135516270](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210721135516270.png)

- Producer：生产者，也就是发送消息的一方。生产者负责创建消息，然后将其发送到 Kafka。
- Consumer：消费者，也就是接受消息的一方。消费者连接到 Kafka 上并接收消息，进而进行相应的业务逻辑处理。
- Consumer Group：一个消费者组可以包含一个或多个消费者。使用多分区 + 多消费者方式可以极大提高数据下游的处理速度，**同一消费组中的消费者不会重复消费消息**，同样的，不同消费组中的消费者消息消息时互不影响。Kafka 就是通过消费组的方式来实现消息 P2P 模式和广播模式。
- Broker：服务代理节点。Broker 是 Kafka 的服务节点，即 Kafka 的服务器。
- Topic：Kafka 中的消息以 Topic 为单位进行划分，生产者将消息发送到特定的 Topic，而消费者负责订阅 Topic 的消息并进行消费。
- Partition：Topic 是一个逻辑的概念，它可以细分为多个分区，每个分区只属于单个主题。同一个主题下不同分区包含的消息是不同的，分区在存储层面可以看作一个可追加的日志（Log）文件，消息在被追加到分区日志文件的时候都会分配一个特定的偏移量（offset）。
- Offset：offset 是消息在分区中的唯一标识，Kafka 通过它来保证消息在分区内的顺序性，不过 offset 并不跨越分区，也就是说，Kafka 保证的是分区有序性而不是主题有序性。
- Replication：副本，是 Kafka 保证数据高可用的方式，Kafka 同一 Partition 的数据可以在多 Broker 上存在多个副本，通常只有主副本对外提供读写服务，当主副本所在 broker 崩溃或发生网络异常，Kafka 会在 Controller 的管理下会重新选择新的 Leader 副本对外提供读写服务。
- Record：实际写入 Kafka 中并可以被读取的消息记录。每个 record 包含了 key、value 和 timestamp。
- Leader: 消费只找Leader
- Zookeeper: 帮助kafka存储信息

##   二、Kafka架构

### 2.1 Kafka工作流程及文件储存

![img](C:\Users\ADMINI~1\AppData\Local\Temp\企业微信截图_16292839085304.png)

生产者生产的消息会不断追加到log文件末尾，为防止log文件过大导致数据定位效率低下，Kafka采取了**分片和索引**机制。

这些文件位于一个文件夹下，该文件夹的命名规则为：**topic名称+分区序号**，例如first这个topic有三个分区，则其对应的文件夹为first-0，first-1，first-2

 index和log文件以当前**segment的第一条消息的offset命名**

二分查找法寻找index文件，index中存储消息在log文件中的起始位置及偏移量，据此可快速定位Message

### 2.2 Kafka生产者

#### 2.2.1 分区的原则

(1) 指明patition的情况下，将指定值作为patition值

(2) 没指明patition但有key值的情况下，将key的hash值与topic的patition数进行取余得到patition值

(3)patition和key都没有的情况下，通过round-robin算法

#### 2.2.2 数据可靠性保证

**ACK机制**

为保证producer发送的数据能到指定的topic，topic的每个patition收到数据后**返回确认ACK**，且**副本全部同步完成后才发送ACK**

**ISR**

Leader维护了一个动态的in-sync replica set (ISR), ISR中所有的follower完成数据的同步后，leader就会给follower发送ack，如果长时间未向leader同步数据，则该follower会被踢出ISR

**故障处理细节**

LEO：指的是每个副本最大的offset

HW：指的是消费者能见到的最大的offset，ISR队列中最小的LEO

![img](C:\Users\ADMINI~1\AppData\Local\Temp\企业微信截图_16292866304200.png)

### 2.3 Kafka消费者

#### 2.3.1 消费方式

consumer采用**Pull**模式从broker读取数据，其不足在于kafka没有数据的话，消费者可能会陷入循环中，一直返回空数据。

针对这一点，Kafka的消费者在消费数据时会传入一个**时长参数timeout**，如果当前没有数据可消费，会等待一段时间再返回。

#### 2.3.2 offset的维护

从kafka-0.9版本及以后，kafka的消费者组和offset信息就不存zookeeper了，而是存到broker服务器上，所以，如果你为某个消费者指定了一个消费者组名称（group.id），那么，一旦这个消费者启动，这个消费者组名和它要消费的那个topic的offset信息就会被记录在broker服务器上





