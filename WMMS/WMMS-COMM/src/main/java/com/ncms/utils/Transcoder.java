package com.ncms.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.ncms.comm.exception.BizException;

/** 
 * serialized or compressed data. 
 *  
 * @author dongjian 
 */  
@SuppressWarnings("unchecked")  
public final class Transcoder {  
  
    protected static String charset = "UTF-8";  
  
    private Transcoder() { }  
      
    public static <T> T decodeObject(byte[] b) {  
        return (T) deserialize(decompress(b));  
    }  
  
    public static byte[] encodeObject(Object o) {  
        byte[] b = serialize(o);  
        return compress(b);  
    }  
      
    /** 
     * Decode the string with the current character set. 
     */  
    public static String decodeString(byte[] data) {  
        String rv = null;  
        try {  
            if (data != null) {  
                rv = new String(data, charset);  
            }  
        } catch (UnsupportedEncodingException e) {  
            throw new RuntimeException(e);  
        }  
        return rv;  
    }  
  
    /** 
     * Encode a string into the current character set. 
     */  
    public static byte[] encodeString(String in) {  
        byte[] rv = null;  
        try {  
            rv = in.getBytes(charset);  
        } catch (UnsupportedEncodingException e) {  
            throw new RuntimeException(e);  
        }  
        return rv;  
    }  
  
    /** 
     * Get the bytes representing the given serialized object. 
     */  
    private static byte[] serialize(Object o) {  
        if (o == null) {  
            throw new NullPointerException("Can't serialize null");  
        }  
        byte[] rv = null;  
        ByteArrayOutputStream bos = null;  
        ObjectOutputStream os = null;  
        try {  
            bos = new ByteArrayOutputStream();  
            os = new ObjectOutputStream(bos);  
            os.writeObject(o);  
            os.close();  
            bos.close();  
            rv = bos.toByteArray();  
        } catch (IOException e) {  
            throw new IllegalArgumentException("Non-serializable object", e);  
        } finally {  
            CloseUtil.close(os);  
            CloseUtil.close(bos);  
        }  
        return rv;  
    }  
  
    /** 
     * Get the object represented by the given serialized bytes. 
     */  
    private static Object deserialize(byte[] in) {  
        Object rv = null;  
        ByteArrayInputStream bis = null;  
        ObjectInputStream is = null;  
        try {  
            if (in != null) {  
                bis = new ByteArrayInputStream(in);  
                is = new ObjectInputStream(bis);  
                rv = is.readObject();  
                is.close();  
                bis.close();  
            }  
        } catch (IOException e) {  
            throw new BizException("Caught IOException decoding " 
            		+ (in == null ? 0 : in.length) + " bytes of data");  
        } catch (ClassNotFoundException e) {   
            throw new BizException("Caught CNFE decoding " + (in == null ? 0 : in.length)  
                    + " bytes of data");  
        } finally {  
            CloseUtil.close(is);  
            CloseUtil.close(bis);  
        }  
        return rv;  
    }  
  
    /** 
     * Compress the given array of bytes. 
     */  
    private static byte[] compress(byte[] in) {  
        if (in == null) {  
            throw new NullPointerException("Can't compress null");  
        }  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        GZIPOutputStream gz = null;  
        try {  
            gz = new GZIPOutputStream(bos);  
            gz.write(in);  
        } catch (IOException e) {  
            throw new RuntimeException("IO exception compressing data", e);  
        } finally {  
            CloseUtil.close(gz);  
            CloseUtil.close(bos);  
        }  
        byte[] rv = bos.toByteArray();  
//        log.debug("Compressed " + in.length + " bytes to " + rv.length);  
        return rv;  
    }  
  
    /** 
     * Decompress the given array of bytes. 
     *  
     * @return null if the bytes cannot be decompressed 
     */  
    private static byte[] decompress(byte[] in) {  
        ByteArrayOutputStream bos = null;  
        if (in != null) {  
            ByteArrayInputStream bis = new ByteArrayInputStream(in);  
            bos = new ByteArrayOutputStream();  
            GZIPInputStream gis = null;  
            try {  
                gis = new GZIPInputStream(bis);  
  
                byte[] buf = new byte[8192];  
                int r = -1;  
                while ((r = gis.read(buf)) > 0) {  
                    bos.write(buf, 0, r);  
                }  
            } catch (IOException e) {
            	if(bos != null)
					try {
						bos.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}  
                throw new BizException("Failed to decompress data");  
            } finally {  
                CloseUtil.close(gis);  
                CloseUtil.close(bis);  
                CloseUtil.close(bos);  
            }  
        }  
        return bos == null ? null : bos.toByteArray();  
    }  
  
    private static final class CloseUtil {  
        private CloseUtil() { }  
        public static void close(Closeable closeable) {  
            if (closeable != null) {  
                try {  
                    closeable.close();  
                } catch (Exception e) {  
                    throw new BizException("Unable to close " + e.getMessage());  
                }  
            }  
        }  
    }  
  
}