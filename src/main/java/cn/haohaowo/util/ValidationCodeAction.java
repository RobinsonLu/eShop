package cn.haohaowo.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.sun.image.codec.jpeg.ImageFormatException;

public class ValidationCodeAction implements Action, SessionAware {
	
	private Map session;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 20;
	private static final int CODE_AMOUNT = 4;
	
	private static final char[] randomSequence = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G',
		'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
		'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	private byte[] imageBytes;
	
	private Color getRangeColor(int fc, int bc) {
		Random random = new Random();
		if(fc > 255) {
			fc = 200;
		}
		if(bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		
		return new Color(r, g, b);
	}
	
	private void generateValidationCodeImage() throws ImageFormatException, IOException {
		int fontHeight;
		int rectWidth, rectHeight;
		int offsetWidth;
		int addition;
		int codeHeight;
		
		fontHeight = HEIGHT - 2;
		rectWidth = WIDTH - 1;
		rectHeight = HEIGHT - 1;
		offsetWidth = WIDTH / (CODE_AMOUNT + 1);
		addition = offsetWidth / 2;
		codeHeight = HEIGHT - 4;
		
		BufferedImage buffImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
		Graphics2D g = buffImg.createGraphics();
		Random random = new Random();
		g.setColor(getRangeColor(200, 250));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		Font font = new Font("Times New Roman", Font.PLAIN, fontHeight);
		g.setFont(font);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, rectWidth, rectHeight);
		g.setColor(getRangeColor(160, 200));
		for(int i = 0; i < 160; i++) {
			int x = random.nextInt(WIDTH);
			int y = random.nextInt(HEIGHT);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y, x1, y1);
		}
		StringBuffer sb = new StringBuffer();
		
		int red = 0, green = 0, blue = 0;
		for(int i = 0; i < CODE_AMOUNT; i++) {
			int index = random.nextInt(35);
			String strRand = String.valueOf(randomSequence[index]);
			sb.append(strRand);
			red = random.nextInt(110);
			green = random.nextInt(50);
			blue = random.nextInt(50);
			g.setColor(new Color(20 + red, 20 + green, 20 + blue));
			g.drawString(strRand, offsetWidth * i + addition, codeHeight);
		}
		
		session.put("validationCode", sb.toString());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(buffImg, "jpeg", baos);
		imageBytes = baos.toByteArray();
		baos.close();
	}

	public String execute() throws Exception {
		generateValidationCodeImage();
		return SUCCESS;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public String getContentType() {
		return "image/jpeg";
	}
	
	public int getContentLength() {
		return imageBytes.length;
	}
	
	public byte[] getImageInBytes() {
		return imageBytes;
	}

}
