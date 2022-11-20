## 项目说明

根据`mango权限管理系统`开发的一个系统【对着书写的】

### 1 前端

vue.js 官网: https://cn.vuejs.org

vue.js教程：https://www.runoob.com/vue2/vue-tutorial.html

vue-router教程：https://router.vuejs.org/zh/

vuex教程：https://vuex.vuejs.org/zh/guide

element教程： https://element-cn.eleme.io/#/zh-CN

### 2 后端

#### (1)Swagger集成

官方网站：https://swagger.io/

```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>3.0.0</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>3.0.0</version>
</dependency>
```

新建config包，新建`SwaggerConfig.java`文件

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().build();
    }
}
```

#### (2) Maven集成

中文官网：http://www.mybatis.cn/

```xml
<!-- maven集成-->
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.2.0</version>
</dependency>
<!-- mysql 集成 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

在config包下新建`MybatisConfig.java`

```java
@Configuration
@MapperScan("com.jun.my_mango.**.dao")  //扫描dao
public class MybatisConfig {
    @Autowired
    private DataSource dataSource;
    
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("com.jun.my_mango.**.model");
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResource("classpath*:**/sqlmap/*.xml"));
        return sessionFactory.getObject();
    }
}
```

添加数据源配置

```yml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mango?useUnicode=true&zeroDateTimeBehavior=converToNull&autoReconnect=true&charachterEncoding=utf-8
    username: root
    password: 123456
```

#### (3)Mybatis generator

mybatis generator 官网：http://www.mybatis.org/generator/index.html

mybatis generator 教程：https://blog.csdn.net/weixin_42250302/article/details/106986707

`src/main/resources/mybatis-generator`

`generator.properties`

```xml
driverLocation=D:/IdeaWorkSpace/my_mango/my_mango/src/main/resources/mybatis-generator/mysql-connector-java-8.0.30.jar
driverClassName=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/mango?serverTimezone=UTC&useSSL=true
username=root
password=123456
```

`pom.xml`

```xml
<!-- 打包时拷贝MyBatis的映射文件 -->
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/sqlmap/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.*</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
```

`generatorConfig.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>
    <properties resource="mybatis-generator/generator.properties"/>
    <!--    连接数据库jar包的路径-->
    <classPathEntry location="${driverLocation}"/>
    <context id="DB2Tables"  targetRuntime="MyBatis3">
        <commentGenerator>
            <property name="suppressDate" value="true"/>
            <!-- 是否去除自动生成的注释 true：是 ： false:否 -->
            <property name="suppressAllComments" value="true"/>
        </commentGenerator>

        <!--数据库连接参数 -->
        <jdbcConnection
                driverClass="${driverClassName}"
                connectionURL="${url}"
                userId="${username}"
                password="${password}">
        </jdbcConnection>

        <javaTypeResolver>
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <!-- 实体类的包名和存放路径 -->
        <javaModelGenerator targetPackage="com.jun.my_mango.model" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>

        <!-- 生成映射文件*.xml的位置-->
        <sqlMapGenerator targetPackage="com.jun.my_mango.sqlmap" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
        </sqlMapGenerator>

        <!-- 生成DAO的包名和位置 -->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.jun.my_mango.dao" targetProject="src/main/java">
            <property name="enableSubPackages" value="true"/>
        </javaClientGenerator>

        <!-- tableName：数据库中的表名或视图名；domainObjectName：生成的实体类的类名-->
        <table tableName="sys_config" domainObjectName="SysConfig"
               enableCountByExample="false"
               enableUpdateByExample="false"
               enableDeleteByExample="false"
               enableSelectByExample="false"
               selectByExampleQueryId="false"/>
    </context>
</generatorConfiguration>


```

上述使用mybatis-genertor生成的代码有问题，会生成数据库连接下的所有数据表。

#### (4)Druid数据源集成

数据库连接池负责分配、管理和释放数据库连接，它允许应用程序重复使用一个现有的数据库连接，而不是重新连接一个，释放的空闲时间超过最大空闲时间的数据库连接来避免因为没有释放数据库丽娜姐而引起的数据库连接遗漏。

官方：https://github.com/alibaba/druid/wiki

- 添加依赖

```
<!-- druid -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.2.8</version>
</dependency>
```

- 添加配置

```yml
spring:
  datasource:
    name: druidDataSource
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      driver-class-name: com.mysql.jdbc.Driver
      url: jdbc:mysql://localhost:3306/mango?useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8
      username: root
      password: 123456
      filters: stat,wall,log4j,config
      max-active: 100
      initial-size: 1
      max-wait: 60000
      min-idle: 1
      time-between-eviction-runs-millis: 60000
      min-evictable-idle-time-millis: 300000
      validation-query: select 'x'
      test-while-idle: true
      test-on-borrow: false
      test-on-return: false
      pool-prepared-statements: true
      max-open-prepared-statements: 50
      max-pool-prepared-statement-per-connection-size: 20
```

```java
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DruidDataSourceProperties {

	// jdbc
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	// jdbc connection pool
	private int initialSize;
	private int minIdle;
	private int maxActive = 100;
	private long maxWait;
	private long timeBetweenEvictionRunsMillis;
	private long minEvictableIdleTimeMillis;
	private String validationQuery;
	private boolean testWhileIdle;
	private boolean testOnBorrow;
	private boolean testOnReturn;
	private boolean poolPreparedStatements;
	private int maxPoolPreparedStatementPerConnectionSize;
	// filter
	private String filters;
    // 省略getter和setter
}
```

```java
@Configuration
@EnableConfigurationProperties({DruidDataSourceProperties.class})
public class DruidConfig {
    @Autowired
    private DruidDataSourceProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(properties.getDriverClassName());
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());
        druidDataSource.setInitialSize(properties.getInitialSize());
        druidDataSource.setMinIdle(properties.getMinIdle());
        druidDataSource.setMaxActive(properties.getMaxActive());
        druidDataSource.setMaxWait(properties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(properties.getValidationQuery());
        druidDataSource.setTestWhileIdle(properties.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(properties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(properties.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());

        try {
            druidDataSource.setFilters(properties.getFilters());
            druidDataSource.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return druidDataSource;
    }

    /**
     * 注册Servlet信息， 配置监控视图
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public ServletRegistrationBean<Servlet> druidServlet() {
        ServletRegistrationBean<Servlet> servletRegistrationBean = new ServletRegistrationBean<Servlet>(new StatViewServlet(), "/druid/*");

        //白名单：
//        servletRegistrationBean.addInitParameter("allow","127.0.0.1,139.196.87.48");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny","192.168.1.119");
        //登录查看信息的账号密码, 用于登录Druid监控后台
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "admin");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable", "true");
        return servletRegistrationBean;

    }

    /**
     * 注册Filter信息, 监控拦截器
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
```

- 添加log4j依赖

```
<!-- log4j -->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

```properties
### set log levels ###
log4j.rootLogger = INFO,DEBUG, console, infoFile, errorFile ,debugfile,mail 
LocationInfo=true    

log4j.appender.console = org.apache.log4j.ConsoleAppender  
log4j.appender.console.Target = System.out  
log4j.appender.console.layout = org.apache.log4j.PatternLayout 

log4j.appender.console.layout.ConversionPattern =[%d{yyyy-MM-dd HH:mm:ss,SSS}]-[%p]:%m   %x %n 

log4j.appender.infoFile = org.apache.log4j.DailyRollingFileAppender  
log4j.appender.infoFile.Threshold = INFO  
log4j.appender.infoFile.File = D:/IdeaWorkSpace/logs/log
log4j.appender.infoFile.DatePattern = '.'yyyy-MM-dd'.log'  
log4j.appender.infoFile.Append=true
log4j.appender.infoFile.layout = org.apache.log4j.PatternLayout  
log4j.appender.infoFile.layout.ConversionPattern =[%d{yyyy-MM-dd HH:mm:ss,SSS}]-[%p]:%m  %x %n 

log4j.appender.errorFile = org.apache.log4j.DailyRollingFileAppender  
log4j.appender.errorFile.Threshold = ERROR  
log4j.appender.errorFile.File = D:/IdeaWorkSpace/logs/error  
log4j.appender.errorFile.DatePattern = '.'yyyy-MM-dd'.log'  
log4j.appender.errorFile.Append=true  
log4j.appender.errorFile.layout = org.apache.log4j.PatternLayout  
log4j.appender.errorFile.layout.ConversionPattern =[%d{yyyy-MM-dd HH:mm:ss,SSS}]-[%p]:%m  %x %n

log4j.appender.debugfile = org.apache.log4j.DailyRollingFileAppender  
log4j.appender.debugfile.Threshold = DEBUG  
log4j.appender.debugfile.File = D:/IdeaWorkSpace/logs/debug  
log4j.appender.debugfile.DatePattern = '.'yyyy-MM-dd'.log'  
log4j.appender.debugfile.Append=true  
log4j.appender.debugfile.layout = org.apache.log4j.PatternLayout  
log4j.appender.debugfile.layout.ConversionPattern =[%d{yyyy-MM-dd HH:mm:ss,SSS}]-[%p]:%m  %x %n
```

#### (5)跨域解决方案——CORS

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许跨域访问的路径
                .allowedOrigins("*")  // 允许跨域访问的源
                .allowedMethods("POST","GET","PUT","OPTIONS","DELETE")   // 允许请求方法
                .maxAge(168000)   // 预检间隔时间
                .allowedHeaders("*")  // 允许头部设置
                .allowCredentials(true);  // 是否发送cookie
    }
}
```

#### (6)Excel报表导出

Apache POI

官网：http://poi.apache.org

- 添加依赖

```xml
<!-- poi -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.0.0</version>
</dependency>
```

- 工具类代码

`PoiUtils`、`FileUtils`

#### (7) 登录流程

##### A. 登录验证码

- 添加依赖

```xml
<!--kaptcha-->
<dependency>
    <groupId>com.github.axet</groupId>
    <artifactId>kaptcha</artifactId>
    <version>0.0.9</version>
</dependency>
```

- 添加配置

KapthcaConfig

```java

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import java.util.Properties;
@Configuration
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textproducer.font.color", "black");
        properties.put("kaptcha.textproducer.char.space", "5");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
```

- 使用

```java
@Autowired
private Producer producer;

@GetMapping(value = "captcha.jpg")
public void  captcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
    response.setHeader("Cache-Control", "no-store, no-cache");
    response.setContentType("image/jpeg");
    // 生成文字验证码
    String text = producer.createText();
    // 生成图片验证码
    BufferedImage image = producer.createImage(text);
    // 保存验证码到session
    request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
    ServletOutputStream out = response.getOutputStream();
    ImageIO.write(image, "jpg", out);
    IOUtils.closeQuietly(out);
}

// IOUtils
public class IOUtils {
    /**
     * 关闭对象，连接
     * @param closeable
     */
    public static void closeQuietly(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException ioe) {
            // ignore
        }
    }
}
```

##### B. SpringSecurity + JWT

JWT官网： https://jwt.io/introduction

SpringSecurity官网： https://spring.io/projects/spring-security

SpringSecurity教程：https://www.w3school.cn/springsecurity/

https://www.cnblogs.com/xifengxiaoma/p/10020960.html

- 添加依赖

内容太多

### 3 数据库

#### (1)数据备份还原

mysqldump --opt  --add-drop-database  --add-drop-table  -hlocalhost -uroot -p123456 --result-file=C:\Users\liusu\_mango_backup\backup\mango.sql --default-character-set=utf8 mango

