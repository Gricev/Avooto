<#import "blocks/template.ftlh" as t>
<@t.template user>
<h2 style="text-align: center">Авторизация</h2>
<hr>
<form action="/forgetPassword" method="post">
    <div class="form-group">

        <label for="email">Укажите электронный адрес</label>
        <input type="email" class="form-control <#if RequestParameters.error??>is-invalid</#if>"
               placeholder="Например:Avooto@mail.ru" id="email" name="username">
        <div id="email" class="invalid-feedback">
            Указанный почтовый адрес неверный или не существует.
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit" style="width: 100%" class="btn btn-dark">Отправить</button>
</form>
    <#if errorMessage??>
        <h5 style="color: red">${errorMessage}</h5>
    </#if>
</@t.template>