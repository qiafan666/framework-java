package com.ning.web.jotato.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.InvalidMimeTypeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

public abstract class AbstractExceptionHandler {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public AbstractExceptionHandler() {
    }

    protected abstract Object buildResp(String code, Throwable e);

    protected abstract Object buildResp(String code, String msg);

    @ExceptionHandler({Throwable.class})
    public Object handleThrowable(Throwable e) {
        return this.buildResp("SYS500", e);
    }

    @ExceptionHandler({NoHandlerFoundException.class})
    @ResponseStatus(
            code = HttpStatus.NOT_FOUND
    )
    public Object handleNoHandlerFoundException(NoHandlerFoundException e) {
        return this.buildResp("SYS004", (Throwable)e);
    }

    @ExceptionHandler({NoSuchMethodException.class})
    public Object noSuchMethodException(NoSuchMethodException e) {
        return this.buildResp("SYS004", (Throwable)e);
    }

    @ExceptionHandler({InvalidMimeTypeException.class})
    public Object invalidMimeTypeException(InvalidMimeTypeException e) {
        return this.buildResp("SYS030", (Throwable)e);
    }

    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public Object handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        return this.buildResp("SYS007", (Throwable)e);
    }
/*

    @ExceptionHandler({JSONException.class})
    public Object handleJSONException(JSONException e) {
        return this.buildResp("SYS008", (Throwable)e);
    }

    @ExceptionHandler({SQLException.class})
    public Object handleSQLException(SQLException e) {
        return this.buildResp("SYS002", (Throwable)e);
    }

    @ExceptionHandler({PersistenceException.class})
    public Object handlePersistenceException(PersistenceException e) {
        return this.buildResp("SYS002", (Throwable)e);
    }

    @ExceptionHandler({DataAccessException.class})
    public Object handleDataAccessException(DataAccessException e) {
        return this.buildResp("SYS002", (Throwable)e);
    }

    @ExceptionHandler({DBException.class})
    public Object handleDBException(DBException e) {
        return this.buildResp("SYS002", (Throwable)e);
    }
*/

    @ExceptionHandler({RestException.class})
    public Object handleRestException(RestException e) {
        return this.buildResp(e.getCode(), e.getMsg());
    }
}
