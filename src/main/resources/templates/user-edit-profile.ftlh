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
    <form action="/profile/edit" method="post" enctype="multipart/form-data">

        <div class="custom-file">
            <input required type="file" name="file" id="file">
            <label class="custom-file-label" for="file">Добавить аватар</label>
        </div>
        <div class="form-group">
            <label for="productName">Введите новый почтовый адрес</label>
            <input type="text" required class="form-control" name="email">
        </div>
        <div class="form-group">
            <label for="description">Введите новый пароль</label>
            <input type="password" required class="form-control" name="password">
        </div>
        <div class="form-group">
            <label for="description">Введите имя пользователя</label>
            <input type="text" required class="form-control" name="name">
        </div>
        <div class="form-group">
            <label for="description">Введите номер телефона пользователя</label>
            <input type="text" required class="form-control" name="phoneNumber">
        </div>
        <div class="form-group">

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-dark">Редактировать</button>
        </div>
    </form>
</@t.template>