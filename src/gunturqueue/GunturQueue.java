package gunturqueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Kelas utama
 * 
 * @author gtrdp
 */
public class GunturQueue {
    
    static int jumlahPengantri = 0, pilihan = 99, noAntrian = 0;
    
    //implementasi antrian dalam java menggunakan class:
    //  LinkedList
    static LinkedList antrian = new LinkedList();
    
    public static void main(String[] args) {
        //inisialisasi awal
        init();
        
        //saat pilihan tidak nol (0), maka loop terus menerus
        while(pilihan != 0)
        {
            //tampilkan menu utama
            menu();
            
            //potongan kode ini mengantisipasi masukan dari user yang salah
            try {
                pilihan = Integer.parseInt(getInput());
            } catch (Exception e) {
                println("Nilai yang anda masukkan salah.");
                println("Harap cek lagi nilai yang anda masukkan.");
                pilihan = 99;
                pause();
            }
                
            /**
             * Menyortir pilihan menu dari user:
             *  1. mengambil nomor antrian
             *  2. mengecek berapa orang yang mengantri
             *  0. keluar dari program
             */
            switch (pilihan)
            {
                //Mengambil nomor antrian
                case 1:
                    //Jika nol, maka langsung menuju teller, kemudian keluar
                    if (jumlahPengantri == 0)
                    {
                        
                        println("Antrian kosong, silakan menuju teller!");
                        pause();
                        println("Anda telah dilayani oleh teller!");
                        pause();
                        
                        //keluar
                        pilihan = 0;
                    }
                    else
                    {
                        //Jika ada yg mengantri, maka menunggu sampai nomor
                        //dari user dipanggil
                        
                        //menentukan nomor antrian
                        //diambil dari nomor antrian pengantri terakhir + 1
                        noAntrian = ((Integer) antrian.get(jumlahPengantri - 1)) + 1;
                        
                        //konfirmasi bahwa user telah mengambil nomor antrian
                        println("Anda telah mengambil nomor antrian " + 
                                Integer.toString(noAntrian) +
                                "! Harap tunggu sebentar.");
                        pause();
                        
                        //loop untuk menunggu sampai user dipanggil
                        while(antrian.size() != 0)
                        {
                            println("Pengantri nomor " +
                                    antrian.getFirst() + " dipanggil " +
                                    "ke teller!");
                            
                            //pada struktur linked list
                            //pengantri pertama dikeluarkan dari antrian
                            antrian.removeFirst();
                            pause();
                        }
                        
                        //Nomor antrian user dipanggil, user menuju teller
                        println("Nomor antrian anda ("+noAntrian+") dipanggil!");
                        pause();
                        println("Anda telah dilayani oleh teller!");
                        pause();
                        
                        //keluar
                        pilihan = 0;
                    }
                    break;
                    
                //User ingin mengecek berapa orang yg mengantri
                case 2:
                    if (jumlahPengantri == 0)
                    {
                        println("Antrian kosong!");

                        pause();
                    }
                    else
                    {
                        //menampilkan informasi antrian
                        println("Saat ini ada " + jumlahPengantri + " pengantri.");
                        //data nomor antrian ada pada variabel "antrian"
                        System.out.println("Daftar antrian: " + antrian);

                        pause();
                    }
                    break;
                default:
                    break;
            }
        }
        
        //keluar dari loop utama
        space(1);
        println("Sampai jumpa dan terimakasih!");
    }
    
    /**
     * Inisialisasi awal, berupa penggenerasian bilangan acak dari 0 - 9
     * untuk menentukan jumlah pengantri awal.
     */
    private static void init()
    {
        //batas bawah
        int min = (int)(Math.random() * 9);
        
        //jika batas bawah adalah nol, maka antrian masih kosong, maka keluar
        if(min == 0)
            return;
        
        //batas atas antrian
        int max = min + (int)(Math.random() * 9);
        
        //menentukan jumlah pengantri
        jumlahPengantri = max - min;
                
        //menambahkan tiap nomor ke dalam antrian
        int i;
        for (i = min; i < max; i++)
            antrian.add(i);
        
    }
    
    /**
     * Menampilkan menu utama.
     */
    private static void menu()
    {
        println("Selamat datang di Layanan Antrian Bank ASD!");
        println("(c) Guntur DP 2013");
        println("NIM : 35393");
        println("Silakan pilih menu di bawah ini.");
        space(1);
        println("1. Ambil antrian");
        println("2. Cek antrian");
        println("0. Keluar");
        space(1);
        print("Pilihan Anda:  ");
    }
    
    /**
     * Method print untuk menampilkan teks.
     * @param sentence 
     */
    private static void print(String sentence)
    {
        System.out.print(sentence);
    }
    
    /**
     * Menampilkan teks diikuti dengan carriage return.
     * @param sentence 
     */
    private static void println(String sentence)
    {
        System.out.println(sentence);
    }
    
    /**
     * Memberikan jarak antar baris sesuai jumlah space.
     * @param space 
     */
    private static void space(int space)
    {
        int i = 0;
        for (i = 0; i  < space;i++)
            println("");
    }
    
    /**
     * Untuk menunggu user menekan tombol Enter.
     */
    private static void pause()
    {
        String bar = getInput();
    }
    
    /**
     * Method untuk membaca masukan dari user.
     * Mengembalikan nilai bertipe String.
     * @return 
     */
    private static String getInput()
    {
        //  open up standard input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String string = null;

        //  read the username from the command-line; need to use try/catch with the
        //  readLine() method
        try {
           string = br.readLine();
        } catch (IOException ioe) {
           System.out.println("IO error!");
           System.exit(1);
        }
        
        return string;
    }
}
