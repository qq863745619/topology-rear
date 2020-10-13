package com.nju.software.Controller;


import com.alibaba.fastjson.JSONObject;
import com.nju.software.Bean.Blobimage;
import com.nju.software.Model.Config;
import com.nju.software.Token.UserLoginToken;
import com.nju.software.service.BlobimageService;
import com.nju.software.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
public class BlobimageController {

    @Autowired
     private BlobimageService blobimageService;

    @Autowired
    private UUID uuid;
    private String fileurl = Config.devfile;

//    @CrossOrigin
//    @ResponseBody
//    @PostMapping(value = "/upload_t_image")
//    public Map<String,Object> save(@RequestParam("file") MultipartFile blobFile, @RequestParam("randomName")int randomname,
//                                                     @RequestParam("public")boolean isPublic) throws IOException {
//
//
//        Blobimage blobimage = new Blobimage();
//        String name = uuid.getUUID();
//        String url = "thumb_"+name+".png";
//        blobimage.setPath(url);
//        blobimage.setIspublic(isPublic);
//        blobimage.setRandomName(randomname);
//        if(blobFile==null){
//            blobimage.setFile(null);
//        }else {
//            blobimage.setFile(blobFile.getBytes());
//        }
//        String uri = blobimageService.save(blobimage);
//        Map<String,Object> sMap = new HashMap<>();
//
//        sMap.put("url",uri);
//        return sMap;
//    }

    @CrossOrigin
    @UserLoginToken
    @ResponseBody
    @PostMapping(value = "/upload_t_image")
    public Map<String,Object> save(@RequestParam("file") MultipartFile blobFile, @RequestParam("randomName")int randomname,
                                   @RequestParam("public")boolean isPublic) throws IOException {


        Blobimage blobimage = new Blobimage();
        String name = uuid.getUUID();
        String url = "thumb_"+name+".png";
        blobimage.setPath(url);
        blobimage.setIspublic(isPublic);
        blobimage.setRandomName(randomname);
        blobimage.setFile(null);
        File f = null;
        f = new File(fileurl+url);
        try ( InputStream in  = blobFile.getInputStream(); OutputStream os = new FileOutputStream(f)){
            // 得到文件流。以文件流的方式输出到新文件
            // 可以使用byte[] ss = multipartFile.getBytes();代替while
            byte[] buffer = new byte[4096];
            int n;
            while ((n = in.read(buffer,0,4096)) != -1){
                os.write(buffer,0,n);
            }
            // 读取文件第一行
//            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
//            System.out.println(bufferedReader.readLine());
//
//            bufferedReader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        // 输出路径
        System.out.println(f.getAbsolutePath());

        String uri = blobimageService.save(blobimage);
        Map<String,Object> sMap = new HashMap<>();

        sMap.put("url",uri);
        return sMap;
    }
    @CrossOrigin
    @UserLoginToken
    @ResponseBody
    @DeleteMapping(path = "/delete_t_image/{id}")
    public void delete(@PathVariable(name = "id") String id){

        blobimageService.delete(id);
        //TODO 这里需要根据文件名删除文件
        File file = new File(fileurl+id);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileurl+id + "成功！");

            } else {
                System.out.println("删除单个文件" + fileurl+id + "失败！");
            }
        }
        System.out.println(id);
    }

//    @CrossOrigin
//    @GetMapping(path = "/blob/{id}")
//    public ResponseEntity<byte[]> getblob(@PathVariable("id")String path){
//        byte[] image = null;
//        try {
//             image= blobimageService.findBlob(path).getFile();
//        }catch (NullPointerException e){
//            System.out.println(e);
//        }
//
//
//        final HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_PNG);
//        return new ResponseEntity<>(image, headers, HttpStatus.OK);
//    }



    @CrossOrigin
    @UserLoginToken
    @ResponseBody
    @PatchMapping(path = "/blob/{id}")
    public Map<String,Integer> alterShared(@PathVariable("id")String path, @RequestBody JSONObject jsonParam){
        boolean ispublic = (boolean) jsonParam.get("public");
        int i = blobimageService.updateBlob(path,ispublic);

        Map<String,Integer> sMap = new HashMap<>();
        sMap.put("i",i);
        return sMap;
    }


}

