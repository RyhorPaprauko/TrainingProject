function getComments() {

    var commentsSection = document.getElementById('commentsSection');
    cleanElement(commentsSection);

    let options = {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: document.getElementById('book-id').value
    };

    fetch('/api/comment', options)
        .then(response => {
            return response.json();
        })
        .then(comments => {
            for (var i = 0; i < comments.length; i++) {
                var comDiv = document.createElement('div');
                var commentAuthor = document.createElement('b').textContent = comments[i].authorLogin;
                var comment = document.createElement('p').textContent = comments[i].text;

                var buttonDiv = document.createElement('div');
                buttonDiv.setAttribute('sec:authorize', 'isAuthenticated()');
                var deleteButton = document.createElement('button');
                buttonDiv.append(deleteButton);

                comDiv.append(commentAuthor, comment, buttonDiv);

                commentsSection.append(comDiv);
            }
        })
}

function addComment() {

    let comment = {
        name: document.getElementById('book-name').value,
        genre: document.getElementById('genre').value,
        price: document.getElementById('price').value,
        limit: document.getElementById('limit').value,
        page: page != undefined ? page : 1
    };
}

function cleanElement(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild)
    }
}