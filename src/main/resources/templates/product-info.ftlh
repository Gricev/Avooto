<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Avooto</title>
</head>
<body>
<h1>Avooto</h1><hr>
<h4>Подробная информация о товаре</h4>
<#list  images as img>
    <img src="/images/${img.id}" height="250px"/><br><br>
</#list>
<b>Название товара: </b>${product.title}<br>
<b>Описание товара: </b>${product.description}<br>
<b>Цена: </b>${product.price}<br>
<b>Город: </b>${product.city}<br>
<b>Продавец: </b><a href="/user/${product.user.id}">${product.user.name}</a><br>
</body>
</html>