package com.tander.artemis;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import java.io.FileOutputStream;

public class FileProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        // Получаем тело сообщения в виде массива байт
        byte[] messageBody = exchange.getIn().getBody(byte[].class);

        // Получаем имя файла из заголовка сообщения
        String fileName = exchange.getIn().getHeader("CamelFileName", String.class);

        // Создаем файл в папке с тем же именем
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(messageBody);
        }
    }
}