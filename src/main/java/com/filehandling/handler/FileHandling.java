package com.filehandling.handler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
@RestController
@RequestMapping("/")
public class FileHandling{
    @PostMapping("/add/{name}")
    String t(@PathVariable("name") String a,@RequestParam String u){
        try{
        a="template/"+a+".txt";
        BufferedWriter br=new BufferedWriter(new FileWriter(a,true));
        br.newLine();
        br.write(u);
        br.close();
        return "Success";
        }catch(Exception i){
            return "failure";
        }
    }
    @GetMapping("/get/{name}")
    String ta(@PathVariable("name") String name) throws IOException{
        return Files.readString(Path.of("template/"+name+".txt"));
    }
    @GetMapping("/load")
    List<String> load() throws IOException{
        return Files.list(Path.of("template")).map(path->path.getFileName().toString().substring(0,path.getFileName().toString().length()-4)).collect(Collectors.toList());
    }
}