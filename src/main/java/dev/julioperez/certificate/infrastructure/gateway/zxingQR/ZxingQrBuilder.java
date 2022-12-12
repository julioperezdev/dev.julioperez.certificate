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
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@Service
@Slf4j
public class ZxingQrBuilder implements ZxingQr{
    private final String JULIO_PEREZ_CERTIFICATE_REDIRECTION = "https://julioperez.dev/certificate/";
    private final String QR_CHARSET = "UTF-8";
    private final String QR_FORMAT = "png";
    private final String QR_PATH_BASE = "src/main/resources/certificateBucket/qrValidator/";
    private final String QR_PATH_RETURN = "classpath:certificateBucket/qrValidator/";
    private final int QR_SIZE_IN_PIXELS = 200;

    @Override
    public String createQrToCertificate(UUID certificateId){
        try{
            String certificateRedirectionUrl = buildCertificateRedirectionUrl(certificateId);
            BitMatrix matrix = new MultiFormatWriter()
                    .encode(new String(certificateRedirectionUrl.getBytes(QR_CHARSET), QR_CHARSET),
                            BarcodeFormat.QR_CODE, QR_SIZE_IN_PIXELS, QR_SIZE_IN_PIXELS);
            String qrPath = buildPathToWriteQr(certificateId);
            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(-13421740,-5126657);
            MatrixToImageWriter.writeToPath(matrix, QR_FORMAT, Path.of(qrPath),matrixToImageConfig);
            return QR_PATH_RETURN.concat(certificateId.toString()).concat(".png");
        }catch (WriterException | IOException exception){
            throw new QrBuilderCantCompleteProcess(exception.getMessage());
        }
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
}
