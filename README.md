# Half Quickstart Examples

### Step-by-step
- Check your Maven dependencies :
```sh
$ mvn dependency:resolve -P hdp-2.5.3.0-37
$ mvn dependency:list -P hdp-2.5.3.0-37| grep ":.*:.*:.*" | cut -d] -f2- | sed 's/:[a-z]*$//g' | sort -u
$ mvn dependency:tree -Dverbose -P hdp-2.5.3.0-37
```

- Build with the following commands :
```sh
mvn clean package -P hdp-2.5.3.0-37
```

- Set a classpath containing the uber-jar and two additionals libraries not correctly included :
```sh
$ export CLASSPATH=./target/half-quickstart-0.1-SNAPSHOT.jar
$ files=(
hadoop-hdfs-2.7.3.2.5.3.0-37.jar
hadoop-yarn-common-2.7.3.2.5.3.0-37.jar
)
$ for i in "${files[@]}"
do
   : 
   export CLASSPATH=$CLASSPATH:"$(find ~/.m2 -name $i)"
done
```

- Execute with the options that fit your setup :
```sh
$ java \
  -Djavax.net.ssl.trustStorePassword=trustStorePassword \
  -Djavax.net.ssl.trustStore=$JAVA_HOME/lib/security/trust.jks \
  -Djava.security.krb5.conf=/etc/krb5.conf \
  -Dhadoop.conf.dir=/etc/hadoop/conf \
  -Duser.kerberos.keytab=/home/user/principal.keytab \
  -Duser.kerberos.principal=principal@REAL.COM \
  -Dhive.server.host=my.hive.server.domain \
  -Dhive.server.port=10001 \
  -Dhive.kerberos.principal=hive/_HOST@REALM.COM \
  -classpath $CLASSPATH \
  com.manganit.half.quickstart.Diagnostic
```

- If everything goes well, you should see the following logs :
```sh
Configuration file added : /etc/hadoop/conf/core-site.xml
Configuration file added : /etc/hadoop/conf/hdfs-site.xml
Configuration file added : /etc/hadoop/conf/yarn-site.xml
Configuration file added : /etc/hadoop/conf/mapred-site.xml
Configuration file added : /etc/hadoop/conf/hbase-site.xml
Configuration file added : /etc/hadoop/conf/hive-site.xml
...
a bunch of context information to help debug
...
INFO  HealthCheck:58 - HDFS check ...
INFO  HealthCheck:60 - HDFS check : OK (Free space is XXXX GB)
INFO  HealthCheck:71 - Metastore check ...
INFO  metastore:402 - Trying to connect to metastore with URI thrift://metastore.domain:9083
INFO  metastore:498 - Connected to metastore.
INFO  HealthCheck:73 - Metastore check : OK (X available databases)
INFO  HealthCheck:84 - Hive check ...
INFO  HiveJdbcClient:88 - Hive connection string : jdbc:hive2://zk1.domain:2181,zk2.domain:2181,zk3.domain:2181/default;principal=hive/_HOST@REALM.COM;ssl=true;sslTrustStore=/usr/local/jdk1.8.0_45/jre/lib/security/trust.jks;trustStorePassword=trustStorePassword;transportMode=http;zooKeeperNamespace=hiveserver2;serviceDiscoveryMode=zooKeeper;httpPath=cliservice?#
INFO  CuratorFrameworkImpl:230 - Starting
INFO  ConnectionStateManager:228 - State change: CONNECTED
INFO  HiveJdbcExecutor:82 - HiveUtil.execute:sql =show databases
INFO  HealthCheck:96 - Hive check : OK (X available databases)
INFO  HealthCheck:111 - HBase check ...
INFO  HealthCheck:117 - HBase check : OK (X tables availables in Y namespaces)
INFO  HealthCheck:128 - Yarn check ...
INFO  TimelineClientImpl:299 - Timeline service address: https://my.timeline.server.domain:8190/ws/v1/timeline/
INFO  AHSProxy:42 - Connecting to Application History server at my.applicationhistory.server.domain/ip:10200
INFO  HealthCheck:133 - Yarn check : OK (X Node Managers)
INFO  HealthCheck:144 - Pig check ...
INFO  HealthCheck:147 - Pig check : OK
```


