To control cpu used rate, to control jvm memory rate
Sample maven project containing a Java agent and examples of bytecode manipulation Javassist.


## Build

```
$ # From the agent dir
$ mvn clean install
error可忽略
```

## Run

```

With agent
$ java -Xms8g -Xms8g -Xmn1g -javaagent:agent/target/agent-1.0.jar=7,0,80 -jar yourApplication.jar 
参数说明：Xmn根据实际情况调整，7为cpu物理核数，建议最大配置n-1，否则机器会卡死，80为占用老年代内存空间的大小百分比

