package bstrom.akimts.demo_JUNIT.service;

import bstrom.akimts.demo_JUNIT.entity.File;
import bstrom.akimts.demo_JUNIT.exception.NotFoundException;
import bstrom.akimts.demo_JUNIT.model.FileDTO;

import java.util.List;

public interface FileService {

    long upload( FileDTO file );
    FileDTO getImage( long id ) throws NotFoundException;
    List<Long> getIds();

}
