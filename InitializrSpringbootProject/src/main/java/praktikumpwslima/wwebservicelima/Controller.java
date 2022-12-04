/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package praktikumpwslima.wwebservicelima;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aqyla-PC
 */
@RestController
public class Controller {
    private static Map<String, Product> productRepo = new HashMap<>();
    static {
       //membuat objek model honey
      Product honey = new Product();
      //mengisi data pada model
      honey.setId("1");
      honey.setName("Honey");
      //untuk memasukan data lewat HashMap ke model
      productRepo.put(honey.getId(), honey);
      
      //membuat objek model almond
      Product almond = new Product();
      almond.setId("2");
      almond.setName("Almond");
      productRepo.put(almond.getId(), almond);
   }
   //untuk hapus data
   //RequestMapping berfungsi untuk menandakan atau menentukan url yang akan di pakai
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
   //
   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
       //untuk menghapus data menggunakan HashMap
      productRepo.remove(id);
      //untuk menampilkan pesan ketika delete berhasil
      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
   }
   //untuk mengupdate data. caranya dengan menambahkan id data yang ingin dihapus pada url
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
      //untuk menghapus data menggunakan HashMap4
      productRepo.remove(id);
      //untuk menambahkan id baru
      product.setId(id);
      //untuk mengupdate data 
      productRepo.put(id, product);
      //menampilkan pesan jika data berhasil di update
      return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
   }
   //membuat data baru 
   @RequestMapping(value = "/products", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody Product product) {
      //untuk menambahkan data melalui HashMap yang beisi data dari model
      productRepo.put(product.getId(), product);
      //menampilkan pesan jika data berhasil di tambahkan
      return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
   }
   // untuk menampilkan data 
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
   }
   
   //http://10.69.2.189:8080/products. 10.69.2.189 ipv4. cara cek ip : ipconfig di cmd
}
