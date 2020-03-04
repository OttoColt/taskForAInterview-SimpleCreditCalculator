package org.example.ejb.payments;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.ejb.Stateless;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PaymentSchedule {
/*    public static final String AMOUNT = "amount";
    public static final String PERCENT = "percent";
    public static final String PERIOD = "period";
    public static final String TYPE_SCHEDULE = "type_schedule";*/

/*    public ByteArrayOutputStream getByteOutputStream(InputStream in) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, Boolean.TRUE);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;
        }

        DataForSchedule dto = getDTO(in, builder);
        List<Payment> schedule = getPayments(dto);

        Document docOut = getDocument(builder, schedule);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        transformerFactory.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");

        Transformer transformer;

        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            return null;
        }

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(docOut);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        StreamResult result = new StreamResult(bos);

        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        return bos;
    }*/

/*    private Document getDocument(DocumentBuilder builder, List<Payment> schedule) {
        Document docOut = builder.newDocument();
        Element rootElement = docOut.createElement("schedule");
        docOut.appendChild(rootElement);

        for (int i = 0; i < schedule.size(); i++) {

            Element payment = docOut.createElement("payment");
            rootElement.appendChild(payment);

            Element currentBalance = docOut.createElement("currentBalance");
            currentBalance.appendChild(docOut.createTextNode(schedule.get(i).getCurrentBalance() + ""));
            payment.appendChild(currentBalance);

            Element monthPay = docOut.createElement("monthPay");
            monthPay.appendChild(docOut.createTextNode(schedule.get(i).getPaymentInMonth() + ""));
            payment.appendChild(monthPay);

            Element percentPart = docOut.createElement("percentPart");
            percentPart.appendChild(docOut.createTextNode(schedule.get(i).getPercentPart() + ""));
            payment.appendChild(percentPart);
        }
        return docOut;
    }*/

/*    public DataForSchedule getDTO(InputStream in, DocumentBuilder builder) {
        DataForSchedule dto = new DataForSchedule();
        Document doc;
        try {
            doc = builder.parse(in);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
            return null;
        }
        Element root = doc.getDocumentElement();
        NodeList children = root.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child instanceof Element) {
                Element childElement = (Element) child;
                if (childElement.getTagName().equals(AMOUNT))
                    dto.setAmount(Double.parseDouble(childElement.getTextContent()));
                else if (childElement.getTagName().equals(PERCENT)) {
                    dto.setPercent(Integer.parseInt(childElement.getTextContent()));
                } else if (childElement.getTagName().equals(PERIOD))
                    dto.setPeriod(Integer.parseInt(childElement.getTextContent()));
                else if (childElement.getTagName().equals(TYPE_SCHEDULE))
                    dto.setTypeSchedule(childElement.getTextContent());
            }
        }
        return dto;
    }*/

    public List<Payment> getPayments(DataForSchedule dto) {

        List<Payment> schedule = new ArrayList<>();

        if (dto.getTypeSchedule().equals("ann")) {
            createAnnuitySchedule(schedule, dto);
        } else if (dto.getTypeSchedule().equals("diff")) {
            createDifferentiatedSchedule(schedule, dto);
        }
        return schedule;
    }

    public void createAnnuitySchedule(List<Payment> annuitySchedule, DataForSchedule dto) {

        BigDecimal monthPercentPay, paymentInMonth;
        BigDecimal balance = BigDecimal.valueOf(dto.getAmount());
        BigDecimal monthPercent = (BigDecimal.valueOf(dto.getPercent()).divide(BigDecimal.valueOf(12), 6, BigDecimal.ROUND_DOWN));

        monthPercentPay = monthPercent.divide(BigDecimal.valueOf(100), 6, BigDecimal.ROUND_DOWN);
        paymentInMonth = calcAnnPaymentInMonth(balance, monthPercentPay, dto.getPeriod());

        for (int i = 0; i < dto.getPeriod(); i++) {

            monthPercentPay = calcAnnMonthPercentPay(balance, monthPercent);
            balance = balance.subtract(paymentInMonth.subtract(monthPercentPay));

            if (i == dto.getPeriod() - 1) {
                monthPercentPay = monthPercentPay.subtract(balance);
                balance = BigDecimal.ZERO;
            }

            Payment payment = new Payment(Double.parseDouble("" + paymentInMonth), Double.parseDouble("" + monthPercentPay), Double.parseDouble("" + balance));
            annuitySchedule.add(payment);
        }
    }

    public void createDifferentiatedSchedule(List<Payment> differentiatedSchedule, DataForSchedule dto) {

        BigDecimal monthPercentPart, monthPercentPay, paymentInMonth;
        BigDecimal balance = BigDecimal.valueOf(dto.getAmount());
        BigDecimal paymentInMonthWithoutPercent = calcDiffPaymentInMonth(balance, dto.getPeriod());
        BigDecimal monthPercent = (BigDecimal.valueOf(dto.getPercent()).divide(BigDecimal.valueOf(12), 6, BigDecimal.ROUND_DOWN));

        monthPercentPart = monthPercent.divide(BigDecimal.valueOf(100), 6, BigDecimal.ROUND_DOWN);

        for (int i = 0; i < dto.getPeriod(); i++) {
            monthPercentPay = calcDiffMonthPercentPay(balance, monthPercentPart);

            balance = balance.subtract(paymentInMonthWithoutPercent);
            paymentInMonth = paymentInMonthWithoutPercent.add(monthPercentPay);
            if (i == dto.getPeriod() - 1) {
                paymentInMonth = paymentInMonth.add(balance);
                balance = BigDecimal.ZERO;
            }

            Payment payment = new Payment(Double.parseDouble("" + paymentInMonth), Double.parseDouble("" + monthPercentPay), Double.parseDouble("" + balance));
            differentiatedSchedule.add(payment);
        }

    }

    private BigDecimal calcAnnPaymentInMonth(BigDecimal amount, BigDecimal monthPercentPay, int period) {
        BigDecimal calcPaymentInMonth;

        //amount*(monthPercentPay+monthPercentPay/((monthPercentPay+1)^period-1))
        calcPaymentInMonth = amount.multiply(monthPercentPay.add
                ((monthPercentPay.divide((((monthPercentPay.add(BigDecimal.ONE)).pow(period)).subtract(BigDecimal.ONE)),
                        6,
                        BigDecimal.ROUND_DOWN))));

        return calcPaymentInMonth.setScale(2, BigDecimal.ROUND_DOWN);
    }

    private BigDecimal calcAnnMonthPercentPay(BigDecimal balance, BigDecimal monthPercent) {
        BigDecimal calcMonthPercentPay;

        calcMonthPercentPay = balance.multiply(monthPercent.divide(BigDecimal.valueOf(100), 6, BigDecimal.ROUND_DOWN));

        return calcMonthPercentPay.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal calcDiffPaymentInMonth(BigDecimal amount, int period) {
        BigDecimal calcPaymentInMonth;

        calcPaymentInMonth = amount.divide(BigDecimal.valueOf(period), 6, BigDecimal.ROUND_DOWN);

        return calcPaymentInMonth.setScale(2, BigDecimal.ROUND_DOWN);
    }

    private BigDecimal calcDiffMonthPercentPay(BigDecimal balance, BigDecimal monthPercentPay) {
        BigDecimal calcMonthPercentPay;

        calcMonthPercentPay = balance.multiply(monthPercentPay);

        return calcMonthPercentPay.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
