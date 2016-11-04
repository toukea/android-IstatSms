# IstatSms
An Android Library than make Sms manipulation easy
-Send SMS, Receive SMS and Query them from SmSProvider usin and easy smq query builder



# Make Query using SmsQL
All SmsProvider Request is made using SmsQL class. Sms is describe by a class named Sms.

 ```java
    SmsQL sql= new SmsQL(mContext);
 ```
  
do a Selection on SmsProvider data base   
```java
        List<Sms> selectedSms = sql.selectSms()
        .whereAdressEqual("40101383")
        .and....
        .or....
        .andBodyLike("hello")
        .execute();
 ``` 

do an insertion on SmsProvider data base
```java
        SmsQL sql = new SmsQL(this);
        int insertCount = sql.insertSms()
        .setAddress("40101383")
        .set....
        .set....
        .setBody("Yooo istat")
        .execute();
```

update on SmsProvider data base   
```java
        List<Sms> selectedSms = sql.selectSms().whereAdressEqual("40101383").execute();
        int updateCount = sql.updateSms()
        .setBody("new body")
        .set....
        .set....
        .setSubject("new subject")
        .whereAdressEqual("40101383")
        .and...
        .or...
        .execute();
 ``` 
 
delete record from SmsProvider data base
 
```java
        int deleteCount = sql.deleteSms()
        .whereAdressEqual("40101383")
        .and...
        .or...
        .execute();
```
multiple nested sms selection 
```java
        SmsSelection selection1 = sql.selectSms().whereAdressEqual("40101383");
        SmsSelection selection2 = sql.selectSms().whereBodyLike("hello");
        SmsSelection selection3 = sql.selectSms().whereBodyLike("world");
        List<Sms> smss = sql.selectSms()
        .WHERE(selection1)
        .AND(selection2)
        .OR(selection3)
        .execute();
```
multiple nested sms update
```java
        SmsSelection selection1 = sql.selectSms().whereAdressEqual("40101383");
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

