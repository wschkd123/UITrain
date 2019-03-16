package com.wsc.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Rect;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BitmapUtils {
    public static Bitmap createScaledBitmap(Bitmap src_, int width, int height,
            Bitmap.Config config) {
        if ((width == src_.getWidth()) && (height == src_.getHeight())) {
            return src_.copy(config, true);
        }
        return Bitmap.createScaledBitmap(src_, width, height, true);
    }

	public static Bitmap createZoomOutBitmap(InputStream input_, int zoomOutRate) {
		 
		Bitmap datas = null;
		Options opts = new Options();
		input_ = FileUtils.makeInputBuffered(input_);
		if (zoomOutRate > 1) {
			opts.inSampleSize = zoomOutRate;
		}
		datas = BitmapFactory.decodeStream(input_, null, opts);
		FileUtils.closeStream(input_);
		return datas;
	}

	public static Bitmap createZoomOutBitmap(File inputFile, int zoomOutRate) {
		return createZoomOutBitmap(
				new BufferedInputStream(FileUtils.getFileInputStream(inputFile)),
				zoomOutRate);
	}

	public static Bitmap createZoomOutBitmap(String inputPath, int zoomOutRate) {
		return createZoomOutBitmap(new File(inputPath), zoomOutRate);
	}

	public static boolean getZoomOutBitmapBound(InputStream input_,
			int zoomOutRate, Rect outRect_) {
		
		Options opts = new Options();
		input_ = FileUtils.makeInputBuffered(input_);
		opts.inJustDecodeBounds = true;
		if (zoomOutRate > 1) {
			opts.inSampleSize = zoomOutRate;
		}
		BitmapFactory.decodeStream(input_, null, opts);
		FileUtils.closeStream(input_);
		if ((opts.outWidth > 0) && (opts.outHeight > 0)) {
			outRect_.set(0, 0, opts.outWidth, opts.outHeight);
			return true;
		}
		return false;
	}

	public static boolean getZoomOutBitmapBound(byte[] data_, int offset,
			int length, int zoomOutRate, Rect outRect_) {
		
		Options opts = new Options();
		opts.inJustDecodeBounds = true;
		if (zoomOutRate > 1) {
			opts.inSampleSize = zoomOutRate;
		}
		BitmapFactory.decodeByteArray(data_, offset, length, opts);
		if ((opts.outWidth > 0) && (opts.outHeight > 0)) {
			outRect_.set(0, 0, opts.outWidth, opts.outHeight);
			return true;
		}
		return false;
	}

	public static boolean getZoomOutBitmapBound(File bmpfile, int zoomOutRate,
			Rect outRect_) {
        if (!FileUtils.doesExisted(bmpfile)) {
            return false;
        }
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(bmpfile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        InputStream input = FileUtils.makeInputBuffered(fileInputStream);
        return getZoomOutBitmapBound(input, zoomOutRate, outRect_);
    }

	public static boolean getZoomOutBitmapBound(String path, int zoomOutRate,
			Rect outRect_) {
		return getZoomOutBitmapBound(new File(path), zoomOutRate, outRect_);
	}

	public static Bitmap createRightRotatedBitmap(Bitmap srcbmp,
			Bitmap.Config config) {

		Matrix m = new Matrix();
		m.postRotate(90.0F);
		Bitmap newBmp = Bitmap.createBitmap(srcbmp, 0, 0, srcbmp.getWidth(),
				srcbmp.getHeight(), m, true);
		return newBmp;
	}

	public static Bitmap createLeftRotatedBitmap(Bitmap srcbmp,
			Bitmap.Config config) {

		Matrix m = new Matrix();
		m.postRotate(-90.0F);
		Bitmap newBmp = Bitmap.createBitmap(srcbmp, 0, 0, srcbmp.getWidth(),
				srcbmp.getHeight(), m, true);
		return newBmp;
	}

	public static Bitmap create180RotatedBitmap(Bitmap srcbmp,
			Bitmap.Config config) {
		Matrix m = new Matrix();
		m.postRotate(180.0F);
		Bitmap newBmp = Bitmap.createBitmap(srcbmp, 0, 0, srcbmp.getWidth(),
				srcbmp.getHeight(), m, true);
		return newBmp;
	}

	@Deprecated
	public static void recycleBitmap(Bitmap bmp) {
		if ((bmp != null) && (!bmp.isRecycled()))
			bmp.recycle();		
	}

    /**
     * 图片输入成Bitmap时的压缩选项
     * 
     * @param portraitFile
     * @return
     * @throws IOException
     */
    public static Options getPicOptions(File portraitFile) throws IOException {
    	if (!FileUtils.doesExisted(portraitFile)) {
    		throw new FileNotFoundException(portraitFile == null ? "null"
    				: portraitFile.getAbsolutePath());
    	}
    	FileInputStream input = new FileInputStream(portraitFile);
    	final Options opts = new Options();
    	opts.inJustDecodeBounds = true;
    	BitmapFactory.decodeStream(input, null, opts);
    	FileUtils.closeStream(input);
    	return opts;
    }
    
	/**
	 * 图片高斯模糊 ，效率差， 一般处理压缩后的图片
	 */
	public static Bitmap fastblur(Bitmap sentBitmap, int radius) {

		Bitmap bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);

		if (radius < 1) {
			return (null);
		}

		int w = bitmap.getWidth();
		int h = bitmap.getHeight();

		int[] pix = new int[w * h];
		bitmap.getPixels(pix, 0, w, 0, 0, w, h);

		int wm = w - 1;
		int hm = h - 1;
		int wh = w * h;
		int div = radius + radius + 1;

		int r[] = new int[wh];
		int g[] = new int[wh];
		int b[] = new int[wh];
		int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
		int vmin[] = new int[Math.max(w, h)];

		int divsum = (div + 1) >> 1;
		divsum *= divsum;
		int temp = 256 * divsum;
		int dv[] = new int[temp];
		for (i = 0; i < temp; i++) {
			dv[i] = (i / divsum);
		}

		yw = yi = 0;

		int[][] stack = new int[div][3];
		int stackpointer;
		int stackstart;
		int[] sir;
		int rbs;
		int r1 = radius + 1;
		int routsum, goutsum, boutsum;
		int rinsum, ginsum, binsum;

		for (y = 0; y < h; y++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			for (i = -radius; i <= radius; i++) {
				p = pix[yi + Math.min(wm, Math.max(i, 0))];
				sir = stack[i + radius];
				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);
				rbs = r1 - Math.abs(i);
				rsum += sir[0] * rbs;
				gsum += sir[1] * rbs;
				bsum += sir[2] * rbs;
				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}
			}
			stackpointer = radius;

			for (x = 0; x < w; x++) {

				r[yi] = dv[rsum];
				g[yi] = dv[gsum];
				b[yi] = dv[bsum];

				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;

				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];

				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];

				if (y == 0) {
					vmin[x] = Math.min(x + radius + 1, wm);
				}
				p = pix[yw + vmin[x]];

				sir[0] = (p & 0xff0000) >> 16;
				sir[1] = (p & 0x00ff00) >> 8;
				sir[2] = (p & 0x0000ff);

				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];

				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;

				stackpointer = (stackpointer + 1) % div;
				sir = stack[(stackpointer) % div];

				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];

				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];

				yi++;
			}
			yw += w;
		}
		for (x = 0; x < w; x++) {
			rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
			yp = -radius * w;
			for (i = -radius; i <= radius; i++) {
				yi = Math.max(0, yp) + x;

				sir = stack[i + radius];

				sir[0] = r[yi];
				sir[1] = g[yi];
				sir[2] = b[yi];

				rbs = r1 - Math.abs(i);

				rsum += r[yi] * rbs;
				gsum += g[yi] * rbs;
				bsum += b[yi] * rbs;

				if (i > 0) {
					rinsum += sir[0];
					ginsum += sir[1];
					binsum += sir[2];
				} else {
					routsum += sir[0];
					goutsum += sir[1];
					boutsum += sir[2];
				}

				if (i < hm) {
					yp += w;
				}
			}
			yi = x;
			stackpointer = radius;
			for (y = 0; y < h; y++) {
				pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16)
						| (dv[gsum] << 8) | dv[bsum];

				rsum -= routsum;
				gsum -= goutsum;
				bsum -= boutsum;

				stackstart = stackpointer - radius + div;
				sir = stack[stackstart % div];

				routsum -= sir[0];
				goutsum -= sir[1];
				boutsum -= sir[2];

				if (x == 0) {
					vmin[y] = Math.min(y + r1, hm) * w;
				}
				p = x + vmin[y];

				sir[0] = r[p];
				sir[1] = g[p];
				sir[2] = b[p];

				rinsum += sir[0];
				ginsum += sir[1];
				binsum += sir[2];

				rsum += rinsum;
				gsum += ginsum;
				bsum += binsum;

				stackpointer = (stackpointer + 1) % div;
				sir = stack[stackpointer];

				routsum += sir[0];
				goutsum += sir[1];
				boutsum += sir[2];

				rinsum -= sir[0];
				ginsum -= sir[1];
				binsum -= sir[2];

				yi += w;
			}
		}
		bitmap.setPixels(pix, 0, w, 0, 0, w, h);
		return (bitmap);
	}
	
	/**
	 * 横向适应view大小，纵向按比例缩放并居中crop
	 *
	 * @param bwidth bitmap width
	 * @param bheight bitmap height
	 * @param vwidth view width
	 * @param vheight view height
	 * @param matrix cached matrix avoid create object in hot path
	 * @return
	 */
	public static Matrix getFixXCropYMatrix(int bwidth, int bheight, int vwidth, int vheight, Matrix matrix){
    	float scale;
        float dx = 0, dy = 0;
        scale = (float) vwidth / (float) bwidth;
        dy = (vheight - bheight * scale) * 0.5f;
        matrix.reset();
        matrix.setScale(scale, scale);
        matrix.postTranslate(Math.round(dx), Math.round(dy));
        return matrix;
    }
}