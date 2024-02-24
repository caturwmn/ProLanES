# ProLanES

## Refleksi Modul 3:
1. Prinsip-prinsip SOLID yang saya implementasikan pada proyek saya adalah:

- Single Responsibility Principle yang menyatakan bahwa sebuah class (atau modul) bertanggung jawab terhadap satu fungsi, hal ini untuk mempermudah testing terhadap sebuah class serta mempermudah proses navigasi proyek tersebut. Selain itu hal ini mengurangi ketergantungan antar modul (perubahan pada satu bagian tidak terlalu berpengaruh terhadap bagian lainnya). Prinsip ini saya terapkan dengan memisah controller menjadi 2, yaitu untuk Car dan Product dibandingkan sebelumnya yang menyatukan mereka.

- Interface Segregation Principle yang menyatakan interface yang besar sebaiknya dibagi menjadi interface-interface yang lebih kecil agar bagian interface yang diperlukan saja yang di implementasi. Hal ini saya gunakan terhadap beberapa interface seperti interface untuk service serta repository. Namun saya mensegregasi mereka secukupnya saja (bukan tiap fungsi satu interface karena terasa redundant dan memperumit navigasi proyek).

- Dependency Inversion Principle yang menyatakan bahwa class yang kita buat sebaiknya bergantung pada class abstract(atau interface), bukan concrete class. Hal ini mempermudah testing dengan membuat kita tidak perlu menerapkan kelas yang dibutuhkan. Hal ini juga membuat class lebih fleksible dengan membiarkan kita menggunakan class berbeda untuk mengisi keperluan class tersebut. Hal ini saya implementasikan dengan membuat interface untuk repository dan membuat service menggunakan interface tersebut dibandingkan memanggil implementasi repository secara langsung.

Terdapat 2 prinsip lain yaitu:

- Open-Closed Principle dimana class tertutup terhadap perubahan namun bisa di modifikasi dengan diextend agar perubahan tidak membuat kita merubah seluruh program yang tidak saya implementasikan karena tidak ada modifikasi kecil yang terjadi. Serta

- Liskov Substitution Principle yang menyatakan subclass dan superclass bisa di tukar satu sama lain. Tidak saya gunakan karena tidak ada subclass baru (car controller pada dasarnya hal yang berbeda dari product controller)


2. Keuntungan yang saya dapat antara lain: 

- untuk SRP, apabila ada perubahan pada product controller saya tidak mengubah cara kerja car controller.

- untuk ISP, saya tidak perlu mengimplementasikan find by id untuk product repository dan find yang khusus untuk product repository pada car repository (juga tidak perlu mengimplementasikan 2 fungsi delete yang berbeda untuk 2 repository tersebut). Lalu 

- untuk DIP, saya ketika testing hanya perlu mengandalkan abstraksi dan jika perlu saya bisa mengubah repository yang digunakan oleh service dengan implementasi yang berbeda pada kasus tertentu.

3. Kerugian yang mungkin antara lain:

- untuk SRP, apabila saya merubah product controller, car controller juga berubah. Selain itu untuk mengetes product controller saya juga harus mengetes car controller.

- untuk ISP, apabila saya ingin mengimplementasikan car repository, saya harus mengimplementasikan fungsi find dan delete dari product repository. Selain itu, paling tidak file tiap interface terlihat lebih rapi(lebih sedikit barisnya).

- untuk DIP, saya tidak akan bisa menggunakan implementasi yang berbeda untuk repository yang digunakan oleh service.

## Refleksi Modul 2:
1. Terkait kualitas kode, saya  tidak bisa mendapatkan 100% code coverage karena saya bingung tentang apa yang harus saya lakukan untuk membuat test-test tersebut karena menggunakan mockito sangat rumit bagi saya. Selain itu dalam dokumentasi saya masih bingung sehingga saya berusaha menggunakan format tutoriaal semirip-miripnya.

2.Implementasi yang saya buat tidak memenuhi CI/CD karena usaha saya dalam deployment bermasalaha sehingga saya tidak berhasil menerapkan continuous deployment. Namun untuk continuous integration saya mungkin berhasil karena berhasil melakukan automasi terhadap konsep testing aplikasi sehingga proses testing walaupun belum 100%, paling tidak bisa bekerja. Jadi kurang lebih saya hanya bisa menerapkan setengah dari konsep CI/CD  

## Refleksi Modul 1:

Dikarenakan saya telat mulai mengerjakan, saya mungkin masih belum sempat untuk menambahkan fungsi tambahan yang diminta yaitu gunfsi edit dan delete karena framework yang sangat asing bagi saya. Selain itu saya masih bingung mengenai apabila saya sudah memenuhi prinsip clean coding karena kurangnya comments yang saya buat dikarenakan saya takut akan membuat comment yang "redundant" karena sebagian besar kodenya seharusnya sudah jelas dan saya format paling mendekati dengan apa yang ditunjukan di tutorial. 

1. Jumlah unit test dalam tiap kelas seharusnya tidak terlalu banyak, namun tetap cukup untuk mengetes setiap fungsi yang ada pada suatu bagian dari program. Selain itu, walaupun code coverage 100% belum tentu tidak ada bugnya karena bisa saja test yang kita buat ternyata tidak sempurna dan menyatakan sukses dibagian yang seharusnya salah
2. Karena saya belum sempat untuk membuat test yang baru, jadi saya tidak bisa mengatakan apa-apa  
