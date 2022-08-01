import java.util.Scanner;

public class Main {
	public static Scanner scan=new Scanner(System.in);
	public char tahta[][]=new char[8][8];
	public  int indexX;
	public  int y;
	public static void main(String[] args) {
		Main main =new Main();
		main.tahtaTemizle();
		while(true) {
			System.out.println("\nHangi satranc tasinin hareketini gormek istiyorsun:\n");
			System.out.println("1:piyon");
			System.out.println("2:kale");
			System.out.println("3:at");
			System.out.println("4:fil");
			System.out.println("5:Vezir");
			System.out.println("6:Sah");
			System.out.println("7:Cikis");
			System.out.print("\nLutfen bir secim yapiniz:");
			int secim=scan.nextInt();
			if(secim==1) {
				main.konumBilgisiGir('P');
				main.piyonAyarla();
			}else if(secim==2) {
				main.konumBilgisiGir('K');
				main.kaleAyarla();
			}else if(secim==3) {
				main.konumBilgisiGir('A');
				main.atAyarla();
			}else if(secim==4) {
				main.konumBilgisiGir('F');
				main.filAyarla();
			}else if(secim==5) {
				main.konumBilgisiGir('V');
				main.vezirAyarla();
			}else if(secim==6) {
				main.konumBilgisiGir('S');
				main.sahAyarla();
			}else if(secim==7) {
				break;
			}else {
				System.out.println("Hatali giris!!");
				continue;
			}
			System.out.println("\nGidebilecegi Yerler:");
			main.tahtaGoster();
			try {
				System.out.println("\n10 saniye bekle");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			main.tahtaTemizle();
			System.out.println("##########################################\n");
		}
	}
	public  void tahtaGoster() {
		System.out.println();
		System.out.println("  A B C D E F G H");
		for(int i=0;i<8;i++) {
			System.out.print(i+" ");
			for(int j=0;j<8;j++) {
			    System.out.print(tahta[i][j]+" "); 	
			}
			System.out.println();
		 }
	}
	public void tahtaTemizle() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				tahta[i][j]='-';
			}
		}
	}
	public  void konumBilgisiGir(char tas) {
		tahtaGoster();
		char[] karakterler={'A','B','C','D','E','F','G','H'};
		char x;
		System.out.print("\nKonumunu giriniz (x(A,B),y(0,1))(ORN:D 5)=");
		x=scan.next().charAt(0);
		y=scan.nextInt();
		indexX=0;
		for(int i=0;i<karakterler.length;i++) {
			if(karakterler[i]==x) {
				indexX=i;
				break;
			}
		}
		tahta[y][indexX]=tas;
		
		
	}
	public  void piyonAyarla() {
		if(y==7) {
			tahta[y-1][indexX]='*';
		}else if(y==0) {
			tahta[y+1][indexX]='*';
		}else {
			tahta[y-1][indexX]='*';
			tahta[y+1][indexX]='*';
		}
	}
	public void kaleAyarla() {
		for(int i=0;i<8;i++) {
			if(tahta[i][indexX]=='-') {
				tahta[i][indexX]='*';
			}
			if(tahta[y][i]=='-') {
				tahta[y][i]='*';
			}
		}
	}
	public void filAyarla() {
		int tmpXArttir=indexX;
		int tmpXAzalt=indexX;
		int tmpYArttir=y;
		int tmpYAzalt=y;
		for(int i=0;i<8;i++) {
			tmpYAzalt--;
			tmpXAzalt--;
			tmpYArttir++;
			tmpXArttir++;
			if(tmpXAzalt>=0 && tmpYAzalt>=0) {
				tahta[tmpYAzalt][tmpXAzalt]='*';
			}
			if(tmpXArttir<=7 && tmpYArttir<=7) {
				tahta[tmpYArttir][tmpXArttir]='*';
			}
			if(tmpXAzalt>=0 && tmpYArttir<=7) {
				tahta[tmpYArttir][tmpXAzalt]='*';
			}
			if(tmpXArttir<=7 && tmpYAzalt>=0) {
				tahta[tmpYAzalt][tmpXArttir]='*';
			}
		}
	}
	public void atAyarla() {
		if(indexX-2 >=0) {
			if(y==0) {
				tahta[y+1][indexX-2]='*';
			}else if(y==7) {
				tahta[y-1][indexX-2]='*';
			}else {
				tahta[y+1][indexX-2]='*';
				tahta[y-1][indexX-2]='*';
			}
		}
		if(indexX+2 <= 7) {
			if(y==0) {
				tahta[y+1][indexX+2]='*';
			}else if(y==7) {
				tahta[y-1][indexX+2]='*';
			}else {
				tahta[y+1][indexX+2]='*';
				tahta[y-1][indexX+2]='*';
			}
		}
		if(y+2 <= 7) {
			if(indexX==0) {
				tahta[y+2][indexX+1]='*';
			}else if(indexX==7) {
				tahta[y+2][indexX-1]='*';
			}else {
				tahta[y+2][indexX+1]='*';
				tahta[y+2][indexX-1]='*';
			}
		}
		if(y-2 >= 0) {
			if(indexX==0) {
				tahta[y-2][indexX+1]='*';
			}else if(indexX==7) {
				tahta[y-2][indexX-1]='*';
			}else {
				tahta[y-2][indexX+1]='*';
				tahta[y-2][indexX-1]='*';
			}
		}
	}
	public void vezirAyarla() {
		filAyarla();
		kaleAyarla();
	}
	public void sahAyarla() {
		piyonAyarla();
		sahSagSol();
		sahCapraz();
	}
	private void sahSagSol() {
		if(indexX==7) {
			tahta[y][indexX-1]='*';
		}else if(indexX==0) {
			tahta[y][indexX+1]='*';
		}else {
			tahta[y][indexX-1]='*';
			tahta[y][indexX+1]='*';
		}
	}
	private void sahCapraz() {
		int tmpXAzalt=indexX;
		int tmpXArttir=indexX;
		int tmpYArttir=y;
		int tmpYAzalt=y;
		tmpXArttir++;
		tmpXAzalt--;
		tmpYArttir++;
		tmpYAzalt--;
		if(tmpXAzalt>=0 && tmpYAzalt>=0) {
			tahta[tmpYAzalt][tmpXAzalt]='*';
		}
		if(tmpXArttir<=7 && tmpYArttir<=7) {
			tahta[tmpYArttir][tmpXArttir]='*';
		}
		if(tmpXAzalt>=0 && tmpYArttir<=7) {
			tahta[tmpYArttir][tmpXAzalt]='*';
		}
		if(tmpXArttir<=7 && tmpYAzalt>=0) {
			tahta[tmpYAzalt][tmpXArttir]='*';
		}
	}
}
