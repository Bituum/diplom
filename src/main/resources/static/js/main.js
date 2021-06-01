let addRow = function () {
    let listName = [[${productsEntity}]]; //list name in Catalog.class
    let fieldsNames = ['id','productsEntity','counterOrder']; //field names from Movie.class
    let rowIndex = document.querySelectorAll('.item').length; //we can add mock class to each movie-row
    let selectList = document.createElement("select");



    let row = document.createElement('div');
    row.classList.add('row', 'item');

    fieldsNames.forEach((fieldName) => {
        let col = document.createElement('div');
        col.classList.add('col', 'form-group');
        if (fieldName === 'id') {
            col.classList.add('d-none'); //field with id - hidden
        }
        if (fieldName === 'productsEntity'){
            selectList.setAttribute('th:field', '*{'+ listName +'}.id');
            for(var key in listName){
                let option = document.createElement("option");
                option.value = listName.get(key);
                option.text = listName.get(key);
                option.setAttribute('th:field', listName + '['+ rowIndex + '].id');
                option.setAttribute('th:each', 'i : ${'+ listName +'}' );
                option.setAttribute('th:value', 'i.'+i);
                option.setAttribute('th:text', '${i.productDescription');
                selectList.appendChild(option);
            }
            col.appendChild(selectList);
            row.appendChild(col);
        }
        if (fieldName === 'counterOrder'){
            let input = document.createElement('input');

            input.type = 'text';
            input.classList.add('form-control');
            input.id = listName + rowIndex + '.' + fieldName;
            input.setAttribute('name', listName + '[' + rowIndex + '].' + fieldName);
            col.appendChild(input);
            row.appendChild(col);
        }
    });

    document.getElementById('dishList').appendChild(row);
};