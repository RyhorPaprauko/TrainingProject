function getAuthors() {

    var authorSelect = document.getElementById('author-select');
    cleanElement(authorSelect);

    fetch('/api/author')
        .then(response => {
            return response.json();
        })
        .then(authors => {
            for (var i = 0; i < authors.length; i++) {
                var option = document.createElement('option');
                option.value = authors[i].id;
                option.textContent = authors[i].surname;
                authorSelect.append(option);
            }
        })
}

function addAuthor() {

    let author = {
        name: document.getElementById('author-name').value,
        surname: document.getElementById('author-surname').value,
        bio: document.getElementById('author-bio').value
    };

    let options = {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'PUT',
        body: JSON.stringify(author)
    };

    fetch('/api/author', options)
        .then(getAuthors)
}

function cleanElement(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild)
    }
}