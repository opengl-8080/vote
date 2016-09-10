package vote.infrastructure.jsf.exception;

import org.eclipse.persistence.exceptions.OptimisticLockException;
import vote.domain.studymeeting.IllegalParticipateStudyMeetingException;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import java.util.Iterator;

public class OptimisticLockExceptionHandler extends ExceptionHandlerWrapper {
    private ExceptionHandler wrapped;

    public OptimisticLockExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void handle() throws FacesException {
        Iterator<ExceptionQueuedEvent> iterator = this.getUnhandledExceptionQueuedEvents().iterator();

        while (iterator.hasNext()) {
            ExceptionQueuedEvent event = iterator.next();
            ExceptionQueuedEventContext eventContext = event.getContext();
            Throwable exception = eventContext.getException();

            if (Util.hasRootCause(exception, OptimisticLockException.class)
                    || Util.hasRootCause(exception, IllegalParticipateStudyMeetingException.class)) {
                FacesContext facesContext = eventContext.getContext();
                NavigationHandler nav = facesContext.getApplication().getNavigationHandler();

                try {
                    nav.handleNavigation(facesContext, null, "/common/optimistic-exception.xhtml");
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
