let karakterler=[1,2,3,4,5,6,7,8,9,0]
let sifre=""
//let btn=document.getElementById("btn-sifre")
//btn.addEventListener("click",sifreOlustur)

function sifreOlustur(){
    sifre=""
    for(let i=0;i<5;i++){
        let sayi=Math.floor(Math.random()*karakterler.length)
        sifre += karakterler[sayi]
    }

    let sonuc=document.getElementById("sonuc")
    sonuc.innerHTML="<b>"+sifre+"</b>"
}

