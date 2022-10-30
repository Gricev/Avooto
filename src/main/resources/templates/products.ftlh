<#import "blocks/template.ftlh" as t>
<@t.template user>
<h1 style="text-align: center">Поиск товаров</h1><hr>
<form method="get" action="/">
    <div class="form-row">
        <div class="form-group col-md-2">
            <label for="searchCity">Город</label>
            <select id="searchCity"  required name="city" class="form-control">
                <option value="">Город не выбран</option>
                <option value="Москва">Москва</option>
                <option value="Красноярск">Красноярск</option>
                <option value="Казань">Казань</option>
                <option value="Краснодар">Краснодар</option>
                <option value="Санкт-Петербург">Санкт-Петербург</option>
                <option value="Ростов на Дону">Ростов на Дону</option>
            </select>
        </div>
        <div class="form-group col-md-2">
            <label for="searchCategory">Категория товара</label>
            <select id="searchCategory" required name="category" class="form-control">
                <option value="">Выберете подходящую категорию</option>
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
        <div class="form-group col-md-2">
            <label for="searchPrice">Цена, руб.</label>
            <input type="number" name="minPrice" placeholder="от" class="form-control"
                   id="searchMinPrice">
        </div>

        <div class="form-group col-md-2">
            <label for="searchPrice">-</label>
            <input type="number" name="maxPrice" placeholder="до" class="form-control"
                   id="searchMaxPrice">
        </div>


        <div class="form-group col-md-4">
            <label for="searchWord">Поиск по объявлениям</label>

            <input type="text" name="title" <#if searchWord??>value="${searchWord}"</#if> class="form-control"
                   id="searchWord">
        </div>
        <button style="width: 100%" type="submit" class="btn btn-dark">Поиск</button>
    </div>
</form>
<hr>
<div class="card-columns">
    <#list products as product>
        <a href="/product/${product.id}/${product.user.id}" class="product-link">
            <div class="card text-white bg-dark my-3 product">

                <img src="/images/${product.previewImageId}"
                     class="card-img-top">
                <div class="card-body">
                    <h5 class="card-title">${product.title}</h5>
                    <p class="card-text">${product.price} ₽</p>
                </div>
                <div class="card-footer">
                    ${product.category}
                </div>
                <div class="card-footer">
                    ${product.city}
                </div>
            </div>
        </a>
    <#else>
        <h1 style="text-align: center">Не найдено</h1>
    </#list>
</div>
</@t.template>