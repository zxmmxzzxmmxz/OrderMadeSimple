ContainerFlow = class {
    constructor(colsEachRow, idPrefix, container, size){
        this.colsEachRow = colsEachRow;
        this.colIndex = 0;
        this.rowIndex = 0;
        this.idPrefix = idPrefix;
        this.container = container;
        this.size = size;
    }

    insert(element){
        if(this.colIndex === 0){
            this.currentRow = document.createElement("div");
            this.currentRow.classList.add("row");
            this.currentRow.setAttribute("id",this.idPrefix+"-row-"+this.rowIndex);
            this.container.append(this.currentRow);
            this.rowIndex += 1;
        }
        let col = document.createElement("div");
        //col.classList.add("col-md-"+this.size);
        col.classList.add("col");
        col.classList.add("col-padding");
        col.setAttribute("id",this.idPrefix+"-col-"+this.colIndex);
        col.append(element);
        this.currentRow.append(col);
        this.colIndex += 1;
        if(this.colIndex === this.colsEachRow){
            this.colIndex = 0;
        }
    }

    finish(){
        while(this.colIndex !== 0){
            this.insert("");
        }
    }
};