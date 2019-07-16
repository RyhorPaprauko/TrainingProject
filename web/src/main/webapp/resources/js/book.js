function getBooks() {

    var catalogRow = document.getElementById('book-container');
    while (catalogRow.firstChild) {
        catalogRow.removeChild(catalogRow.firstChild)
    }

    var limit = document.getElementById('limit').value;

    let filterDto = {
        name: document.getElementById('book-name').value,
        genre: document.getElementById('genre').value,
        price: document.getElementById('price').value,
        limit: document.getElementById('limit').value,
        page: 1
    };

    let options = {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(filterDto)
    };
    
}