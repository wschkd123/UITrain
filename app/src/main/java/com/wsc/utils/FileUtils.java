package com.wsc.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 文件操作工具类
 * @author nieyu
 *
 */
public class FileUtils {

	public static final int GLOBLE_BUFFER_SIZE = 2 * 1024;

	private static boolean  sHasSDCardMounted = false;
	private static boolean  sHasCacheSDCardStatus = false;
	
	public static String printSDCardRoot() {
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	public static File getFileFromUri(Uri uri) {
	
		if ((uri.getScheme().equals("file"))
				&& (!TextUtils.isEmpty(uri.getPath()))) {
			return new File(uri.getPath());
		}

		return null;
	}

	public static boolean closeStream(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
				return true;
			}
		} catch (IOException e) {
		}

		return false;
	}

	public static boolean hasSDCardMounted() {
		return hasCacheSDCardMounted();

	}

	private static boolean hasCacheSDCardMounted() {
		if(sHasCacheSDCardStatus){
			return sHasSDCardMounted;
		}
		sHasCacheSDCardStatus = true;

		return sHasSDCardMounted = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}

	public static void onSDCardChanged(boolean hasMounted){
		sHasCacheSDCardStatus = false;
		sHasSDCardMounted = hasMounted;
	}

	public static boolean doesExisted(File file) {
		return (file != null) && (file.exists());
	}

	public static InputStream makeInputBuffered(InputStream input_) {

		if ((input_ instanceof BufferedInputStream)) {
			return input_;
		}

		return new BufferedInputStream(input_, GLOBLE_BUFFER_SIZE);
	}

	public static FileInputStream getFileInputStream(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return fis;
	}

	public static FileInputStream getFileInputStream(String path) {
		return getFileInputStream(new File(path));
	}

	// 检测文件是否存在
	public static boolean isFileExist(String filePath) {
		if (TextUtils.isEmpty(filePath)) {
			return false;
		}
		File file = new File(filePath);
		return file.isFile() && file.exists();
	}

	// 检测目录是否存在
	public static boolean isDirectoryExist(String filePath) {
		if (TextUtils.isEmpty(filePath)) {
			return false;
		}
		File file = new File(filePath);
		return file.isDirectory() && file.exists();
	}

	/**
	 * 以字符的方式读取文件,主要是log里用的，所以oom和异常不抛出，静默处理
	 * @param path
	 * @return
	 */
	public static String readStringFromFile(String path) {
		if (!isFileExist(path)) {
			return "";
		}
		File file = new File(path);
		BufferedReader reader = null;
		// 这一步可能OOM并被抛出
		StringBuilder content = new StringBuilder((int)file.length());
		try {
			reader = new BufferedReader(new FileReader(file));
			String temp = null;
			while ((temp = reader.readLine()) != null) {
				content.append(temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}

		}
		return content.toString();
	}

	// Convert Uri to absolute path
	public static String getAbsolutePathFromUri(String uriString) {

		if (TextUtils.isEmpty(uriString)) {
			return null;
		}

		Uri uri = Uri.parse(uriString);

		if (uri == null) {
			return null;
		}

		return uri.getPath();
	}

	private static final int DEFAULT_DELETE_LINITE = 5;
//	private static FileFilter fileOnlyFilter;
//	private static FileFilter dirOnlyFilter;

	public static void copy(InputStream input_, OutputStream output_)
			throws IOException {
		try {
			byte[] buffer = new byte[GLOBLE_BUFFER_SIZE];
			int temp = -1;
			input_ = makeInputBuffered(input_);
			output_ = makeOutputBuffered(output_);
			while ((temp = input_.read(buffer)) != -1) {
				output_.write(buffer, 0, temp);
				output_.flush();
			}
		} catch (IOException e) {
			throw e;
		} finally {
			FileUtils.closeStream(input_);
			FileUtils.closeStream(output_);
		}
	}

	public static OutputStream makeOutputBuffered(OutputStream output_) {
		if ((output_ instanceof BufferedOutputStream)) {
			return output_;
		}

		return new BufferedOutputStream(output_, GLOBLE_BUFFER_SIZE);
	}

	public static void copyWithoutOutputClosing(InputStream input_,
			OutputStream output_) throws IOException {
		try {
			byte[] buffer = new byte[GLOBLE_BUFFER_SIZE];
			int temp = -1;
			input_ = makeInputBuffered(input_);
			output_ = makeOutputBuffered(output_);
			while ((temp = input_.read(buffer)) != -1) {
				output_.write(buffer, 0, temp);
				output_.flush();
			}
			output_.flush();
		} catch (IOException e) {
			throw e;
		} finally {
			FileUtils.closeStream(input_);
		}
	}

	public static byte[] readInputStream(InputStream is_) {
		if (is_ != null) {
			ByteArrayOutputStream baos = null;
			try {
				baos = new ByteArrayOutputStream(GLOBLE_BUFFER_SIZE);
				copyWithoutOutputClosing(is_, baos);
				byte[] res = baos.toByteArray();
				byte[] arrayOfByte1 = res;
				return arrayOfByte1;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
                if (baos != null) {
                    FileUtils.closeStream(baos);
                }
                try {
                    is_.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
			}
		}

		return null;
	}

	public static byte[] readFile(File file_) {
		return readInputStream(getFileInputStream(file_));
	}

	public static byte[] readFile(String path_) throws FileNotFoundException {
		File f = new File(path_);
		if ((doesExisted(f)) && (f.isFile()))
			return readFile(f);
		throw new FileNotFoundException(f.getAbsolutePath());
	}

	public static String readFileForString(File file_, String charset_)
			throws UnsupportedEncodingException {
		return new String(readFile(file_), charset_);
	}

	public static String readFileForString(String path_, String charset_)
			throws UnsupportedEncodingException, FileNotFoundException {
		return new String(readFile(path_), charset_);
	}

	public static String readFileForString(File file_) {
        byte[] result = readFile(file_);
        if (result == null) {
            return "";
        } else {
            return new String(result);
        }
    }

	public static String readFileForString(String path_)
			throws FileNotFoundException {
        byte[] result = readFile(path_);
        if (result == null) {
            return "";
        } else {
            return new String(result);
        }
    }

	public static Object loadObject(String filepath_) throws IOException,
			ClassNotFoundException {
		if (doesExisted(new File(filepath_)))
			return loadObject(new FileInputStream(filepath_));
		return null;
	}

	public static Object loadObject(InputStream input_) throws IOException,
			ClassNotFoundException {
		ObjectInputStream ois = null;
		try {
			Object obj = null;
			ois = new ObjectInputStream(input_ = makeInputBuffered(input_));
			obj = ois.readObject();
			Object localObject2 = obj;
			return localObject2;
		} catch (IOException e) {
			throw e;
		} catch (ClassNotFoundException e) {
			throw e;
		} finally {
            if (ois != null) {
                FileUtils.closeStream(ois);
            } else {
                input_.close();
            }
        }
	}

	public static void saveObject(Object obj_, String filepath_)
			throws IOException {

		saveObject(obj_, new FileOutputStream(filepath_));
	}

	public static void saveObject(Object obj_, OutputStream output_)
			throws IOException {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(makeOutputBuffered(output_));
			oos.writeObject(obj_);
		} catch (IOException e) {
			throw e;
		} finally {
            if (oos != null) {
                FileUtils.closeStream(oos);
            } else {
                output_.close();
            }
        }
	}
	
	public static void unZipFile(File source, String destDir) throws IOException{
		if(!source.exists()){
			return;
		}
		File outFile = new File(destDir);
		if (!outFile.exists()){
			outFile.mkdirs();
		}
		ZipFile zipFile = new ZipFile(source);
		Enumeration<? extends ZipEntry> entries = zipFile.entries();
	    while (entries.hasMoreElements()) {
	    	ZipEntry entry = entries.nextElement();
	    	File destination = new File(outFile, entry.getName());
			if (entry.isDirectory()) {
				destination.mkdirs();
			}else{
				destination.createNewFile();
				FileOutputStream outStream = new FileOutputStream(destination);
				copy(zipFile.getInputStream(entry),outStream);
				outStream.close();
			}
		}
		zipFile.close();
	}
	
	public static long getFileSize(File file_) {

		int totalsize = 0;
		if (!file_.exists()) {
			Log.e("FileUtils", file_.getAbsolutePath() + " dones't exist.");
		} else if (file_.isFile()) {
			totalsize = (int) (totalsize + file_.length());
		} else {
			File[] childrenFile = file_.listFiles();
			if (childrenFile != null) {
				for (File f : childrenFile) {
					totalsize = (int) (totalsize + getFileSize(f));
				}
			}

		}

		return totalsize;
	}

	public static long getFileSize(String filepath_) {
		return getFileSize(new File(filepath_));
	}

	public static boolean doesExisted(String filepath) {
		if (TextUtils.isEmpty(filepath))
			return false;
		return doesExisted(new File(filepath));
	}

	/**
	 * 如果doesExisted(filepath_)为true且是文件类型则删除文件，并返回true； 否则什么也不做并且返回false。
	 * 如果删除失败，不会重试。
	 * 
	 * @param file
	 * @return
	 */
	public static boolean deleteDependon(File file) {
		return FileUtils.deleteDependon(file, 0);
	}

	/**
	 * 如果doesExisted(file_)为true则删除文件，并返回true； 否则什么也不做并且返回false。
	 * 其中，删除文件的时候如果删除失败会尝试最多删除 maxRetryCount 次.
	 * 
	 * @param file
	 * @return
	 */
	public static boolean deleteDependon(File file, int maxRetryCount) {
		int retryCount = 1;
		maxRetryCount = maxRetryCount < 1 ? FileUtils.DEFAULT_DELETE_LINITE
				: maxRetryCount;
		boolean isDeleted = false;

		if (file != null) {
			while (!isDeleted && (retryCount <= maxRetryCount)
					&& doesExisted(file)) {
				if (!(isDeleted = file.delete())) {
					Log.d("FileUtils", file.getAbsolutePath() + "删除失败，失败次数为:"
							+ retryCount);
					retryCount++;
				}
			}

		}

		return isDeleted;
	}

	/**
	 * 如果doesExisted(filepath_)为true且是文件类型则删除文件，并返回true； 否则什么也不做并且返回false。
	 * 如果删除失败，不会重试。
	 * 
	 * @param filepath
	 * @return
	 */
	public static boolean deleteDependon(String filepath) {
		return FileUtils.deleteDependon(filepath, 0);
	}

	/**
	 * 如果doesExisted(file_)为true且是文件类型则删除文件，并返回true； 否则什么也不做并且返回false。
	 * 其中，删除文件的时候如果删除失败会尝试最多删除 maxRetryCount 次.
	 * 
	 * @param filepath
	 * @param maxRetryCount
	 * @return
	 */
	public static boolean deleteDependon(String filepath, int maxRetryCount) {
		if (TextUtils.isEmpty(filepath)) {
			return false;
		}
		return FileUtils.deleteDependon(new File(filepath), maxRetryCount);
	}

    /**
     * 依靠系统打开文件，类型依靠filepath的后缀名获取
     * 
     * @param context
     * @param filepath
     */
    public static void openFileByOS(Context context, String filepath) { 
        if (TextUtils.isEmpty(filepath) || !filepath.contains(".")) {
            return;
        }
        File file = new File(filepath);
        if (file == null || !file.exists()) {
            return;
        }
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        String type = getMIMEType(file);
        intent.setDataAndType(Uri.fromFile(file), type);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            // no activity found
        }
    }

    /**
     * 根据后缀名获取文件的MIME
     * 
     * @param file
     * @return
     */
    public static String getMIMEType(File file) {
        String type = "*/*";
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex < 0) {
            return type;
        }
        String end = fileName.substring(dotIndex, fileName.length());
        if (TextUtils.isEmpty(end) && end.length() < 2) {
            return type;
        }
        end = end.substring(1, end.length()).toLowerCase();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        type = mimeTypeMap.getMimeTypeFromExtension(end);
        return type;
    }
    
    /**
     * 获取本地apk文件的信息
     * 
     * @param context
     * @param file
     * @return
     */
    public static PackageInfo getPackageInfo(Context context, String file) {
        PackageManager pm = context.getPackageManager();
        PackageInfo info = pm.getPackageArchiveInfo(file, PackageManager.GET_ACTIVITIES);
        return info;
    }

		/**
		 * 计算文件夹大小(没有过滤)
		 * @param directory 文件夹
		 * @return the number of bytes in this folder
		 */
		public static long folderSize(File directory) {
			long length = 0;
            if (doesExisted(directory)) {
                File[] listFiles= directory.listFiles() ;
                if (listFiles != null) {
                    for (File file : listFiles){
                        if (file.isFile()) {
                            length += file.length();
                        } else {
                            length += folderSize(file);
                        }
                    }
                }
            }
			return length;
		}

    public static void deleteAllFiles(File file){
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				file.delete(); // delete()方法 你应该知道 是删除的意思;
			} else if (file.isDirectory() ) { // 否则如果它是一个目录
                File[] files = file.listFiles();
                if (files != null) {
                    for (File fileItem : files) { // 遍历目录下所有的文件
                        deleteAllFiles(fileItem); // 把每个文件 用这个方法进行迭代
                    }
                }
			}
			file.delete();
		}
	}

	public static boolean isDirectoryNotEmpty(String filePath) {
		if (!isDirectoryExist(filePath))
			return false;
		File directory = new File(filePath);
		if (directory.isDirectory() && directory.exists()) {
			File[] files = directory.listFiles();
			return files != null && files.length > 0;
		}
		return false;
	}
}
