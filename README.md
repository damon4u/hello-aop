## Spring AOP 学习项目

* case-1: JDK动态代理
* case-2: CGLIB代理
* case-3: 底层ProxyFactory实现
* case-4: ProxyFactoryBean实现
* case-5: aop命名空间实现
* case-6: 注解实现
* case-7: DefaultAdvisorAutoProxyCreator实现

### Spring AOP原理

1. 解析xml或者注解配置的`Advisor`(`Advice` + `Pointcut`),注册到容器中备用.这个过程是在Spring上下文解析过程中做的.
2. 在调用`getBean()`方法从容器中获取bean实例时,会调用所有满足条件的`BeanPostProcessor`,初始化之前调用`postProcessBeforeInitialization()`方法,
初始化之后,调用`postProcessAfterInitialization()`方法.
跟AOP有关的是`AbstractAutoProxyCreator`的`postProcessAfterInitialization()`方法.
3. 先取出所有注册的`Advisor`,然后从中找到能够代理当前bean的`Advisor`.查找过程中,如果是`PointcutAdvisor`,需要匹配Pointcut的ClassFilter和MethodMatcher;如果是`IntroductionAdvisor`,只需要匹配ClassFilter.
4. 创建`ProxyFactory`,将找到的`Advisor`列表传进去.
5. 创建代理对象,有两条路径,一种是JDK动态代理,一种是CGLIB代理.如果没有强制使用使用CGLIB代理,且目标bean实现了接口,那么优先使用JDK动态代理;否则使用CGLIB代理.
此时,从容器中拿到的bean就不是目标对象,而是代理类.
6. 对于JDK动态搭理,当调用目标对象的方法时,会调用其代理对象的`invoke()`方法.
7. 首先将`Advisor`转化为`MethodInterceptor`.这一步是通过`GlobalAdvisorAdapterRegistry`工具类完成的.其中硬编码了三种`AdvisorAdapter`(`MethodBeforeAdviceAdapter`,`AfterReturningAdviceAdapter`,`ThrowsAdviceAdapter`),
其中`AdvisorAdapter`接口包含两个方法,一个是`supportsAdvice()`,判断`Advisor`是否可以用当前的`AdvisorAdapter`进行转化;另一个方法是`getInterceptor()`,用来将`Advisor`转化为`MethodInterceptor`.
轮训这三个适配器,将当前`Advisor`适配成`MethodInterceptor`.
8. 创建一个`MethodInvocation`对象(具体是`ReflectiveMethodInvocation`类型)来链式调用`MethodInterceptor`.先调用before,然后是around(前),然后是目标方法,然后是around(后),然后是afterReturn或者afterThrowing.
`MethodInterceptor`的`invoke(MethodInvocation invocation)`方法参数为invocation,传的都是之前创建的`ReflectiveMethodInvocation`对象,内部计数器每次调用interceptor后自增.
9. 对于CGLIB代理,它创建的`CglibMethodInvocation`对象是继承了JDK动态代理所创建的`ReflectiveMethodInvocation`，覆写了`invokeJoinpoint`方法。
`CglibMethodInvocation`与`ReflectiveMethodInvocation`仅仅在执行目标方法的时候有所不同，当目标方法是public方法时，`ReflectiveMethodInvocation`一直采用反射的策略执行目标方法。而`CglibMethodInvocation却使用`this.methodProxy.invoke(this.target, this.arguments)`代理方法来执行。
当执行public方法时，会比反射有一个更好的性能。

附上一张类图:
![Spring AOP](http://dl2.iteye.com/upload/attachment/0101/5528/977a5376-572f-3e69-b13b-2aeeb44a5b24.png)