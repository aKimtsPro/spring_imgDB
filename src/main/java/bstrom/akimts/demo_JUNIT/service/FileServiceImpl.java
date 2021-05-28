package bstrom.akimts.demo_JUNIT.service;

import bstrom.akimts.demo_JUNIT.entity.File;
import bstrom.akimts.demo_JUNIT.exception.NotFoundException;
import bstrom.akimts.demo_JUNIT.model.FileDTO;
import bstrom.akimts.demo_JUNIT.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class FileServiceImpl implements FileService {

    private final FileRepository repository;

    public FileServiceImpl(FileRepository repository) {
        this.repository = repository;
    }

    @Override
    public long upload(FileDTO file) {

        byte[] compressedBytes = compress(file.getBytes());

        System.out.println("compressed: " + compressedBytes.length +
                " \nuncompressed: " + file.getBytes().length );

        File toSave = File.builder()
                .name( file.getName() )
                .type( file.getType() )
                .bytes( compressedBytes )
                .build();

        toSave = repository.save( toSave );

        return toSave.getId();

    }

    @Override
    public FileDTO getImage(long id) throws NotFoundException {

        File file = repository.findById(id).orElseThrow(NotFoundException::new);

        return FileDTO.builder()
                .id( file.getId() )
                .name( file.getName() )
                .type( file.getType() )
                .bytes( decompress(file.getBytes()) )
                .build();
    }

    @Override
    public List<Long> getIds() {
        return repository.getIds();
    }

    private byte[] compress(byte[] input) {
        Deflater compresseur = new Deflater();
        compresseur.setInput( input );
        compresseur.finish();

        byte[] buffer = new byte[1024];
        byte[] result = null;

        try( ByteArrayOutputStream outputStream = new ByteArrayOutputStream(input.length) ){

            while( !compresseur.finished() ) {
                int count = compresseur.deflate( buffer );
                outputStream.write(buffer, 0, count);
            }

            compresseur.end();
            result = outputStream.toByteArray();

        }
        catch (IOException ignored) {}

        return result;
    }

    private byte[] decompress(byte[] input) {
        Inflater decompresseur = new Inflater();
        decompresseur.setInput(input);

        byte[] buffer = new byte[1024];
        byte[] result = null;


        try ( ByteArrayOutputStream outputStream = new ByteArrayOutputStream() ) {

            while( !decompresseur.finished() ) {
                int count = decompresseur.inflate( buffer );
                outputStream.write(buffer,0,count);
            }
            decompresseur.end();
            result = outputStream.toByteArray();

        }
        catch (IOException | DataFormatException ignored) {}

        return result;

    }
}
