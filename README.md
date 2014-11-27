OrderingWebServices
===================

The purpose of this set of services is to provide a model implementation of a Java EE based application. 
The application domain is ordering. This means that the domain consists of business objects such as 

Country
Currency
Customer
User
Address
Product
SKU
Order

A DAO is created for each business object and a service layer provides a layer of abstraction over the DAO classes. 
The service layers are exposed via SOAP and ReST web services. 

