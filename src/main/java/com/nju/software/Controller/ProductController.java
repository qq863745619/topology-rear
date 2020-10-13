package com.nju.software.Controller;

import com.nju.software.Bean.Blobimage;
import com.nju.software.Bean.Component;
import com.nju.software.Bean.Product;
import com.nju.software.Model.Config;
import com.nju.software.Model.Response;
import com.nju.software.Model.retInfo;
import com.nju.software.Token.UserLoginToken;
import com.nju.software.service.ProductService;
import com.nju.software.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    UUID uuid;
    private String imgPath = Config.imgPath;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/save")
    public Response save(@RequestBody Product product){
        String id =  productService.saveProduct(product);
        Response response = new Response();
        if(id!=null){
            response.setCode(200);
            response.setData(new retInfo("id",id));
        }
        return response;
    }
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/saveAll")
    public void saveProductAll(@RequestBody List<Product> productList){
        for(Product product:productList){
            String id =  productService.saveProduct(product);
        }
    }

    //向groupId组内添加或更改产品信息
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/saveProductbygroup")
    public Response saveProductByGroup(@RequestBody Product product){
        String groupid = product.getGroupId();
        Response r = new Response();
        if(groupid == null || groupid==""){
            r.setCode(-1);
            r.setData(new retInfo("error","group不存在，请先创建分组"));
            return r;
        }
        if(product.getId()==null || product.getId()==""){
            product.setId(uuid.getUUID4());
        }
        String pid = this.productService.saveProduct(product);
        r.setCode(200);
        r.setData(new retInfo("id",pid));
        return r;

    }

    //根据group获取到该分组中的所有产品信息
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getProductByGroup/{groupId}")
    public Response getProductByGroup(@PathVariable("groupId")String groupId){
        List<Product> list = this.productService.getAllbyGroupId(groupId);
        Response r = new Response();
        if(list!=null){
            r.setCode(200);
            r.setData(new retInfo("list",list));
        }
        return r;
    }


    //上传产品图片
    @CrossOrigin
    @ResponseBody
    @PostMapping(value = "/updateImage")
    public Response saveImg(@RequestParam("file") MultipartFile blobFile) throws IOException {

        String name = uuid.getUUID();
        String posturl = blobFile.getContentType().substring(6);
        System.out.println(posturl);
        String url = "product_"+name+"."+posturl;
        File f = null;
        f = new File(imgPath+url);
        Response r = new Response();
        try (InputStream in  = blobFile.getInputStream(); OutputStream os = new FileOutputStream(f)){
            // 得到文件流。以文件流的方式输出到新文件
            // 可以使用byte[] ss = multipartFile.getBytes();代替while
            byte[] buffer = new byte[4096];
            int n;
            while ((n = in.read(buffer,0,4096)) != -1){
                os.write(buffer,0,n);
            }
            r.setCode(200);
            r.setData(new retInfo("successes","/Product/"+url));
        }catch (IOException e){
            e.printStackTrace();
        }
        // 输出路径
        System.out.println(f.getAbsolutePath());
        return r;
    }
    //

    //删除产品
    @CrossOrigin
    @ResponseBody
    @DeleteMapping(path = "/deleteProduct/{id}")
    public Response deleteProductById( @PathVariable(name = "id") String id){
        Response r = new Response();
        int res = productService.deleteProduct(id);
        if(res>0){
            r.setCode(200);
            r.setData(new retInfo("successes",res));
        }
        return r;
    }
}
