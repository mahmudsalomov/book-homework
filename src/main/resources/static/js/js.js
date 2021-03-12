function getBooks() {

    axios.get("/api/books")
        .then(function (response) {

            // console.log(response.data)
            let books=response.data.object
            // console.log(books);

            let out="<tr>\n" +
                "        <th>id</th>\n" +
                "        <th>name</th>\n" +
                "        <th>author</th>\n" +
                "        <th>action</th>\n" +
                "    </tr>";

            for (let i = 0; i <books.length ; i++) {
                out+="<tr>\n" +
                    "        <td>"+books[i].id+"</td>\n" +
                    "        <td>"+books[i].name+"</td>\n" +
                    "        <td>"+books[i].author+"</td>\n" +
                    "        <td>\n" +
                    "            <button class='btn btn-success' onclick='getEditPage("+books[i].id+")'>" +
                    // "<a href=\"/edit/"+books[i].id+"\">" +
                    "Edit" +
                    // "</a></button>\n" +
                    "            <button class='btn btn-danger ml-1' value='"+books[i].id+"' onclick='del(this.value)'>Delete</button>\n" +
                    "        </td>\n" +
                    "    </tr>"
            }
            document.getElementById("table").innerHTML=out;

        })
}


function addBook() {

    let book={
        name : document.getElementById("name").value,
        author : document.getElementById("author").value
    }

    axios.post("/api/add",book)
        .then(function (response) {
            console.log(response.data);
            window.location.href="/";
        })
        .catch(function (error) {
            alert("Sizni qo'shish uchun huquqingiz yo'q!")
        })

}

function del(id) {

    axios.delete("/api/delete/"+id)
        .then(function (response) {


                console.log(response);
                location.reload();


        })
        .catch(function (error) {
            alert("Sizni o'chirish uchun huquqingiz yo'q")
        })

}


function edit() {
    let book={
        id : document.getElementById("id").value,
        name : document.getElementById("name").value,
        author : document.getElementById("author").value
    }

    axios.put("/api/edit",book)
        .then(function (response) {
            console.log(response.data);
            window.location.href="/";
        })
        .catch(function (error) {
            alert("Sizni Yangilash uchun huquqingiz yo'q!")
        })
}


function getAddPage() {
    axios.get("/add")
        .then(function (response) {
            location.href="/add"
        })
        .catch(function (error) {
            alert("Sizni qo'shish uchun huquqingiz yo'q!")
        })
}

function getEditPage(id) {
    axios.get("/edit/"+id)
        .then(function (response) {
            location.href="/edit/"+id;
        })
        .catch(function (error) {
            alert("Sizni yangilash uchun huquqingiz yo'q!")
        })
}