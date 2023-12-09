不知道为什么一直报错, 无法启动，明明配置方面跟domo-elasticsearch-java一样，推测可能是因为一开始的时候创建模块没创建好，删除过一次，后面模块图标一直显示有问题


2023-12-09 21:40:09.766  INFO 65278 --- [           main] sticsearchRestHighLevelClientApplication : Starting DemoElasticsearchRestHighLevelClientApplication using Java 1.8.0_361 on mrdongshandeMacBook-Pro.local with PID 65278 (/Users/mrdongshan/IdeaProjects/spring-boot-learn/out/production/spring-boot-learn started by mrdongshan in /Users/mrdongshan/IdeaProjects/spring-boot-learn)
2023-12-09 21:40:09.769  INFO 65278 --- [           main] sticsearchRestHighLevelClientApplication : No active profile set, falling back to 1 default profile: "default"
2023-12-09 21:40:10.724  INFO 65278 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2023-12-09 21:40:10.729  INFO 65278 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2023-12-09 21:40:10.729  INFO 65278 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.83]
2023-12-09 21:40:10.869  INFO 65278 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2023-12-09 21:40:10.870  INFO 65278 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1055 ms
2023-12-09 21:40:10.941  WARN 65278 --- [           main] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'elasticsearchConfiguration': Injection of autowired dependencies failed; nested exception is java.lang.IllegalArgumentException: Could not resolve placeholder 'es.hosts' in value "${es.hosts}"
2023-12-09 21:40:10.944  INFO 65278 --- [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2023-12-09 21:40:10.959  INFO 65278 --- [           main] ConditionEvaluationReportLoggingListener :

Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2023-12-09 21:40:10.978 ERROR 65278 --- [           main] o.s.boot.SpringApplication               : Application run failed

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'elasticsearchConfiguration': Injection of autowired dependencies failed; nested exception is java.lang.IllegalArgumentException: Could not resolve placeholder 'es.hosts' in value "${es.hosts}"
at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessProperties(AutowiredAnnotationBeanPostProcessor.java:414) ~[spring-beans-5.3.31.jar:5.3.31]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.populateBean(AbstractAutowireCapableBeanFactory.java:1431) ~[spring-beans-5.3.31.jar:5.3.31]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:619) ~[spring-beans-5.3.31.jar:5.3.31]
at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:542) ~[spring-beans-5.3.31.jar:5.3.31]
at org.springframework.beans.factory.support.AbstractBeanFactory.lambda$doGetBean$0(AbstractBeanFactory.java:335) ~[spring-beans-5.3.31.jar:5.3.31]
at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:234) ~[spring-beans-5.3.31.jar:5.3.31]
at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:333) ~[spring-beans-5.3.31.jar:5.3.31]
at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:208) ~[spring-beans-5.3.31.jar:5.3.31]
at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:955) ~[spring-beans-5.3.31.jar:5.3.31]
at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:929) ~[spring-context-5.3.31.jar:5.3.31]
at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:591) ~[spring-context-5.3.31.jar:5.3.31]
at org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.refresh(ServletWebServerApplicationContext.java:147) ~[spring-boot-2.7.18-20231122.155204-53.jar:2.7.18-SNAPSHOT]
at org.springframework.boot.SpringApplication.refresh(SpringApplication.java:732) [spring-boot-2.7.18-20231122.155204-53.jar:2.7.18-SNAPSHOT]
at org.springframework.boot.SpringApplication.refreshContext(SpringApplication.java:409) [spring-boot-2.7.18-20231122.155204-53.jar:2.7.18-SNAPSHOT]
at org.springframework.boot.SpringApplication.run(SpringApplication.java:308) [spring-boot-2.7.18-20231122.155204-53.jar:2.7.18-SNAPSHOT]
at org.springframework.boot.SpringApplication.run(SpringApplication.java:1300) [spring-boot-2.7.18-20231122.155204-53.jar:2.7.18-SNAPSHOT]
at org.springframework.boot.SpringApplication.run(SpringApplication.java:1289) [spring-boot-2.7.18-20231122.155204-53.jar:2.7.18-SNAPSHOT]
at com.mrdongshan.elasticsearch.DemoElasticsearchRestHighLevelClientApplication.main(DemoElasticsearchRestHighLevelClientApplication.java:11) [spring-boot-learn/:na]
Caused by: java.lang.IllegalArgumentException: Could not resolve placeholder 'es.hosts' in value "${es.hosts}"
at org.springframework.util.PropertyPlaceholderHelper.parseStringValue(PropertyPlaceholderHelper.java:180) ~[spring-core-5.3.31.jar:5.3.31]
at org.springframework.util.PropertyPlaceholderHelper.replacePlaceholders(PropertyPlaceholderHelper.java:126) ~[spring-core-5.3.31.jar:5.3.31]
at org.springframework.core.env.AbstractPropertyResolver.doResolvePlaceholders(AbstractPropertyResolver.java:239) ~[spring-core-5.3.31.jar:5.3.31]
at org.springframework.core.env.AbstractPropertyResolver.resolveRequiredPlaceholders(AbstractPropertyResolver.java:210) ~[spring-core-5.3.31.jar:5.3.31]
at org.springframework.context.support.PropertySourcesPlaceholderConfigurer.lambda$processProperties$0(PropertySourcesPlaceholderConfigurer.java:191) ~[spring-context-5.3.31.jar:5.3.31]
at org.springframework.beans.factory.support.AbstractBeanFactory.resolveEmbeddedValue(AbstractBeanFactory.java:936) ~[spring-beans-5.3.31.jar:5.3.31]
at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:1332) ~[spring-beans-5.3.31.jar:5.3.31]
at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:1311) ~[spring-beans-5.3.31.jar:5.3.31]
at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.resolveFieldValue(AutowiredAnnotationBeanPostProcessor.java:710) ~[spring-beans-5.3.31.jar:5.3.31]
at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor$AutowiredFieldElement.inject(AutowiredAnnotationBeanPostProcessor.java:693) ~[spring-beans-5.3.31.jar:5.3.31]
at org.springframework.beans.factory.annotation.InjectionMetadata.inject(InjectionMetadata.java:119) ~[spring-beans-5.3.31.jar:5.3.31]
at org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor.postProcessProperties(AutowiredAnnotationBeanPostProcessor.java:408) ~[spring-beans-5.3.31.jar:5.3.31]
... 17 common frames omitted

