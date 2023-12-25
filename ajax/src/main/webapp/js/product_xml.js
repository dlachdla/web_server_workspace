document.querySelector("#btn-product").onclick = () => {
    $.ajax( {
        url: `${contextPath}/xml/sample-product.xml`,
        method: "get",
        dataType: "xml",
        success(xmlDoc) {
            const tbody = document.querySelector("#products tbody");
            tbody.innerHTML = '';
            console.log(xmlDoc);

            const root = xmlDoc.querySelector(":root");
            console.dir(root);
            const value = root.getAttribute("myattr");
            console.log(value);

            const products = xmlDoc.querySelectorAll("Product");
            products.forEach((Product) => {
                console.log(Product);
                const[Product_ID, SKU, Name, Product_URL, Price, Retail_Price, Thumbnail_URL,
                    Search_Keywords, Description, Category, Category_ID, Brand, Child_SKU,
                    Child_Price, Color, Color_Family, Color_Swatches, Size, Shoe_Size,
                    Pants_Size, Occassion, Season, Badges, Rating_Avg, Rating_Count,
                    Inventory_Coun, Date_Created] = Product.children;

                tbody.innerHTML +=`
                <tr>
                    <td>${Product_ID.textContent}</td>
                    <td>${SKU.textContent}</td>
                    <td>${Name.textContent}</td>
                    <td>${Product_URL.textContent}</td>
                    <td>${Price.textContent}</td>
                    <td>${Retail_Price.textContent}</td>
                    <td>${Thumbnail_URL.textContent}</td>
                    <td>${Search_Keywords.textContent}</td>
                    <td>${Description.textContent}</td>
                    <td>${Category.textContent}</td>
                    <td>${Category_ID.textContent}</td>
                    <td>${Brand.textContent}</td>
                    <td>${Child_SKU.textContent}</td>
                    <td>${Child_Price.textContent}</td>
                    <td>${Color.textContent}</td>
                    <td>${Color_Family.textContent}</td>
                    <td>${Color_Swatches.textContent}</td>
                    <td>${Size.textContent}</td>
                    <td>${Shoe_Size.textContent}</td>
                    <td>${Pants_Size.textContent}</td>
                    <td>${Occassion.textContent}</td>
                    <td>${Season.textContent}</td>
                    <td>${Badges.textContent}</td>
                    <td>${Rating_Avg.textContent}</td>
                    <td>${Rating_Count.textContent}</td>
                    <td>${Inventory_Coun.textContent}</td>
                    <td>${Date_Created.textContent}</td>
                </tr>
                `;

            });
        }
    });
}