package com.laba.solvd.xml;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidatorSAX {
    private static final Logger LOGGER = LogManager.getLogger(ValidatorSAX.class);

    public static final String schemaName = "src/main/resources/flight.xsd";
    public static final String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    public static boolean isValid(String fileName) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
        try{
            Schema schema = schemaFactory.newSchema(new File(schemaName));
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            validator.validate(source);
            return true;
        } catch (SAXException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
