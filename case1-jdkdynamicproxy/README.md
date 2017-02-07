## JDK动态代理
JDK动态代理就是通过`Proxy.newProxyInstance`来创建代理对象的：

* 第一个参数是`ClassLoader`：因为此次代理会创建一个`LoginService`接口的实现类，需要将这个类加载到jvm中，所以用到了ClassLoader。
* 第二个参数是代理类要实现的所有接口：当你调用这些接口的方法时都会进行拦截。
* 第三个参数是`InvocationHandler`，每次调用代理对象的方法时，都会先执行`InvocationHandler`的`invoke`方法，在该方法中实现我们的拦截逻辑。