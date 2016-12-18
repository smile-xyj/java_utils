package TestUtils;

import org.junit.Test;
import utils.FileOperation;

/**
 * Created by xieyijun on 16-12-17.
 */
public class FileOperationTest {
    FileOperation fileOperation = null;

    public FileOperationTest(){
        fileOperation = new FileOperation();
    }

    @Test
    public void testCreatFile(){
        fileOperation.CreateFile("/home/xieyijun/playground/java/fileoperation/test.txt");
    }

    @Test
    public void testCreatDir(){
        fileOperation.CreatDir("/home/xieyijun/playground/java/fileoperation/test");
    }

    @Test
    public void testReadFileBychars(){
        String content = fileOperation.ReadFileBychars("/home/xieyijun/WorkSpace/solc/huitongbao.sol");
        System.out.println(content);

    }

    @Test
    public void testReadFileByBytes(){
        String content = fileOperation.ReadFileByBytes("/home/xieyijun/WorkSpace/solc/huitongbao.sol");
        System.out.println(content);

    }

    @Test
    public void testwriteFileByFileWriter(){
        String content = fileOperation.ReadFileByBytes("/home/xieyijun/WorkSpace/solc/huitongbao.sol");
        fileOperation.CreateFile("/home/xieyijun/playground/java/fileoperation/test.txt");
        fileOperation.writeFileByFileWriter("/home/xieyijun/playground/java/fileoperation/test.txt",content);
    }
}
