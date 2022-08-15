<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Редактирование имени</h1>
    <hr>
    <form action="/profile/edit/username" method="post">
        <div class="form-group">
            <label for="productName">Введите новое имя пользователя</label>
            <input type="text" value="${username}" required class="form-control" name="name">
        </div>
        <div class="form-group">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="width: 100%" class="btn btn-dark">Редактировать</button>
        </div>
    </form>
</@t.template>