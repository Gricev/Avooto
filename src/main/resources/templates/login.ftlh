<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Авторизация</h2>
    <hr>
    <form action="/login " method="post">
        <div class="form-group">
            <label for="email">Электронная почта</label>
            <input type="email" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="email" name="username">
            <div id="email" class="invalid-feedback">
                Неверная электронная почта или аккаунт не подтверждён. Проверьте электронный адрес.
            </div>
        </div>
        <div class="form-group">
            <label for="password">Пароль</label>
            <input type="password" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="password" name="password">
            <div id="password" class="invalid-feedback">
                Неверный пароль
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Войти</button>
    </form>
    <hr>
    <h5><a href="/registration">Регистрация</a></h5>
    <h5><a href="/forgetPassword">Забыли пароль?</a></h5>

</@t.template>
