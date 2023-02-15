<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Правка фотографий</h2>
    <hr>

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
                        <form action="/my/products/edit/${product.id}/delete/${image.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <a href="/images/${image.id}">
                                <img class="product" style="height: 100px" src="/images/${image.id}">
                            </a>
                            <button type="submit" class="btn btn-danger m-3">
                                Удалить
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </#list>
    </div>
        <a class="carousel-control-prev" href="#myCarousel" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Предыдущий</span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Следующий</span>
        </a>
    </div>
</@t.template>