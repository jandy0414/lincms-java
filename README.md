# lincms-java
lincms的java Spring boot 实现的后台版本 ，对应 '七月'的开源项目的。

## lincms-java的前端是Vue实现的，对应的源代码如下地址
[https://github.com/TaleLin/lin-cms-vue](https://github.com/TaleLin/lin-cms-vue)

## lincms-java的接口及编程思维借鉴 ，'七月'的风格，其代码如下地址
[https://github.com/TaleLin/lin-cms-flask](https://github.com/TaleLin/lin-cms-flask)


# 系统技术栈
## 1、使用redis缓存的token信息
## 2、架构层级在经典MVC架构，稍作调整
	  1）、实体层除了对接底层数据库的，另增加对应需要序列化输出及入参输入json层
	  2）、业务处理service：业务逻辑尽可能在这层，并在此可调dao层
	  3）、control 尽可能遵循RESTFUL风格的接口控制层
	  4）、authorization:权限注解及全局的权限验证处理
## 3、使用第三方的tk.mybatis 做为数据库中间件

# 其他说明
  ## 本系统仅供学习研究。
  ## 能力有限不足之处欢迎指正！
  ## 联系方式：1142682991@qq.com

