## CGLIB代理
使用cglib的Enhancer来创建，创建出的代理对象继承了指定的class。

* `enhancer.setClassLoader`：也是指定类加载器，将创建出来的新类加载到jvm中。
* `enhancer.setSuperclass(t.getClass())`：设置目标类作为代理对象的父类。
* `enhancer.setCallback`：设置一个回调函数，每次调用代理类的方法时，先执行该回调函数。

注意,CGLIB代理虽然可以搭理非接口类,但是,由于他是通过创建目标类的子类来完成代理,而final方法是不能被继承的,
所以final方法不可被代理.