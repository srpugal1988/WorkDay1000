package com.niralcenter.jcmswebface.uploader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niralcenter.jcmswebface.common.ServerDefs;


@Controller
@RequestMapping("/files")
public class FileController {

  @Autowired
  FilesStorageService storageService;

  /*
  @GetMapping("/")
  public String homepage() {
    return "redirect:/files";
  }
 

  @GetMapping("/files/new")
  public String newFile(Model model) {
    return "upload_form";
  }

  @PostMapping("/files/upload")
  public String uploadFiles(Model model, @RequestParam("files") MultipartFile[] files) {
    List<String> messages = new ArrayList<>();

    Arrays.asList(files).stream().forEach(file -> {
      try {
        storageService.save(file);
        messages.add(file.getOriginalFilename() + " [Successful]");
      } catch (Exception e) {
        messages.add(file.getOriginalFilename() + " <Failed> - " + e.getMessage());
      }
    });

    model.addAttribute("messages", messages);

    return "upload_form";
  }

  @GetMapping("/files")
  public String getListFiles(Model model) {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileInfo(filename, url);
    }).collect(Collectors.toList());

    model.addAttribute("files", fileInfos);

    return "files";
  }

  @GetMapping("/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);

    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }

  @GetMapping("/files/delete/{filename:.+}")
  public String deleteFile(@PathVariable String filename, Model model, RedirectAttributes redirectAttributes) {
    try {
      boolean existed = storageService.delete(filename);

      if (existed) {
        redirectAttributes.addFlashAttribute("message", "Delete the file successfully: " + filename);
      } else {
        redirectAttributes.addFlashAttribute("message", "The file does not exist!");
      }
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message",
          "Could not delete the file: " + filename + ". Error: " + e.getMessage());
    }

    return "redirect:/files";
  }
   */
  

  @PostMapping("/upload")
  public ModelAndView uploadFiles(@RequestParam("files") MultipartFile myfile,@RequestParam("branch") String branch,@RequestParam("pageindex") String backurl) {
    List<String> messages = new ArrayList<>();
      try {
    	  System.out.println("reached the file controller.........."+backurl);
    	  
        storageService.save(myfile,branch);
        
        messages.add(myfile.getOriginalFilename() + " [Successful]");
      } catch (Exception e) {
        messages.add(myfile.getOriginalFilename() + " <Failed> - " + e.getMessage());
      }

    //model.addAttribute("messages", messages);
    ModelAndView mav = new ModelAndView("redirect:" + ServerDefs.SERVER_URL +"/"+backurl);
    return mav;
  }

  
  
  
  
  
  
  
  
  
  
  
}
