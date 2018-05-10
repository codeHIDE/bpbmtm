package com.bypay.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class UploadPicUtil {
	private static final int BUFFER_SIZE = 16 * 1024; 
	// 图片上传
	public static boolean uploadImgs(File src, File dst) { 
        InputStream in = null; 
        OutputStream out = null; 
        try { 
            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE); 
            out = new BufferedOutputStream(new FileOutputStream(dst), 
                    BUFFER_SIZE); 
            byte[] buffer = new byte[BUFFER_SIZE]; 
            int len = 0; 
            while ((len = in.read(buffer)) > 0) { 
                out.write(buffer, 0, len); 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
            return false;
        } finally { 
            if (null != in) { 
                try { 
                    in.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                    return false;
                } 
            } 
            if (null != out) { 
                try { 
                    out.close(); 
                } catch (IOException e) { 
                    e.printStackTrace(); 
                    return false;
                } 
            } 
        } 
        return true;
    } 
}
