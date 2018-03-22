package gra;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameOfLife {
    //początkowe wymiary
    int w=10;
    int m=10;  
    private boolean zycie[][];//aktualne życie
    private boolean zycieStare[][]; //poprzednie życie

    GameOfLife(int w, int m){
        this.w=w;
        this.m=m;
        zycie = new boolean[w][m]; //konstruktor życia
    }
  
    public void wypelnij(){  // TRYB 1 - wypełnienie maciezy randomowo
        Random r = new Random();
        for(int i=0;i<(2*w);i++){
            zycie[r.nextInt(w)][r.nextInt(m)]=true;
        }
        zycieStare=new boolean[w][m];
    }
    public void wypelnij2(){ //TRYB DEMO - prostokąt
        zycieStare=new boolean[w][m];
        zycie[5][5] = true;
        zycie[5][7] = true;
        zycie[5][9] = true;
        zycie[6][5] = true;
        zycie[6][9] = true;
        zycie[7][5] = true;
        zycie[7][9] = true;
        zycie[8][5] = true;
        zycie[8][9] = true;
        zycie[9][5] = true;
        zycie[9][7] = true;
        zycie[9][9] = true;
    }
   
     public void wypelnij3(){ //TRYB 3 - gwiazdy
        zycieStare=new boolean[w][m];
        zycie[1][0] = true;zycie[1][1] = true;zycie[1][2] = true;
        zycie[1][4] = true;zycie[1][5] = true;zycie[1][6] = true;
        zycie[1][8] = true;zycie[1][9] = true;zycie[1][10] = true;
        zycie[1][12] = true;zycie[1][13] = true;zycie[1][14] = true;
        zycie[5][0] = true;zycie[5][1] = true;zycie[5][2] = true;
        zycie[5][4] = true;zycie[5][5] = true;zycie[5][6] = true;
        zycie[5][8] = true;zycie[5][9] = true;zycie[5][10] = true;
        zycie[5][12] = true;zycie[5][13] = true;zycie[5][14] = true;
        zycie[9][0] = true;zycie[9][1] = true;zycie[9][2] = true;
        zycie[9][4] = true;zycie[9][5] = true;zycie[9][6] = true;
        zycie[9][8] = true;zycie[9][9] = true;zycie[9][10] = true;
        zycie[9][12] = true;zycie[9][13] = true;zycie[9][14] = true;
        zycie[13][0] = true;zycie[13][1] = true;zycie[13][2] = true;
        zycie[13][4] = true;zycie[13][5] = true;zycie[13][6] = true;
        zycie[13][8] = true;zycie[13][9] = true;zycie[13][10] = true;
        zycie[13][12] = true;zycie[13][13] = true;zycie[13][14] = true;

    }
    public void rysuj(){
   
        for(int i=0;i<w;i++){
            for(int j=0;j<m;j++){
                zycieStare[i][j]=zycie[i][j]; //kopiowanie aktualnego życia
            }
        }
        for(int i=0;i<w;i++){
            for(int j=0;j<m;j++){//wyswietlanie zycia
                if(zycie[i][j]==true)
                    System.out.print("O");
                else
                    System.out.print(" ");    
   
            }
        System.out.println();
        }
        for(int i=0;i<w;i++){
            for(int j=0;j<m;j++){
                this.sprawdz(i, j); //kolejne zycie
            }
        }
    }   
   
    private void sprawdz(int w, int k){ //sprawdzanie komórek
        
        if(zycie[w][k] == false){ //jesli martwa
            boolean tymczasowa[] = new boolean[8];
            Arrays.fill(tymczasowa, false);//standarowo false
            
            // sprawdzamy każdego sąsiada
            //(w-1,k+1)(w,k+1)(w+1,k+1)
            //(w-1,k)         (w+1,k)
            //(w-1,k-1)(w,k-1)(w+1,k-1)
            
            if((((w-1)>=0 && (k-1)>=0) && zycieStare[w-1][k-1]==true)){
                tymczasowa[0]=true;
            }
            if((w-1)>=0 && zycieStare[w-1][k]==true){
                tymczasowa[1]=true;
            }
            if((w-1)>=0 && (k+1)<m && zycieStare[w-1][k+1]==true){
                tymczasowa[2]=true;
            }
            if((k+1)<m && zycieStare[w][k+1]==true){
                tymczasowa[3]=true;
            }
            if((k+1)<m && (w+1)<m && zycieStare[w+1][k+1]==true){
                tymczasowa[4]=true;
            }
            if((w+1)<m && zycieStare[w+1][k]==true){
                tymczasowa[5]=true;
            }
            if((k-1)>=0 && (w+1)<m &&  zycieStare[w+1][k-1]==true){
                tymczasowa[6]=true;
            }
            if((k-1)>=0 && zycieStare[w][k-1]==true){
                tymczasowa[7]=true;
            }
           
            int iloscZywychSasiadow=0;
            for(int i=0;i<8;i++){
        
                if(tymczasowa[i]==true){
                    iloscZywychSasiadow=iloscZywychSasiadow+1; //ilość żywych sąsiadów komórki
                }
            }
            if(iloscZywychSasiadow==3){
                zycie[w][k]=true; //jesli jest 3 żywych sasiadów to ozywa
            }
        }

        if(zycie[w][k]== true){ //jesli zyje
            boolean tymczasowa[] = new boolean[8];
            Arrays.fill(tymczasowa, false); //standarowo false

            // sprawdzamy każdego sąsiada
            //(w-1,k+1)(w,k+1)(w+1,k+1)
            //(w-1,k)         (w+1,k)
            //(w-1,k-1)(w,k-1)(w+1,k-1)
            
            if((((w-1)>=0 && (k-1)>=0) && zycieStare[w-1][k-1]==true)){
                tymczasowa[0]=true;
            }
            if((w-1)>=0 && zycieStare[w-1][k]==true){
                tymczasowa[1]=true;
            }
            if((w-1)>=0 && (k+1)<m && zycieStare[w-1][k+1]==true){
                tymczasowa[2]=true;
            }
            if((k+1)<m && zycieStare[w][k+1]==true){
                tymczasowa[3]=true;
            }
            if((k+1)<m && (w+1)<m && zycieStare[w+1][k+1]==true){
                tymczasowa[4]=true;
            }
            if((w+1)<m && zycieStare[w+1][k]==true){
                tymczasowa[5]=true;
            }
            if((k-1)>=0 && (w+1)<m &&  zycieStare[w+1][k-1]==true){
                tymczasowa[6]=true;
            }
            if((k-1)>=0 && zycieStare[w][k-1]==true){
                tymczasowa[7]=true;
            }
            
            int iloscZywychSasiadow=0;
            for(int i=0;i<8;i++){
                if(tymczasowa[i]==true){
                iloscZywychSasiadow=iloscZywychSasiadow+1; //ilość żywych sąsiadów komórki
                }
            } 

            if(iloscZywychSasiadow==3 || iloscZywychSasiadow ==2){//jesli ma 3 lub 2 sasiadow to zyje dalej     
                zycie[w][k]=true;
            }
            else{//jesli nie to martwa
                zycie[w][k]=false;          
            }
        }
    }
    
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("===================");
        System.out.println("|   GAME OF LIFE  |");
        System.out.println("|  BY ADAM MAŁOTA |");
        System.out.println("===================");
        System.out.println();
        System.out.println("Aby wyświetlać kolejne generacje zycia wpisz 'e'");
        System.out.println("Aby wyjść wpisz cokolwiek");
        System.out.println();
        System.out.println("Wybierz tryb:\n 1.DEMO \n 2.Gwiazdy \n 3.Zmienny");
        
        int tryb = in.nextInt();
        if(tryb==1){
            GameOfLife gol = new GameOfLife(15,15);
            gol.wypelnij2();
            while(true){  
                gol.rysuj();
                String enterkey = in.next();
                if(!enterkey.equals("e")){
                    break;               
                }
            }
        }
        if(tryb==2){
            GameOfLife gol = new GameOfLife(15,15);
            gol.wypelnij3();
            while(true){  
                gol.rysuj();
                String enterkey = in.next();
                if(!enterkey.equals("e")){
                    break;               
                }
            }
        }
        if(tryb==3){
            System.out.println("Wpisz liczbę wierszy");
            int ln = in.nextInt();
            System.out.println("Wpisz liczbę kolumn");
            int lm = in.nextInt();
            GameOfLife gol = new GameOfLife(ln,lm);
            gol.wypelnij();
            while(true){  
                gol.rysuj();
                String enterkey = in.next();
                if(!enterkey.equals("e")){
                    break;               
                }
            }
        }
        else{
            System.out.println("Wpisz poprawny numer trybu");
        }
        
    }
}
   