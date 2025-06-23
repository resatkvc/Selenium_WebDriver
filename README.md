# 🧪 Selenium WebDriver ile Otomatik Login Test Projesi

Bu projede, gerçek bir web uygulaması olan [SauceDemo](https://www.saucedemo.com/) sitesine otomatik giriş (login) testleri gerçekleştirilmiştir.

---

## 🔍 Proje Özeti

Bu otomasyon projesinde aşağıdaki senaryolar ele alınmıştır:

- ✅ **Doğru kullanıcı bilgileriyle başarılı login testi**
- ❌ **Hatalı kullanıcı adı veya şifre ile başarısız login testi**
- 🔄 Her kullanıcı tipi için:
  - Girişin başarılı olup olmadığı kontrol edilir.
  - Başarısız girişlerde, ekranda **beklenen hata mesajının** görünürlüğü doğrulanır.

---

## 🧩 Proje Yapısı ve Yaklaşımlar


- 🗂️ **Page Object Model (POM):**  
  Login işlemleri ve sayfa etkileşimleri, tekrar kullanılabilir bir yapıyla modüler hale getirilmiştir.

- ⚙️ **Konfigürasyon Yönetimi:**  
  Kullanıcı bilgileri, site URL'si, hata mesajları gibi değişken veriler ayrı dosyalarda saklanarak koddan ayrıştırılmıştır.

- 🧾 **Loglama:**  
  Testler çalıştırıldığında, tüm adımlar detaylı olarak loglanır.

- 📸 **Hata Anında Ekran Görüntüsü:**  
  Başarısız olan testlerde otomatik olarak ekran görüntüsü alınır.

- 📊 **Allure Raporlama:**  
  Test sonuçları, adım adım görselleştirilmiş ve herkes tarafından kolayca anlaşılabilecek şekilde raporlanır.

---
