<#import "blocks/template.ftlh" as t>
<@t.template userByPrincipal>
    <h2 style="text-align: center">О товаре</h2><hr>
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <#list images as image>
                <li data-target="#myCarousel" data-slide-to="0" class=""></li>
            </#list>
        </ol>
        <div class="carousel-inner">
            <#list images as image>
                <div class="carousel-item <#if image.previewImage>active</#if>">
                    <svg class="bd-placeholder-img" width="100%" height="100%" xmlns="http://www.w3.org/2000/svg"
                         role="img" aria-label=" :  " preserveAspectRatio="xMidYMid slice" focusable="false">
                        <title></title>
                        <rect width="100%" height="100%" fill="#777"></rect>
                        <text x="50%" y="50%" fill="#777" dy=".3em"></text>
                    </svg>

                    <div class="container">
                        <div class="carousel-caption">
                            <a href="/images/${image.id}">
                                <img class="product" style="height: 100px" src="/images/${image.id}">
                            </a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
        <hr>

        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    <hr>
    <form action="/product/favorite/add/${product.id}/${user.id}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" style="text-align: center; width: 100%" class="btn btn-dark mb-2">
            Добавить в избранное
        </button>
    </form>
    <hr>
    <a href="/product/category/${product.category}">
        <p><h3 style="text-align: center">${product.category}</h3></p>
    </a>
    <p><h2 style="text-align: center">${product.title} | ${product.price} ₽</h2></p>
    <hr>
    <p style="text-align: center">${product.description}</p>
    <hr>
    <p style="text-align: center">Номер телефона продавца: <b>${authorProduct.phoneNumber}</b></p>
    <a href="/product/city/${product.city}">
        <p><h3 style="text-align: center">${product.city}</h3></p>
    </a>
    <p style="text-align: center">
        <a href="https://wa.clck.bar/${phone}">
                <img src="/static/images/whatsup.png" width="32" height="32" class="rounded-circle"> Связаться с продавцом
        </a>
    </p>
    <p style="text-align: center">Дата создания объявления: ${date}
    </p>
    <hr>
    <p style="text-align: center">
        <a href="/user/${authorProduct.id}">
             <#if user.previewImageId??>
             <img src="/images/${user.previewImageId}" width="32" height="32" class="rounded-circle"> ${authorProduct.name}
             <#else>
            <img src="/static/images/avatar.png" alt="mdo" width="32" height="32" class="rounded-circle"> ${authorProduct.name}
             </#if>
        </a>
    </p>

</@t.template>