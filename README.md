# Events shop
Project for PV243: Advanced JavaEE Java course at Faculty of Informatics MU

### Prerequisities:

`install jdk-8, maven, nodejs, npm`


### Run backend (linux):

```
cd events-shop/

mvn clean install

cd events-shop-rest/

mvn wildfly:run
```


### Test in your browser:
```
http://localhost:8080/events-shop-rest/api/v0.1/categories
```
Should return JSON Array with three sample objects



### Run frontend (linux):
```
cd events-shop-client/

npm install

npm start
```


### Test in your browser:
```
http://localhost:3000
```
Should show default React page. Hot reload should also work. 
