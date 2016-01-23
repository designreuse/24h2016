
package com.bee.team.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Date;
import javax.el.ExpressionFactory;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.base.CaseFormat;

public class Utils {
	private static final Logger	logger	= LoggerFactory.getLogger(Utils.class);

	public static Object getValueExpression(FacesContext facesContext, String expr, Class<?> cla) {
		return facesContext.getApplication().evaluateExpressionGet(facesContext, expr, cla);
	}

	public static void setValueExpression(FacesContext facesContext, String expr, Class<?> cla, Object obj) {
		ExpressionFactory factory = facesContext.getApplication().getExpressionFactory();
		factory.createValueExpression(facesContext.getELContext(), expr, cla).setValue(facesContext.getELContext(), obj);
	}

	public static Object getFieldValue(Object obj, String field) {
		Class aClass = obj.getClass();
		Field f;
		try {
			f = aClass.getDeclaredField(field);
			f.setAccessible(true);
			return f.get(obj);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void setFieldValue(Object obj, String field, Object value) {
		Class aClass = obj.getClass();
		Field f;
		try {
			f = aClass.getDeclaredField(field);
			f.setAccessible(true);
			f.set(obj, value);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static String getFieldType(Object obj, String field) {
		Class aClass = obj.getClass();
		Field f;
		try {
			f = aClass.getDeclaredField(field);
			f.setAccessible(true);
			return f.getGenericType().getTypeName();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String underscoreNameToCamelCase(String s) {
		return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, s);
	}

	public static String now() {
		try {
			return DateFormatUtils.format(new Date(), "dd/MM/yyyy HH:mm:ss");
		} catch (Exception e) {
			logger.error(Utils.now() + " Date : " + new Date() + "  Format : " + "dd/MM/yyyy HH:mm");
			e.printStackTrace();
		}
		return "";
	}

	public static String formatDate(Date date, String format) {
		if (date == null) { return ""; }
		try {
			return DateFormatUtils.format(date, format);
		} catch (Exception e) {
			logger.error(Utils.now() + " Date : " + date + "  Format : " + format);
			e.printStackTrace();
		}
		return "";
	}

	public static String fileSize(String fileSize) {
		if (fileSize.equals("")) {
			return "";
		} else {
			return FileUtils.byteCountToDisplaySize(Long.valueOf(fileSize));
		}
	}
	

	public static void downloadFile(String sourceFile, FacesContext context, String contentType, String filename, boolean inline) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		File file = new File(sourceFile);

		try {
			inputStream = new FileInputStream(file.getAbsolutePath());

			// byte[]buffer = FileUtil.getBytes(inputStream);
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.setContentType(contentType);
			if (inline) {
				response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
			} else {
				response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
			}
			response.setContentLength((int) file.length());
			outputStream = response.getOutputStream();

			byte[] buffer = new byte[256];
			int numRead;
			while ((numRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, numRead);
			}

			buffer = null;
			inputStream.close();
			inputStream = null;// no close twice

			outputStream.flush();
			outputStream.close();
			outputStream = null;// no close twice
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		context.responseComplete();
	}

	public static void downloadFile(FacesContext context, String path, String name, String mimeType, boolean inline) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		File file = new File(path);

		try {

			byte[] buffer = new byte[256];

			inputStream = new FileInputStream(file.getAbsolutePath());

			// byte[]buffer = FileUtil.getBytes(inputStream);
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			response.setContentType(mimeType);
			if (inline) {
				response.setHeader("Content-Disposition", "inline; filename=\"" + name + "\"");
			} else {
				response.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
			}
			response.setContentLength((int) file.length());
			outputStream = response.getOutputStream();

			int numRead;
			while ((numRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, numRead);
			}

			inputStream.close();
			inputStream = null;// no close twice

			outputStream.flush();
			outputStream.close();
			outputStream = null;// no close twice

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		context.responseComplete();
	}

}
