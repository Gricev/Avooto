<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Редактирование номера телефона</h2>
    <hr>
    <form action="/profile/edit/phone" method="post">
        <div class="form-group">
            <label for="productName">Введите новый номер телефона без символов, начиная с "7", например: 79001234567</label>
            <input type="text" value="${phone}" required class="form-control" name="phoneNumber">
        </div>
        <div class="form-group">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="width: 100%" class="btn btn-dark">Редактировать</button>
        </div>
    </form>
</@t.template>