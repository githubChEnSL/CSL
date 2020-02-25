# connection-transaction

## 作用
### 1.连接池的以及事务控制的学习（原理部分了解，应用部分会用）
### 2.基于XML配置的动态SQL语句的使用（会用即可）
- mappers配置文件中的标签：
- if where foreach sql

### 3.多表操作（掌握）
- 一对多
- 一对一（？）
- 多对多

## 事务
## 什么是事务
	指访问并可能更新数据库中各种数据项的一个程序执行单元(unit)。事务通常由高级数据库操纵语言或编程语言（如SQL,C++或Java）书写的用户程序的执行所引起,并用形如begin transaction和end transaction语句（或函数调用）来界定。事务由事务开始(begin transaction)和事务结束(end transaction)之间执行的全体操作组成。

## 事务的四大特性 ACID

## 不考虑隔离性会产生的3个问题

## 解决方法 四种隔离级别

### 动态Sql语句