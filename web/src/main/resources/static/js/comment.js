function getComments() {

    var commentsSection = document.getElementById('commentsSection');
    var username = document.getElementById('username').value;
    var isAdmin = document.getElementById('isAdmin').value;
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
                comDiv.append(commentAuthor, comment);
                if (username == comments[i].authorLogin || isAdmin == 'true') {
                    var deleteButton = document.createElement('button');
                    deleteButton.textContent = 'Delete';
                    deleteButton.value = comments[i].id;
                    deleteButton.onclick = function () {
                        deleteComment(this.value)
                    };
                    comDiv.append(deleteButton);
                }
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

function deleteComment(commentId) {
    let options = {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'DELETE',
        body: commentId
    };
    fetch('/api/comment', options)
        .then(getComments)
}


function cleanElement(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild)
    }
}