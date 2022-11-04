package fun.pigeondog.common.core;

import fun.pigeondog.common.exception.PigeondogAbstractException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.NestedServletException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Default error controller.
 *
 * @author johnniang
 */
@Component
@Slf4j
public class DefaultErrorController extends BasicErrorController {

    public DefaultErrorController(
        ErrorAttributes errorAttributes,
        ServerProperties serverProperties,
        List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, serverProperties.getError(), errorViewResolvers);
    }

    @Override
    protected HttpStatus getStatus(HttpServletRequest request) {
        var status = super.getStatus(request);
        // deduce status
        var exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if (exception instanceof NestedServletException) {
            var nse = (NestedServletException) exception;
            if (nse.getCause() instanceof PigeondogAbstractException) {
                status = resolveHaloException((PigeondogAbstractException) nse.getCause(), request);
            }
        } else if (exception instanceof PigeondogAbstractException) {
            status = resolveHaloException((PigeondogAbstractException) exception, request);
        }
        return status;
    }

    private HttpStatus resolveHaloException(PigeondogAbstractException haloException,
                                            HttpServletRequest request) {
        HttpStatus status = haloException.getStatus();
        if (log.isDebugEnabled()) {
            log.error("Halo exception occurred.", haloException);
        }
        // reset status
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, status.value());
        request.setAttribute(RequestDispatcher.ERROR_EXCEPTION, haloException);
        request.setAttribute(RequestDispatcher.ERROR_MESSAGE, haloException.getMessage());
        request.setAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE, haloException.getClass());
        return status;
    }
}