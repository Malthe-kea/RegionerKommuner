import {fetchAnyUrl} from "./modulejson.js";

console.log("er i kommunetable")

const urlKommune = "http://localhost:8080/kommuner/kommuner"
const tblKommuner = document.getElementById("tblKommuner")
const kommuneMap = new Map()

actionGetKommuner();

function createTable(kommune) {
    let cellCount = 0
    let rowCount = tblKommuner.rows.length
    let row = tblKommuner.insertRow(rowCount)
    console.log(kommune)

    let cell = row.insertCell(cellCount++)
    cell.innerHTML = kommune.kode
    cell.style.width = "25%"

    cell = row.insertCell(cellCount++)
    cell.innerHTML = kommune.navn
    cell.style.width = "25%"

    cell = row.insertCell(cellCount++)
    cell.innerHTML = kommune.href
    cell.style.width = "50%"

    cell = row.insertCell(cellCount++);
    let img = document.createElement("img");
    img.setAttribute("src", kommune.hrefPhoto);
    img.setAttribute("alt", "intet billede");
    img.setAttribute("width", 150);
    img.setAttribute("height", 150);
    cell.appendChild(img);

    cell = row.insertCell(cellCount++)
    cell.innerHTML = kommune.region.kode

    cell = row.insertCell(cellCount++)
    const dropdown = document.createElement('select');
    cell.appendChild(fillDropDown(dropdown));
    dropdown.value = kommune.region.kode;
    dropdown.addEventListener('change', (event) => {
        const selectedValue = event.target.value; // dette er k.region.kode
        console.log("Valgt værdi:", selectedValue);
        sendValueToDatabase(selectedValue);
    });


    cell = row.insertCell(cellCount++);
    const pbDelete = document.createElement("button");
    pbDelete.innerHTML = "Delete";
    pbDelete.className = "btn1";
    cell.appendChild(pbDelete);

    row.id = kommune.kode;

    pbDelete.onclick = async function () {
        console.log("skal slette kommune med kode=" + kommune.kode);

        try {
            const response = await fetch(`http://localhost:8080/kommuner/deletekommune/${kommune.kode}`, {
                method: "DELETE"
            });

            if (!response.ok) {
                throw new Error("Kunne ikke slette kommunen i DB (status " + response.status + ")");
            }

            document.getElementById(kommune.kode).remove();
            alert("Kommune " + kommune.navn + " slettet");
            console.log("Kommune slettet fra DB og tabel");
        } catch (err) {
            console.error(err);
            alert("Der skete en fejl ved sletning af kommune " + kommune.kode);
        }
    };

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

function fillDropDown(dropdown) {
    console.log("filling dropdown in fillDropDown()");

    const addedRegions = new Set();

    kommuner.forEach(k => {
        if (!addedRegions.has(k.region.navn)) {
            const el = document.createElement("option");
            el.textContent = k.region.navn;
            el.value = k.region.kode;
            dropdown.appendChild(el);
            addedRegions.add(k.region.navn);
        }

        // Opdater kommuneMap uanset om option blev tilføjet
        kommuneMap.set(k.kode, k.navn);
    });

    return dropdown;
}

function sendValueToDatabase(kode) {
    fetch('http://localhost:8080/kommuner/updateKommune', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ regionKode: kode })
    })
        .then(response => response.json())
        .then(data => {
            console.log("Database opdateret:", data);
        })
        .catch(error => {
            console.error("Fejl ved opdatering:", error);
        });
}


