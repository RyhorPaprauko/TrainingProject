function getCatalog(page) {

    var catalogRow = document.getElementById('catalog');
    cleanElement(catalogRow);

    let catalogFilterDto = {
        name: document.getElementById('book-name').value,
        genre: document.getElementById('genre').value,
        price: document.getElementById('price').value,
        limit: document.getElementById('limit').value,
        page: page != undefined ? page : 1
    };

    let options = {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(catalogFilterDto)
    };

    fetch('/api/book', options)
        .then(response => {
            return response.json();
        })
        .then(catalogDto => {
            var books = catalogDto.books;
            for (var i = 0; i < books.length; i++) {
                var divCol = document.createElement('div');
                var divItem = document.createElement('div');
                var img = document.createElement('img');
                var title = document.createElement('h3');
                var aTitle = document.createElement('a');
                var price = document.createElement('h6');
                var spanPrice = document.createElement('span');
                var aPrice = document.createElement('a');

                divCol.className = 'col-md-3';
                divItem.className = 'item';
                spanPrice.className = 'price';
                img.src = books[i].image;
                aTitle.href = 'book/' + books[i].id;
                aTitle.textContent = books[i].name;
                spanPrice.textContent = '$' + books[i].price;
                aPrice.href = 'book/' + books[i].id;
                aPrice.textContent = 'Buy Now';

                title.append(aTitle);
                price.append(spanPrice, ' / ', aPrice);
                divItem.append(img, title, price);
                divCol.append(divItem);

                catalogRow.append(divCol);
            }
            createPages(catalogDto.totalCount,
                catalogFilterDto.limit,
                catalogFilterDto.page);
        })
}

function createPages(itemCount, limit, actualPage) {
    var pageLine = document.getElementById('page-line');
    cleanElement(pageLine);

    var pageCount = Math.ceil(itemCount / limit);
    if (pageCount > 1) {
        for (var i = 1; i <= pageCount; i++) {
            var button = document.createElement('button');
            button.value = i;
            button.innerText = i;
            button.onclick = function () {
                getCatalog(this.value)
            };

            i == actualPage ?
                button.className = 'actual-page' :
                button.className = 'page-number';

            pageLine.append(button);
        }
    }
}

function cleanElement(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild)
    }
}
