# 懵逼直播

## 环境要求
* Java8+
* Maven3+
* MariaDB10+(MySQL5.6+) 

## 安装说明

1.获得本项目源码：

运行命令

```
git clone https://github.com/x-pengg/MBLive.git
```


2.安装 maven 依赖

在 clone 下来的`MBLive`文件夹中运行命令

```
mvn install
```

3.导入SQL文件 `live.sql`

4.修改配置文件  
执行命令（如果处于 windows 环境下可利用 git bash 执行该命令）  

  `cp example.env.properties env.properties`  
  
 4.1 修改七牛云/乐视云配置信息
 
```
vim env.properties
```

 4.2 修改MySQL数据库连接信息（其他数据库需要修改 dialect ）
 
```
vim application.yml
```

启动：
```
mvn spring-boot:run

#指定 profiles
mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=production"
```

## LICENSE

MIT © [Tate](https://ridog.me)





