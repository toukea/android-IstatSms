# IstatSms
An Android Library than make Sms manipulation easy
-Send SMS, Receive SMS and Query them from SmSProvider using and easy sms query builder



# Make Query using SmsQL
All SmsProvider Request is made using SmsQL class. Sms is describe by a class named Sms.

 ```java
    SmsQL sql= new SmsQL(mContext);
 ```
  
do a Selection on SmsProvider data base   
```java
        List<Sms> selectedSms = sql.selectSms()
        .whereAddressEqual("40101383")
        .and....//use auto complete to see more
        .or....
        .andBodyLike("hello")
        .execute();
 ``` 

do an insertion on SmsProvider data base
```java
        SmsQL sql = new SmsQL(this);
        int insertCount = sql.insertSms()
        .setAddress("40101383")
        .set....//use auto complete to see more
        .set....
        .setBody("Yooo istat")
        .execute();
```

update on SmsProvider data base   
```java
        int updateCount = sql.updateSms()
        .setBody("new body")
        .set....//use auto complete to see more
        .set....
        .setSubject("new subject")
        .whereAddressEqual("40101383")
        .and...
        .or...
        .execute();
 ``` 
 
delete record from SmsProvider data base
 
```java
        int deleteCount = sql.deleteSms()
        .whereAddressEqual("40101383")
        .and...//use auto complete to see more
        .or...
        .execute();
```
multiple nested sms selection 
```java
        SmsSelection selection1 = sql.selectSms().whereAddressEqual("40101383");
        SmsSelection selection2 = sql.selectSms().whereBodyLike("hello");
        SmsSelection selection3 = sql.selectSms().whereBodyLike("world");
        List<Sms> smss = sql.selectSms()
        .WHERE(selection1)
        .AND(selection2)
        .OR(selection3)
        .execute();
```
 update from multiple nested sms selection
```java
        SmsSelection selection1 = sql.selectSms().whereAddressEqual("40101383");
        SmsSelection selection2 = sql.selectSms().whereBodyLike("hello");
        SmsSelection selection3 = sql.selectSms().whereBodyLike("world");
        int count = sql.updateSms()
        .setBody("Hello World")
        .WHERE(selection1)
        .AND(selection2)
        .OR(selection3)
        .execute();
```

# Send Sms

//Documentation in progress.. :-)

# Watch for incoming Sms

//Documentation in progress.. :-)

Usage
-----
Just add the dependency to your `build.gradle`:

```groovy
dependencies {
   compile 'istat.android.telephony.sms:istat-sms:1.2.0'
}
```

minSdkVersion = 10
------------------
IstatSms is compatibile with Android 2.3 and newer.

Download
--------
add the dependency to your pom.xml:

```xml
<dependency>
  <groupId>istat.android.telephony.sms</groupId>
  <artifactId>istat-sms</artifactId>
  <version>1.2.0</version>
  <type>pom</type>
</dependency>
```

