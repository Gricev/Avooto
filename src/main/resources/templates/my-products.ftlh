<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Мои товары</h1>
    <hr>

    <div class="card-columns">
        <#list products as product>
            <a href="/product/${product.id}/${product.user.id}" class="product-link">
                <div class="card text-white bg-dark my-3 product">
                    <div class="card-top">
                        <form action="/my/products/edit/${product.id}" method="get" >
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-warning m-3">
                                Редактировать
                            </button>
                        </form>
                        <form action="/product/delete/${product.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-danger m-3">
                                Удалить
                            </button>
                        </form>
                    </div>
                    <img src="/images/${product.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${product.title}</h5>
                        <p class="card-text">${product.price} ₽</p>
                    </div>
                    <div class="card-footer">
                        ${product.city}
                    </div>
                    <div class="card-footer">
                        ${product.category}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Не найдено</h1>
        </#list>
    </div>
</@t.template>