<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Добавление аватара</h1>
    <hr>
    <form action="/profile/edit/avatar/change" method="post" enctype="multipart/form-data">
        <div class="custom-file">
            <input required type="file" name="file" id="file">
            <label class="custom-file-label" for="file">Добавить аватар</label>
        </div>
        <div class="form-group">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="width: 100%" class="btn btn-dark">Редактировать</button>
        </div>
    </form>
</@t.template>