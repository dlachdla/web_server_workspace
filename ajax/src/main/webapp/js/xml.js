
document.querySelector("#btn1").onclick = () => {
    console.log(12);
    $.ajax({
        url: `${contextPath}/xml/sample.xml`,
        method : "get",
        dataType : "xml",
        success(xmlDoc){
            const tbody = document.querySelector("#books tbody");
            tbody.innerHTML = ''; // 초기화

            // xml문서를 응답받아 parsing, javascript객체(document)로 반환
            console.log(xmlDoc); // xml문서를 파싱해서 document객체 반환

            const root = xmlDoc.querySelector(":root");
            console.dir(root); // <books></books> // xml문서의 최상위 요소
            const value = root.getAttribute("myattr"); // 사용자속성
            console.log(value);

            // const books = root.chidren;
            const books = xmlDoc.querySelectorAll("book");
            books.forEach((book) => {
                console.log(book);
                const [subject, title, author] = book.children;
                console.log(subject.textContent, title.textContent, author.textContent);
                tbody.innerHTML += `
                <tr>
                    <td>${subject.textContent}</td>
                    <td>${title.textContent}</td>
                    <td>${author.textContent}</td>
                </tr>     
               `;
            });

        }
    });
};