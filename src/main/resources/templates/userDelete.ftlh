<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Подтверждение удаления аккаунта</h2>
    <hr>
    <form action="/profile/edit/delete " method="post">
        <div class="form-group">
            <label for="email">Электронная почта</label>
            <input type="email" class="form-control <#if RequestParameters.error??>is-invalid</#if>" id="email" name="email">
            <div id="email" class="invalid-feedback">
                Неверная электронная почта
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
        <button type="submit" style="width: 100%" class="btn btn-dark">Подтвердить удаление</button>
    </form>
    </form>
    <hr>
</@t.template>