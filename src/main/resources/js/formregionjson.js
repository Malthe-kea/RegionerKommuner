console.log("Jeg er i formregionjson.js")

const urlPostRegion = "http://localhost:8080/tool/region"

async function postDataAsJson(url, obj){
    const objectAsJsonString = JSON.stringify(obj);
    console.log(objectAsJsonString);
    const fetchOptions = {
        method: "POST",
        headers: {
            "content-type": "application/json"
        },
        body: objectAsJsonString
    };

    const response = await fetch(url,fetchOptions);

    if (!response.ok) {
        const errorMessage = await response.text();
        throw new Error(errorMessage);
    }
    return response.json();
}

async function postRegion(region) {
    try {
        const nogetjson = await postDataAsJson(urlPostRegion, region);
        console.log("noget json");
        console.log(nogetjson);
    } catch (e) {
        console.error(e);
    }
}

function createRegion () {
    const region = {}
    region.kode = "0000"
    region.navn = "00000000"
    region.href = "0000000000000000"
    return region
}

const reg1 = createRegion()
console.log(reg1)
console.log("Jeg er tilbage fra DB")


