<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Удаление аватара</h2>
    <hr>
    <form action="/profile/edit/avatar/delete" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-danger m-3">
            Удалить аватар
        </button>
    </form>
</@t.template>