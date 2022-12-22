<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Редактирование аватара</h2>
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
    <form action="/profile/edit/avatar/delete" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-danger mb-3" style="text-align: center; width: 100%">Удалить аватар
        </button>
    </form>
</@t.template>