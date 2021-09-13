# hscrm 系统
## 一、数据库设计

### 1、数据库创建

```
hscrm
字符集：utf-8
```



### 2、表结构

```
create  table  emp
(
e_id  int  PRIMARY   key  auto_increment,
e_name  varchar(12)  not  null,
e_sex   char(3),
e_tel   char(11),
username  varchar(20)   not  null  UNIQUE,
passwd    VARCHAR(32)   not  null
);


create   table  customer
(
c_id   int  PRIMARY   key  auto_increment,
c_name  varchar(12)  not  null,
c_sex  char(3),
c_tel  char(11),
c_job  varchar(30),
c_company  varchar(60)
);


create  table  track
(
t_id  int  PRIMARY   key  auto_increment,
c_id  int,
e_id  int,
record  varchar(255),
intention   varchar(60)
);
```

![image-20210904103032625](CRM系统编写.assets/image-20210904103032625.png)



![image-20210904103044238](CRM系统编写.assets/image-20210904103044238.png)



![image-20210904103053243](CRM系统编写.assets/image-20210904103053243.png)



### 3、数据库连接信息

```
mysql8.0.16
ip:localhost
port:3306
database:hscrm
user:root
password:root
```





## 二、后端项目搭建

### 1、创建Java项目hscrm

### 2、添加web框架

![image-20210904103403449](CRM系统编写.assets/image-20210904103403449.png)

### 3、创建目录

hscrm/web/WEB-INF/lib

![image-20210904103801909](CRM系统编写.assets/image-20210904103801909.png)



hscrm/web/WEB-INF/classes

![image-20210904103846408](CRM系统编写.assets/image-20210904103846408.png)



### 4、添加tomcat类库

![image-20210904103935710](CRM系统编写.assets/image-20210904103935710.png)



### 5、创建包

com.hscrm.controller   servlet包

com.hscrm.service    业务接口包

com.hscrm.service.impl  业务实现类包

com.hscrm.dao         dao接口包

com.hscrm.dao.impl   dao实现类包

com.hscrm.domain   实体类包

com.hscrm.util    工具类包

com.hscrm.filter 过滤器包

![image-20210904104142061](CRM系统编写.assets/image-20210904104142061.png)



### 6、依赖导入

commons-fileupload-1.2.1.jar
commons-io-1.4.jar

jackson-annotations-2.13.0-rc2.jar
jackson-core-2.13.0-rc2.jar
jackson-databind-2.13.0-rc2.jar

mysql-connector-java-8.0.15.jar

![image-20210904104713124](CRM系统编写.assets/image-20210904104713124.png)



### 7、工具类

DBUtil数据库连接
MD5Util加密
VerifyCode验证码

![image-20210904105257962](CRM系统编写.assets/image-20210904105257962.png)



### 8、实体类

Emp：

![image-20210904105732032](CRM系统编写.assets/image-20210904105732032.png)

Customer：

![image-20210904111246900](CRM系统编写.assets/image-20210904111246900.png)

Track：

![image-20210904143416440](CRM系统编写.assets/image-20210904143416440.png)





## 三、后端程序设计

### 1、系统安全

#### （1）跨域过滤

#### （2）登录过滤

#### （3）字符集过滤

#### （4）数据响应格式约定

```
{
code:状态码，
message:状态描述,
data:数据
}
```

```
1000:"OK"
1001:"用户登录认证失败"
1002:"用户名不唯一"
1003:"请求参数异常"
1004:"注册失败"
1005:"操作失败"
1006:"验证码错误"

```



### 2、用户功能接口

#### （1）注册

代码实现：



接口文档编写：

参考聚合数据平台

https://www.juhe.cn/docs/api/id/54

```
url:http://localhost:8080/hscrm/reg
method:get/post
参数说明:
e_name   姓名，必填，2到4个汉字
e_sex    性别，选填，只能是男或女
e_tel    电话，选填，必须是11位数字
。。。
响应数据:
//成功的案例
{
code:1000,
message:"操作成功"，
data:1
}
//失败的案例
。。。

```



#### （2）登录



#### （3）退出登录



#### （4）获取用户名



#### （5）修改密码



#### （6）获取验证码



#### （7）唯一性验证



### 3、客户管理接口

#### （1）添加新客户



#### （2）删除客户



#### （3）修改客户



#### （4）查询所有客户



#### （5）查询单个客户（c_id）



### 4、客户跟踪接口

（1）添加新跟踪记录



（2）删除跟踪记录



（3）修改跟踪记录



（4）查询跟踪记录





## 四、前端项目搭建

### 1、创建项目hscrm

### 2、创建目录

images

css

js

### 3、依赖文件导入

js/jquery-3.6.0.js



### 4、展示

![image-20210904102624694](CRM系统编写.assets/image-20210904102624694.png)



## 五、前端程序设计

### 1、用户页面

#### （1）登录页面



#### （2）注册页面



#### （3）管理首页



#### （4）欢迎页



#### （5）修改用户密码页面



### 2、客户管理页面

#### （1）客户信息查询页面



#### （2）客户信息添加页面



#### （3）客户信息修改页面



### 3、跟踪信息管理页面

#### （1）跟踪信息查询页面



#### （2）跟踪信息添加页面



#### （3）跟踪信息修改页面





## 四、项目测试





## 五、crm项目打包

