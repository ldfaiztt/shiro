## 三、授权authorization

授权，也叫访问控制，即在应用中控制谁能访问哪些资源（如访问页面/编辑数据/页面操作等）。在授权中需了解的几个关键对象：主体（Subject）、资源（Resource）、权限（Permission）、角色（Role）。
主体
主体，即访问应用的用户，在Shiro中使用Subject代表该用户。用户只有授权后才允许访问相应的资源。
资源
在应用中用户可以访问的任何东西，比如访问JSP页面、查看/编辑某些数据、访问某个业务方法、打印文本等等都是资源。用户只要授权后才能访问。
权限
安全策略中的原子授权单位，通过权限我们可以表示在应用中用户有没有操作某个资源的权力。即权限表示在应用中用户能不能访问某个资源，如：
访问用户列表页面
查看/新增/修改/删除用户数据（即很多时候都是CRUD（增查改删）式权限控制）
打印文档等等。

* 用户-角色 ：规则即：“用户名=密码,角色1，角色2”，
* 角色-权限 ：规则：“用户名=密码，角色1，角色2”“角色=权限1，权限2”，

此时我们继承AuthorizingRealm而不是实现Realm接口；推荐使用AuthorizingRealm，因为：
AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)：表示获取身份验证信息；
AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)：表示根据用户身份获取授权信息。
这种方式的好处是当只需要身份验证时只需要获取身份验证信息而不需要获取授权信息。

 