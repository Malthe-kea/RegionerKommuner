const arr = ["hej","dyt","abc","123","xyz"];

const srt = arr.sort((a,b) => { if (a>b) {return 1} else { return -1 }})

console.log(srt);