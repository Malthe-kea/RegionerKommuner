import {fetchAnyUrl} from "./modulejson.js";

console.log("er i kommunetable")

const urlKommune = "http://localhost:8080/kommuner/getkommuner"
const pbCreateKommuneTable = document.getElementById("pbGetKommuner")
const tblKommuner = document.getElementById("tblKommuner")

function createTable(kommune) {
    let cellCount = 0
    let rowCount = tblKommuner.rows.length
    let row = tblKommuner.insertRow(rowCount)
    console.log(kommune)
    //console.log(kommune)
    let cell = row.insertCell(cellCount++)
    cell.innerHTML = kommune.kode
    cell.style.width = "25%"

    cell = row.insertCell(cellCount++)
    cell.innerHTML = kommune.navn
    cell.style.width = "25%"

    cell = row.insertCell(cellCount++)
    cell.innerHTML = kommune.href
    cell.style.width = "50%"

    cell = row.insertCell(cellCount++)
    cell.innerHTML = kommune.region.kode

    cell = row.insertCell(cellCount++)
    cell.innerHTML = kommune.region.navn

    cell = row.insertCell(cellCount++)
    const pbDelete = document.createElement("input");
    pbDelete.type = "button";
    pbDelete.setAttribute("value", "Slet kommune");
    cell.appendChild(pbDelete);

    pbDelete.className = "btn1"

    row.id = kommune.navn
    pbDelete.onclick = function() {
        document.getElementById(kommune.navn).remove();
        deleteKommune(kommune);
    }

}

let kommuner = []
async function fetchKommuner() {
    kommuner = await fetchAnyUrl(urlKommune)
    if (kommuner) {
        kommuner.forEach(createTable)
    } else {
        alert("Fejl ved kald til backend url=" + urlKommune + " vil du vide mere så kig i Console")
    }
}


function actionGetKommuner() {
    fetchKommuner();
}

pbCreateKommuneTable.addEventListener('click', actionGetKommuner)
