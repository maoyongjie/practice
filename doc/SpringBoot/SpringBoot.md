# SpringBoot

## 一、SpringBoot配置

#### 1.多环境下核心配置文件的使用工作中开发的环境：**开发环境、测试环境、准生产环境、生产环境**

写多个application配置文件，格式：application-dev.properties，在总配置文件中使用spring.profiles.active = product切换环境

#### 2.多环境下核心配置文件的使用(yaml或yml)，和上面相同

#### 3.springboot 在核心配置文件application.properties自定义配置

通过@Value("${field}")获取自定义配置值

```java
@Value("${field}")
private Sting field;
```

#### 4.SpringBoot在核心配置文件将自定义配置映射到一个对象

```java
@Component //将类交给Spring容器管理
@ConfigurationProperties(prefix = "abc")  //设置属性前缀
public class School(){
    //todo
}
//通过AutoWired注入
@AutoWired
private School school;
```

## 二、SpringBoot集成MyBastic

a.添加mybatis依赖、MySql驱动
