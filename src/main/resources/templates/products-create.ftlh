<#import "blocks/template.ftlh" as t>
<@t.template user>
<h1 style="text-align: center">Добавить новый товар</h1>
<hr>
<a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
   data-toggle="collapse" href="#collapseExample" role="button"
   aria-expanded="true" aria-controls="collapseExample">
    Добавить новый товар
</a>
<div class="collapse mb-3" id="collapseExample" style="">
    <form action="/product/create" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="productName">Название товара</label>
            <input type="text" required class="form-control"
                   id="productName" name="title">
        </div>
        <div class="form-group">
            <label for="price">Цена товара</label>
            <div class="input-group mb-3">
                <input type="number" required class="form-control" id="price" name="price">
                <div class="input-group-append">
                    <span class="input-group-text">₽</span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="searchCity">Город</label>
            <select id="searchCity" required name="city" class="form-control">
                <option value="">Город не выбран</option>

                <option value="Москва">Москва</option>
                <option value="Красноярск">Красноярск</option>
                <option value="Казань">Казань</option>
                <option value="Санкт-Петербург">Санкт-Петербург</option>
                <option value="Краснодар">Краснодар</option>
                <option value="Ростов на Дону">Ростов на Дону</option>

            </select>
        </div>
        <div class="form-group">
            <label for="description">Описание товара</label>
            <textarea required class="form-control" id="description" name="description"></textarea>
        </div>
        <div class="form-group">
            <div class="custom-file">
                <input required type="file" name="file1" id="file1">
                <label class="custom-file-label" for="file1">Добавить фото</label>
            </div>
        </div>
        <div class="form-group">
            <div class="custom-file">
                <input type="file" name="file2" id="file2">
                <label class="custom-file-label" for="file2">Добавить фото</label>
            </div>
        </div>
        <div class="form-group">
            <div class="custom-file">
                <input type="file" name="file3" id="file3">
                <label class="custom-file-label" for="file3">Добавить фото</label>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file4" id="file4">
                    <label class="custom-file-label" for="file4">Добавить фото</label>
                </div>
                <div class="custom-file">
                    <input type="file" name="file5" id="file5">
                    <label class="custom-file-label" for="file5">Добавить фото</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-dark">Добавить</button>
        </div>
    </form>
</@t.template>