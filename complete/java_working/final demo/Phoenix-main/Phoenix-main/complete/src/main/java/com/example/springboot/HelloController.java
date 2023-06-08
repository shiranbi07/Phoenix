package com.example.springboot;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class HelloController {

    @RequestMapping(value = "/checkCode", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    @CrossOrigin(origins = "*")

    public ResponseEntity<CodeResolve> test(@RequestBody String filePath) {
        try {
            System.out.println("%%%%%%%%%%%%%%%%"+filePath);
            FilePath filePath2 = new Gson().fromJson(filePath, FilePath.class);
            CodeResolve res = Helper.getResponse(filePath2.filePath);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public class FilePath {
        String filePath;
    }

//    @PostMapping("/checkCode")
//    public ResponseEntity<CodeResolve> test(@RequestParam(value = "filePath", defaultValue ="https://github.com/shiranbi07/Phoenix/blob/main/hackaton/amat/java/bug/BankAccount.java") Object filePath) {
//        try {
//            String res = Helper.getResponse( filePath.toString());
//			return ResponseEntity.ok(new CodeResolve(res,"tempsss",10));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
