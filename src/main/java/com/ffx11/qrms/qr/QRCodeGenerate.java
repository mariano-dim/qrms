package com.ffx11.qrms.qr;

import java.io.IOException;
        import java.nio.file.FileSystems;
        import java.nio.file.Path;

        import com.google.zxing.BarcodeFormat;
        import com.google.zxing.WriterException;
        import com.google.zxing.client.j2se.MatrixToImageWriter;
        import com.google.zxing.common.BitMatrix;
        import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerate {
    private static final String QR_CODE_LOCATION = "./HelloQRCode.jpg";

    private static void generateQRCode(String text, int width, int height, String filePath)
            throws WriterException, IOException {

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(matrix, "JPG", path);
    }

    public static void main(String[] args) {
        try {
            generateQRCode("Hello!!!!", 350, 350, QR_CODE_LOCATION);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}