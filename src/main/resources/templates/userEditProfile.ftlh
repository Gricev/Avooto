<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Редактирование профиля</h1>
    <hr>
    <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Приступим?
    </a>

    <div class="collapse mb-3" id="collapseExample" style="">

        <form action="/profile/edit/avatar/delete" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-danger m-3" style="text-align: center; width: 100%">Удалить аватар
            </button>
        </form>
        <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
           href="/profile/edit/avatar/change">
            Добавить аватар
            </a>
    <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
       href="/profile/edit/password">
        Редактировать  пароль
    </a>
    <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
       href="/profile/edit/username">
        Редактировать  имя пользователя
    </a>
    <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
       href="/profile/edit/phone">
        Редактировать номер телефона
    </a>
        <a class="btn btn-danger mb-3" style="text-align: center; width: 100%"
           href="/profile/edit/delete">
            Удалить профиль
        </a>
        </div>
</@t.template>
