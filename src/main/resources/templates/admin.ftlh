<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Панель администратора</h2><hr>
    <form method="get" action="/admin">
        <div class="form-group col-md-3">
            <label for="searchUser">введите id пользователя</label>
            <input type="number" name="id" class="form-control"
                   id="searchUser">
        </div>
        <button style="width: 25%" type="submit" class="btn btn-dark">Поиск</button>
    </form>
    <hr>
    <table class="table table-hover table-dark">
        <tr>
            <th scope="col">Email</th>
            <th scope="col">Номер телефона</th>
            <th scope="col">Активность</th>
            <th scope="col">Роли</th>
            <th scope="col">Бан</th>
            <th scope="col">Редактирование</th>
            <th scope="col">Подробная информация</th>
            <th scope="col">Удалить пользователя</th>
        </tr>
        <#list users as user>
            <tr>
                <th>${user.email}</th>
                <th>${user.phoneNumber}</th>
                <th><#if user.active>true<#else>false</#if></th>
                <th><#list user.roles as role>${role}</#list></th>
                <th>
                    <form action="/admin/user/ban/${user.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" class="btn btn-light" value="Бан">
                    </form>
                </th>
                <th><a href="/admin/user/edit/${user.id}">Редактировние</a></th>
                <th><a href="/user/${user.id}">Подробная информация</a></th>
                <th>
                     <form action="/admin/user/delete/${user.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                          <input type="submit" class="btn btn-light" value="Удалить пользователя">
                        </form>
                </th>
            </tr>
        <#else>
            <h3>Пользователей нет</h3>
        </#list>
    </table>
</@t.template>