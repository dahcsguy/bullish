# Bullish take home

## Overview


### Requirements
- Java 17
- Maven
- H2
- Postman (or any tools to test API)
### Installation
1. Clone repo
```
  git clone https://github.com/dahcsguy/bullish-take-home.git
```
2. Go to project directory
3. ```mvn clean package```
4. ```java --enable-preview -jar .\target\bullish-0.0.1-SNAPSHOT.jar```
5. Run tests```mvn test```
6. localhost:8080
   7. change to other port by updating `server.port` in application.properties
7. Database: localhost:8080/h2-console 



### Usage
#### Products
Create product
```
POST /admin/products/add
{
    "name": "Apple",
    "price" : 12.35
}
```
Delete product
```
DELETE /admin/products/delete/{productId}
```

Get all products
```
GET /products
```

Get product
```
GET /products/{productId}
```

#### Promotions
Create promotions
```
POST /admin/promotions/add
{
    "productId": 1,
    "maxUse": 2,
    "y" : 1,
    "x" : 1,
    "percent" : 50
}
```

#### Cart

Create cart
```
POST /carts
```

Add item to cart
```
POST /carts/{cartId}/add
{
    "product": {
      "productId" : 1
    },
    "quantity" : 14
}

```

Remove item from cart
```
POST /carts/{cartId}/delete/{cartItemId}
```

Get cart
```
GET /carts/{cartId}
```

#### Order
Place order
```
POST /carts/{cartId}/checkout
```

Receipt of all purchases
```
GET /orders

[
    {
        "orderId": 1,
        "orderItemList": [
            {
                "orderItemId": 1,
                "productName": "apple",
                "price": 12.35,
                "quantity": 14,
                "subtotal": 172.9,
                "promotion": {
                    "promotionId": 1,
                    "productId": 1,
                    "maxUse": 2,
                    "y": 1,
                    "x": 1,
                    "percent": 50.0
                },
                "discount": 12.35
            }
        ],
        "grantTotal": 160.55,
        "subtotal": 172.9,
        "discount": 12.35
    }
]
```