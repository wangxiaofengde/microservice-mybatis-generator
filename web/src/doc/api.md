# 标题：某公司_用户信息管理系统_接口文档


<a name="overview"></a>
## 概览
描述：用于管理集团旗下公司的人员信息,具体包括XXX,XXX模块...


### 版本信息
*版本* : 版本号:1.0


### URI scheme
*域名* : localhost:8080  
*基础路径* : /


### 标签

* test-contoller : Test Contoller




<a name="paths"></a>
## 资源

<a name="test-contoller_resource"></a>
### Test-contoller
Test Contoller


<a name="addusingpost"></a>
#### 添加
```
POST /test/add
```


##### 参数

|类型|名称|说明|类型|
|---|---|---|---|
|**Query**|**a**  <br>*可选*|a|integer (int32)|
|**Query**|**name**  <br>*可选*|name|string|


##### 响应

|HTTP代码|说明|类型|
|---|---|---|
|**200**|OK|无内容|
|**201**|Created|无内容|
|**401**|Unauthorized|无内容|
|**403**|Forbidden|无内容|
|**404**|Not Found|无内容|


##### 消耗

* `application/json`


##### 生成

* `\*/*`







