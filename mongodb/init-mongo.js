db = db.getSiblingDB('product-db');

db.createCollection('product-collection');
db['product-collection'].insertMany([ 
  { "_id": "1", "name": "Xpto" }
]);