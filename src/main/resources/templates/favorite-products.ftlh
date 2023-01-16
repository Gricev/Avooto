<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Избранные товары</h2>
    <hr>
    <#if errorMessage??>
        <h5 style="color: red">${errorMessage}</h5>
    </#if>
    <div class="card-columns">
        <#list favoriteProducts as product>
            <a href="/product/${product.id}/${product.user.id}" class="product-link">
<#--            <a a href="/product/${product.id}/${user.id}" class="product-link">-->
                <div class="card text-white bg-dark my-3 product">
                    <div class="card-top">
                        <form action="/product/favorite/delete/${product.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-danger m-3">
                                Удалить из избранного
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
            <h3 style="text-align: center">Товаров не найдено</h3>
        </#list>
    </div>
</@t.template>