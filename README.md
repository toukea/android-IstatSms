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
        SmsSelection selection2 = sql.selectSms().whereBodyLike("%hello%");
        SmsSelection selection3 = sql.selectSms().whereBodyLike("%world%");
        List<Sms> smss = sql.selectSms()
        .WHERE(selection1)
        .AND(selection2)
        .OR(selection3)
        .execute();
```
 update from multiple nested sms selection
```java
        SmsSelection selection1 = sql.selectSms().whereAddressEqual("40101383");
        SmsSelection selection2 = sql.selectSms().whereBodyLike("%hello%");
        SmsSelection selection3 = sql.selectSms().whereBodyLike("%world%");
        int count = sql.updateSms()
        .setBody("Hello World")
        .WHERE(selection1)
        .AND(selection2)
        .OR(selection3)
        .execute();
```

# Send Sms
You can send sms using SmsSender class.
```java
 SmsSender sender = new SmsSender(this);
 sender.sendSms("40101383", "Hello world", new SmsSender.SendCallBack(){ 
     @Override
     public void onSuccessSending(Sms sms) {
         //when Sms has been sent succesfully.
     }
 
     @Override
     public void onGenericFail(Sms sms) {
        //when Generic Error, ex: no enougth mobile credit.
     }
 
     @Override
     public void onRadioOffFail(Sms sms) {
         //when errordue to mobile network.
     }
 
     @Override
     public void onBadFormedSmsFail(Sms sms) {
         //when sms is bad formed ex: not correct phone number
     }
 });
```

# Watch for incoming Sms
Using the SmsWatcher, you can watch for incoming Sms
```java
SmsWatcher smsWatcher=new SmsWatcher(mContext);
smsWatcher.startWatching(new SmsListener(){
@Override
    public void onReceiveSms(SmsWatcher.SmsPart sms, BroadcastReceiver receiver) {
        /*
        Do what you want with your SmsPart instance.
        it contain phoneNumber and SmsBody.
        
        You have also the broadcastReceiver used to receive Sms. (Do what you want with ...)
        you can for exemple abort them
        if you don't want auther application to get this sms
        */
    }
})
```
There is also possible to watch with some priority (your application can be the first to get the SMS, more priority is big, must you can be the first to get Sms received)
```java
smsWatcher.startWatching(mSmsListener, 2147483647)//there, i listen with max priority
```
After you have get your incoming Sms or you don't want to watch anymore (ex: the Activity->onDestroy())
you have to stop watching.
```java
smsWatcher.stopWatching();
```

#Usage
-----
Just add the dependency to your `build.gradle`:

```groovy
dependencies {
   compile 'istat.android.telephony.sms:istat-sms:1.2.0'
}
```

minSdkVersion = 10
------------------
IstatSms is compatible with Android 2.3 and newer.

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

