$(
    ()=>{
        if (counter != 0) {
            for (let index = 1; index <= counter; index++) {
                let newTr = $("<tr>");
                trId = newTr.attr("id")= "tr-" + counter;
                $("tbody").append(newTr);
            }
        }
        
    }
);