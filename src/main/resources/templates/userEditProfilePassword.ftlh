<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Редактирование номера телефона</h2>
    <hr>
    <form action="/profile/edit/password" method="post">
        <div class="form-group">
            <label for="productName">Введите старый пароль</label>
            <input type="password"  required class="form-control" name="pass">
        </div>
        <div class="form-group">
            <label for="productName">Введите новый пароль</label>
            <input type="password"  required class="form-control" name="password">
        </div>
        <div class="form-group">
            <label for="productName">Повторите новый пароль</label>
            <input type="password"  required class="form-control" name="passRep">
        </div>
        <div class="form-group">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="width: 100%" class="btn btn-dark">Редактировать</button>
        </div>
    </form>
    <#if message??>
        <h2 style="color: red">${message}</h2>
    </#if>
</@t.template>
