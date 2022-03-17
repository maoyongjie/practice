# Spring

## 1.依赖

### 1.1 SpringMVC包

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.16.RELEASE</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.2.16.RELEASE</version>
</dependency>
```

### 1.2 Spring依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">
</beans>
```

## 2.IOC 理论推导



使用Set借口实现业务层代码减少

```java
public class UserServiceImp implements UserService{
    
    //利用SET进行动态实现值的注入！
    private UserDao userDao;
    public  void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    public void getUser(){
        userDao.getUser();
    }
}

public class MyTest {
    public static void main(String[] args) {
        // 用户实际调用的是业务层，dao层不需要接触
        UserService userService = new UserServiceImp();
        ((UserServiceImp) userService).setUserDao(new UserMysqlImpl());
        userService.getUser();
    }
}
```

![image-20210729131105708](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210729131105708.png)

## 3.Hello Spring

```xml
    <!--使用Sping来创建对象，在Spring这些都成为Bean
        bean = 对象
        id = 变量名
        class = new的对象
        property 相当于给这个类赋值
    -->
    <bean id="hello" class="com.kuang.pojo.Hello">
        <property name="str" value="spring"></property>
     </bean>
```

```java
public class Mytest {
    public static void main(String[] args) {
        //获取Spring的上下文对象
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
        //我们的对象现在都在Spring中管理了，使用时直接去取就行了
        Hello hello = (Hello) context.getBean("hello");
        System.out.println(hello.toString());
    }
}
```

```xml
    <bean id="daoimpl" class="com.kuang.dao.UserDaoImpl"></bean>
    <bean id="mysqlimpl" class="com.kuang.dao.UserMysqlImpl"></bean>

    <bean id="userserviceimpl" class="com.kuang.service.UserServiceImp">
        <!-- ref : 引用Spring容器中创建好的对象
             value : 具体的值，基本数据类型
        -->
        <property name="userDao" ref="daoimpl"></property>
    </bean>
```

## 4.IOC创建对象的方式

1. 使用**无参构造方法**创建对象，默认！

2. 假设我们要使用**有参构造对象**。

   - 下标赋值

     ```xml
         <bean id="user" class="com.mao.pojo.User">
             <constructor-arg index="0" value="毛"></constructor-arg>
         </bean>
     ```

   - 通过类型创建（当两个参数同类型时，无法使用）

     ```xml
         <bean id="user" class="com.mao.pojo.User">
             <constructor-arg type="java.lang.String" value="毛"></constructor-arg>
         </bean>
     ```

   - 直接通过**参数名**来设置

     ```xml
         <bean id="user" class="com.mao.pojo.User">
             <constructor-arg name="name" value="毛"></constructor-arg>
         </bean>
     ```

3.**即使没有要求获取某对象，Sping在加载配置文件时也会将容器中所有的对象实例化了。**

## 5.Spring配置

### 5.1 别名

```java
	<alias name = "user" alias="userNew"/>
```

### 5.2 import

一般用于团队开发，可将多个配置文件导入合并为一个。



## 6.DI 依赖注入

### 6.1 构造器注入

见前文

### 6.2 Set方式注入

- **依赖注入**: Set注入！
  - 依赖：bean对象的创建依赖于容器！
  - 注入：bean对象中的所有属性，由容器注入！

### 6.3 拓展方式注入

可以使用p命名空间和c命名空间进行注入；

注意点：p命名和c命名空间不能直接使用，需要添加xml约束

```xml
 xmlns:p="http://www.springframework.org/schema/p"
 xmlns:c="http://www.springframework.org/schema/c"
```

### 6.4 bean的作用域

![image-20210730092012132](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210730092012132.png)

1. 单例模式（默认模式）

2. 原型模式：每次从容器中get时，都会产生一个新对象！

3. 其余的request session application 只能在web开发中使用到



## 7.Bean的自动装配

- 自动装配时Spring满足bean依赖的一种方式！
- Spring会在上下文中自动寻找，并自动给bean装配属性

### 7.1装配方式

Spring中三种装配方式

1. 在xml中显示的装配

   **byname 和 bytype**

2. 在Java中手动装配

3. 隐式的自动装配

### 7.2 使用注解实现自动装配

使用注解须知：

1. 导入约束

2. 配置注解的支持 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

</beans>
```

@**Autowired** 

​	在属性上进行标注，也可以在set方式上使用；

​	使用Autowired可以不用写set方法，但须符合byname规则

```java
@Nullable  //字段标记了该注解，表面该注解可为空
```

![image-20210730101409540](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210730101409540.png)

**@Resource**

小结:

- @Resource和@Autowired的区别
- 都是用来自动装配的，都可以放在属性字段上
- @Autowired通过byname的方式实现，而且必须要求这个对象存在！
- @Resource 先通过byname，再通过bytype @Autowired则默认通过bytype实现

## 8. 使用注解开发

1. bean

2. 属性如何注入

3. 衍生的注解

   @Component 有几个衍生注解，在web开发中，会按照mvc三层架构分层

   dao 【@Repository】

   service 【@Service】

   controller 【@Controller】

   这四个注解功能一样，都是代表将某个类注册到容器之中

4. 自动装配

5. 作用域

    @Scope 在类前

6. 小结

![image-20210730104504695](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20210730104504695.png)

## 9. 纯Java注解配置

## 10. 代理模式

代理模式 是 SpringAOP的底层 【SpringAOP 和 SpringMVC】

代理模式的分类：

- 静态代理
- 动态代理

### 10.1 静态代理

代码步骤：

1. 接口

2. 真实角色
3. 代理角色
4. 客户端访问代理角色

代理模式的好处;

- 可以使真实角色的操作更加存粹，不用去关注一些公共的业务
- 公共业务交给代理角色！实现业务的分工
- 公共业务发生扩展的时候，方便集中管理

缺点：

- 一个真实角色就会产生一个代理角色；代码量会翻倍，开发效率低

### 10.2 动态代理

- 动态代理和静态代理角色一样

- 动态代理的动态类是动态生成的，不是我们直接写好的

- 动态代理分类为两大类：基于接口的动态代理，基于类的动态代理

  ​	基于接口——JDK动态代理

  ​	基于类——cglib

  ​	java字节码实现 ：javasist

  

  **InvocationHandler**类

  

  

  





