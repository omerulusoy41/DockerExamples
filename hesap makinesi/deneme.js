let a,b
let c=document.getElementById("text-Sonuc")
document.getElementById("btn-topla").onclick=function(){
    degerleriAl()
    c.style.display="inline"
    c.textContent=a+b
}
document.getElementById("btn-cikar").onclick=function(){
    degerleriAl()
    c.style.display="inline"
    c.textContent=a-b
}
document.getElementById("btn-carp").onclick=function(){
    degerleriAl()
    c.style.display="inline"
    c.textContent=a*b
}
document.getElementById("btn-bol").onclick=function(){
    degerleriAl()
    c.style.display="inline"
    c.textContent=a/b
}
document.getElementById("btn-usAl").onclick=function(){
    degerleriAl()
    c.style.display="inline"
    c.textContent=a**b //Math.pow(a,b)
}
document.getElementById("btn-kokAl").onclick=function(){
    degerleriAl()
    c.style.display="inline"
    c.textContent=Math.pow(a,1.0/b)
}
function degerleriAl(){
    a=Number(document.getElementById("text-A").value)
    b=Number(document.getElementById("text-B").value)
}