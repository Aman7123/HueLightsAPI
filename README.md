# HueLightsAPI

HueLightsAPI is a Java framework designed to parse the JSON from a Philips Hue bridge. The framework is designed to connect with the debug network on the bridge, this is a LAN network communication that can only happen within your home.

## Prerequisites

Login to your router and find the bridge IP address [192.168.0.1](https://19216801.one/)

## 
Next, you will want to connect with that network bridge through the debug portal on a browser, *yes any browser*, on the LAN.

```
https://developers.meethue.com/develop/get-started-2/
```

## Executing
Currently the only runnable code is the release here on github, using the information you have just learned you should be able to run the test program. Good luck!

## Permissions
I own all the code, *unless otherwise stated*, and none of the resources!

## Updates
This code is no longer relevant, if you are reading this let me inform you that you should research this:
```
https://fasterxml.github.io/jackson-databind/javadoc/2.7/com/fasterxml/jackson/databind/ObjectMapper.html 
```
The days of mapping an entire Java object manually are over, learn how to deserialize an object and it will save your life!
