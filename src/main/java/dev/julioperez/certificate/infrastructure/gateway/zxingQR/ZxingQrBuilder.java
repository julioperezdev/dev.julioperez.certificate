package dev.julioperez.certificate.infrastructure.gateway.zxingQR;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import dev.julioperez.certificate.domain.exception.QrBuilderCantCompleteProcess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.UUID;

@Service
@Slf4j
public class ZxingQrBuilder implements ZxingQr{
    private final String JULIO_PEREZ_CERTIFICATE_REDIRECTION = "https://julioperez.dev/certificate/";
    private final String QR_CHARSET = "UTF-8";
    private final String QR_FORMAT = "png";
    //private final String QR_PATH_BASE = "src/main/resources/certificateBucket/qrValidator/";
    private final String QR_PATH_BASE = "./src/main/resources/certificateBucket/qrValidator/";
    private final int QR_SIZE_IN_PIXELS = 200;

    @Override
    public String createQrToCertificateReturningBase64(UUID certificateId){
        try{
            String certificateRedirectionUrl = buildCertificateRedirectionUrl(certificateId);
            BitMatrix matrix = buildQrWithBitMatrix(certificateRedirectionUrl);
            String qrPathString = buildPathToWriteQr(certificateId);
            Path qrPath = Path.of(qrPathString);
            MatrixToImageConfig matrixToImageConfig = configureQrByMatrix();
            writeQrByMatrix(matrix, qrPath, matrixToImageConfig);
            byte[] byteArrayQrByPath = getByteArrayQrByPath(qrPath);
            return convertByteArrayQrToBase64(byteArrayQrByPath);
        }catch (WriterException | IOException exception){
            throw new QrBuilderCantCompleteProcess(exception.getMessage());
        }
    }

    private BitMatrix buildQrWithBitMatrix(String certificateRedirectionUrl) throws UnsupportedEncodingException, WriterException {
        return new MultiFormatWriter()
                .encode(new String(certificateRedirectionUrl.getBytes(QR_CHARSET), QR_CHARSET),
                        BarcodeFormat.QR_CODE, QR_SIZE_IN_PIXELS, QR_SIZE_IN_PIXELS);
    }

    private MatrixToImageConfig configureQrByMatrix(){
        return new MatrixToImageConfig(-13421740,-5126657);
    }

    private void writeQrByMatrix(BitMatrix matrix, Path qrPath, MatrixToImageConfig matrixToImageConfig) throws IOException {
        MatrixToImageWriter.writeToPath(matrix, QR_FORMAT, qrPath,matrixToImageConfig);
    }

    private String buildCertificateRedirectionUrl(UUID certificateId){
        return JULIO_PEREZ_CERTIFICATE_REDIRECTION
                .concat(certificateId.toString());
    }
    private String buildPathToWriteQr(UUID certificateId){
        return QR_PATH_BASE
                .concat(certificateId.toString())
                .concat(".")
                .concat(QR_FORMAT);
    }

    private byte[] getByteArrayQrByPath(Path qrPath) throws IOException {
        return Files.readAllBytes(qrPath);
    }
    private String convertByteArrayQrToBase64(byte[] byteArrayQr){
        return Base64.getEncoder().encodeToString(byteArrayQr);
    }
}
