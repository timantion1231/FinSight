package com.finsight.exceptions;

/**
 * Исключение, выбрасываемое при попытке регистрации с email, который уже существует в системе
 */
public class EmailAlreadyExistsException extends Exception {

    /**
     * Создает исключение с сообщением об ошибке
     * @param message сообщение об ошибке
     */
    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Создает исключение с сообщением об ошибке и причиной
     * @param message сообщение об ошибке
     * @param cause причина исключения
     */
    public EmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

