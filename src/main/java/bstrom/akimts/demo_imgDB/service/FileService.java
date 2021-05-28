package bstrom.akimts.demo_imgDB.service;

import bstrom.akimts.demo_imgDB.exception.NotFoundException;
import bstrom.akimts.demo_imgDB.model.FileDTO;

import java.util.List;

public interface FileService {

    long upload( FileDTO file );
    FileDTO getImage( long id ) throws NotFoundException;
    List<Long> getIds();

}
