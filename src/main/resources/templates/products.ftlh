<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Avooto</title>
</head>
<body>
<h1>Avooto</h1>
<hr>
<#if user.email??>
    <h3>Здравствуй,  ${user.name}</h3>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти"/>
    </form>
    <#if user.isAdmin()>
        <a href="/admin">Панель администратора</a>
        </#if>
<#else>
    <a href="/login">Войти</a> </h1>
</#if>
<hr>
<h4>Товары со всей России</h4>
<form action="/" method="get">
    Поиск по названию объявления: <input type="text" name="title">
    <input type="submit" value="Поиск"/>
</form>
<#list products as product>
    <div>
        <p><b>${product.title}</b> ${product.price} руб. | <a href="/product/${product.id}">Подробнее</a></p>
    </div>
    <#else>
    <h3>Товаров нет</h3>
</#list>
<#if user.email??>
<hr>
<h3> Создать новый товар</h3>
<form action="/product/create" method="post" enctype="multipart/form-data">
    Название объявления: <input type="text" name="title"/><br><br>
    Описание объявления: <input type="text" name="description"/><br><br>
    Цена: <input type="text" name="price"/><br><br>
    Город: <input type="text" name="city"/><br><br>
    Добавить фото: <input type="file" name="file1"/><br><br>
    Добавить фото: <input type="file" name="file2"/><br><br>
    Добавить фото: <input type="file" name="file3"/><br><br>
    Добавить фото: <input type="file" name="file4"/><br><br>
    Добавить фото: <input type="file" name="file5"/><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Добавить товар"/>
</form>
</#if>
</body>
</html>