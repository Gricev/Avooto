<!DOCTYPE html>
<html>
<head>
    <title>Avooto</title>
</head>
<body>
<h1>Avooto</h1><hr>
<h3>Подробная информация о пользователе ${user.name}</h3>
<b>Номер телефона: </b> ${user.phoneNumber}<br>

<h4>Профиль ${user.name}</h4>
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} руб. | <a href="/product/${product.id}">Подробнее</a></p>
    </div>
    <#else>
    <h3>оваров у данного пользователя не найдено.</h3>
</#list>
<#if errorMessage??>
    <h2 style="color: red">${errorMessage }</h2>
</#if>
</body>
</html>