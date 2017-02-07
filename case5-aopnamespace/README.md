## `<aop:config>`命名空间
从标签解析接口开始，即找`BeanDefinitionParser`的实现类，最终我们会找到`AspectJAutoProxyBeanDefinitionParser`是用来处理`aspectj-autoproxy`标签的，而`ConfigBeanDefinitionParser`则是用来处理`aop:config`标签的。

它的`parse`方法这里主要注册一些`Advisor`,同时注册了一个`AspectJAwareAdvisorAutoProxyCreator`,并且设置xml中所配置的`proxy-target-class`和`expose-proxy`到它的属性中.
`AspectJAwareAdvisorAutoProxyCreator`本身存储着配置信息,然后使用这些配置创建出来代理对象，在它的父类`AbstractAutoProxyCreator的createProxy`方法中.