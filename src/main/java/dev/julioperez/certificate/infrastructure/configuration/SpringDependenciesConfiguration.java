package dev.julioperez.certificate.infrastructure.configuration;

import dev.julioperez.certificate.application.createQrValidator.adapter.CreateQrValidatorAdapterQrGenerator;
import dev.julioperez.certificate.application.createQrValidator.service.CreateQrValidatorService;
import dev.julioperez.certificate.application.createStudentCertificate.adapter.CreateStudentCertificateAdapterRepository;
import dev.julioperez.certificate.application.createStudentCertificate.delivery.CreateStudentCertificateDelivery;
import dev.julioperez.certificate.application.createStudentCertificate.service.CreateStudentCertificateService;
import dev.julioperez.certificate.application.generateCertificate.adapter.GenerateCertificateAdapterCertificateGenerator;
import dev.julioperez.certificate.application.generateCertificate.service.GenerateCertificateService;
import dev.julioperez.certificate.application.modelMapper.StudentCertificateModelMapper;
import dev.julioperez.certificate.infrastructure.gateway.iTextRenderPdf.ITextRenderPdf;
import dev.julioperez.certificate.infrastructure.gateway.zxingQR.ZxingQr;
import dev.julioperez.certificate.infrastructure.repository.dao.StudentCertificateDao;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
@EnableJpaRepositories(basePackages = "dev.julioperez.certificate.*")
@EntityScan(basePackages = "dev.julioperez.certificate.*")
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackages = {"dev.julioperez.certificate.*"})
public class SpringDependenciesConfiguration {
    private final StudentCertificateDao studentCertificateDao;
    private final ITextRenderPdf iTextRenderPdf;
    private final ZxingQr zxingQr;

    public SpringDependenciesConfiguration(StudentCertificateDao studentCertificateDao, ITextRenderPdf iTextRenderPdf, ZxingQr zxingQr) {
        this.studentCertificateDao = studentCertificateDao;
        this.iTextRenderPdf = iTextRenderPdf;
        this.zxingQr = zxingQr;
    }


//    @Bean
//    public ClassLoaderTemplateResolver emailTemplateResolver() {
//        ClassLoaderTemplateResolver pdfTemplateResolver = new ClassLoaderTemplateResolver();
//        pdfTemplateResolver.setPrefix("pdf-templates/");
//        pdfTemplateResolver.setSuffix(".html");
//        pdfTemplateResolver.setTemplateMode("HTML5");
//        pdfTemplateResolver.setCharacterEncoding("UTF-8");
//        pdfTemplateResolver.setOrder(1);
//        return pdfTemplateResolver;
//    }

    /**
     * ===========================================================
     * =======================application======================
     * ===========================================================
     */

    /**
     * modelMapper
     */

    @Bean
    StudentCertificateModelMapper studentCertificateModelMapper(){
        return new StudentCertificateModelMapper();
    }
    /**
     * createStudentCertificate
     */
    @Bean
    public CreateStudentCertificateAdapterRepository createStudentCertificateAdapterRepository(){
        return new CreateStudentCertificateAdapterRepository(
                studentCertificateDao,
                studentCertificateModelMapper());
    }

    @Bean
    public CreateStudentCertificateService createStudentCertificateService(){
        return new CreateStudentCertificateService(
                createStudentCertificateAdapterRepository(),
                generateCertificateService(),
                createQrValidatorService());
    }

    @Bean
    public CreateStudentCertificateDelivery createStudentCertificateDelivery(){
        return new CreateStudentCertificateDelivery(
                createStudentCertificateService(),
                studentCertificateModelMapper());
    }
    /**
     * generateCertificate
     */
    @Bean
    public GenerateCertificateAdapterCertificateGenerator generateCertificateAdapterPdfGenerator(){
        return new GenerateCertificateAdapterCertificateGenerator(
                iTextRenderPdf);
    }

    @Bean
    public GenerateCertificateService generateCertificateService(){
        return new GenerateCertificateService(
                generateCertificateAdapterPdfGenerator());
    }
    /**
     * createQrValidator
     */
    @Bean
    public CreateQrValidatorAdapterQrGenerator createQrValidatorAdapterQrGenerator(){
        return new CreateQrValidatorAdapterQrGenerator(
                zxingQr);
    }

    @Bean
    public CreateQrValidatorService createQrValidatorService(){
        return new CreateQrValidatorService(
                createQrValidatorAdapterQrGenerator());
    }

    /**
     * RabbitMQ Configuration


     @Value("${julioperezdev.queue.name}")
     public String QUEUE_NAME;

     @Value("${julioperezdev.exchange.name}")
     public String EXCHANGE_NAME;

     @Value("${julioperezdev.routing.key}")
     public String ROUTING_KEY;

     @Bean
     public Queue queue(){
     return new Queue(QUEUE_NAME, true);
     }

     @Bean
     public TopicExchange exchange(){
     return new TopicExchange(EXCHANGE_NAME);
     }

     @Bean
     public Binding binding(Queue queue, TopicExchange exchange){
     return BindingBuilder
     .bind(queue)
     .to(exchange)
     .with(ROUTING_KEY);
     }

     @Bean
     public MessageConverter messageConverter() {
     return new Jackson2JsonMessageConverter();
     }

     @Bean
     public AmqpTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
     RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
     rabbitTemplate.setMessageConverter(messageConverter());
     return rabbitTemplate;
     }
     */
}
