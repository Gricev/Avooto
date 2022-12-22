<#import "blocks/template.ftlh" as t>
<@t.template userByPrincipal>
    <h2 style="text-align: center">Информация о пользователе</h2>
    <hr>
    <div style="text-align: center">
        <#if user.previewImageId??>
        <img src="/images/${user.previewImageId}"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <#else>
        <img src="/static/images/avatar.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        </#if>
        <h2>${user.name}</h2>
        <br>
        Номер телефона: <b>${user.phoneNumber}</b>
        <p style="text-align: center">
            <a href="https://wa.clck.bar/${user.phoneNumber}">
                <img src="/static/images/whatsup.png" width="32" height="32" class="rounded-circle"> Связаться с продавцом
            </a>
        </p>
    </div>
    <br>
    <hr>
    <h1 style="text-align: center">Товары пользователя ${user.name}</h1>
    <br>
    <div class="card-columns">
        <#list products as product>
            <a href="/product/${product.id}/${userByPrincipal.id}" class="product-link">
                <div class="card text-white bg-dark my-3 product">

                    <img src="/images/${product.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${product.title}</h5>
                        <p class="card-text">${product.price} ₽</p>
                    </div>
                    <div class="card-footer">
                        ${product.user.name}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>