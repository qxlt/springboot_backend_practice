print('START')
db = db.getSiblingDB('product-microservice')

db.createUser(
    {
        user: 'admin',
        pwd: 'password',
        roles: [ {role: 'readWrite', db: 'product-microservice'} ]
    }
);

db.createCollection('user');


print('END')
