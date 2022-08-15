<#import "blocks/template.ftlh" as t>
<@t.template user>
<h1 style="text-align: center">Редактирование почтового адреса</h1>
<hr>
    <form action="/profile/edit/email" method="post">
<div class="form-group">
    <label for="productName">Введите новый почтовый адрес</label>
    <input type="text" value="${email}" required class="form-control" name="email">
</div>
        <div class="form-group">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="width: 100%" class="btn btn-dark">Редактировать</button>
        </div>
    </form>
</@t.template>