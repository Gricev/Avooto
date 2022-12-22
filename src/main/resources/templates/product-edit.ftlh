<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h2 style="text-align: center">Изменить информацию о товаре</h2>
    <hr>
    <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Изменить информацию
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
    <form action="/product/edit/${productId}" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="category">Категория товара</label>
            <select id="category" autofocus name="category"  class="form-control">
                <option selected value="${category}">${category}</option>
                <option value="Автомобили">Автомобили</option>
                <option value="Мотоциклы и мототехника">Мотоциклы и мототехника</option>
                <option value="Грузовики и спецтехника">Грузовики и спецтехника</option>
                <option value="Водный транспорт">Водный транспорт</option>
                <option value="Запчасти и аксессуары">Запчасти и аксессуары</option>
                <option value="Недвижимость">Недвижимость</option>
                <option value="Квартиры">Квартиры</option>
                <option value="Комнаты">Комнаты</option>
                <option value="Земельные участки">Земельные участки</option>
                <option value="Гаражи">Гаражи</option>
                <option value="Коммерческие помещения">Коммерческие помещения</option>
                <option value="Недвижимость за рубежом">Недвижимость за рубежом</option>
                <option value="Одежда">Одежда</option>
                <option value="Обувь">Обувь</option>
                <option value="Сумки, рюкзаки">Аксесуары</option>
                <option value="Детская одежда и обувь">Детская одежда и обувь</option>
                <option value="Товары для детей и игрушки">Товары для детей и игрушки</option>
                <option value="Красота и здоровье">Красота и здоровье</option>
                <option value="Часы и украшения">Часы и украшения</option>
                <option value="Ремонт и строительство">Ремонт и строительство</option>
                <option value="Мебель и интерьер">Мебель и интерьер</option>
                <option value="Бытовая техника">Бытовая техника</option>
                <option value="Продукты питания">Продукты питания</option>
                <option value="Растения">Растения</option>
                <option value="Посуда и товары для кухни">Посуда и товары для кухни</option>
                <option value="Запчасти">Запчасти</option>
                <option value="Шины, диски, колеса">Шины, диски, колеса</option>
                <option value="Аудио и видеотехника автомобиля">Аудио и видеотехника автомобиля</option>
                <option value="Тюнинг">Тюнинг</option>
                <option value="Инструменты">Инструменты</option>
                <option value="Прицепы">Прицепы</option>
                <option value="Экипировка">Экипировка</option>
                <option value="Автохимия">Автохимия</option>
                <option value="Противоугонные устройства">Противоугонные устройства</option>
                <option value="GPS-навигаторы">GPS-навигаторы</option>
                <option value="Телефоны">Телефоны</option>
                <option value="Аудио и видео">Аудио и видео</option>
                <option value="Товары для компьютера">Товары для компьютера</option>
                <option value="Игры, приставки">Игры, приставки</option>
                <option value="Ноутбуки">Ноутбуки</option>
                <option value="Настольные компьютеры">Настольные компьютеры</option>
                <option value="Запчасти для электронных устройств">Запчасти для электронных устройств</option>
                <option value="Фототехника">Фототехника</option>
                <option value="Планшеты, электронные книги">Планшеты, электронные книги</option>
                <option value="Оргтехника и расходные материалы">Оргтехника и расходные материалы</option>
                <option value="Билеты и путешествия">Билеты и путешествия</option>
                <option value="Велосипеды">Велосипеды</option>
                <option value="Книги и журналы">Книги и журналы</option>
                <option value="Коллекционирование">Коллекционирование</option>
                <option value="Музыкальные инструменты">Музыкальные инструменты</option>
                <option value="Охота и рыбалка">Охота и рыбалка</option>
                <option value="Спорт и отдых">Спорт и отдых</option>
                <option value="Собаки">Собаки</option>
                <option value="Кошки">Кошки</option>
                <option value="Птица">Птица</option>
                <option value="Прочие животные">Прочие животные</option>
                <option value="Товары для животных">Товары для животных</option>
            </select>
        </div>
        <div class="form-group">
            <label for="productName">Название товара</label>
            <input type="text" value="${title}" required class="form-control"
                   id="productName" name="title">
        </div>
        <div class="form-group">
            <label for="price">Цена товара</label>
            <div class="input-group mb-3">
                <input type="number"  value="${price}" required class="form-control" id="price" name="price">
                <div class="input-group-append">
                    <span class="input-group-text">₽</span>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label for="city">Город</label>
            <select id="city" autofocus name="city" class="form-control">
                <option selected value="${city}">${city}</option>
                <option value="Москва">Москва</option>
                <option value="Красноярск">Красноярск</option>
                <option value="Казань">Казань</option>
                <option value="Санкт-Петербург">Санкт-Петербург</option>
                <option value="Краснодар">Краснодар</option>
                <option value="Ростов на Дону">Ростов на Дону</option>
            </select>
        </div>
        <div class="form-group">
            <label for="productName">Описание товара</label>
            <input type="text" value="${description}" required class="form-control"
                   id="productName" name="description">
        </div>
        <hr>
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

            <button type="submit" style="width: 100%" class="btn btn-dark">Редактировать</button>
        </div>
    </form>
</@t.template>