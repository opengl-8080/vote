package vote.infrastructure.jsf.exception;

import vote.domain.EntityNotFoundException;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;

public class EntityNotFoundExceptionHandler extends ExceptionHandlerWrapper {
    private ExceptionHandler wrapped;

    public EntityNotFoundExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterator<ExceptionQueuedEvent> iterator = this.getUnhandledExceptionQueuedEvents().iterator();

        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = iterator.next();

            ExceptionQueuedEventContext eventContext = event.getContext();
            Throwable exception = eventContext.getException();

            if (Util.hasRootCause(exception, EntityNotFoundException.class)) {
                FacesContext facesContext = eventContext.getContext();
                NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();

                try {
                    navigationHandler.handleNavigation(facesContext, null, "/common/not-found.xhtml");
                } finally {
                    iterator.remove();
                }
            }
        }

        this.wrapped.handle();
    }

    @Override
    public ExceptionHandler getWrapped() {
        return this.wrapped;
    }
}
