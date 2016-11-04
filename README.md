# IstatSms
An Android Library than make Sms manipulation easy
-Send SMS, Receive SMS and Query them from SmSProvider usin and easy smq query builder



# Make Query using SmsQL
All SmsProvider Request is made using SmsQL class. Sms is describe by a class named Sms.

 ```java
    SmsQL sql= new SmsQL(mContext);
 ```
  
do a Selection on Smsprovider data base   
```java
        List<Sms> selectedSms = sql.selectSms().whereAdressEqual("40101383").execute();
        int updateCount = sql.updateSms()
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
        .setBody("Yooo istat")do a
        .execute();
```

update on Smsprovider data base   
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

# Send Sms

//Documentation in progress.. :-)

# Watch for incomming Sms

//Documentation in progress.. :-)

