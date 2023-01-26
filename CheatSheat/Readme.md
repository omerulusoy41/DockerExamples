Docker farkli 2 uygulamayi birbirinden dependency bazinda izole bir ortamda(container) ayni isletim sistemi  
uzerinde calistirma olanagi saglayan teknolojidir.  
2. olarak yapitigin console uygulamasini global olarak servis etmeyi saglar.  
komutlar:  
```docker info =>Docker bilgilerini verir. ```  
```docker ps => Sistemde calisan conteinerlari gosterir.```  
```docker ps -a => Sistemde calisan ve bitmis olan tum conteinerlari gosterir.```  
```docker pull <imajName> =>Docker.hub da olan imaji locale ceker.``` 
```docker container rename 39 ubuntu => 39ID ile baslayan containerin adini ubuntu yap.```  
```docker run <imajName> => localdeki imaji  calistirir.Lokalde yoksa pull edip calistirir.```  
```docker run --name <istedigin isim> <imajName> => Calisacak conteynira isim verir.```    
```docker rm <conatinerName> => Containeri siler.```  
```docekr rmi <imajid> = imajı siler.```  
```docker container prune =>Sistemdeki calismayan tum containerlari siler.```  
```docker logs  <conatinerName> yada <conatinerId> => Cotnaienr kayitlarini gosterir.```  
```docker run -d <conatinerName> => imaji arka planda calistirir.```  
 
```docker rm -f <Id> = CAlısan container ı sıler ```    
```docker stop <containerId> => Calisan containeri durdurur.```  
```docker start <containerId> =>Durmus konteyneri calistirir.Arka planda calistirir.```  
```docker run ubuntu <echo 'hello'> =>Bu sekilde container calistirmak istenilirse container uzerindeki sadece komut calistirilir ve container kapanir.```  
```docker exec ubuntu <echo 'hello'> => Bu sekilde calisan bir containerin komutlari calistirilir ```  
```docker exec -it ubuntu =>Arka planda Calisan bir conteynira baglanmak icin kullanilir.```  
```docker inspect image||container ID => kontayner||image bilgisinu verir.```  
```docker images =>imajlari gosterir.```  

- Container icerisinde sadece bir uygulama calisir.Temel mantik bu
- Container imajlardan olusur.Butun dependenciler imaj olusurken tamamlanir.
- Containerlar silinip tekrar olusturulabilri.N aninda hata aldin serveri yenilemek yerine bastan yapmak daha kolay ve sisteme uyanda budur.
- Imajlar yaratildikdan sonra degisiklik yapilmaz.Degisiklik icin yeni imaj olusturulmalidir.
- Containerla uzeerinde yapilan degisiklerden imajlar etkilenmez(writble layer a yazilir.).Imajlar degisitirilemez(Read-only Layer).

```docker logs --tail x <containerID> => Son x logu gosterir.```  
```docker logs -f <ContaienerID> =>Sistemde calisan containeri secmeyi saglayip loglari gosterir,canli olarak yapismayi saglar.```   
```docker top <containerID> => containerdeki processleri gosterir.```  
```docker stats <containerID> => container kullandigi CPU,Ram gibi fiziksel alanlari gosteriri.```    
```docker container run --memory=1gb --cpus="3" => fiziksel limitleri ayarlar.```  
```docker run --env key=value <containerId> => Container olusurken icerisine gonderilecek degiskenleri ornegin dbserver baglantisini gonderir.```  
- Containerlara girmek pek dogru degildir.   
```docker run --env-file env.list <containerId> => Tek bir dosya icerisinde birden cok env olusturulup gonderilir.```  
- Bazi durumlarda containerlar uzerindeki veriler tutulmak istenirse (Yeni bir container olusurutulunca sifirdan olusur cunku containerdaki 
degisiklikten imaj etkilenmez.) volume kullanilir.Container silindikten sonra veri kaybi olmaz(read-only(imaj) yazar).  
```docker volume ls =>volumleri listeler.```  
```docker volume create first_volume = Yeni voluem olsturur.```  
```docker run -v first_volume:/app -it ubuntu  => first_volume olusturulduktan sonra container ayaga kalkarken root klasoru altindaki app dosyasini artik veri tabani gibi kullabiliriz.Container silindikten sornra yeni bir container ayaga kaldirirsak ayni ve first_volume u herhangi bir file a baglarsak verilerin kaybolmadigini goururuz. ```  
```docker volume inspect first_volume => Bilgileri gosterir.```  
```docker volume rm first_volume => Volume siler```  
```docker run -v path/baglanacakklasor ubuntu => bind mount = Localdeki veriyi containere baglanamaya yarar.Pathde ki degisiklikler hem containeri hemde lokali degisitirir.```  

```docker network ls => Sistemdeki ag driverlarini gosterir.```  
```docker network inspect container => container ag bilgisini gosterir.```  
```docker network crate first_netwokr => default bridge network olusturur.```  
```docker run --network first_network containerId => containere networke baglar.```  
```docker network connect first_network containerId => containeri networke baglar```  

- Bridge = Ana bilgisyar ve container arasında bir köprü görevi gören dağıtıcıyla haberlşir. 2 container birbirleriyle ip adress ile haberleşebilir.
- none = Hiçbir şekilde newtork ü olmaz.  
- host = ana bilgisayar ile aynı network.  

```docker run -p distakiport[100]:contaynerportu[200] containerID => containeri localHost/100 den calistirabilirsin.```  

docker push hub.docker.com:(containercalismaportu)/<repoName>:<imajtag> !!Oncesinde taglaman gerekir (docker tag imageIDorImageName omerulusoy/hesapmakinesi:<tagname>)
var olan repoya gonderirken docker push omerulusoy/javascriptkodlari:mediumsitesi demem yetti.

docker.i/omerulusoy/ilkDepo:hesapMakinesi => registry(dockerhub) / kullanici / depo ismi / uygulamatagi(versiyonlama islevi gorur)

## Dockerfile dosya yapisi komutlar----
- FROM image:version => olusturulacak imajin hangi imajin(base-image)  uzerinde olusturulacagini soyler  
- RUN komutlar => shell uzerinden komut calistirmak icin kullanilir.  
- WOKRDIR path=> dosya dizinine gider.  
- COPY . path=>dizindeki veriyi pathe kopyalamaya yarar.  
- CMD ["",""] => imajin ilk calisma aninda calisaacak kodu belirtir.  

docker image build -t tag . =dızındekı dockerfile ı calıstır imaj haline getir.
docker image build -t tag -f path = pathdeki dockerfile ı calıstır imaj haline getir.

*Docker Compose birden fazla ve ayni hizmet icerside bulunan uygulamalarin tek bir dockerfile uzerinden olusturulmasini saglar.(Docker-compose.yml) 
*Docker temel olarak katmanlı bir mimari sergiler. Örnegin Dockerfile üzerinde komutlar indirmeler gercekleştirildiğinde değişmemiş kısmı tekrardan işleme tabii tutumaz.
Bu yuzden dockerfile az değisen kısımları en yukarı yazmak imaj olusturma acısından daha hızlı olucak.

CMD vs Entrypoint
runtime da cmd değiştirilir,entrypoint değiştirilemez

CMD["",""] vs CMD . .(shell form)
shell form da sistemin env değişkenlerine erişilebilir.

## multiStage

- FROM mcr.microsoft.com/java/jdk:8-zulu-alpine as derle
- COPY /source /usr/src/uygulama
- WORKDIR /usr/src/uygulama
- RUN javac uygulama.java

- FROM mcr.microsoft.com/java/jre:8-zulu-alpine
- Workdir /uygulama 
- Copy --from=derle /usr/src/uygulama .
- CMD java uygulama  

İlk katmandaki container en son katmandaki container için çalısır.Totalde en sondaki container imaj halina gelir.

-ARG => dockerfile da kullanılcak bir değişkendir <=> ENV den farkı 
docker run --build-arg X = 5 alpine 
 








