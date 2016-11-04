# IstatSms
An Android Library than make Sms manipulation easy
-Send SMS, Receive SMS and Query them from SmSProvider usin and easy smq query builder



# Make Query using SmsQL
All SmsProvider Request is made using SmsQL class. Sms is describe by a class named Sms.

 ```java
    SmsQL sql= new SmsQL(mContext);
 ```
  
do a selection on Smsprovider data base   
```java
        List<Sms> selectedSms = sql.selectSms().whereAdressEqual("40101383").execute();
        int updateCount = sql.updateSms()
        .setBody("new body")
        .set....
        .set....
        .setSubject("new subject")
        .execute();
 ``` 

do an insertion on SmsProvider data base
```java
        SmsQL sql = new SmsQL(this);
        int insertCount = sql.insertSms()
        .setAddress("+22575343728")
        .set....
        .set....
        .setBody("Yooo istat")do a
        .execute();
```
delete record from SmsProvider data base
 
```java
        int deleteCount = sql.deleteSms()
        .whereAdressEqual("40101383")
        .where...
        .where...
        .execute();
```

# Send Sms


# Watch for incomming Sms

