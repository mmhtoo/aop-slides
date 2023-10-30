---
layout: intro
---

<Title value="Terms in AOP" />

* <Text value="Aspect" /> : A modularization of a concern that cuts across multiple classes
* <Text value="Join Point" /> : A point during the execution of a program, such as the execution of a method or the handling of an exception. In Spring AOP, a join point always represents a method execution
* <Text value="Advice" /> : Action taken by an aspect at a particular join point. Different types of advice include <Text value="Around" />, <Text value="Before" />, <Text value="After" />, <Text value="After throwing" />, <Text value="After finally" /> advice
* <Text value="Pointcut" /> :  A predicate that matches join points. Advice is associated with a pointcut expression and runs at any join point matched by the pointcut
* <Text value="Target Object" /> : An object being advised by one or more aspects, this object is always a proxied object.
* <Text value="AOP Proxy" /> : An object created by the AOP framework in order to implement the aspect contracts (advise method executions and so on). A AOP Proxy is JDK dynamic proxy or a CGLIB proxy
* <Text value="Weaving" /> : Linking aspects with other application types or objects to create an advised object