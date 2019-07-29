function addBook() {

    let buyDto = {
        bookId: document.getElementById('book-id').value,
        username: document.getElementById('username').value
    };

    let options = {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'PUT',
        body: JSON.stringify(buyDto)
    };

    fetch('/api/booking/add', options);
}

function removeBook(booId) {

    let buyDto = {
        bookId: booId,
        username: document.getElementById('username').value
    };

    let options = {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'DELETE',
        body: JSON.stringify(buyDto)
    };

    fetch('/api/booking/remove', options)
        .then(getBooking);
}

function getBooking() {

    var section = document.getElementById('order-section');
    cleanElement(section);

    let options = {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'POST',
        body: JSON.stringify(document.getElementById('username').value)
    };

    fetch('/api/booking', options)
        .then(response => {
            return response.json();
        })
        .then(booking => {
            var books = booking.books;
            if (books.length == null || books.length == 0) {
                var alert = document.createElement('h1');
                alert.textContent = 'Your order is empty yet';
                section.append(alert);
            } else {
                for (var i = 0; i < books.length; i++) {
                    var div = document.createElement('div');
                    var bookName = document.createElement('h1');
                    bookName.textContent = books[i].name;
                    var bookPrice = document.createElement('h2');
                    bookPrice.textContent = books[i].price;
                    var removeButton = document.createElement('button');
                    removeButton.value = books[i].id;
                    removeButton.textContent = '-';
                    removeButton.onclick = function () {
                        removeBook(this.value)
                    };

                    div.append(bookName, bookPrice, removeButton);
                    section.append(div);
                }
                var totalPrice = document.createElement('h1');
                totalPrice.textContent = booking.totalPrice;
                var completeButton = document.createElement('button');
                completeButton.textContent = 'Order';
                completeButton.onclick = function () {
                    completeOrder()
                };

                section.append(totalPrice, completeButton);
            }
        })
}

function completeOrder() {

    let options = {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'PUT',
        body: JSON.stringify(document.getElementById('username').value)
    };

    fetch('/api/booking', options)
        .then(getBooking)

}

function processedBookings() {

    var section = document.getElementById('order-section');
    cleanElement(section);

    fetch('/api/booking/admin')
        .then(response => {
            return response.json();
        })
        .then(bookings => {
                if (bookings.length == null || bookings.length == 0) {
                    var alert = document.createElement('h1');
                    alert.textContent = 'There is no completed orders yet';
                    section.append(alert);
                } else {
                    for (var i = 0; i < bookings.length; i++) {
                        var div = document.createElement('div');
                        var username = document.createElement('h1');
                        username.textContent = bookings[i].username;
                        var list = document.createElement('ul');

                        var books = bookings[i].books;
                        for (var j = 0; j < books.length; j++) {
                            var li = document.createElement('li');
                            li.textContent = books[j].name;
                            list.append(li);
                        }
                        var totalPrice = document.createElement('h1');
                        totalPrice.textContent = bookings[i].totalPrice;

                        div.append(username, list, totalPrice);
                        section.append(div);
                    }
                }
            }
        )
}

function cleanElement(element) {
    while (element.firstChild) {
        element.removeChild(element.firstChild)
    }
}