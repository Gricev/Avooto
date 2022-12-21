<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Восстановление доступа</h1>
    <hr>
    <form action="/forgetPasswordNumb" method="post">
        <div class="form-group">

            <label for="number">На ваш электронный адрес было направлено письмо с 4-х значным кодом подтверждения.</label>
            <input type="number" class="form-control <#if RequestParameters.error??>is-invalid</#if>"
                   placeholder="Например:1234" id="number" name="forgetPasswordNumber">
            <div id="number" class="invalid-feedback">
                Неверный код доступа, либо пароли не совпадают
            </div>
        </div>
        <hr>
        <div class="form-group">
            <label for="password">Введите пароль</label>
            <input type="password" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="password" name="password">
            <div id="password" class="invalid-feedback">
                Неверный код доступа, либо пароли не совпадают
            </div>
        </div>
        <div class="form-group">
            <label for="password">Повторите пароль</label>
            <input type="password" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="password" name="passwordRepeat">
            <div id="password" class="invalid-feedback">
                Неверный код доступа, либо пароли не совпадают
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit" style="width: 100%" class="btn btn-dark">Войти</button>
    </form>
    <#if errorMessage??>
        <h5 style="color: red">${errorMessage}</h5>
    </#if>
</@t.template>