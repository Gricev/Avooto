<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Личный кабинет</h2>
    <hr>
    <br>
    <div style="text-align: center">
        <#if user.previewImageId??>
            <a href="/profile/edit/avatar/change">
        <img src="/images/${user.previewImageId}"
        style="border:2px solid black; border-radius: 20px" height="120">
            </a>
        <br>
        <br>
        <#else>
        <a href="/profile/edit/avatar/change">
        <img src="/static/images/avatar.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        </a>
        <br>
        <br>
        </#if>
        <h2>${user.name}</h2>
        <br>
        Электоронная почта: <b>${user.email}</b>
        <br>
        Номер телефона: <b>${user.phoneNumber}</b>
        <br>
        <br>
        <#if user.isAdmin()>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
               href="/admin">
                Панель администратора
            </a><br>
        </#if>
        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
           href="/product/favorite">
            Избранные товары
        </a>
        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
           href="/profile/edit">
            Редактировать  профиль
        </a>
        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
           href="my/products/create">
            Добавить товар
        </a>
        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
           href="/my/products">
            Мои товары
        </a>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Выйти из аккаунта
            </button>
        </form>
    </div>
</@t.template>