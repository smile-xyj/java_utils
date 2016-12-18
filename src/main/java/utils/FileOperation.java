package utils;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import jdk.jfr.events.FileReadEvent;

import java.io.*;

/**
 * Created by xieyijun on 16-12-17.
 */
public class FileOperation {
    /**
     * 功能：创建单个文件
     * @param filepath 要创建文件的文件名，一般是绝对路径
     * @return
     */
    public boolean CreateFile(String filepath){
        File file = new File(filepath);
        if(file.exists()){
            System.out.println("创建文件"+filepath+"失败"+", 文件已存在");
            return false;
        }
        if(filepath.endsWith(File.separator)){
            System.out.println("创建文件失败"+",创建目标是目录！");
            return false;
        }
        //检查父级目录是否存在,不存在就创建
        if(!file.getParentFile().exists()){
            System.out.println("目标文件的父级目录不存在，准备创建父级目录");
            if(!file.getParentFile().mkdirs()){
                return false;
            }
        }
        //创建文件
        try{
            if(file.createNewFile()){
                System.out.println("创建文件"+filepath+"成功");
                return true;
            } else {
                System.out.println("创建文件"+filepath+"失败");
                return false;
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("创建文件"+filepath+"失败"+e.getMessage());
            return false;
        }
    }

    /**
     *功能：创建文件夹，父级目录不存在的时候先创建父级目录
     * @param dirpath 要创建的文件夹的绝对路径，先测试父级目录存在的情况，再测父级目录不存在
     * @return
     */
    public boolean CreatDir(String dirpath){
        File dir = new File(dirpath);
        if (dir.exists()) {
            System.out.println("创建目录" + dirpath + "失败，目标目录已经存在");
            return false;
        }
        //添加分隔符，也就是斜杠
        if (!dirpath.endsWith(File.separator)) {
            dirpath = dirpath + File.separator;
        }
        //创建目录
        if (dir.mkdirs()) {
            System.out.println("创建目录" + dirpath + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + dirpath + "失败！");
            return false;
        }
    }

    /**
     *
     *
     * @param filename　文件的绝对路径
     * @return  返回文件内容，以字符串来保存
     */
    public String ReadFileBychars(String filename){
        File file =  new File(filename);
        String content = "";
        //判断文件是否存在
        if(!file.exists()||!file.isFile()){
            return "";
        }
        FileReader fir = null;
        BufferedReader bir = null;
        try{
            fir = new FileReader(file);
            bir = new BufferedReader(fir);
            String temp = "";
            while((temp=bir.readLine())!=null){
                content = content + temp;
            }

        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                bir.close();
                fir.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        return content;
    }

    /**
     *
     * @param filename  文件名
     * @return　　返回文件内容
     */
    public String ReadFileByBytes(String filename){
        File file = new File(filename);
        String content = "";
        if(!file.exists()||!file.isFile()){
            return new String();
        }
        FileInputStream fis = null;
        try{
            fis= new FileInputStream(file);
            int size = fis.available();
            for(int i=0; i<size; i++){
                content = content + (char)fis.read();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return content;

    }

    /**
     *
     * @param filePath 要写的文件路径
     * @param content　要写入的内容
     * @return
     * @throws IOException
     */

    public boolean writeFileByFileWriter(String filePath, String content)  {
        File file = new File(filePath);
        synchronized (file) {
            FileWriter fw = null;
            try {
                fw = new FileWriter(filePath);
                fw.write(content);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                try{
                    fw.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }
    }

}
