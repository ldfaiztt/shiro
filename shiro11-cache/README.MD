## 九、JSP标签

### 标签

* guest标签 用户没有身份验证时显示相应信息，即游客访问信息
* user标签    用户已经身份验证/记住我登录后显示相应的信息
* authenticated标签 	用户已经身份验证通过，即Subject.login登录成功，不是记住我登录的。
* notAuthenticated标签 	用户已经身份验证通过，即没有调用Subject.login进行登录，包括记住我自动登录的也属于未进行身份验证。
* principal标签  	显示用户身份信息，默认调用Subject.getPrincipal()获取，即Primary Principal。
* hasRole标签   	如果当前Subject有角色将显示body体内容。
* hasAnyRoles标签   	如果当前Subject有任意一个角色（或的关系）将显示body体内容
* lacksRole标签    	如果当前Subject没有角色将显示body体内容。 
* hasPermission标签    	如果当前Subject有权限将显示body体内容。  
* lacksPermission标签    	如果当前Subject没有权限将显示body体内容。

### 相关单词

* Modular		模块化
* Wildcard		通配符
* Resolver		决策者



 