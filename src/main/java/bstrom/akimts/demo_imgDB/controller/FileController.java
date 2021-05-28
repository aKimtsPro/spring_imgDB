package bstrom.akimts.demo_imgDB.controller;

import bstrom.akimts.demo_imgDB.model.FileDTO;
import bstrom.akimts.demo_imgDB.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
@CrossOrigin("*")
public class FileController {

    private final FileService service;

    public FileController(FileService service) {
        this.service = service;
    }

    @PostMapping
    public Long upload(@RequestParam MultipartFile file) throws IOException {
        System.out.println(
                file.getOriginalFilename() + " -*- " +
                file.getContentType() + " -*- " +
                file.getBytes().length
        );

        FileDTO toSave = FileDTO.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .bytes(file.getBytes())
                .build();

        return service.upload(toSave);

    }

    @GetMapping("/{id}")
    public FileDTO getFile(@PathVariable Long id) {
        return service.getImage(id);
    }

    @GetMapping("/ids")
    public List<Long> getIds(){
        return service.getIds();
    }

}
